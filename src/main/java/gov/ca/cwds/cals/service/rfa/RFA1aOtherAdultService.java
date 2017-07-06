package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aOtherAdultDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.service.rfa.factory.OtherAdultFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aOtherAdultService
    extends AbstractRFAExternalEntityService<RFA1aOtherAdult, OtherAdult> {

  @Inject
  public RFA1aOtherAdultService(RFA1aOtherAdultDao dao) {
    super(dao, OtherAdultFactory.INSTANCE);
  }
}
