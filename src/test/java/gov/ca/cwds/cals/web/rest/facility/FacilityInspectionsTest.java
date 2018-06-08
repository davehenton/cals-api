package gov.ca.cwds.cals.web.rest.facility;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionsDTO;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionsTest extends BaseCalsApiIntegrationTest {

  private static final String INSPECTIONS_RESPONSE_JSON = "fixtures/facility/facility-inspections-response.json";
  private static final String INSPECTION_RESPONSE_JSON = "fixtures/facility/facility-inspection-response.json";

  private static final Integer FACILITY_NUMBER = 100001732;
  private static final String INSPECTION_ID = "24-CR-CCHR-6T5TUV-20060829145639";

  private static final String PRINCIPAL_NO_PRIVILAGES_JSON = "security/principal-no-privilages.json";
  private static final String PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON = "security/principal-priv-CWS_Case_Management_System.json";
  private static final String PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON = "security/principal-priv-Resource_Management.json";

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpFas();
    }

    @Test
    public void getFacilityInspectionsResponseTest() throws Exception {
        WebTarget target = clientTestRule.target(FACILITIES + "/"
                + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionsDTO inspectionsDTO = invocation.get(FacilityInspectionsDTO.class);

        String fixture = fixture(INSPECTIONS_RESPONSE_JSON);
        assertEqualsResponse(fixture, transformDTOtoJSON(inspectionsDTO));
    }


    @Test
    public void getInspectionResponseTest() throws Exception {
        WebTarget target = clientTestRule.target(FACILITIES + "/"
                + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS + "/" + INSPECTION_ID);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionDTO inspectionDTO = invocation.get(FacilityInspectionDTO.class);

        String fixture = fixture(INSPECTION_RESPONSE_JSON);
        assertEqualsResponse(fixture, transformDTOtoJSON(inspectionDTO));
    }


    @Test
    public void inspectionsNotFoundTest() {
        WebTarget target = clientTestRule.target(FACILITIES + "/" + 0 + "/" + Constants.API.INSPECTIONS);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        Response response = invocation.get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void inspectionNotFoundTest() {
        WebTarget target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS + "/" + 0);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        Response response = invocation.get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getAllFacilityInspectionsTest() {
        WebTarget target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionsDTO inspectionsDTO = invocation.get(FacilityInspectionsDTO.class);
        assertNotNull(inspectionsDTO);
        assertNotNull(inspectionsDTO.getInspections());
        assertEquals(1, inspectionsDTO.getInspections().size());

        FacilityInspectionDTO inspectionDTO0 = inspectionsDTO.getInspections().get(0);
        String inspectionId = inspectionDTO0.getId();

        target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS + "/" + INSPECTION_ID);
        invocation = target.request(MediaType.APPLICATION_JSON);
        final FacilityInspectionDTO inspectionDTO = invocation.get(FacilityInspectionDTO.class);

        // Expected only 1 deficiency object
        assertEquals(2, inspectionDTO.getDeficiencies().size());

        inspectionsDTO.getInspections().forEach(inspection -> {
            if (INSPECTION_ID.equals(inspection.getId())) {
                assertEquals(inspection, inspectionDTO);
            }
        });
    }

  @Test
  public void getFacilityInspectionWithNoPrivilegesTest() {
    Response response = getFacilityInspectionResponse(PRINCIPAL_NO_PRIVILAGES_JSON);
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void getFacilityInspectionWithCWSCaseManagementSystemPrivilegesTest() {
    Response response = getFacilityInspectionResponse(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void getFacilityInspectionWithResourceManagementPrivilegesTest() {
    Response response = getFacilityInspectionResponse(PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  private Response getFacilityInspectionResponse(String principalJsonPath) {
    String pathInfo = FACILITIES + "/" + FACILITY_NUMBER + "/" + API.INSPECTIONS + "/" + INSPECTION_ID
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + principalJsonPath;
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(fixture(principalJsonPath));
    WebTarget target = clientTestRule.target(pathInfo, params);
    return target.request(MediaType.APPLICATION_JSON).get();
  }

  @Test
  public void getFacilityInspectionsWithNoPrivilegesTest() {
    Response response = getFacilityInspectionsResponse(PRINCIPAL_NO_PRIVILAGES_JSON);
    assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
  }

  @Test
  public void getFacilityInspectionsWithCWSCaseManagementSystemPrivilegesTest() {
    Response response = getFacilityInspectionsResponse(PRINCIPAL_PRIV_CWS_CASE_MANAGEMENT_SYSTEM_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  @Test
  public void getFacilityInspectionsWithResourceManagementPrivilegesTest() {
    Response response = getFacilityInspectionsResponse(PRINCIPAL_PRIV_RESOURCE_MANAGEMENT_JSON);
    assertEquals(HttpStatus.SC_OK, response.getStatus());
  }

  private Response getFacilityInspectionsResponse(String principalJsonPath) {
    String pathInfo = FACILITIES + "/" + FACILITY_NUMBER + "/" + API.INSPECTIONS
        + '?' + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + principalJsonPath;
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(fixture(principalJsonPath));
    WebTarget target = clientTestRule.target(pathInfo, params);
    return target.request(MediaType.APPLICATION_JSON).get();
  }


}
