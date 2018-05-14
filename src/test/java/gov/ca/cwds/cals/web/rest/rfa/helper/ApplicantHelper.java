package gov.ca.cwds.cals.web.rest.rfa.helper;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANT_FIXTURE;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantHelper {

  private RestClientTestRule clientTestRule;

  public ApplicantHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public ApplicantDTO getApplicant() throws IOException {
    return getApplicantDTO();
  }

  public ApplicantDTO getValidApplicant() throws IOException {
    ApplicantDTO applicant = getApplicantDTO();

    RFA1bFormDTO rfa1bForm = new FormBHelper(clientTestRule).getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);

    return applicant;
  }

  public ApplicantDTO postApplicant(long formId) throws IOException {
    return postApplicant(formId, getApplicantDTO());
  }

  private ApplicantDTO getApplicantDTO() throws IOException {
    return clientTestRule.getMapper()
        .readValue(APPLICANT_FIXTURE, ApplicantDTO.class);
  }

  public ApplicantDTO postApplicant(long formId, ApplicantDTO applicantDTO) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);
  }

  public Response deleteApplicant(long formId, long applicantId) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS + "/" + applicantId)
        .request(MediaType.APPLICATION_JSON)
        .delete();
  }

  public ApplicantDTO getFirstExistedOrPostNewApplicant(long formId, ApplicantDTO applicantDTO) {
    ApplicantDTO storedApplicantDTO = getFirstApplicant(formId);
    if (storedApplicantDTO == null) {
      storedApplicantDTO = postApplicant(formId, applicantDTO);
    }
    return storedApplicantDTO;
  }

  public ApplicantDTO getFirstApplicant(long formId) {
    Response response = clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS)
        .request(MediaType.APPLICATION_JSON)
        .get();
    ApplicantDTO applicantDTO = null;
    if (response.getStatus() == 200) {
      GenericType<CollectionDTO<ApplicantDTO>> genericType = new GenericType<CollectionDTO<ApplicantDTO>>() {
      };
      CollectionDTO<ApplicantDTO> applicants = response.readEntity(genericType);
      if (applicants != null && !applicants.getCollection().isEmpty()) {
        applicantDTO = applicants.getCollection().iterator().next();
      }
    }
    return applicantDTO;
  }

  /**
   * Updates Applicant.
   *
   * @param formId RFA1a form id
   * @param applicantDTO applicantDTO for update
   * @return updated applicantDTO
   */
  public ApplicantDTO updateApplicant(long formId, ApplicantDTO applicantDTO) {
    return clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId())
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);
  }

}
