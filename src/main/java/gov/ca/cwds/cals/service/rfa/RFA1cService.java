package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1cDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFA1cFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.rest.exception.ExpectedException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author CWDS CALS API Team
 */
public class RFA1cService extends AbstractRFAExternalEntityService<RFA1cForm, RFA1cFormDTO> {

  @Inject
  public RFA1cService(RFA1cDao dao) {
    super(dao, RFA1cFactory.INSTANCE);
  }

  @Override
  public RFA1cFormDTO create(RFAExternalEntityUpdateParameterObject<RFA1cFormDTO> request) {
    long count = getDao().findAllByFormId(request.getFormId()).count();
    if (count != 0) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.RFA_1C_FORM_ALREADY_EXISTS, BAD_REQUEST);
    }
    return super.create(request);
  }
}
