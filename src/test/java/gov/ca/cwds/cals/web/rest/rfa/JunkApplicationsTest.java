package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class JunkApplicationsTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void junkApplicationsTest() throws Exception {
    int previousSize = rfaHelper.getRFA1aForms().getCollection().size();
    rfaHelper.assertDraft(createJunkForm().getId());
    assertEquals(previousSize, rfaHelper.getRFA1aForms().getCollection().size());
    rfaHelper.assertInProgress(createInProgressForm().getId());
    assertEquals(previousSize + 1, rfaHelper.getRFA1aForms().getCollection().size());
  }

  private RFA1aFormDTO createInProgressForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    RFA1aFormDTO rfaForm =
        target
            .request(MediaType.APPLICATION_JSON)
            .post(
                Entity.entity(new RFA1aFormDTO(), MediaType.APPLICATION_JSON_TYPE),
                RFA1aFormDTO.class);
    rfaHelper.postApplicant(rfaForm.getId(), new ApplicantDTO());
    return rfaForm;
  }

  private RFA1aFormDTO createJunkForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(new RFA1aFormDTO(), MediaType.APPLICATION_JSON_TYPE),
        RFA1aFormDTO.class);
  }

}
