package gov.ca.cwds.cals.web.rest.rfa.helper;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResourceTest.MINOR_CHILDREN_FIXTURE;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class MinorChildHelper {

  private RestClientTestRule clientTestRule;

  public MinorChildHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public MinorChildDTO postMinorChild(Long formId, MinorChildDTO minorChild) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_MINOR_CHILDREN);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(minorChild, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);
  }

  public MinorChildDTO putMinorChild(Long formId, MinorChildDTO minorChild) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_MINOR_CHILDREN + "/" + minorChild.getId());
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(minorChild, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);
  }

  public List<MinorChildDTO> createMinorChildren(Long formId, ApplicantDTO reletiveApplicant)
      throws Exception {
    List<MinorChildDTO> minorChildDTOs = new ArrayList<>(2);
    for (int i = 0; i < 2; i++) {
      MinorChildDTO minorChildDTO = buildNewMinorChildDTO(reletiveApplicant);
      minorChildDTO.setOtherRelativeFirstName(minorChildDTO.getOtherRelativeFirstName() + i);
      minorChildDTO.setOtherRelativeLastName(minorChildDTO.getOtherRelativeLastName() + i);
      minorChildDTOs.add(postMinorChild(formId, minorChildDTO));
    }
    return minorChildDTOs;
  }

  public MinorChildDTO buildNewMinorChildDTO(ApplicantDTO reletiveApplicant) throws IOException {
    MinorChildDTO minorChildDTO = clientTestRule.getMapper().readValue(MINOR_CHILDREN_FIXTURE, MinorChildDTO.class);
    // Assume that we have only one relationship object
    minorChildDTO.getRelationshipToApplicants().get(0).setApplicantId(reletiveApplicant.getId());
    return minorChildDTO;
  }

}
