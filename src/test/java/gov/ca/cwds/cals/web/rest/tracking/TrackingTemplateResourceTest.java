package gov.ca.cwds.cals.web.rest.tracking;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
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
public class TrackingTemplateResourceTest extends BaseCalsApiIntegrationTest {

  private final Logger LOG = LoggerFactory.getLogger(TrackingTemplateResourceTest.class);

  private static final String TEMPLATE_FIXTURE = FixtureHelpers
      .fixture("fixtures/tracking/tracking-template.json");

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createTrackingTemplateTest() throws Exception {
    TrackingTemplate trackingTemplate = createTrackingTemplate();
    Assert.assertNotNull(trackingTemplate);

    // clean created template
    deleteTemplate(trackingTemplate.getId());
  }

  @Test
  public void updateTrackingTemplateTest() throws Exception {
    TrackingTemplate trackingTemplate = createTrackingTemplate();
    String newTemplateType = "NewTemplateType";
    trackingTemplate.setTemplateType(newTemplateType);

    TrackingTemplate response = clientTestRule.target(API.TRACKING_TEMPLATES +
        "/" + trackingTemplate.getId())
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(trackingTemplate, MediaType.APPLICATION_JSON_TYPE),
            TrackingTemplate.class);

    TrackingTemplate template = getTemplate(trackingTemplate.getId());

    Assert.assertEquals(newTemplateType, template.getTemplateType());

    // clean created template
    deleteTemplate(trackingTemplate.getId());
  }

  @Test
  public void getTrackingTemplateTest() throws Exception {
    TrackingTemplate trackingTemplate = createTrackingTemplate();
    Assert.assertNotNull(getTemplate(trackingTemplate.getId()));
    // clean created template
    deleteTemplate(trackingTemplate.getId());
  }

  @Test
  public void deleteTrackingTemplateTest() throws Exception {
    TrackingTemplate trackingTemplate = createTrackingTemplate();

    Assert.assertNotNull(getTemplate(trackingTemplate.getId()));

    deleteTemplate(trackingTemplate.getId());

    Assert.assertNull(getTemplate(trackingTemplate.getId()));
  }

  private TrackingTemplate createTrackingTemplate() throws Exception {
    TrackingTemplate request = clientTestRule.getMapper()
        .readValue(TEMPLATE_FIXTURE, TrackingTemplate.class);
    return clientTestRule.target(API.TRACKING_TEMPLATES)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), TrackingTemplate.class);
  }

  private TrackingTemplate getTemplate(Long id) {
    TrackingTemplate trackingTemplate = null;
    try {
      trackingTemplate = clientTestRule.target(API.TRACKING_TEMPLATES + "/" + id)
          .request(MediaType.APPLICATION_JSON)
          .get(TrackingTemplate.class);
    } catch (NotFoundException nfe) {
      LOG.info(nfe.getMessage());
    }

    return trackingTemplate;
  }

  private void deleteTemplate(Long id) {
    clientTestRule.target(API.TRACKING_TEMPLATES + "/" + id)
        .request(MediaType.APPLICATION_JSON)
        .delete(TrackingTemplate.class);
  }


}