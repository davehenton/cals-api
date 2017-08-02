package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistry;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @author CWDS CALS API Team
 */
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class DroolsFieldValidator extends
    DroolsValidator<BusinessValidationFieldConstraint, BaseDTO> {

  private DroolsValidationConfiguration<BaseDTO> configuration;

  public void initialize(BusinessValidationFieldConstraint constraint) {
    configuration = ValidationConfigurationRegistry.INSTANCE
        .get(constraint.configuration());
  }

  @Override
  protected String getAgendaGroup() {
    return configuration.getAgendaGroup();
  }

  @Override
  protected DroolsValidationConfiguration<BaseDTO> getConfiguration() {
    return configuration;
  }

}
