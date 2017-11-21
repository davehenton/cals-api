package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.LIC198bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.LIC198bFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;

/**
 * @author CWDS CALS API Team
 */
public class LIC198bService extends AbstractRFAExternalEntityService<LIC198bForm, LIC198bFormDTO> {

  @Inject
  public LIC198bService(LIC198bDao dao) {
    super(dao, LIC198bFactory.INSTANCE);
  }

  @Override
  public LIC198bFormDTO create(RFAExternalEntityUpdateParameterObject<LIC198bFormDTO> request) {
    LIC198bForm lic198bForm = composeEntity(request);
    LIC198bDao dao = (LIC198bDao) getDao();
    if (request instanceof RFAApplicantAwareEntityUpdateParams) {

      RFAApplicantAwareEntityUpdateParams params = (RFAApplicantAwareEntityUpdateParams) request;
      lic198bForm = dao.createForApplicant(lic198bForm, params.getApplicantId());

    } else if (request instanceof RFAOtherAdultAwareEntityUpdateParams) {

      RFAOtherAdultAwareEntityUpdateParams params = (RFAOtherAdultAwareEntityUpdateParams) request;
      lic198bForm = dao.createForOtherAdult(lic198bForm, params.getOtherAdultId());

    }
    return extractDTO(lic198bForm);
  }


}
