package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */
public class RFAHelper {

  private RestClientTestRule clientTestRule;

  public RFAHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public RFA1aFormDTO createForm() {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);

    RFA1aFormDTO rfaFormDTOBefore = new RFA1aFormDTO();
    CountyType county = new CountyType();
    county.setId(1L);
    county.setValue("Alameda");
    rfaFormDTOBefore.setApplicationCounty(county);
    rfaFormDTOBefore.setInitialApplication(true);
    rfaFormDTOBefore.setOtherType(true);
    rfaFormDTOBefore.setOtherTypeDescription("otherDescription");
    RFA1aFormDTO rfaFormDTOAfter =
        target
            .request(MediaType.APPLICATION_JSON)
            .post(
                Entity.entity(rfaFormDTOBefore, MediaType.APPLICATION_JSON_TYPE),
                RFA1aFormDTO.class);
    assertNotNull(rfaFormDTOAfter);
    assertNotNull(rfaFormDTOAfter.getId());
    assertTrue(rfaFormDTOAfter.isInitialApplication());
    assertTrue(rfaFormDTOAfter.isOtherType());
    assertEquals("otherDescription", rfaFormDTOAfter.getOtherTypeDescription());
    assertEquals(rfaFormDTOBefore.getApplicationCounty(), rfaFormDTOAfter.getApplicationCounty());
    return rfaFormDTOAfter;
  }

  public ApplicantDTO postApplicant(long formId, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);

  }

  public Response changeApplicationStatusTo(RFAApplicationStatus newStatus, Long formId) {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.STATUS);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(newStatus.toDTO(), MediaType.APPLICATION_JSON));
  }


  public Response submitApplication(long formId) {
    return changeApplicationStatusTo(RFAApplicationStatus.SUBMITTED, formId);
  }

}
