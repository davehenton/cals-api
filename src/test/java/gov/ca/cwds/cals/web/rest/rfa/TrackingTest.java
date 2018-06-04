package gov.ca.cwds.cals.web.rest.rfa;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;


import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;

public class TrackingTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void testCreate() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());

    WebTarget target = clientTestRule.target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    Response response = target.request(MediaType.APPLICATION_JSON).post(null);

    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }

  @Test
  public void testGet() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());

    WebTarget target = clientTestRule.target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    Tracking tracking = target.request(MediaType.APPLICATION_JSON).post(null).readEntity(Tracking.class);

    Response response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking/" + tracking.getId())
        .request().get();

    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
  }


  @Test
  public void testGetTrackingId() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    statusHelper.submitApplication(form.getId());

    WebTarget target = clientTestRule.target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    Tracking tracking = target.request(MediaType.APPLICATION_JSON).post(null).readEntity(Tracking.class);

    RFA1aFormDTO response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId())
        .request().get().readEntity(RFA1aFormDTO.class);

    Assert.assertEquals(response.getTrackingId(), tracking.getId());
  }
}
