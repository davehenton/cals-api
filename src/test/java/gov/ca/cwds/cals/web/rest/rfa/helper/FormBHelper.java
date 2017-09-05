package gov.ca.cwds.cals.web.rest.rfa.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.RFA1bResourceTest;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class FormBHelper {

  private RestClientTestRule clientTestRule;

  public FormBHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public RFA1bFormDTO getRfa1bForm() throws IOException {
    return clientTestRule.getMapper()
        .readValue(fixture(RFA1bResourceTest.RFA1B_FORM_FIXTURE_PATH), RFA1bFormDTO.class);
  }

  public RFA1bFormDTO postRfa1bForm(Long formId, Long applicantId, RFA1bFormDTO rfa1bFormDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/"
                + API.RFA_1B_FORMS + "/" + API.RFA_1A_APPLICANTS + "/" + applicantId);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(rfa1bFormDTO, MediaType.APPLICATION_JSON_TYPE), RFA1bFormDTO.class);
  }

}
