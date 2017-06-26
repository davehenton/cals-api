package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */
public final class RFAHelper {

  private RFAHelper() {
  }

  public static RFA1aForm createForm(RestClientTestRule clientTestRule) {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aForm rfaForm = invocation.post(null, RFA1aForm.class);
    assertNotNull(rfaForm);
    assertNotNull(rfaForm.getId());
    return rfaForm;
  }

}
