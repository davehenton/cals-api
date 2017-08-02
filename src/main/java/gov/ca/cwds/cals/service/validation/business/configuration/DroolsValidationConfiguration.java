package gov.ca.cwds.cals.service.validation.business.configuration;

import static gov.ca.cwds.cals.Constants.Validation.DEFAULT_DROOLS_VALIDATION_SESSION;

/**
 * @author CWDS CALS API Team
 */
public interface DroolsValidationConfiguration<I> {

  String getAgendaGroup();

  default String getDroolsSessionName() {
    return DEFAULT_DROOLS_VALIDATION_SESSION;
  }

  Object getValidatedFact(I input);
}
