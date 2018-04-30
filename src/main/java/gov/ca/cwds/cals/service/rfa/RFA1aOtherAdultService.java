package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aOtherAdultDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.rfa.factory.OtherAdultFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aOtherAdultService
    extends DefaultRFAExternalEntityService<RFA1aOtherAdult, OtherAdultDTO> {

  @Inject
  public RFA1aOtherAdultService(RFA1aOtherAdultDao dao) {
    super(dao, OtherAdultFactory.INSTANCE);
  }
}
