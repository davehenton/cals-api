package gov.ca.cwds.cals.service.rfa.rules.submission;

import static gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups.FORM_SUBMISSION_VALIDATION;
import static gov.ca.cwds.cals.Constants.RulesConfigPaths.FORM_SUBMISSION;
import static gov.ca.cwds.cals.Constants.Validation.FORM_SUBMISSION_VALIDATION_SESSION;

import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.drools.DroolsConfiguration;

/**
 * @author CWDS CALS API Team
 */
public final class RFASubmissionDroolsConfiguration extends DroolsConfiguration<RFA1aFormDTO> {

  public static final RFASubmissionDroolsConfiguration INSTANCE =
      new RFASubmissionDroolsConfiguration();

  private RFASubmissionDroolsConfiguration() {
    super(
        FORM_SUBMISSION_VALIDATION_SESSION,
        FORM_SUBMISSION_VALIDATION,
        FORM_SUBMISSION);
  }
}
