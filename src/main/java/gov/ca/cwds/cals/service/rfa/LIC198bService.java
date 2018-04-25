package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.LIC198bDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aOtherAdultDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.LIC198bFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;

/**
 * @author CWDS CALS API Team
 */
public class LIC198bService extends DefaultRFAExternalEntityService<LIC198bForm, LIC198bFormDTO> {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  public LIC198bService(LIC198bDao dao) {
    super(dao, LIC198bFactory.INSTANCE);
  }

  @Override
  public LIC198bFormDTO create(RFAExternalEntityUpdateParameterObject<LIC198bFormDTO> request) {
    LIC198bForm lic198bForm = composeEntity(request);
    LIC198bDao dao = (LIC198bDao) getDao();
    if (request instanceof RFAApplicantAwareEntityUpdateParams) {

      RFAApplicantAwareEntityUpdateParams params = (RFAApplicantAwareEntityUpdateParams) request;
      lic198bForm = dao.createForApplicant(lic198bForm, params.getApplicantId());

    } else if (request instanceof RFAOtherAdultAwareEntityUpdateParams) {

      RFAOtherAdultAwareEntityUpdateParams params = (RFAOtherAdultAwareEntityUpdateParams) request;
      lic198bForm = dao.createForOtherAdult(lic198bForm, params.getOtherAdultId());

    }
    return extractDTO(lic198bForm);
  }

  @Override
  public LIC198bFormDTO find(RFAExternalEntityGetParameterObject params) {
    Long entityId = params.getEntityId();
    if (params instanceof RFAApplicantAwareEntityGetParameterObject) {
      RFA1aApplicant applicant = applicantDao.find(entityId);
      return extractDTO(applicant.getLic198bForm());
    } else if (params instanceof RFAOtherAdultAwareEntityGetParameterObject) {
      RFA1aOtherAdult rfa1aOtherAdult = otherAdultDao.find(entityId);
      return extractDTO(rfa1aOtherAdult.getLic198bForm());
    }
    return super.find(params);
  }

  public LIC198bFormDTO find(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> params) {
    if (params instanceof RFAApplicantAwareEntityUpdateParams) {
      Long applicantId = ((RFAApplicantAwareEntityUpdateParams) params).getApplicantId();
      RFA1aApplicant applicant = applicantDao.find(applicantId);
      return extractDTO(applicant.getLic198bForm());
    } else if (params instanceof RFAOtherAdultAwareEntityUpdateParams) {
      Long otherAdultId = ((RFAOtherAdultAwareEntityUpdateParams) params).getOtherAdultId();
      RFA1aOtherAdult rfa1aOtherAdult = otherAdultDao.find(otherAdultId);
      return extractDTO(rfa1aOtherAdult.getLic198bForm());
    }
    return null;
  }


}
