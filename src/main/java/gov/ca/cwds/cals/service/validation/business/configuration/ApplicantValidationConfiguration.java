package gov.ca.cwds.cals.service.validation.business.configuration;

import static gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups.APPLICANT_VALIDATION;
import static gov.ca.cwds.cals.Constants.RulesConfigPaths.FORM_INPROGRESS;
import static gov.ca.cwds.cals.Constants.Validation.DEFAULT_DROOLS_VALIDATION_SESSION;

import gov.ca.cwds.drools.DroolsConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantValidationConfiguration extends DroolsConfiguration {

  public static final ApplicantValidationConfiguration INSTANCE =
      new ApplicantValidationConfiguration();

  private ApplicantValidationConfiguration() {
    super(DEFAULT_DROOLS_VALIDATION_SESSION, APPLICANT_VALIDATION, FORM_INPROGRESS);
  }

}
