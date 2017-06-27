package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildrenDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    RFA1aFormDTO form = RFAHelper.createForm(clientTestRule);
    MinorChild created = createMinorChild(form);

    assertNotNull(created);
    assertNotNull(created.getId());

    MinorChild founded = findMinorChild(form, created.getId());
    assertThat(founded).isEqualTo(created);
  }

  @Test
  public void updateMinorChild() throws Exception {
    RFA1aFormDTO form = RFAHelper.createForm(clientTestRule);

    MinorChild created = createMinorChild(form);

    MinorChild founded = findMinorChild(form, created.getId());

    founded.setOtherRelativeFirstName("FIRST_NAME");

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_MINOR_CHILDREN
                + "/"
                + created.getId());
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

    MinorChild updated = invocation
        .put(Entity.entity(founded, MediaType.APPLICATION_JSON_TYPE), MinorChild.class);

    founded = findMinorChild(form, created.getId());

    assertThat(founded).isEqualTo(updated);
    assertThat(founded).isNotEqualTo(created);

  }

  @Test
  public void getMinorChildById() throws Exception {
    RFA1aFormDTO form = RFAHelper.createForm(clientTestRule);
    MinorChild created = createMinorChild(form);
    MinorChild founded = findMinorChild(form, created.getId());
    assertThat(founded).isEqualTo(created);
  }

  @Test
  public void getMinorChildByFormId() throws Exception {
    RFA1aFormDTO form = RFAHelper.createForm(clientTestRule);
    MinorChild created1 = createMinorChild(form);
    MinorChild created2 = createMinorChild(form);
    MinorChild created3 = createMinorChild(form);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_MINOR_CHILDREN);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    MinorChildrenDTO minorChildrenDTO = invocation.get(MinorChildrenDTO.class);

    assertThat(minorChildrenDTO.getCollection().size()).isEqualTo(3);
  }

  @Test
  public void deleteMinorChild() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    MinorChild created = createMinorChild(form);
    MinorChild founded = findMinorChild(form, created.getId());

    assertThat(founded).isEqualTo(created);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + API.RFA_1A_MINOR_CHILDREN
                + "/"
                + founded.getId());
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.delete();
    assertThat(response.getStatus()).isEqualTo(200);
    response = invocation.delete();
    assertThat(response.getStatus()).isEqualTo(404);
  }

  private MinorChild createMinorChild(RFA1aFormDTO form) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    MinorChild minorChild = new MinorChild();
    return invocation.post(
        Entity.entity(minorChild, MediaType.APPLICATION_JSON_TYPE), MinorChild.class);
  }

  private MinorChild findMinorChild(RFA1aFormDTO form, Long minorChildId) {
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
