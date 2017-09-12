package gov.ca.cwds.cals.web.rest.rfa.changed;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.changed.ChangedFacilityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import java.time.LocalDateTime;
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
    clientTestRule.getMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    CollectionDTO<ChangedFacilityDTO> changedFacilities =
        getChangedFacilitiesAfter(LocalDateTime.now(), LocalDateTime.now());
    assertTrue(changedFacilities.getCollection().size() > 0);
  }

  private CollectionDTO<ChangedFacilityDTO> getChangedFacilitiesAfter(LocalDateTime dateAfter,
      LocalDateTime lisDateAfter) {
    WebTarget target =
        clientTestRule.target(
            PATH_CHANGED_FACILITY
                + "/?"
                + DATE_AFTER
                + "=1970-01-01&"
                + LIS_DATE_AFTER
                + "=1970-01-01");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(new GenericType<CollectionDTO<ChangedFacilityDTO>>() {
    });

  }


}
