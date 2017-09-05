package gov.ca.cwds.cals.web.rest.rfa.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
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
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RESIDENCE);
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(residenceDTO, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);

  }

  public ResidenceDTO getResidenceDTO() throws IOException {
    String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-residence-request.json";
    return clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ResidenceDTO.class);
  }

}
