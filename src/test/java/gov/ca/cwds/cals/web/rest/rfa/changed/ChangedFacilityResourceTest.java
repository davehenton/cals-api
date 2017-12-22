package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.changed.ChangedFacilityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javax.swing.text.DateFormatter;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-2
 */
public class ChangedFacilityResourceTest extends BaseCalsApiIntegrationTest {

  static final String PATH_CHANGED_FACILITY = "changed-" + FACILITIES;
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
    CollectionDTO<TestChangedFacilityDTO> changedFacilities =
        getChangedFacilitiesAfter("2017-12-19");
    String fixture = fixture("fixtures/changed-facility-service.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(changedFacilities));

  }

  private CollectionDTO<TestChangedFacilityDTO> getChangedFacilitiesAfter(String  dateAfter) {
    WebTarget target =
        clientTestRule.target(
            PATH_CHANGED_FACILITY
                + "/?"
                + DATE_AFTER
                + "=" + dateAfter + "&"
                + LIS_DATE_AFTER
                + "=" + dateAfter);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(new GenericType<CollectionDTO<TestChangedFacilityDTO>>() {
    });

  }


}
