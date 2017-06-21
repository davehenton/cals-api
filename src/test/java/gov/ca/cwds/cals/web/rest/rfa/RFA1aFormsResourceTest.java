package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormsDTO;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createApplicationForm() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaForm = invocation.post(null, RFA1aForm.class);
    assertNotNull(rfaForm);
    assertNotNull(rfaForm.getId());
  }

  @Test
  public void getAplicationForm() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaFormCreate = invocation.post(null, RFA1aForm.class);

    target = clientTestRule.target(API.RFA_1A_FORMS + "/" + rfaFormCreate.getId());
    invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaFormGet = invocation.get(RFA1aForm.class);

    assertNotNull(rfaFormCreate);
    assertNotNull(rfaFormGet);
    assertEquals(rfaFormCreate, rfaFormGet);
  }

  @Test
  public void getAllAplicationForms() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaFormCreate1 = invocation.post(null, RFA1aForm.class);
    RFA1aForm rfaFormCreate2 = invocation.post(null, RFA1aForm.class);
    RFA1aForm rfaFormCreate3 = invocation.post(null, RFA1aForm.class);

    assertNotEquals(rfaFormCreate1, rfaFormCreate2);
    assertNotEquals(rfaFormCreate2, rfaFormCreate3);
    assertNotEquals(rfaFormCreate3, rfaFormCreate1);

    target = clientTestRule.target(API.RFA_1A_FORMS);
    invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aFormsDTO rfaForms = invocation.get(RFA1aFormsDTO.class);

    assertTrue(rfaForms.getCollection().size() >= 3);
  }
}
