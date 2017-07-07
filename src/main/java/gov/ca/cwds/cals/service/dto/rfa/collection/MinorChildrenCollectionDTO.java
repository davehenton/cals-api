package gov.ca.cwds.cals.service.dto.rfa.collection;

import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class MinorChildrenCollectionDTO extends CollectionDTO<MinorChildDTO> {

  private static final long serialVersionUID = 3347226957017640546L;

  public MinorChildrenCollectionDTO() {
  }

  public MinorChildrenCollectionDTO(Collection<MinorChildDTO> collection) {
    super(collection);
  }
}
