package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;
import gov.ca.cwds.cals.service.validation.business.parameters.TwoParametersRetrievingStrategy;
import org.hibernate.Hibernate;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantPhoneNumbersDuplicationConstraintPutConfiguration
    implements BusinessValidationConfiguration<ApplicantDTO> {
  public static final ApplicantPhoneNumbersDuplicationConstraintPutConfiguration INSTANCE =
      new ApplicantPhoneNumbersDuplicationConstraintPutConfiguration();

  private ApplicantPhoneNumbersDuplicationConstraintPutConfiguration() {
  }

  @Override
  public String getAgendaGroup() {
    return Constants.BusinessRulesAgendaGroups.APPLICANT_PHONE_NUMBERS_DUPLICATION_VALIDATION;
  }

  public BusinessValidationParameterObject<ApplicantDTO> getBusinessValidationParameterObject(Object[] parameters) {
    return TwoParametersRetrievingStrategy.INSTANCE.retrieveParameters(parameters, ApplicantDTO.class);
  }

  @Override
  public RFA1aForm buildModifiedForm(RFA1aForm form, ApplicantDTO applicantDTO) {
    RFA1aApplicant newApplicant = new RFA1aApplicant();
    newApplicant.setApplicant(applicantDTO);
    Hibernate.initialize(form.getApplicants());
    getCurrentSession().detach(form);
    form.getApplicants().add(newApplicant);
    return form;
  }
}