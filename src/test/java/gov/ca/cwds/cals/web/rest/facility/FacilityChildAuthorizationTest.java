package gov.ca.cwds.cals.web.rest.facility;

import static gov.ca.cwds.cals.Constants.API.CHILDREN;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PathParams.FACILITY_ID;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDto;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildAuthorizationTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
    setUpCmsRs();
  }

  @Test(expected = NotFoundException.class)
  public void testStateSealed() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  @Test
  public void testSameCountySealed() throws Exception {
    getFacilityChildren("security/client/principal-same-county-sealed.json",
        "fixtures/facility/authorization/get-children-sealed.json");
  }

  @Test(expected = NotFoundException.class)
  public void testDifferentCountySealed() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  @Test(expected = NotFoundException.class)
  public void testCaseWorkerOnly() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  @Test(expected = NotFoundException.class)
  public void testStateSensitive() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  @Test(expected = NotFoundException.class)
  public void testDifferentCountySensitive() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  @Test(expected = NotFoundException.class)
  public void testSameCountySensitive() throws Exception {
    getFacilityChildrenDTO("security/client/principal-state-sealed.json");
  }

  private void getFacilityChildren(String principal, String childrenFixture) throws Exception {
    FacilityChildrenDto facilityChildDTO = getFacilityChildrenDTO(principal);

    String fixture = fixture(
        childrenFixture);
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityChildDTO));
  }

  private FacilityChildrenDto getFacilityChildrenDTO(String principal) {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN + "?"
        + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + principal;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "412252222");
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(fixture(principal));
    WebTarget target = clientTestRule.target(pathInfo, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(FacilityChildrenDto.class);
  }


}

