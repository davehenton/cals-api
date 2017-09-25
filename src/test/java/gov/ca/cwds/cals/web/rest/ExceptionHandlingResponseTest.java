package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixture;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixtureTemplate;
import static gov.ca.cwds.rest.exception.IssueDetails.BASE_MESSAGE;
import static gov.ca.cwds.rest.exception.IssueType.UNEXPECTED_EXCEPTION;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.BaseRFAIntegrationTest;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import gov.ca.cwds.rest.exception.BaseExceptionResponse;
import gov.ca.cwds.rest.exception.IssueDetails;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

public class ExceptionHandlingResponseTest extends BaseRFAIntegrationTest {

  private static final String FACILITY_ID = "107201149";
  private static final String WRONG_FACILITY_ID = "1";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
    setUpFas();
  }

  @Test
  public void corruptedJSONValidationTest() throws Exception {
    String fixture = "{\"wrong\": -1}";
    Response response = clientTestRule.target(API.RFA_1A_FORMS).request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(
            clientTestRule.getMapper().readValue(fixture, Wrong.class),
            MediaType.APPLICATION_JSON_TYPE));
    assertEquals(400, response.getStatus());
    String entity = response.readEntity(String.class);
    BaseExceptionResponse baseExceptionResponse = clientTestRule.getMapper()
        .readValue(entity, BaseExceptionResponse.class);

    VelocityHelper velocityHelper = new VelocityHelper();
    IssueDetails details = baseExceptionResponse.getIssueDetails().iterator().next();
    velocityHelper.setParameter("incident_id", details.getIncidentId());
    velocityHelper.setParameter("user_message", BASE_MESSAGE);
    assertResponseByFixture(entity,
        velocityHelper.process("fixtures/exception/json-error-response.json"));
  }

  @Test
  public void technicalValidationTest() throws Exception {
    String fixture = "{\"application_county\": {\"id\": -1, \"value\": \"Unknown\"}}";
    Response response = clientTestRule.target(API.RFA_1A_FORMS).request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(
            clientTestRule.getMapper().readValue(fixture, RFA1aFormDTO.class),
            MediaType.APPLICATION_JSON_TYPE));
    assertEquals(422, response.getStatus());
    String entity = response.readEntity(String.class);
    Map<String, Object> parameters = new HashMap<>();
    BaseExceptionResponse exceptionResponse = clientTestRule.getMapper()
        .readValue(entity, BaseExceptionResponse.class);
    Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
    IssueDetails detail = issueDetails.iterator().next();
    parameters.put("incident_id", detail.getIncidentId());

    assertResponseByFixtureTemplate(entity,
        "fixtures/exception/technical-validation-exception.json",
        parameters);

  }

  @Test
  public void businessValidationTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(form.getId(), getApplicantDTO());
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS);
    Response response = target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(getApplicantDTO(), MediaType.APPLICATION_JSON_TYPE));
    assertEquals(422, response.getStatus());

    String entity = response.readEntity(String.class);
    Map<String, Object> parameters = new HashMap<>();
    BaseExceptionResponse exceptionResponse = clientTestRule.getMapper()
        .readValue(entity, BaseExceptionResponse.class);
    Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
    IssueDetails detail = issueDetails.iterator().next();
    parameters.put("incident_id", detail.getIncidentId());

    assertResponseByFixtureTemplate(entity,
        "fixtures/exception/business-validation-exception.json",
        parameters);
  }

  @Test
  public void expectedExceptionTest() throws Exception {
    WebTarget target = clientTestRule
        .target(FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS + "/" +
            WRONG_FACILITY_ID);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
    String entity = response.readEntity(String.class);
    BaseExceptionResponse baseExceptionResponse = clientTestRule.getMapper()
        .readValue(entity, BaseExceptionResponse.class);
    VelocityHelper velocityHelper = new VelocityHelper();
    IssueDetails details = baseExceptionResponse.getIssueDetails().iterator().next();
    velocityHelper.setParameter("incident_id", details.getIncidentId());
    velocityHelper.setParameter("user_message", BASE_MESSAGE);
    velocityHelper.setParameter(
        "technical_message", Constants.ExpectedExceptionMessages.COMPLAINT_NOT_FOUND_BY_ID);
    assertResponseByFixture(entity,
        velocityHelper.process("fixtures/exception/expected-exception.json"));
  }

  @Test
  public void unExpectedExceptionTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + FACILITY_ID);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(500, response.getStatus());
    BaseExceptionResponse unexpectedErrorResponse =
        response.readEntity(BaseExceptionResponse.class);
    IssueDetails details = unexpectedErrorResponse.getIssueDetails().iterator().next();
    assertNotNull(details.getStackTrace());
    assertEquals(UNEXPECTED_EXCEPTION, details.getType());
    assertNotNull(details.getIncidentId());
    assertEquals(IssueDetails.BASE_MESSAGE, details.getUserMessage());
  }

  private ApplicantDTO getApplicantDTO() throws Exception {
    String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-applicant.json";
    return clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

  private static class Wrong implements Serializable {

    public Wrong() {
    }

    int wrong;

    public int getWrong() {
      return wrong;
    }

    public void getWrong(int wrong) {
      this.wrong = wrong;
    }

  }

}
