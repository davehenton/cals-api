package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsRelationship;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team.
 */
public class ApplicantsRelationshipService extends CrudServiceAdapter {

  private final RFA1aFormsDao applicationDao;

  @Inject
  public ApplicantsRelationshipService(RFA1aFormsDao applicationDao) {
    this.applicationDao = applicationDao;
  }

  @Override
  public Response find(Serializable applicationId) {
    RFA1aForm form = applicationDao.find(applicationId);
    if (form == null) {
      throw new UserFriendlyException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    return form.getRelationships();
  }

  @Override
  public Response update(Serializable applicationId, Request request) {
    RFA1aForm form = applicationDao.find(applicationId);
    if (form == null) {
      throw new UserFriendlyException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(SYSTEM_USER_ID);
    if (!(request instanceof ApplicantsRelationship)) {
      throw new IllegalStateException(
          "Unexpected request class type. Expected is ApplicantsRelationship");
    }
    ApplicantsRelationship applicantsRelationship = (ApplicantsRelationship) request;
    form.setRelationships(applicantsRelationship);

    RFA1aForm updatedForm = applicationDao.update(form);
    return updatedForm.getRelationships();
  }
}
