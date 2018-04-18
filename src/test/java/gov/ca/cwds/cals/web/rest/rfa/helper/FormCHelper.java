package gov.ca.cwds.cals.web.rest.rfa.helper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;

/**
 * @author CWDS CALS API Team
 */

public class FormCHelper {

  private RestClientTestRule clientTestRule;

  public FormCHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }


  public RFA1cFormDTO postRfa1cForm(Long formId, RFA1cFormDTO rfa1cFormDTO) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1C_FORMS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(rfa1cFormDTO, MediaType.APPLICATION_JSON_TYPE), RFA1cFormDTO.class);
  }

}
