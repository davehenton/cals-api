package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2
 */
public class ChangedFacilityResourceTest extends BaseCalsApiIntegrationTest {

  static final String PATH_CHANGED_FACILITY = "changed-" + FACILITIES;
  static final String PATH_INITIAL = "initial";
  static final String PATH_INITIAL_FACILITY_LOAD = PATH_CHANGED_FACILITY + "/" + PATH_INITIAL;
  static final String DATE_AFTER = "dateAfter";
  static final String LIS_DATE_AFTER = "lisDateAfter";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
    setUpCmsrs();
    setUpFas();
    setUpLis();
  }

  @Test
  public void getChangedFacilitiesTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    WebTarget target = clientTestRule.target(PATH_CHANGED_FACILITY + "/?" + DATE_AFTER + "=2017-12-19&"
            + LIS_DATE_AFTER + "=2017-12-19");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<TestChangedFacilityDTO> changedFacilities = invocation.get(new GenericType<CollectionDTO<TestChangedFacilityDTO>>() {
    });
    Assert.assertTrue(changedFacilities.getCollection().size() > 0);
    //assertResponse(changedFacilities);
  }

  @Test
  public void initialLoadTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    WebTarget target = clientTestRule.target(PATH_INITIAL_FACILITY_LOAD + "/?" + LIS_DATE_AFTER + "=2017-12-19");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<TestChangedFacilityDTO> changedFacilities = invocation.get(new GenericType<CollectionDTO<TestChangedFacilityDTO>>() {
    });
    Assert.assertTrue(changedFacilities.getCollection().size() > 0);

    //assertResponse(changedFacilities);
  }

  private void assertResponse(CollectionDTO<TestChangedFacilityDTO> changedFacilities) throws Exception {
    String fixture = fixture("fixtures/changed-facility-service.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(changedFacilities));
  }

}
