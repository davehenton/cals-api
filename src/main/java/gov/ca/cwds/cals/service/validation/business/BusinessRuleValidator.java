package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.business.configuration.BusinessValidationConfiguration;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import org.hibernate.Session;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author CWDS CALS API Team
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class BusinessRuleValidator
    implements ConstraintValidator<BusinessValidationConstraint, Object[]>,
    CalsSessionFactoryAware {

  private BusinessValidationConfiguration businessValidationConfiguration;

  public void initialize(BusinessValidationConstraint constraint) {
    this.businessValidationConfiguration = constraint.businessValidationConfiguration()
        .getConfiguration();
  }

  public boolean isValid(Object[] parameters, ConstraintValidatorContext context) {
    BusinessValidationParameterObject parameterObject =
        businessValidationConfiguration.getBusinessValidationParameterObject(parameters);
    if (parameterObject.getEntityDTO() == null) {
      return true;
    }
    try (Session ignored = openSession()) {
      RFA1aForm form = getFormDao().find(parameterObject.getFormId());

      RFA1aForm modifiedForm =
          businessValidationConfiguration.buildModifiedForm(form, parameterObject.getEntityDTO());

      Set<String> validationMessages =
          validateForm(modifiedForm, businessValidationConfiguration.getAgendaGroup());
      if (validationMessages.isEmpty()) {
        return true;
      } else {
        context.disableDefaultConstraintViolation();
        validationMessages.forEach(
            (message ->
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation()));
        return false;
      }
    }
  }

  private Set<String> validateForm(RFA1aForm modifiedForm, String agendaGroup) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    KieSession kSession = null;
    try {
      kSession = kc.newKieSession("inProgressValidationSession");
      kSession.insert(modifiedForm);
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

  private RFA1aFormsDao getFormDao() {
    return InjectorHolder.INSTANCE.getInstance(RFA1aFormsDao.class);
  }

}