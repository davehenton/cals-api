package gov.ca.cwds.cals.service.validation.business;

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
    Set<String> validationMessages = droolsService.validate(validatedFact, configuration);
    if (validationMessages.isEmpty()) {
      return true;
    } else {
      context.disableDefaultConstraintViolation();
      validationMessages.forEach(
          (message ->
              context.buildConstraintViolationWithTemplate(message)
                  .addPropertyNode("<" + configuration.getAgendaGroup() + ">")
                  .addConstraintViolation()
          ));
      return false;
    }

  }

  protected abstract DroolsValidationConfiguration<T> getConfiguration();

}
