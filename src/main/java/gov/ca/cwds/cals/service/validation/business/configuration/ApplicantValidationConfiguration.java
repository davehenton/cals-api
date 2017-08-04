package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantValidationConfiguration implements
    DroolsFieldValidationConfiguration<ApplicantDTO> {

  public static final ApplicantValidationConfiguration INSTANCE =
      new ApplicantValidationConfiguration();

  private ApplicantValidationConfiguration() {
  }

  @Override
  public String getAgendaGroup() {
    return BusinessRulesAgendaGroups.APPLICANT_VALIDATION;
  }

}
