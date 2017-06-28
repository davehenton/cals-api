package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public abstract class BaseExternalEntityApiTest<T extends BaseDTO> extends
    BaseCalsApiIntegrationTest {

  protected abstract BaseExternalEntityApiHelper<T> getExternalEntityApiHelper();

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createEntityTest() throws Exception {
    getExternalEntityApiHelper().createEntity();
  }

  @Test
  public void updateEntityTest() throws Exception {
    getExternalEntityApiHelper().updateEntity();
  }

  @Test
  public void getEntityByIdTest() throws Exception {
    getExternalEntityApiHelper().getEntityById();
  }

  @Test
  public void getEntitiesByFormIdTest() throws Exception {
    getExternalEntityApiHelper().getEntitiesByFormId();
  }

  @Test
  public void deleteentityTest() throws Exception {
    getExternalEntityApiHelper().deleteEntity();
  }

}
