package gov.ca.cwds.cals.web.rest.facility;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ComplaintsDTO;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

public class FacilityComplaintResourceTest extends BaseCalsApiIntegrationTest {

  private static final String FACILITY_ID = "100001317";
  private static final String ALPHANUMERICAL_FACILITY_ID = "EEwv7B3197";
  private static final String WRONG_FACILITY_ID = "-1";
  private static final String COMPLAINT_ID = "24-CR-241214-20050106141240";
  private static final String WRONG_COMPLAINT_ID = "-1";

  public static final String PRINCIPAL_NO_PRIVILAGES_JSON = "security/principal-no-privilages.json";
  public static final String PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON = "security/principal-priv-CWS_Case_Management_System.json";
  public static final String PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON = "security/principal-priv-Resource_Management.json";


  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpFas();
  }

  @Test
  public void getAllFacilityComplaintsTest() throws Exception {
    WebTarget target = clientTestRule
        .target(FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    ComplaintsDTO complaintsDTO = invocation.get(ComplaintsDTO.class);

    String fixture = fixture("fixtures/facility/complaints-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(complaintsDTO));
  }

  @Test
  public void getAllFacilityComplaintsAlphanumericalIdTest() {
    WebTarget target = clientTestRule
        .target(FACILITIES + "/" + ALPHANUMERICAL_FACILITY_ID + "/" + Constants.API.COMPLAINTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getAllFacilityComplaintsWrongFacilityIdTest() throws JsonProcessingException {
    WebTarget target = clientTestRule
        .target(FACILITIES + "/" + WRONG_FACILITY_ID + "/" + Constants.API.COMPLAINTS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getFacilityComplaintTest() throws Exception {
    WebTarget target = clientTestRule
        .target(
            FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS + "/" + COMPLAINT_ID);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    ComplaintDTO complaintDTO = invocation.get(ComplaintDTO.class);

    String fixture = fixture("fixtures/facility/complaint-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(complaintDTO));
  }

  @Test
  public void getFacilityWrongComplaintTest() throws Exception {
    WebTarget target = clientTestRule
        .target(FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS + "/"
            + WRONG_COMPLAINT_ID);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();

    assertEquals(404, response.getStatus());
  }

  @Test
  public void getFacilityComplaintWithNoPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintResponse(PRINCIPAL_NO_PRIVILAGES_JSON);
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void getFacilityComplaintWithCWSCaseManagementSystemPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintResponse(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void getFacilityComplaintWithResourceManagementPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintResponse(PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  private Response getFacilityComplaintResponse(String principalJsonPath) {
    String pathInfo = FACILITIES + "/" + FACILITY_ID + "/" + API.COMPLAINTS + "/" + COMPLAINT_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + principalJsonPath;
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(fixture(principalJsonPath));
    WebTarget target = clientTestRule.target(pathInfo, params);
    return target.request(MediaType.APPLICATION_JSON).get();
  }

  @Test
  public void getFacilityComplaintsWithNoPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintsResponse(PRINCIPAL_NO_PRIVILAGES_JSON);
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void getFacilityComplaintsWithCWSCaseManagementSystemPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintsResponse(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void getFacilityComplaintsWithResourceManagementPrivilegesTest() throws Exception {
    Response response = getFacilityComplaintsResponse(PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  private Response getFacilityComplaintsResponse(String principalJsonPath) {
    String pathInfo = FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + principalJsonPath;
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(fixture(principalJsonPath));
    WebTarget target = clientTestRule.target(pathInfo, params);
    return target.request(MediaType.APPLICATION_JSON).get();
  }

}