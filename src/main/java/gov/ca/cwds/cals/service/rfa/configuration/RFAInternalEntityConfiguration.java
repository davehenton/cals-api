package gov.ca.cwds.cals.service.rfa.configuration;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S00119")
public abstract class RFAInternalEntityConfiguration<EntityDTO extends BaseDTO> {

  private Class<EntityDTO> entityClass;

  public RFAInternalEntityConfiguration(Class<EntityDTO> entityClass) {
    this.entityClass = entityClass;
  }

  public Class<EntityDTO> getEntityClass() {
    return entityClass;
  }

  protected abstract EntityDTO retrieveEntityFromTheForm(RFA1aForm form);

  public Response getEntityFromTheForm(RFA1aForm form) {
    EntityDTO entity = retrieveEntityFromTheForm(form);
    return (Response) entity;
  }

  public void putEntityToTheForm(RFA1aForm form, Request request) {
    if (!(request.getClass().isAssignableFrom(entityClass))) {
      throw new IllegalStateException(
          "Unexpected request class type. Expected is " + entityClass.getSimpleName());
    }
    saveEntityToTheForm(form, (EntityDTO) request);
  }

  protected abstract void saveEntityToTheForm(RFA1aForm form, EntityDTO entity);

}
