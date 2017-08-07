package gov.ca.cwds.cals.service.validation.business.configuration;

/**
 * @author CWDS CALS API Team
 */

public class ValidationConfigurationRegistry {

  public static final ValidationConfigurationRegistry INSTANCE = new ValidationConfigurationRegistry();

  public static final String APPLICANTS_DUPLICATE_NAME_POST = "APPLICANTS_DUPLICATE_NAME_POST";
  public static final String APPLICANTS_DUPLICATE_NAME_PUT = "APPLICANTS_DUPLICATE_NAME_PUT";
  public static final String APPLICANT_VALIDATION = "APPLICANT_VALIDATION";

  private ValidationConfigurationRegistry() {
  }

  public DroolsValidationConfiguration get(String configurationName) {
    DroolsValidationConfiguration<?> res = null;
    switch (configurationName) {
      case APPLICANTS_DUPLICATE_NAME_POST:
        res = ApplicantNamesDuplicationConstraintPostConfiguration.INSTANCE;
        break;
      case APPLICANTS_DUPLICATE_NAME_PUT:
        res = ApplicantNamesDuplicationConstraintPutConfiguration.INSTANCE;
        break;
      case APPLICANT_VALIDATION:
        res = ApplicantValidationConfiguration.INSTANCE;
        break;
      default:
        res = null;
        break;
    }
    return res;
  }


}
