package gov.ca.cwds.cals.web.rest.rfa;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsRelationship;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import java.time.LocalDate;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aRelationshipResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getApplicationNotFoundTest() throws Exception {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + 5 + "/" + API.APPLICANTS_RELATIONSHIP);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());

    String fixture = fixture("fixtures/rfa/rfa-1a-application-not-found.json");
    assertEqualsResponse(fixture, response.readEntity(String.class));

  }

  @Test
  public void getApplicationRelationshipNotFoundTest() throws Exception {
    RFA1aForm rfa1aForm = RFAHelper.createForm(clientTestRule);
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + rfa1aForm.getId() + "/" + API.APPLICANTS_RELATIONSHIP);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
  }

  @Ignore
  @Test
  public void putApplicationRelationshipTest() throws Exception {
    RFA1aForm rfa1aForm = RFAHelper.createForm(clientTestRule);
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + rfa1aForm.getId() + "/" + API.APPLICANTS_RELATIONSHIP);
    ApplicantsRelationship applicantsRelationship =
        target.request(MediaType.APPLICATION_JSON).put(
            Entity.entity(createApplicantsRelationship(), MediaType.APPLICATION_JSON_TYPE),
            ApplicantsRelationship.class);
    ApplicantsRelationship r = target.request(MediaType.APPLICATION_JSON)
        .get(ApplicantsRelationship.class);
    assertThat(r).isEqualTo(applicantsRelationship);
  }

  private ApplicantsRelationship createApplicantsRelationship() {
    ApplicantsRelationship applicantsRelationship = new ApplicantsRelationship();
    applicantsRelationship.setDateOfRelationship(LocalDate.of(2017, 04, 12));
    applicantsRelationship.setOtherRelationship("Other relationship");
    applicantsRelationship.setPlaceOfRelationshipCity("Sacramento");
    StateType stateType = new StateType();
    stateType.setId(1l);
    stateType.setValue("California");
    ApplicantRelationshipType relationshipType = new ApplicantRelationshipType();
    relationshipType.setId(1l);
    relationshipType.setValue("Married");
    stateType.setId(1l);
    stateType.setValue("California");
    applicantsRelationship.setPlaceOfRelationshipState(stateType);
    applicantsRelationship.setRelationshipType(relationshipType);
    return applicantsRelationship;
  }

}
