package gov.ca.cwds.cals.web.rest.rfa;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * @author CWDS CALS API Team
 */

public class RFA1aCornerCasesSubmitApplicationTest extends BaseRFAIntegrationTest {

  private static final String FIXTURE_PATH_TO_PRINCIPAL = "security/cals-api-principal.json";
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  @BeforeClass
  public static void beforeClass() throws Exception {

    setUpCalsns();
    setUpCms();
    setUpCmsRs();
  }

  @Test
  public void serializeStatusToJSON() throws Exception {
    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(fixture("fixtures/rfa/submitted-status.json"),
            RFAApplicationStatusDTO.class));

    assertThat(MAPPER.writeValueAsString(RFAApplicationStatus.SUBMITTED.toDTO()))
        .isEqualTo(expected);
  }

  @Test
  public void deserializesFromJSON() throws Exception {
    assertThat(MAPPER
        .readValue(fixture("fixtures/rfa/submitted-status.json"), RFAApplicationStatusDTO.class))
        .isEqualTo(RFAApplicationStatus.SUBMITTED.toDTO());
  }

  @Test
  public void form404NotFoundForGetTest() throws Exception {
    WebTarget getTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -9999 + "/" + Constants.API.STATUS);
    Response response = getTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void form404NotFoundForPostTest() throws Exception {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -9999 + "/" + API.STATUS);
    Response response = target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(RFAApplicationStatus.SUBMITTED.toDTO(), MediaType.APPLICATION_JSON));
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getInitialStatusTest() throws Exception {
    Long formId = formAHelper.createRFA1aForm().getId();
    statusHelper.assertDraft(formId);
  }

  @Test
  public void unChangedDraftStatusTest() throws Exception {
    Long formId = formAHelper.createRFA1aForm().getId();
    statusHelper.assertDraft(formId);
    Response response = statusHelper.changeApplicationStatusTo(RFAApplicationStatus.DRAFT, formId);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertDraft(formId);
  }

  @Test
  public void unChangedSubmitStatusTest() throws Exception {
      if (TestModeUtils.isIntegrationTestsMode()) {
          return;
      }
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(form.getId(), applicantHelper.getValidApplicant());
    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    Response response = statusHelper.submitApplication(form.getId(), FIXTURE_PATH_TO_PRINCIPAL);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
    response = statusHelper.submitApplication(form.getId(), FIXTURE_PATH_TO_PRINCIPAL);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
  }

  @Test
  public void changeStatusBackToDraftTest() throws Exception {
      if (TestModeUtils.isIntegrationTestsMode()) {
          return;
      }
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(form.getId(), applicantHelper.getValidApplicant());
    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    Response response = statusHelper.submitApplication(form.getId(), FIXTURE_PATH_TO_PRINCIPAL);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
    response = statusHelper.changeApplicationStatusTo(RFAApplicationStatus.DRAFT, form.getId());
    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
  }

}
