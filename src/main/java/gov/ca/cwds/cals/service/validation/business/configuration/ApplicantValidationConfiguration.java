package gov.ca.cwds.cals.service.validation.business.configuration;

import static gov.ca.cwds.cals.Constants.Validation.DEFAULT_DROOLS_VALIDATION_SESSION;

import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.drools.DroolsConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantValidationConfiguration implements
    DroolsConfiguration {

  public static final ApplicantValidationConfiguration INSTANCE =
      new ApplicantValidationConfiguration();

  private ApplicantValidationConfiguration() {
  }

  @Override
  public String getAgendaGroup() {
    return BusinessRulesAgendaGroups.APPLICANT_VALIDATION;
  }

  @Override
  public String getDroolsSessionName() {
    return DEFAULT_DROOLS_VALIDATION_SESSION;
  }

}
