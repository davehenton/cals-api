package gov.ca.cwds.cals.web.rest.rfa.helper;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1bResourceTest.RFA1B_FORM_FIXTURE;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import javax.ws.rs.client.Entity;
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
        .readValue(RFA1B_FORM_FIXTURE, RFA1bFormDTO.class);
  }

  public RFA1bFormDTO postRfa1bForm(Long formId, Long applicantId, RFA1bFormDTO rfa1bFormDTO) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1B_FORMS + "/" + API.RFA_1A_APPLICANTS + "/" + applicantId)
         .request(MediaType.APPLICATION_JSON)
         .post(Entity.entity(rfa1bFormDTO, MediaType.APPLICATION_JSON_TYPE), RFA1bFormDTO.class);
  }

}
