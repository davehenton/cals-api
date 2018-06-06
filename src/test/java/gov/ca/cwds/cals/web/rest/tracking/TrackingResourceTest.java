package gov.ca.cwds.cals.web.rest.tracking;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2 Team
 */
public class TrackingResourceTest extends BaseCalsApiIntegrationTest {

  private final Logger LOG = LoggerFactory.getLogger(TrackingResourceTest.class);

  private static final String TRACKING_FIXTURE = FixtureHelpers
      .fixture("fixtures/tracking/tracking.json");

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createTrackingTest() throws Exception {
    Tracking tracking = createTracking();
    Assert.assertNotNull(tracking);

    // clean created template
    deleteTracking(tracking.getId());
  }

  @Test
  public void updateTrackingTest() throws Exception {
    Tracking tracking = createTracking();
    String newFacilityName = "New Facility Name";
    tracking.setFacilityName(newFacilityName);

    Tracking response = clientTestRule.target(API.TRACKINGS +
        "/" + tracking.getId())
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(tracking, MediaType.APPLICATION_JSON_TYPE),
            Tracking.class);

    Tracking received = getTracking(tracking.getId());

    Assert.assertEquals(newFacilityName, received.getFacilityName());

    // clean created template
    deleteTracking(tracking.getId());
  }

  @Test
  public void getTrackingTest() throws Exception {
    Tracking tracking = createTracking();
    Assert.assertNotNull(getTracking(tracking.getId()));
    // clean created template
    deleteTracking(tracking.getId());
  }

  @Test
  public void deleteTrackingTest() throws Exception {
    Tracking tracking = createTracking();

    Assert.assertNotNull(getTracking(tracking.getId()));

    deleteTracking(tracking.getId());

    Assert.assertNull(getTracking(tracking.getId()));
  }

  private Tracking createTracking() throws Exception {
    Tracking request = clientTestRule.getMapper()
        .readValue(TRACKING_FIXTURE, Tracking.class);
    return clientTestRule.target(API.TRACKINGS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), Tracking.class);
  }

  private Tracking getTracking(Long id) {
    Tracking tracking = null;
    try {
      tracking = clientTestRule.target(API.TRACKINGS + "/" + id)
          .request(MediaType.APPLICATION_JSON)
          .get(Tracking.class);
    } catch (NotFoundException nfe) {
      LOG.info(nfe.getMessage());
    }
    return tracking;
  }

  private void deleteTracking(Long id) {
    clientTestRule.target(API.TRACKINGS + "/" + id)
        .request(MediaType.APPLICATION_JSON)
        .delete(Tracking.class);
  }
}