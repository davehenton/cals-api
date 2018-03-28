package gov.ca.cwds.cals.web.rest;

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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
  }

  private FacilityChildrenDto getChiildrenByFacilityId(String facilityId) {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", facilityId);
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(FacilityChildrenDto.class);
  }

  @Test
  public void testGetChildrenFromCWSFacility() throws Exception {
    assertTrue(getChiildrenByFacilityId("4K1f8zu0Mc").getChildren().size() > 0);
  }

  @Test
  public void testGetChildrenFromUnlicensedCWSFacility() throws Exception {
    assertEquals(2, getChiildrenByFacilityId("8ivb6Yc03k").getChildren().size());
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

    String fixture = fixture("fixtures/facility-children-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(facilityChildDTO));
  }

  @Test
  public void testGetFacilityChildrenWithAssignedWorkerPresent() throws Exception {
    String pathInfo = FACILITIES + "/{" + FACILITY_ID + "}/" + CHILDREN;
    pathInfo = pathInfo.replace("{" + FACILITY_ID + "}", "412252222");
    WebTarget target = clientTestRule.target(pathInfo);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    FacilityChildrenDto facilityChildDTO = invocation.get(FacilityChildrenDto.class);

    String fixture = fixture("fixtures/facility-children-response-assigned-worker-present.json");
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

    String fixture = fixture("fixtures/facility-child-response.json");
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
}

