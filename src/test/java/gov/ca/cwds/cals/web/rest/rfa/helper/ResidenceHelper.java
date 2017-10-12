package gov.ca.cwds.cals.web.rest.rfa.helper;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResourceTest;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class ResidenceHelper {

  private RestClientTestRule clientTestRule;

  public ResidenceHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public ResidenceDTO putResidence(long formId, ResidenceDTO residenceDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_RESIDENCE);
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(residenceDTO, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);

  }

  public ResidenceDTO getResidenceDTO() throws IOException {
    return clientTestRule.getMapper()
        .readValue(RFA1aResidenceResourceTest.RESIDENCE_FIXTURE, ResidenceDTO.class);
  }

}
