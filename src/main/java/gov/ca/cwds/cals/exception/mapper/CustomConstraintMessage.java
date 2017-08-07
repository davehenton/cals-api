package gov.ca.cwds.cals.exception.mapper;

import static javax.validation.ElementKind.METHOD;
import static javax.validation.ElementKind.PARAMETER;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import io.dropwizard.jersey.validation.JerseyParameterNameProvider;
import io.dropwizard.validation.ValidationMethod;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.model.Parameter;

/**
 * This is modified copy of io.dropwizard.jersey.validation.ConstraintMessage
 * The only change is removing of caching messages
 */
public class CustomConstraintMessage {

  private CustomConstraintMessage() {
  }

  /**
   * Gets the human friendly location of where the violation was raised.
   */
  public static String getMessage(ConstraintViolation<?> v, Invocable invocable) {
    return calculateMessage(v, invocable);
  }

  private static String calculateMessage(ConstraintViolation<?> v, Invocable invocable) {
    final Optional<String> returnValueName = getMethodReturnValueName(v);
    if (returnValueName.isPresent()) {
      final String name = isValidationMethod(v) ?
          StringUtils.substringBeforeLast(returnValueName.get(), ".") : returnValueName.get();
      return name + " " + v.getMessage();
    }

    // Take the message specified in a ValidationMethod annotation if it
    // is what caused the violation
    if (isValidationMethod(v)) {
      return v.getMessage();
    }

    final Optional<String> entity = isRequestEntity(v, invocable);
    if (entity.isPresent()) {
      // A present entity means that the request body failed validation but
      // if the request entity is simple (eg. byte[], String, etc), the entity
      // string will be empty, so prepend a message about the request body
      final String prefix = Strings.isNullOrEmpty(entity.get()) ? "The request body" : entity.get();
      return prefix + " " + v.getMessage();
    }

    // Check if the violation occurred on a *Param annotation and if so,
    // return a human friendly error (eg. "Query param xxx may not be null")
    final Optional<String> memberName = getMemberName(v, invocable);
    if (memberName.isPresent()) {
      return memberName.get() + " " + v.getMessage();
    }

    return v.getPropertyPath() + " " + v.getMessage();
  }

  /**
   * Determines if constraint violation occurred in the request entity. If it did, return a client
   * friendly string representation of where the error occurred (eg. "patient.name")
   */
  public static Optional<String> isRequestEntity(ConstraintViolation<?> violation,
      Invocable invocable) {
    final Path.Node parent = Iterables.get(violation.getPropertyPath(), 1, null);
    if (parent == null) {
      return Optional.empty();
    }
    final List<Parameter> parameters = invocable.getParameters();

    if (parent.getKind() == PARAMETER) {
      final Parameter param = parameters
          .get(parent.as(Path.ParameterNode.class).getParameterIndex());
      if (param.getSource() == Parameter.Source.UNKNOWN) {
        return Optional.of(Joiner.on('.').join(Iterables.skip(violation.getPropertyPath(), 2)));
      }
    }

    return Optional.empty();
  }

  /**
   * Gets a method parameter (or a parameter field) name, if the violation raised in it.
   */
  private static Optional<String> getMemberName(ConstraintViolation<?> violation,
      Invocable invocable) {
    final int size = Iterables.size(violation.getPropertyPath());
    if (size < 2) {
      return Optional.empty();
    }

    final Path.Node parent = Iterables.get(violation.getPropertyPath(), size - 2);
    final Path.Node member = Iterables.getLast(violation.getPropertyPath());

    if (parent.getKind() == PARAMETER) {
      // Constraint violation most likely failed with a BeanParam
      final List<Parameter> parameters = invocable.getParameters();
      final Parameter param = parameters
          .get(parent.as(Path.ParameterNode.class).getParameterIndex());

      // Extract the failing *Param annotation inside the Bean Param
      if (param.getSource() == Parameter.Source.BEAN_PARAM) {
        final Field field = FieldUtils.getField(param.getRawType(), member.getName(), true);
        return JerseyParameterNameProvider
            .getParameterNameFromAnnotations(field.getDeclaredAnnotations());
      }
    }
    if (parent.getKind() == METHOD) {
      return Optional.of(member.getName());
    }
    return Optional.empty();
  }

  /**
   * Gets the method return value name, if the violation is raised in it
   */
  private static Optional<String> getMethodReturnValueName(ConstraintViolation<?> violation) {
    int returnValueNames = -1;

    final StringBuilder result = new StringBuilder("server response");
    for (Path.Node node : violation.getPropertyPath()) {
      if (node.getKind() == ElementKind.RETURN_VALUE) {
        returnValueNames = 0;
      } else if (returnValueNames >= 0) {
        result.append(returnValueNames++ == 0 ? " " : ".").append(node);
      }
    }

    return returnValueNames >= 0 ? Optional.of(result.toString()) : Optional.empty();
  }

  private static boolean isValidationMethod(ConstraintViolation<?> v) {
    return v.getConstraintDescriptor().getAnnotation() instanceof ValidationMethod;
  }

  /**
   * Given a set of constraint violations and a Jersey {@link Invocable} where the constraint
   * occurred, determine the  HTTP Status code for the response. A return value violation is an
   * internal server error, an invalid request body is unprocessable entity, and any params that
   * are invalid means a bad request
   */
  public static <T extends ConstraintViolation<?>> int determineStatus(Set<T> violations,
      Invocable invocable) {
    if (violations.isEmpty()) {
      final ConstraintViolation<?> violation = violations.iterator().next();
      for (Path.Node node : violation.getPropertyPath()) {
        switch (node.getKind()) {
          case RETURN_VALUE:
            return 500;
          case PARAMETER:
            // Now determine if the parameter is the request entity
            final int index = node.as(Path.ParameterNode.class).getParameterIndex();
            final Parameter parameter = invocable.getParameters().get(index);
            return parameter.getSource() == Parameter.Source.UNKNOWN ? 422 : 400;
          default:
            continue;
        }
      }
    }

    // This shouldn't hit, but if it does, we'll return a unprocessable entity
    return 422;
  }
}
