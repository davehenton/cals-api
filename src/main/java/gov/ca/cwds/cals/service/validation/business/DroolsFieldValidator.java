package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @author CWDS CALS API Team
 */
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class DroolsFieldValidator extends
    DroolsValidator<DroolsFieldValidationConstraint, BaseDTO> {

  private DroolsValidationConfiguration validationConfiguration;
  private String validationSessionName;

  public void initialize(DroolsFieldValidationConstraint constraint) {
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
  protected Object getValidatedFact(BaseDTO input) {
    return validationConfiguration.getValidatedFact(input);
  }

}
