package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1BDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bFormDTO;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bCollectionService
    extends AbstractRFAExternalEntitiesCollectionService<
    RFA1bForm, RFA1bFormDTO> {

  @Inject
  public RFA1bCollectionService(RFA1BDao dao) {
    super(dao);
  }
}
