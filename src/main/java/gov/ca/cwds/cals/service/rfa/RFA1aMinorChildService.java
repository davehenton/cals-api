package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aMinorChildDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.rfa.factory.MinorChildFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildService
    extends DefaultRFAExternalEntityService<RFA1aMinorChild, MinorChildDTO> {

  @Inject
  public RFA1aMinorChildService(RFA1aMinorChildDao dao) {
    super(dao, MinorChildFactory.INSTANCE);
  }
}
