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
    ApplicantDTO applicant = rfaHelper.createValidApplicant();
    applicant.setFirstName(null);
    rfaHelper.postApplicant(form.getId(), applicant);
    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/first_name_form_submission_validation.json");
  }

  @Test
  public void lastNameValidationSubmissionTest() throws IOException, JSONException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ApplicantDTO applicant = rfaHelper.createValidApplicant();
    applicant.setLastName(null);
    rfaHelper.postApplicant(form.getId(), applicant);
    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/last_name_form_submission_validation.json");
  }

  @Test
  public void applicantDriverLicenseNumberIsNullValidationSubmissionTest()
      throws IOException, JSONException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ApplicantDTO applicant = rfaHelper.createValidApplicant();
    applicant.setDriverLicenseNumber(null);
    rfaHelper.postApplicant(form.getId(), applicant);
    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

  @Test
  public void applicantDriverLicenseStateIsNullValidationSubmissionTest()
      throws IOException, JSONException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ApplicantDTO applicant = rfaHelper.createValidApplicant();
    applicant.setDriverLicenseState(null);
    rfaHelper.postApplicant(form.getId(), applicant);
    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

}
