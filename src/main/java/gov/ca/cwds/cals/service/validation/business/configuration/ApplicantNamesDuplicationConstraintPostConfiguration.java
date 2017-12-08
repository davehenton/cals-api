package gov.ca.cwds.cals.service.validation.business.configuration;

import static gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups.APPLICANT_NAMES_DUPLICATION_VALIDATION;
import static gov.ca.cwds.cals.Constants.RulesConfigPaths.FORM_INPROGRESS;
import static gov.ca.cwds.cals.Constants.Validation.DEFAULT_DROOLS_VALIDATION_SESSION;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.RFA1aFormsDaoAware;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;
import gov.ca.cwds.cals.service.validation.business.parameters.TwoParametersRetrievingStrategy;
import gov.ca.cwds.drools.DroolsConfiguration;
import org.hibernate.Hibernate;

/**
 * @author CWDS CALS API Team
 */
public final class ApplicantNamesDuplicationConstraintPostConfiguration
    extends DroolsConfiguration<Object[]> implements CalsSessionFactoryAware,
    RFA1aFormsDaoAware {

  public static final ApplicantNamesDuplicationConstraintPostConfiguration INSTANCE =
      new ApplicantNamesDuplicationConstraintPostConfiguration();

  private ApplicantNamesDuplicationConstraintPostConfiguration() {
    super(
        DEFAULT_DROOLS_VALIDATION_SESSION,
        APPLICANT_NAMES_DUPLICATION_VALIDATION,
        FORM_INPROGRESS);
  }

  @Override
  public RFA1aForm getValidatedFact(Object[] input) {
    BusinessValidationParameterObject<ApplicantDTO> parameterObject =
        getBusinessValidationParameterObject(input);
    RFA1aForm form = getFormDao().find(parameterObject.getFormId());
    return buildModifiedForm(form, parameterObject.getEntityDTO());
  }

  public BusinessValidationParameterObject<ApplicantDTO> getBusinessValidationParameterObject(
      Object[] parameters) {
    return TwoParametersRetrievingStrategy.INSTANCE
        .retrieveParameters(parameters, ApplicantDTO.class);
  }

  public RFA1aForm buildModifiedForm(RFA1aForm form, ApplicantDTO applicantDTO) {
    RFA1aApplicant newApplicant = new RFA1aApplicant();
    newApplicant.setApplicant(applicantDTO);
    Hibernate.initialize(form.getApplicants());
    getCurrentSession().detach(form);
    form.getApplicants().add(newApplicant);
    return form;
  }

}
