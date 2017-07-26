package gov.ca.cwds.cals.service.validation.business.configuration;

/**
 * @author CWDS CALS API Team
 */

public enum BusinessValidationConfigurationRegistry {

  APPLICANTS_DUPLICATE_NAME_CONSTRAINT_POST_CONFIGURATION(
      ApplicantDuplicateNamesConstraintPostConfiguration.INSTANCE),
  APPLICANTS_DUPLICATE_NAME_CONSTRAINT_PUT_CONFIGURATION(
      ApplicantDuplicateNamesConstraintPutConfiguration.INSTANCE);

  private BusinessValidationConfiguration configuration;

  BusinessValidationConfigurationRegistry(BusinessValidationConfiguration configuration) {
    this.configuration = configuration;
  }

  public BusinessValidationConfiguration getConfiguration() {
    return configuration;
  }

}
