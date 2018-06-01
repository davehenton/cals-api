package gov.ca.cwds.cals.web.rest.facility;

import static gov.ca.cwds.cals.Constants.API.CHILDREN;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PathParams.FACILITY_ID;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDto;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.helper.RfaHelpersHolder;
import gov.ca.cwds.cals.web.rest.rfa.helper.RfaSubmitHelper;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildResourceTest extends BaseCalsApiIntegrationTest {

  public static final String SECURITY_CLIENT_PRINCIPAL_SAME_COUNTY_SEALED_JSON =
      "security/client/principal-same-county-sealed.json";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
    setUpCmsRs();
    setUpCalsns();
  }

  private Invocation.Builder prepareInvocation(String facilityId) {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", facilityId);
    WebTarget target = clientTestRule.target(pathInfo);
    return target.request(MediaType.APPLICATION_JSON);
  }

  private FacilityChildrenDto getChildrenByFacilityId(String facilityId) {
    return prepareInvocation(facilityId).get(FacilityChildrenDto.class);
  }

  private Response getChildrenByFacilityIdResponse(String facilityId) {
    return prepareInvocation(facilityId).get();
  }

  @Test
  public void testGetChildrenFromCWSFacility() throws Exception {
    assertTrue(getChildrenByFacilityId("4K1f8zu0Mc").getChildren().size() > 0);
  }

  @Test
  public void testGetChildrenFromUnlicensedCWSFacility() throws Exception {
    assertEquals(2, getChildrenByFacilityId("8ivb6Yc03k").getChildren().size());
  }

  @Test
  public void testGetChildrenFromLISFacility() throws Exception {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "577000449");
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityChildrenDto facilityChildDTO = invocation.get(FacilityChildrenDto.class);

    // age of child is calculated by current date, so there is workaround for test
    for (FacilityChildDTO childDTO : facilityChildDTO.getChildren()) {
      if (childDTO.getId().equals("AyRhLiI07n")) {
        childDTO.getPerson().setAge(16); // 17 is age in fixture
      }
      if (childDTO.getId().equals("AKgQavu07n")) {
        childDTO.getPerson().setAge(15); // 16 is age in fixture
      }
    }

    String fixture = fixture("fixtures/facility/facility-children-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityChildDTO));
  }

  @Test
  public void testGetFacilityChildrenWithAssignedWorkerPresent() throws Exception {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN + "?"
        + TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE + '='
        + SECURITY_CLIENT_PRINCIPAL_SAME_COUNTY_SEALED_JSON;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "412252222");
    JsonIdentityAuthParams params = new JsonIdentityAuthParams(
        fixture(SECURITY_CLIENT_PRINCIPAL_SAME_COUNTY_SEALED_JSON));
    WebTarget target = clientTestRule.target(pathInfo, params);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityChildrenDto facilityChildDTO = invocation.get(FacilityChildrenDto.class);

    String fixture = fixture(
        "fixtures/facility/facility-children-response-assigned-worker-present.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityChildDTO));
  }

  @Test
  public void testGetFacilityChild() throws Exception {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "577000449") + "/AyRhLiI07n";
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

    // age of child is calculated by current date, so there is workaround for test
    facilityChildDTO.getPerson().setAge(16); // 17 is age in fixture

    String fixture = fixture("fixtures/facility/facility-child-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityChildDTO));
  }

  @Test
  public void testFacilityChildrenWrongId() throws Exception {
    String wrongId = "-1";
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", wrongId);
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    try {
      invocation.get(FacilityChildrenDto.class);
      fail("Correct response is not expected");
    } catch (NotFoundException e) {
      assertThat("HTTP 404 Not Found").isEqualTo(e.getMessage());
    }
  }

  @Test
  public void testFacilityChildWrongId() throws Exception {
    String wrongChildId = "-1";
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "577000449") + "/" + wrongChildId;
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    try {
      invocation.get(FacilityChildrenDto.class);
      fail("Correct response is not expected");
    } catch (NotFoundException e) {
      assertThat("HTTP 404 Not Found").isEqualTo(e.getMessage());
    }
  }

  @Test
  public void testFacilityWithNoChildren() throws Exception {
    RfaSubmitHelper rfaSubmitHelper = new RfaSubmitHelper(new RfaHelpersHolder(clientTestRule));
    RFA1aFormDTO form = rfaSubmitHelper
        .submitApplication(SECURITY_CLIENT_PRINCIPAL_SAME_COUNTY_SEALED_JSON);
    assertEquals(404,
        getChildrenByFacilityIdResponse(form.getPlacementHomeId()).getStatus());
  }

}

