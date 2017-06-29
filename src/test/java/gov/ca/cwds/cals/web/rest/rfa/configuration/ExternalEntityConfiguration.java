package gov.ca.cwds.cals.web.rest.rfa.configuration;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

public abstract class ExternalEntityConfiguration<T extends BaseDTO> extends
    InternalEntityConfiguration<T> {

  private GenericType<CollectionDTO<T>> entityCollectionGenericType;

  public ExternalEntityConfiguration(
      RestClientTestRule clientTestRule,
      Class<T> entityClass,
      GenericType<CollectionDTO<T>> entityCollectionGenericType,
      String apiPath) {
    super(clientTestRule, entityClass, apiPath);
    this.entityCollectionGenericType = entityCollectionGenericType;
  }

  public abstract void modifyEntity(T entity);

  public GenericType<CollectionDTO<T>> getEntityCollectionGenericType() {
    return entityCollectionGenericType;
  }

  public void setEntityCollectionGenericType(
      GenericType<CollectionDTO<T>> entityCollectionGenericType) {
    this.entityCollectionGenericType = entityCollectionGenericType;
  }

}
