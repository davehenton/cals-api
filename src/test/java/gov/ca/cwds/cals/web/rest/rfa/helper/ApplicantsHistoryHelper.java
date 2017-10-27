package gov.ca.cwds.cals.web.rest.rfa.helper;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import io.dropwizard.testing.FixtureHelpers;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantsHistoryHelper {

  private RestClientTestRule clientTestRule;

  public ApplicantsHistoryHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public ApplicantsHistoryDTO putApplicantsHistory(
      long formId, ApplicantsHistoryDTO applicantsHistoryDTO) {
    WebTarget target =
        clientTestRule
            .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS_HISTORY);
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(applicantsHistoryDTO, MediaType.APPLICATION_JSON_TYPE),
        ApplicantsHistoryDTO.class);
  }

  public ApplicantsHistoryDTO getApplicantHistoryDTO(String fixture) throws IOException {
    return clientTestRule.getMapper()
        .readValue(FixtureHelpers.fixture(fixture), ApplicantsHistoryDTO.class);
  }

}
