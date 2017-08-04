package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.io.IOException;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aFormSubmissionValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void firstNameValidationSubmissionTest() throws IOException, JSONException {
    RFA1aFormDTO form = rfaHelper.createForm();
    rfaHelper.postApplicant(form.getId(), new ApplicantDTO());
    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/first_name_form_submission_validation.json");
  }

}
