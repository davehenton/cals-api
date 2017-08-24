package gov.ca.cwds.cals.service.validation.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.exception.ValidationDetails;
import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import java.lang.annotation.Annotation;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author CWDS CALS API Team
 */
public abstract class DroolsValidator<A extends Annotation, T> implements
    ConstraintValidator<A, T> {

  @Override
  public boolean isValid(T obj, ConstraintValidatorContext context) {
    if (obj == null) {
      return true;
    }

    DroolsValidationConfiguration<T> configuration = getConfiguration();
    Object validatedFact = configuration.getValidatedFact(obj);

    DroolsService droolsService = InjectorHolder.INSTANCE.getInstance(DroolsService.class);
    Set<ValidationDetails> detailsList = droolsService.validate(validatedFact, configuration);
    if (detailsList.isEmpty()) {
      return true;
    } else {
      context.disableDefaultConstraintViolation();
      detailsList.forEach(
          (details ->
              context.buildConstraintViolationWithTemplate(marshallData(details))
                  .addPropertyNode("")
                  .addConstraintViolation()
          ));
      return false;
    }

  }

  public String marshallData(ValidationDetails details) {
    try {
      return Utils.Json.to(details);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(
          "Cannot marshall validation details for message: " + details.getUserMessage(), e);
    }
  }

  protected abstract DroolsValidationConfiguration<T> getConfiguration();

}
