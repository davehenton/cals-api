package gov.ca.cwds.cals.web.rest.rfa.configuration;

import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.dto.BaseDTO;
import java.io.IOException;

/**
 * @author CWDS CALS API Team
 */

public abstract class TestInternalEntityConfiguration<T extends BaseDTO> {

  private RestClientTestRule clientTestRule;
  private Class<T> entityClass;
  private String apiPath;

  public TestInternalEntityConfiguration(
      RestClientTestRule clientTestRule, Class<T> entityClass, String apiPath) {
    this.clientTestRule = clientTestRule;
    this.entityClass = entityClass;
    this.apiPath = apiPath;
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  protected abstract String getFixture();

  public T createEntity() throws IOException {
    String fixture = getFixture();
    return clientTestRule.getMapper().readValue(fixture, getEntityClass());
  }

  public void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public String getApiPath() {
    return apiPath;
  }

  public void setApiPath(String apiPath) {
    this.apiPath = apiPath;
  }
}
