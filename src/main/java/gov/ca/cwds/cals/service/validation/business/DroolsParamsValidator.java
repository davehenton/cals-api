package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @author CWDS CALS API Team
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class DroolsParamsValidator extends
    DroolsValidator<DroolsParamsValidationConstraint, Object[]> implements
    CalsSessionFactoryAware {

  private DroolsValidationConfiguration validationConfiguration;
  private String validationSessionName;

  public void initialize(DroolsParamsValidationConstraint constraint) {
    this.validationConfiguration = constraint.validationConfiguration()
        .getConfiguration();
    this.validationSessionName = constraint.session();
  }

  @Override
  protected String getValidationSessionName() {
    return validationSessionName;
  }

  @Override
  protected String getAgendaGroup() {
    return validationConfiguration.getAgendaGroup();
  }

  @Override
  protected Object getValidatedFact(Object[] parameters) {
    return validationConfiguration.getValidatedFact(parameters);
  }

}
