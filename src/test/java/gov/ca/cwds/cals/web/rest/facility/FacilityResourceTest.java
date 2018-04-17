package gov.ca.cwds.cals.web.rest.facility;

import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

  private static final String LICENSE_NUMBER = "100004470";
  private static final String WRONG_LICENSE_NUMBER = "-1";

  private static final String FACILITY_ID = "E6tloOO0Ql";
  private static final String ADOPTIONS_ONLY_FACILITY_ID = "BQcJtPm75C";
  private static final String FACILITY_FROM_LIS_ID = "100004582";
  public static final String PRINCIPAL_NO_PRIVILAGES_JSON = "security/principal-no-privilages.json";
  public static final String PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON = "security/principal-priv-CWS_Case_Management_System.json";
  public static final String PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON = "security/principal-priv-Resource_Management.json";
  public static final String PRINCIPAL_PRIV_ADOPTIONS_JSON = "security/principal-priv-Adoptions.json";
  public static final String PRINCIPAL_PRIV_ADOPTIONS_ONLY_JSON = "security/principal-priv-Adoptions-only.json";
  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpLis();

    setUpCms();

    setUpCmsRs();

    setUpFas();
  }

  @Test
  public void testGetFacilityByLicenseNumber() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + LICENSE_NUMBER);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

    String fixture = fixture("fixtures/facility/facility-by-license-number-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityDTO));
  }

  @Test
  public void testGetFacilityWithWrongLicenseNumber() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + WRONG_LICENSE_NUMBER);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testGetFacilityByWrongCWSFacilityId() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + "AAAAAA");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void testGetFacilityByFacilityId() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + FACILITY_ID);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

    String fixture = fixture("fixtures/facility/facility-by-id-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityDTO));
  }

  @Test
  public void testGetFacilityWithLastCapacityChanged() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + FACILITY_FROM_LIS_ID);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);
    assertEquals(2006, facilityDTO.getCapacityLastChanged().getYear());
    assertEquals(10, facilityDTO.getCapacityLastChanged().getMonthValue());
    assertEquals(13, facilityDTO.getCapacityLastChanged().getDayOfMonth());
  }


  @Test
  public void testGetFacilityByFacilityIdWithZeroInCountyAndFacilityType() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/BITrEV40OU");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

    String fixture = fixture(
        "fixtures/facility/facility-by-id-with-zero-in-county-and-facility-type-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityDTO));
  }

  @Test
  public void testUnauthorizedCallGetFacilityByFacilityId() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_NO_PRIVILAGES_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_NO_PRIVILAGES_JSON));
    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void testCallGetFacilityByFacilityIdAdoptionsOnly() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + ADOPTIONS_ONLY_FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON));
    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void testCallGetFacilityByFacilityIdAdoptionsOnlyWithAdoptionPriv() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + ADOPTIONS_ONLY_FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_PRIV_ADOPTIONS_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_PRIV_ADOPTIONS_JSON));
    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void testCallGetFacilityByFacilityIdAdoptionsOnlyWithAdoptionPrivOnly() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + ADOPTIONS_ONLY_FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_PRIV_ADOPTIONS_ONLY_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_PRIV_ADOPTIONS_ONLY_JSON));
    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void testCallGetFacilityByFacilityIdByPrivCWSCaseManagementSystem() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON));

    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void testCallGetFacilityByFacilityIdByPrivResourceManagement() throws Exception {
    String targetString = Constants.API.FACILITIES + "/" + FACILITY_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON;

    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON));
    
    WebTarget target = clientTestRule.target(targetString, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

}