package gov.ca.cwds.cals.service.validation.business.configuration;

/**
 * @author CWDS CALS API Team
 */

public enum DroolsValidationConfigurationRegistry {

  APPLICANTS_DUPLICATE_NAME_CONSTRAINT_POST_CONFIGURATION(
      ApplicantDuplicateNamesConstraintPostConfiguration.INSTANCE),

  APPLICANTS_DUPLICATE_NAME_CONSTRAINT_PUT_CONFIGURATION(
      ApplicantDuplicateNamesConstraintPutConfiguration.INSTANCE),

  APPLICANT_PREFERRED_NUMBER_CONSTRAINT_CONFIGURATION(
      ApplicantPreferredNumberValidationConfiguration.INSTANCE);

  private DroolsValidationConfiguration configuration;

  DroolsValidationConfigurationRegistry(DroolsValidationConfiguration configuration) {
    this.configuration = configuration;
  }

  public DroolsValidationConfiguration getConfiguration() {
    return configuration;
  }

}
