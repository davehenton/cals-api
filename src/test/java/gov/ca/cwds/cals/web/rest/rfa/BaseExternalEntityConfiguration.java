package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

public abstract class BaseExternalEntityConfiguration<T extends BaseDTO> {

  private Class<T> entityClass;
  private GenericType<CollectionDTO<T>> entityCollectionGenericType;
  private String apiPath;

  public BaseExternalEntityConfiguration(Class<T> entityClass,
      GenericType<CollectionDTO<T>> entityCollectionGenericType, String apiPath) {
    this.entityClass = entityClass;
    this.entityCollectionGenericType = entityCollectionGenericType;
    this.apiPath = apiPath;
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  protected abstract void updateEntity(T entity);

  protected abstract T createEntity();

  public void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public GenericType<CollectionDTO<T>> getEntityCollectionGenericType() {
    return entityCollectionGenericType;
  }

  public void setEntityCollectionGenericType(
      GenericType<CollectionDTO<T>> entityCollectionGenericType) {
    this.entityCollectionGenericType = entityCollectionGenericType;
  }

  public String getApiPath() {
    return apiPath;
  }

  public void setApiPath(String apiPath) {
    this.apiPath = apiPath;
  }
}
