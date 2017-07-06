package gov.ca.cwds.cals.web.rest.rfa.configuration;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

public abstract class TestExternalEntityConfiguration<T extends BaseDTO> extends
    TestInternalEntityConfiguration<T> {

  public TestExternalEntityConfiguration(
      RestClientTestRule clientTestRule,
      Class<T> entityClass,
      String apiPath) {
    super(clientTestRule, entityClass, apiPath);
  }

  public abstract GenericType<CollectionDTO<T>> getCollectionDTOGenericType();

  public abstract void modifyEntity(T entity);

}
