package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.TRACKING;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixtureTemplate;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.tracking.FamilyDocumentsItemDTO;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDTO;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    validate(response, form);
  }

  @Test
  public void updateTest() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response trackingResponse = createTracking(form);
    TrackingDTO tracking = trackingResponse.readEntity(TrackingDTO.class);

    String newFacilityName = "New Facility Name";
    tracking.setFacilityName(newFacilityName);
    LocalDate now = LocalDate.now();
    getFirstFamilyDocumentsItem(tracking).setReceivedDate(now);
    TrackingDTO putTrackingResponse = updateTracking(tracking)
        .readEntity(TrackingDTO.class);

    Assert.assertEquals(newFacilityName, putTrackingResponse.getFacilityName());
    Assert.assertEquals(now, getFirstFamilyDocumentsItem(putTrackingResponse).getReceivedDate());
    Assert.assertNotNull(putTrackingResponse.getCreateUserId());
    Assert.assertNotNull(putTrackingResponse.getUpdateUserId());
    Assert.assertNotNull(putTrackingResponse.getCreateDateTime());
    Assert.assertNotNull(putTrackingResponse.getUpdateDateTime());

    Response response = updateTracking(-1L, tracking);
    Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
  }

  @Test
  public void checkLicenseNumberLengthTest() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response trackingResponse = createTracking(form);
    TrackingDTO tracking = trackingResponse.readEntity(TrackingDTO.class);
    // LicenseNumber 10 characters
    tracking.setLicenseNumber("1234567890");
    Response response = updateTracking(tracking);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatus());

    // LicenseNumber more then 10 characters
    tracking.setLicenseNumber("12345678901");
    response = updateTracking(tracking);
    Assert.assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, response.getStatus());
  }

  private Response updateTracking(TrackingDTO tracking) {
    return updateTracking(tracking.getRfa1aId(), tracking);
  }

  private Response updateTracking(Long formId, TrackingDTO tracking) {
    WebTarget target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + formId + "/" + TRACKING + "/" + tracking
            .getId());
    return target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(tracking, MediaType.APPLICATION_JSON_TYPE));
  }


  private FamilyDocumentsItemDTO getFirstFamilyDocumentsItem(TrackingDTO tracking) {
    return tracking
        .getTrackingDocuments()
        .getFacilityDocuments()
        .getFamilyDocuments()
        .getCollection().iterator().next();
  }

  @Test
  public void testGet() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    TrackingDTO tracking = response.readEntity(TrackingDTO.class);

    response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/" + TRACKING + "/" + tracking
            .getId())
        .request().get();
    validate(response, form);
  }

  @Test
  public void deleteTest() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    TrackingDTO tracking = response.readEntity(TrackingDTO.class);

    WebTarget target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + tracking.getRfa1aId() + "/" + TRACKING + "/" + tracking
            .getId());
    Response deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();

    Assert.assertEquals(HttpStatus.SC_OK, deleteResponse.getStatus());

    target = clientTestRule.target(
        Constants.API.RFA_1A_FORMS + "/" + tracking.getRfa1aId() + "/" + TRACKING + "/" + -1);
    deleteResponse = target.request(MediaType.APPLICATION_JSON).delete();

    Assert.assertEquals(HttpStatus.SC_NOT_FOUND, deleteResponse.getStatus());
  }

  @Test
  public void testGetTrackingId() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    TrackingDTO tracking = createTracking(form).readEntity(TrackingDTO.class);
    form = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId())
        .request().get().readEntity(RFA1aFormDTO.class);

    Assert.assertEquals(form.getTrackingId(), tracking.getId());
  }

  private RFA1aFormDTO createRfa1a() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicant = applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());
    form.setApplicants(Collections.singletonList(applicant));
    return form;
  }

  private void validate(Response response, RFA1aFormDTO form) throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("formId", form.getId());
    params.put("personId", form.getApplicants().get(0).getId());
    assertResponseByFixtureTemplate(
        response.readEntity(String.class), "fixtures/rfa/tracking/created-tracking.json", params,
        JSONCompareMode.LENIENT);
  }

  private Response createTracking(RFA1aFormDTO form) throws Exception {
    WebTarget target = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/" + TRACKING);
    return target.request(MediaType.APPLICATION_JSON).post(null);
  }
}
