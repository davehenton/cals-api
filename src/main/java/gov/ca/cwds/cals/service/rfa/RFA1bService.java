package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bService extends AbstractRFAExternalEntityService<RFA1bForm, RFA1bFormDTO> {

  @Inject
  public RFA1bService(RFA1bDao dao) {
    super(dao, RFA1bFactory.INSTANCE);
  }

  @Override
  public RFA1bFormDTO create(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> request) {
    RFA1bForm rfa1bForm = composeEntity(request);
    RFA1bDao dao = (RFA1bDao) getDao();
    if (request instanceof RFAApplicantAwareEntityUpdateParams) {

      RFAApplicantAwareEntityUpdateParams params = (RFAApplicantAwareEntityUpdateParams) request;
      rfa1bForm = dao.createForApplicant(rfa1bForm, params.getApplicantId());

    } else if (request instanceof RFAOtherAdultAwareEntityUpdateParams) {

      RFAOtherAdultAwareEntityUpdateParams params = (RFAOtherAdultAwareEntityUpdateParams) request;
      rfa1bForm = dao.createForOtherAdult(rfa1bForm, params.getOtherAdultId());

    }
    return extractDTO(rfa1bForm);
  }


}
