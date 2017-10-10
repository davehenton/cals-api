package gov.ca.cwds.cals.web.rest.rfa.helper;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResourceTest.OTHER_ADULTS_FIXTURE;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResourceTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class OtherAdultsHelper {

  private RestClientTestRule clientTestRule;

  public OtherAdultsHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public List<OtherAdultDTO> createOtherAdults(Long formId, ApplicantDTO relativeApplicant)
      throws Exception {
    List<OtherAdultDTO> otherAdultsDTOs = new ArrayList<>(2);
    for (int i = 0; i < 2; i++) {
      OtherAdultDTO otherAdultDTO = getOtherAdultDTO(relativeApplicant);
      otherAdultDTO.setFirstName(otherAdultDTO.getFirstName() + i);
      otherAdultDTO.setLastName(otherAdultDTO.getLastName() + i);
      otherAdultsDTOs.add(postOtherAdult(formId, otherAdultDTO));
    }
    return otherAdultsDTOs;
  }

  public OtherAdultDTO getOtherAdultDTO(ApplicantDTO relativeApplicant) throws IOException {
    OtherAdultDTO otherAdultDTO = clientTestRule.getMapper()
        .readValue(OTHER_ADULTS_FIXTURE,
            OtherAdultDTO.class);
    // Assume that we have only one relationship object
    otherAdultDTO.getRelationshipToApplicants().get(0).setApplicantId(relativeApplicant.getId());
    return otherAdultDTO;
  }

  public OtherAdultDTO postOtherAdult(Long formId, OtherAdultDTO otherAdult) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_OTHER_ADULTS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(otherAdult, MediaType.APPLICATION_JSON_TYPE), OtherAdultDTO.class);
  }

}
