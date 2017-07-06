package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aMinorChildDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenCollectionService
    extends AbstractRFAExternalEntitiesCollectionService<RFA1aMinorChild, MinorChildDTO> {

  @Inject
  public RFA1aMinorChildrenCollectionService(RFA1aMinorChildDao dao) {
    super(dao);
  }
}
