package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.RequestResponseEntity;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;

/**
 * @author CWDS CALS API Team
 */
public abstract class RFAInternalEntityConfiguration<T extends RequestResponseEntity> {

  private Class<T> entityClass;

  public RFAInternalEntityConfiguration(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  public abstract T getEntityFromTheForm(RFA1aForm form);

  public abstract void putEntityToTheForm(RFA1aForm form, T request);

}
