package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public final class RFA1aFormsDTO extends CollectionDTO<RFA1aFormDTO> {

  public RFA1aFormsDTO() {
  }

  public RFA1aFormsDTO(Collection<RFA1aFormDTO> collection) {
    super(collection);
  }
}
