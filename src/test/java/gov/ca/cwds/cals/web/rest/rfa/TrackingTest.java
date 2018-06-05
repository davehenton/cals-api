package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
  public void testGet() throws Exception {
    RFA1aFormDTO form = createRfa1a();
    Response response = createTracking(form);
    Tracking tracking = response.readEntity(Tracking.class);

    response = clientTestRule
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking/" + tracking.getId())
        .request().get();

    assertResponseByFixturePath(
        response, "fixtures/rfa/tracking/created-tracking.json", JSONCompareMode.LENIENT);
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
        .target(Constants.API.RFA_1A_FORMS + "/" + form.getId() + "/tracking");
    return target.request(MediaType.APPLICATION_JSON).post(null);
  }
}
