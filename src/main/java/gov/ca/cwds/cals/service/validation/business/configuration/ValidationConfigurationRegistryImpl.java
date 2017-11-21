package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.validation.ValidationConfigurationRegistry;

/**
 * @author CWDS CALS API Team
 */

public class ValidationConfigurationRegistryImpl implements ValidationConfigurationRegistry {

  public static final String APPLICANTS_DUPLICATE_NAME_POST = "APPLICANTS_DUPLICATE_NAME_POST";
  public static final String APPLICANTS_DUPLICATE_NAME_PUT = "APPLICANTS_DUPLICATE_NAME_PUT";
  public static final String APPLICANT_VALIDATION = "APPLICANT_VALIDATION";

  public ValidationConfigurationRegistryImpl() {}

  public DroolsConfiguration get(String configurationName) {
    DroolsConfiguration<?> res = null;
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
