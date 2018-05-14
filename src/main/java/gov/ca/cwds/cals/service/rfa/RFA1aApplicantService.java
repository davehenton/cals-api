package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.rfa.factory.ApplicantFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantService
    extends DefaultRFAExternalEntityService<RFA1aApplicant, ApplicantDTO> {

  private RFA1aFormsDao formsDao;

  @Inject
  private RFA1aFormService rfa1aFormService;

  @Inject
  public RFA1aApplicantService(RFA1aApplicantDao applicantDao, RFA1aFormsDao formsDao) {
    super(applicantDao, ApplicantFactory.INSTANCE);
    this.formsDao = formsDao;
  }

  @Override
  public ApplicantDTO create(RFAExternalEntityUpdateParameterObject<ApplicantDTO> request) {
    ApplicantDTO applicant = super.create(request);
    Long formId = request.getFormId();
    rfa1aFormService.updateFacilityName(formId);
    RFA1aForm form = formsDao.find(formId);
    if (form.getStatus() != RFAApplicationStatus.IN_PROGRESS) {
      form.setStatus(RFAApplicationStatus.IN_PROGRESS);
    }
    return applicant;
  }

  @Override
  public ApplicantDTO update(RFAExternalEntityGetParameterObject params,
      RFAExternalEntityUpdateParameterObject<ApplicantDTO> request) {
    ApplicantDTO updated = super.update(params, request);
    rfa1aFormService.updateFacilityName(request.getFormId());
    return updated;
  }

  @Override
  public ApplicantDTO delete(RFAExternalEntityGetParameterObject params) {
    ApplicantDTO deleted = super.delete(params);
    rfa1aFormService.updateFacilityName(params.getFormId());
    return deleted;
  }
}
