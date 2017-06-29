package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseExternalEntityApiTest<
    EntityDTO extends RFAExternalEntityDTO, EntitiesDTO extends CollectionDTO<EntityDTO>>
    extends BaseCalsApiIntegrationTest implements ExternalEntityApiHelper {

  protected abstract BaseExternalEntityApiHelper<EntityDTO, EntitiesDTO>
  getExternalEntityApiHelper();

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  @Override
  public void createEntity() throws Exception {
    getExternalEntityApiHelper().createEntity();
  }

  @Test
  @Override
  public void updateEntity() throws Exception {
    getExternalEntityApiHelper().updateEntity();
  }

  @Test
  @Override
  public void getEntityById() throws Exception {
    getExternalEntityApiHelper().getEntityById();
  }

  @Test
  @Override
  public void getEntitiesByFormId() throws Exception {
    getExternalEntityApiHelper().getEntitiesByFormId();
  }

  @Test
  @Override
  public void deleteEntity() throws Exception {
    getExternalEntityApiHelper().deleteEntity();
  }
}
