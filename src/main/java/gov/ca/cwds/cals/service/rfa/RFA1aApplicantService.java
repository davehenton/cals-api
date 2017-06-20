package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantEntity;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aApplicantParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantService extends CrudServiceAdapter {

  private RFA1aApplicantDao dao;

  @Inject
  public RFA1aApplicantService(RFA1aApplicantDao dao) {
    this.dao = dao;
  }

  @Override
  public Response create(Request request) {
    RFA1aApplicantParameterObject params = (RFA1aApplicantParameterObject) request;
    Applicant applicant = params.getApplicant();

    ApplicantEntity applicantEntity = new ApplicantEntity();

    LocalDateTime now = LocalDateTime.now();
    applicantEntity.setCreateDateTime(now);
    applicantEntity.setCreateUserId(SYSTEM_USER_ID);
    applicantEntity.setUpdateDateTime(now);
    applicantEntity.setUpdateUserId(SYSTEM_USER_ID);

    applicantEntity.setApplicant(applicant);
    applicantEntity.setFormId(params.getFormId());

    applicantEntity = dao.create(applicantEntity);

    applicant = applicantEntity.getApplicant();
    applicant.setId(applicantEntity.getId());

    return applicant;
  }


}
