package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.rest.api.Response;
import java.util.Collection;

/**
 * @author CWDS TPT-2
 */
public class CollectionDTO<T extends BaseDTO> implements Response {
  // todo pagination
  private Collection<T> collection;

  public CollectionDTO(Collection<T> collection) {
    this.collection = collection;
  }

  public Collection<T> getCollection() {
    return collection;
  }
}
