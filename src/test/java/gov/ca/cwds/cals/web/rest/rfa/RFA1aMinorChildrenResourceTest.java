package gov.ca.cwds.cals.web.rest.rfa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createMinorChild() throws Exception {
    RFA1aForm form = createForm();
    MinorChild created = createMinorChild(form);
    assertNotNull(created);
    assertNotNull(created.getId());
    MinorChild founded = findMinorChild(form, created.getId());
    assertThat(founded).isEqualTo(created);
  }

  @Test
  public void updateMinorChild() throws Exception {
  }

  @Test
  public void getMinorChildById() throws Exception {
  }

  @Test
  public void getMinorChildByFormId() throws Exception {
  }

  @Test
  public void deleteMinorChild() throws Exception {
  }

  private RFA1aForm createForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaForm = invocation.post(null, RFA1aForm.class);
    return rfaForm;
  }

  private MinorChild createMinorChild(RFA1aForm form) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    MinorChild minorChild = new MinorChild();
    return invocation.post(
        Entity.entity(minorChild, MediaType.APPLICATION_JSON_TYPE), MinorChild.class);
  }

  private MinorChild findMinorChild(RFA1aForm form, Long minorChildId) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_MINOR_CHILDREN
                + "/"
                + minorChildId);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(MinorChild.class);
  }
}
