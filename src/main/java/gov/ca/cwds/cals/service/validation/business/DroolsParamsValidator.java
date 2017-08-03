package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistry;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @author CWDS CALS API Team
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class DroolsParamsValidator extends
    DroolsValidator<BusinessValidationParamsConstraint, Object[]> implements
    CalsSessionFactoryAware {

  private DroolsValidationConfiguration<Object[]> configuration;

  public void initialize(BusinessValidationParamsConstraint constraint) {
    this.configuration = ValidationConfigurationRegistry.INSTANCE.get(constraint.configuration());
  }

  @Override
  protected String getAgendaGroup() {
    return configuration.getAgendaGroup();
  }

  @Override
  protected DroolsValidationConfiguration<Object[]> getConfiguration() {
    return configuration;
  }

}
