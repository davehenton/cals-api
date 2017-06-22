package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormsDTO;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsCollectionService extends CrudServiceAdapter {

  private RFA1aFormsDao dao;

  @Inject
  public RFA1aFormsCollectionService(RFA1aFormsDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    List<RFA1aForm> forms = dao.findAll();
    return new RFA1aFormsDTO(forms);
  }
}
