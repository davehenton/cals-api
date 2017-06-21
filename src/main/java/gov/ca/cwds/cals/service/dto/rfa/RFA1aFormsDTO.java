package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public final class RFA1aFormsDTO extends CollectionDTO<RFA1aForm> {

  public RFA1aFormsDTO(Collection<RFA1aForm> collection) {
    super(collection);
  }
}
