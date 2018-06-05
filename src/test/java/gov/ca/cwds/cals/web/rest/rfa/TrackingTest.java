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
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }

  @Test
  public void updateTest() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response trackingResponse = createTracking(form);
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

  @Test
  public void testGet() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    Tracking tracking = response.readEntity(Tracking.class);

    response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/" + TRACKING + "/" + tracking
            .getId())
        .request().get();

    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }

  @Test
  public void deleteTest() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    Tracking tracking = response.readEntity(Tracking.class);

    WebTarget target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + tracking.getRfa1aId() + "/" + TRACKING + "/" + tracking
            .getId());
    Response deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();

    Assert.assertEquals(HttpStatus.SC_OK, deleteResponse.getStatus());

    target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + tracking.getRfa1aId() + "/" + TRACKING + "/" + -1);
    deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();

    Assert.assertEquals(HttpStatus.SC_NOT_FOUND, deleteResponse.getStatus());
    ;
  }

  @Test
  public void testGetTrackingId() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Tracking tracking = createTracking(form).readEntity(Tracking.class);
    form = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId())
        .request().get().readEntity(RFA1aFormDTO.class);

    Assert.assertEquals(form.getTrackingId(), tracking.getId());
  }

  private RFA1aFormDTO createRfa1a() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());
    return form;
  }

  private Response createTracking(RFA1aFormDTO form) throws Exception {
    WebTarget target = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/" + TRACKING);
    return target.request(MediaType.APPLICATION_JSON).post(null);
  }
}
