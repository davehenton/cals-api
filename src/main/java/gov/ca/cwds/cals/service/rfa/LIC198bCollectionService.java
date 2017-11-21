package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.LIC198bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.LIC198bFactory;

/**
 * @author CWDS CALS API Team
 */
public class LIC198bCollectionService
    extends AbstractRFAExternalEntitiesCollectionService<LIC198bForm, LIC198bFormDTO> {

  @Inject
  public LIC198bCollectionService(LIC198bDao dao) {
    super(dao, LIC198bFactory.INSTANCE);
  }
}
