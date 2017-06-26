package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Application;
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
    Application application;

    if (request == null) {
      application = new Application();
    } else if (!(request instanceof Application)) {
      throw new IllegalArgumentException("Cannot convert request to Application");
    } else {
      application = (Application) request;
    }

    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(SYSTEM_USER_ID);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(SYSTEM_USER_ID);

    form.setApplication(application);
    form = dao.create(form);
    Application createdApplication = form.getApplication();
    createdApplication.setId(form.getId());
    return createdApplication;
  }

  @Override
  public Response find(Serializable formId) {
    return dao.find(formId);
  }

  public void update(Serializable formId) {
    RFA1aForm rfa1aForm = dao.find(formId);
    rfa1aForm.setUpdateDateTime(LocalDateTime.now());
    rfa1aForm.setUpdateUserId(SYSTEM_USER_ID);
    dao.update(rfa1aForm);
  }
}
