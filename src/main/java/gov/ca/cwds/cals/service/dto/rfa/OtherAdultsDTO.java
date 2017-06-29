package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.util.Collection;

/**
 * @author CWDS CALS API Team
 */
public class OtherAdultsDTO extends CollectionDTO<OtherAdult> {


  private static final long serialVersionUID = -1828042079375417193L;

  public OtherAdultsDTO() {
  }

  public OtherAdultsDTO(Collection<OtherAdult> collection) {
    super(collection);
  }
}
