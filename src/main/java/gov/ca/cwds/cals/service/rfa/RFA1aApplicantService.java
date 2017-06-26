package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aApplicantParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantService extends CrudServiceAdapter {

  private RFA1aApplicantDao dao;
  private RFA1aFormService formService;

  @Inject
  public RFA1aApplicantService(RFA1aApplicantDao dao, RFA1aFormService formService) {
    this.dao = dao;
    this.formService = formService;
  }

  @Override
  public Response create(Request request) {
    RFA1aApplicantParameterObject params = getParamObject(request);
    Applicant applicant = params.getApplicant();
    if (applicant == null) {
      applicant = new Applicant();
    }
    RFA1aApplicant rfa1aApplicant = new RFA1aApplicant();

    RFA1aServiceHelper.fillCreateBaseFields(rfa1aApplicant, SYSTEM_USER_ID);

    rfa1aApplicant.setApplicant(applicant);
    rfa1aApplicant.setFormId(params.getFormId());
    rfa1aApplicant = dao.create(rfa1aApplicant);
    applicant = rfa1aApplicant.getApplicant();
    applicant.setId(rfa1aApplicant.getId());
    return applicant;
  }

  @Override
  public Response find(Serializable params) {
    RFA1aApplicantParameterObject applicantParams = getParamObject(params);
    RFA1aApplicant rfa1aApplicant =
        dao.findApplicantByFormIdAndApplicantId(
            applicantParams.getFormId(), applicantParams.getApplicantId());
    if (rfa1aApplicant == null) {
      throw new UserFriendlyException(
          CalsExceptionInfo.RFA_1A_APPLICANT_NOT_FOUND_BY_ID, NOT_FOUND);
    } else {
      Applicant applicant = rfa1aApplicant.getApplicant();
      applicant.setId(rfa1aApplicant.getId());
      return applicant;
    }
  }

  @Override
  public Response delete(Serializable params) {
    RFA1aApplicantParameterObject applicantParams = getParamObject(params);
    RFA1aApplicant rfa1aApplicant =
        dao.deleteApplicant(applicantParams.getFormId(), applicantParams.getApplicantId());
    Applicant deleted = null;
    if (rfa1aApplicant != null) {
      deleted = rfa1aApplicant.getApplicant();
    }
    return deleted;
  }

  @Override
  public Response update(Serializable applicantId, Request params) {
    final RFA1aApplicantParameterObject applicantParams = getParamObject(params);
    RFA1aApplicant applicantEntity = null;
    if (applicantId != null && applicantId instanceof Long) {
      applicantEntity =
          dao.findApplicantByFormIdAndApplicantId(applicantParams.getFormId(), (Long) applicantId);
    }
    if (applicantEntity != null) {
      applicantEntity.setApplicant(applicantParams.getApplicant());
      applicantEntity.setUpdateDateTime(LocalDateTime.now());
      applicantEntity.setUpdateUserId(SYSTEM_USER_ID);
    }

    RFA1aApplicant updated = dao.update(applicantEntity);

    Applicant applicant = null;
    // Update RFA 1a Form 
    if (updated != null) {
      formService.update(applicantParams.getFormId());
      applicant = updated.getApplicant();
      applicant.setId(updated.getId());
    }

    return applicant;
  }

  private RFA1aApplicantParameterObject getParamObject(Object params) {
    if (!(params instanceof RFA1aApplicantParameterObject)) {
      throw new IllegalStateException("RFA1aApplicantParameterObject is expected here");
    }
    return (RFA1aApplicantParameterObject) params;
  }
}
