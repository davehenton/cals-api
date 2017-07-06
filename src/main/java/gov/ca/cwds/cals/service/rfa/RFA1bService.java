package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bService extends AbstractRFAExternalEntityService<RFA1bForm, RFA1bFormDTO> {

  @Inject
  public RFA1bService(RFA1bDao dao) {
    super(dao, RFA1bFactory.INSTANCE);
  }
}
