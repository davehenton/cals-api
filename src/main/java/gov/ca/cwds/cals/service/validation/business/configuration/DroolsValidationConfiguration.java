package gov.ca.cwds.cals.service.validation.business.configuration;

/**
 * @author CWDS CALS API Team
 */
public interface DroolsValidationConfiguration<I, O> {

  String getAgendaGroup();

  O getValidatedFact(I input);
}
