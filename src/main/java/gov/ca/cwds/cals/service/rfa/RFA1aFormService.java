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

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormService extends CrudServiceAdapter {

  private RFA1aFormsDao dao;

  @Inject
  public RFA1aFormService(RFA1aFormsDao dao) {
    this.dao = dao;
  }

  @Override
  public Response create(Request request) {
    RFA1aForm form = new RFA1aForm();
    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(SYSTEM_USER_ID);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(SYSTEM_USER_ID);
    form = dao.create(form);
    return form;
  }

  @Override
  public Response find(Serializable formId) {
    return dao.find(formId);
  }
}
