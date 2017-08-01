package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantPreferredNumberValidationConfiguration implements
    DroolsFieldValidationConfiguration<ApplicantDTO> {

  public static final ApplicantPreferredNumberValidationConfiguration INSTANCE =
      new ApplicantPreferredNumberValidationConfiguration();

  private ApplicantPreferredNumberValidationConfiguration() {
  }

  @Override
  public String getAgendaGroup() {
    return BusinessRulesAgendaGroups.APPLICANT_PREFERRED_NUMBER_VALIDATION;
  }

  @Override
  public ApplicantDTO getValidatedFact(ApplicantDTO dto) {
    return dto;
  }


}
