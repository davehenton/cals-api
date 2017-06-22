package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.UNEXPECTED_RESIDENCE_CLASS_TYPE;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team.
 */
public class ResidenceService extends CrudServiceAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(ResidenceService.class);

  private final RFA1aFormsDao applicationDao;

  @Inject
  public ResidenceService(RFA1aFormsDao applicationDao) {
    this.applicationDao = applicationDao;
  }

  @Override
  public Response find(Serializable applicationId) {
    try {
      RFA1aForm form = applicationDao.find(applicationId);
      return form.getResidence();
    } catch (NoResultException e) {
      LOG.warn("There is no Residence found for applicationId = {}", applicationId);
      LOG.debug(e.getMessage(), e);
      throw new UserFriendlyException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
  }

  @Override
  public Response update(Serializable applicationId, Request request) {
    try {
      RFA1aForm form = applicationDao.find(applicationId);
      form.setUpdateDateTime(LocalDateTime.now());
      form.setUpdateUserId(SYSTEM_USER_ID);
      if (!(request instanceof Residence)) {
        throw new UserFriendlyException(UNEXPECTED_RESIDENCE_CLASS_TYPE, INTERNAL_SERVER_ERROR);
      }
      Residence residence = (Residence) request;
      form.setResidence(residence);

      RFA1aForm updatedForm = applicationDao.update(form);
      return updatedForm.getResidence();
    } catch (NoResultException e) {
      LOG.warn("There is no Application found for applicationId = {}", applicationId);
      LOG.debug(e.getMessage(), e);
      throw new UserFriendlyException(CalsExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
  }
}
