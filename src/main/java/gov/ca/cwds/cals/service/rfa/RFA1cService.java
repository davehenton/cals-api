package gov.ca.cwds.cals.service.rfa;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1cDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.mapper.RFA1aFormMapper;
import gov.ca.cwds.cals.service.mapper.RFA1cFormMapper;
import gov.ca.cwds.cals.service.rfa.factory.RFA1cFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.rest.exception.ExpectedException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author CWDS CALS API Team
 */
public class RFA1cService extends DefaultRFAExternalEntityService<RFA1cForm, RFA1cFormDTO> {

  @Inject
  private RFA1cFormMapper rfa1cFormMapper;

  @Inject
  private RFA1aFormsDao rfa1aFormDao;

  @Inject
  private RFA1aFormMapper rfa1aFormMapper;

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

    rfa1cFormMapper.toRFA1cFormDTO(request.getEntityDTO(),
        Optional.ofNullable(rfa1aFormDao.find(request.getFormId()))
            .map(rfa1aForm -> rfa1aFormMapper.toExpandedRFA1aFormDTO(rfa1aForm)).orElse(null));

    return super.create(request);
  }

  @Override
  public RFA1cFormDTO find(RFAExternalEntityGetParameterObject params) {
    if (params.getFormId() != null && params.getEntityId() != null) {
      return super.find(params);
    }

    Stream<RFA1cForm> allStream = getDao().findAllByFormId(params.getFormId());
    return allStream.findFirst().map(RFA1cForm::getEntityDTO).orElse(null);
  }
}
