package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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

    Set<String> validationMessages = validate(validatedFact, getAgendaGroup());
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

  protected abstract String getAgendaGroup();

  protected abstract DroolsValidationConfiguration<T> getConfiguration();


  private Set<String> validate(Object obj, String agendaGroup) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    KieSession kSession = null;
    try {
      kSession = kc.newKieSession(getConfiguration().getDroolsSessionName());
      kSession.insert(obj);
      Set<String> validationMessages = new HashSet<>();
      kSession.setGlobal("validationMessages", validationMessages);
      kSession.getAgenda().getAgendaGroup(agendaGroup).setFocus();
      kSession.fireAllRules();
      return validationMessages;
    } finally {
      if (kSession != null) {
        kSession.dispose();
      }
    }
  }

}
