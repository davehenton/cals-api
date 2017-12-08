package gov.ca.cwds.cals.service.validation.business.configuration;

import static gov.ca.cwds.cals.Constants.Validation.DEFAULT_DROOLS_VALIDATION_SESSION;

import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.Constants.RulesConfigPaths;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.RFA1aFormsDaoAware;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;
import gov.ca.cwds.cals.service.validation.business.parameters.ThreeParametersRetrievingStrategy;
import gov.ca.cwds.drools.DroolsConfiguration;
import java.util.Optional;
import org.hibernate.Hibernate;

/**
 * @author CWDS CALS API Team
 */
public final class ApplicantNamesDuplicationConstraintPutConfiguration
    extends DroolsConfiguration<Object[]> implements CalsSessionFactoryAware,
    RFA1aFormsDaoAware {

  public static final ApplicantNamesDuplicationConstraintPutConfiguration INSTANCE =
      new ApplicantNamesDuplicationConstraintPutConfiguration();

  private ApplicantNamesDuplicationConstraintPutConfiguration() {
    super(
        DEFAULT_DROOLS_VALIDATION_SESSION,
        BusinessRulesAgendaGroups.APPLICANT_NAMES_DUPLICATION_VALIDATION,
        RulesConfigPaths.FORM_INPROGRESS);
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
    return ThreeParametersRetrievingStrategy.INSTANCE
        .retrieveParameters(parameters, ApplicantDTO.class);
  }

  public RFA1aForm buildModifiedForm(RFA1aForm form, ApplicantDTO applicantDTO) {
    Hibernate.initialize(form.getApplicants());
    getCurrentSession().detach(form);
    Optional<RFA1aApplicant> foundApplicant = form.getApplicants().stream()
        .filter(applicant -> applicant.getId().equals(applicantDTO.getId()))
        .findAny();
    foundApplicant.ifPresent(applicant -> applicant.setApplicant(applicantDTO));
    return form;
  }

}
