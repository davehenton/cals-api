package gov.ca.cwds.cals.service.rfa;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aOtherAdultDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;
import gov.ca.cwds.rest.exception.ExpectedException;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bService extends AbstractRFAExternalEntityService<RFA1bForm, RFA1bFormDTO> {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  public RFA1bService(RFA1bDao dao) {
    super(dao, RFA1bFactory.INSTANCE);
  }

  @Override
  public RFA1bFormDTO create(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> request) {

    RFA1bFormDTO rfa1bFormDTO = find(request);
    if (rfa1bFormDTO != null) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.RFA_1B_FORM_ALREADY_EXISTS, BAD_REQUEST);
    }

    RFA1bForm rfa1bForm = composeEntity(request);
    RFA1bDao dao = (RFA1bDao) getDao();
    if (request instanceof RFAApplicantAwareEntityUpdateParams) {
      RFAApplicantAwareEntityUpdateParams params = (RFAApplicantAwareEntityUpdateParams) request;
      rfa1bForm = dao.createForApplicant(rfa1bForm, params.getApplicantId());
    } else if (request instanceof RFAOtherAdultAwareEntityUpdateParams) {
      RFAOtherAdultAwareEntityUpdateParams params = (RFAOtherAdultAwareEntityUpdateParams) request;
      rfa1bForm = dao.createForOtherAdult(rfa1bForm, params.getOtherAdultId());
    }
    return extractDTO(rfa1bForm);
  }

  @Override
  public RFA1bFormDTO find(RFAExternalEntityGetParameterObject params) {
    Long entityId = params.getEntityId();
    if (params instanceof RFAApplicantAwareEntityGetParameterObject) {
      RFA1aApplicant applicant = applicantDao.find(entityId);
      return extractDTO(applicant.getRfa1bForm());
    } else if (params instanceof RFAOtherAdultAwareEntityGetParameterObject) {
      RFA1aOtherAdult rfa1aOtherAdult = otherAdultDao.find(entityId);
      return extractDTO(rfa1aOtherAdult.getRfa1bForm());
    }
    return null;
  }

  public RFA1bFormDTO find(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> params) {
    if (params instanceof RFAApplicantAwareEntityUpdateParams) {
      Long applicantId = ((RFAApplicantAwareEntityUpdateParams) params).getApplicantId();
      RFA1aApplicant applicant = applicantDao.find(applicantId);
      return extractDTO(applicant.getRfa1bForm());
    } else if (params instanceof RFAOtherAdultAwareEntityUpdateParams) {
      Long otherAdultId = ((RFAOtherAdultAwareEntityUpdateParams) params).getOtherAdultId();
      RFA1aOtherAdult rfa1aOtherAdult = otherAdultDao.find(otherAdultId);
      return extractDTO(rfa1aOtherAdult.getRfa1bForm());
    }
    return null;
  }
}
