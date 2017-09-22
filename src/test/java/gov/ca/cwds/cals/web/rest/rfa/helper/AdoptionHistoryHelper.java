package gov.ca.cwds.cals.web.rest.rfa.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class AdoptionHistoryHelper {

  private RestClientTestRule clientTestRule;

  public AdoptionHistoryHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public AdoptionHistoryDTO putAdoptionHistory(long formId, AdoptionHistoryDTO adoptionHistoryDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_ADOPTION_HISTORY);
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(adoptionHistoryDTO, MediaType.APPLICATION_JSON_TYPE),
        AdoptionHistoryDTO.class);
  }


  public AdoptionHistoryDTO getAdoptionHistory(String fixturePath) throws IOException {
    return clientTestRule.getMapper()
        .readValue(fixture(fixturePath), AdoptionHistoryDTO.class);
  }

  public AdoptionHistoryDTO getAdoptionHistory(Long formId) throws IOException {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_ADOPTION_HISTORY);
    return target.request(MediaType.APPLICATION_JSON).get(AdoptionHistoryDTO.class);
  }

}
