package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.TRACKING;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class TrackingTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void testCreate() throws Exception {
    Response response = createTracking();
    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }

  @Test
  public void updateTest() throws Exception {
    Response trackingResponse = createTracking();
    Tracking tracking = trackingResponse.readEntity(Tracking.class);

    String newFacilityName = "New Facility Name";
    tracking.setFacilityName(newFacilityName);
    WebTarget target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + tracking.getRfa1aId() + "/" + TRACKING + "/" + tracking
            .getId());
    Tracking putTrackingResponse = target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(tracking, MediaType.APPLICATION_JSON_TYPE), Tracking.class);
    Assert.assertEquals(newFacilityName, putTrackingResponse.getFacilityName());

    target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + -1 + "/" + TRACKING + "/" + tracking
            .getId());

    Response response = target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(tracking, MediaType.APPLICATION_JSON_TYPE));
    Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
  }

  private Response createTracking() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());

    WebTarget target = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    return target.request(MediaType.APPLICATION_JSON).post(null);
  }

  @Test
  public void testGet() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());

    WebTarget target = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    Tracking tracking = target.request(MediaType.APPLICATION_JSON).post(null)
        .readEntity(Tracking.class);

    Response response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking/" + tracking.getId())
        .request().get();

    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }
}
