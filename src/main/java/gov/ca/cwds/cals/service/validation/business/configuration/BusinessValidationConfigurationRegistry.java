package gov.ca.cwds.cals.service.validation.business.configuration;

/**
 * @author CWDS CALS API Team
 */

public enum BusinessValidationConfigurationRegistry {

  APPLICANT_NAMES_DUPLICATION_CONSTRAINT_POST_CONFIGURATION(
      ApplicantNamesDuplicationConstraintPostConfiguration.INSTANCE),
  APPLICANT_NAMES_DUPLICATION_CONSTRAINT_PUT_CONFIGURATION(
      ApplicantNamesDuplicationConstraintPutConfiguration.INSTANCE),

  APPLICANT_PHONE_NUMBERS_DUPLICATION_CONSTRAINT_POST_CONFIGURATION(
      ApplicantPhoneNumbersDuplicationConstraintPostConfiguration.INSTANCE),
  APPLICANT_PHONE_NUMBERS_DUPLICATION_PUT_CONFIGURATION(
      ApplicantPhoneNumbersDuplicationConstraintPutConfiguration.INSTANCE);

  private BusinessValidationConfiguration configuration;

  BusinessValidationConfigurationRegistry(BusinessValidationConfiguration configuration) {
    this.configuration = configuration;
  }

  public BusinessValidationConfiguration getConfiguration() {
    return configuration;
  }

}
