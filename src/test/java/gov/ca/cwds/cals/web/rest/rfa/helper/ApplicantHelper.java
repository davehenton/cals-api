package gov.ca.cwds.cals.web.rest.rfa.helper;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANTS_FIXTURE_PATH;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
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
    String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-applicant.json";
    return clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

  public ApplicantDTO getValidApplicant() throws IOException {
    ApplicantDTO applicant = clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);

    RFA1bFormDTO rfa1bForm = new FormBHelper(clientTestRule).getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);

    return applicant;
  }

  public ApplicantDTO postApplicant(long formId, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);

  }

  public Response deleteApplicant(long formId, long applicantId) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS + "/" + applicantId);
    return target.request(MediaType.APPLICATION_JSON).delete();

  }

  public ApplicantDTO getFirstExistedOrPostNewApplicant(long formId, ApplicantDTO applicantDTO) {
    ApplicantDTO storedApplicantDTO = getFirstApplicant(formId);
    if (storedApplicantDTO == null) {
      storedApplicantDTO = postApplicant(formId, applicantDTO);
    }
    return storedApplicantDTO;
  }

  public ApplicantDTO getFirstApplicant(long formId) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS);
    Builder invocation = target.request(MediaType.APPLICATION_JSON);
    Response response = invocation.get();
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

}
