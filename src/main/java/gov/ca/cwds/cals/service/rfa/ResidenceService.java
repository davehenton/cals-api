package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
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
      LOG.warn("There is no residence found for applicationId = {}", applicationId);
      LOG.debug(e.getMessage(), e);
    }

    return null;
  }

  @Override
  public Response update(Serializable applicationId, Request request) {
    try {
      RFA1aForm form = applicationDao.find(applicationId);
      form.setUpdateDateTime(LocalDateTime.now());
      form.setUpdateUserId(SYSTEM_USER_ID);
//      form.setResidence();

      applicationDao.update(form);
      return null;
    } catch (NoResultException e) {
      LOG.warn("There is application found for applicationId = {}", applicationId);
      LOG.debug(e.getMessage(), e);
    }
    return null;
  }
}
