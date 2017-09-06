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

public class DraftApplicationsTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void draftApplicationsTest() throws Exception {
    int previousSize = formAHelper.getRFA1aForms().getCollection().size();
    statusHelper.assertDraft(createJunkForm().getId());
    assertEquals(previousSize, formAHelper.getRFA1aForms().getCollection().size());
    statusHelper.assertInProgress(createInProgressForm().getId());
    assertEquals(previousSize + 1, formAHelper.getRFA1aForms().getCollection().size());
  }

  private RFA1aFormDTO createInProgressForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    RFA1aFormDTO rfaForm =
        target
            .request(MediaType.APPLICATION_JSON)
            .post(
                Entity.entity(new RFA1aFormDTO(), MediaType.APPLICATION_JSON_TYPE),
                RFA1aFormDTO.class);
    applicantHelper.postApplicant(rfaForm.getId(), new ApplicantDTO());
    return rfaForm;
  }

  private RFA1aFormDTO createJunkForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(new RFA1aFormDTO(), MediaType.APPLICATION_JSON_TYPE),
        RFA1aFormDTO.class);
  }

}
