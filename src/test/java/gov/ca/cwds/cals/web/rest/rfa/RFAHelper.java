package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANTS_FIXTURE_PATH;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
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
    county.setId(59L);
    county.setValue("State of California");
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

  public ApplicantDTO createValidApplicant() throws IOException {
    return clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

  public ApplicantDTO postApplicant(long formId, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);

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
      ;
      if (applicants != null && !applicants.getCollection().isEmpty()) {
        applicantDTO = applicants.getCollection().iterator().next();
      }
    }
    return applicantDTO;
  }

  public ResidenceDTO putResidence(long formId, ResidenceDTO residenceDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RESIDENCE);
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(residenceDTO, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);

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


  public static PhoneNumberType createPhoneNumberType() {
    return createPhoneNumberType(3L, "Work");
  }

  public static PhoneNumberType createPhoneNumberType(Long id, String value) {
    PhoneNumberType phoneType = new PhoneNumberType();
    phoneType.setId(id);
    phoneType.setValue(value);
    return phoneType;
  }

  public static PhoneDTO createPhone() {
    return createPhone("1234567890", "1234567", false, createPhoneNumberType());
  }

  public static PhoneDTO createPhoneNoExtension() {
    return createPhone("1234567890", null, false, createPhoneNumberType());
  }

  public static PhoneDTO createPhone(String number, String extension, boolean preferred,
      PhoneNumberType phoneType) {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber(number);
    phone.setExtension(extension);
    phone.setPreferred(false);
    phone.setPhoneType(phoneType);
    return phone;
  }

  public RFA1bFormDTO getRfa1bForm() throws IOException {
    return clientTestRule.getMapper()
        .readValue(fixture(RFA1bResourceTest.RFA1B_FORM_FIXTURE_PATH), RFA1bFormDTO.class);
  }

  public RFA1bFormDTO postRfa1bForm(Long formId, Long applicantId, RFA1bFormDTO rfa1bFormDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/"
                + API.RFA_1B_FORMS + "/" + API.RFA_1A_APPLICANTS + "/" + applicantId);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(rfa1bFormDTO, MediaType.APPLICATION_JSON_TYPE), RFA1bFormDTO.class);
  }

  public OtherAdultDTO postOtherAdult(Long formId, OtherAdultDTO otherAdult) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_OTHER_ADULTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(otherAdult, MediaType.APPLICATION_JSON_TYPE), OtherAdultDTO.class);
  }

  public MinorChildDTO postMinorChild(Long formId, MinorChildDTO minorChild) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_MINOR_CHILDREN);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(minorChild, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);
  }

  public List<OtherAdultDTO> createOtherAdults(Long formId, ApplicantDTO relativeApplicant)
      throws Exception {
    List<OtherAdultDTO> otherAdultsDTOs = new ArrayList<>(2);
    for (int i = 0; i < 2; i++) {
      OtherAdultDTO otherAdultDTO = clientTestRule.getMapper()
          .readValue(fixture(RFA1aOtherAdultsResourceTest.FIXTURES_RFA_RFA_1A_OTHER_ADULTS_JSON),
              OtherAdultDTO.class);
      otherAdultDTO.setFirstName(otherAdultDTO.getFirstName() + i);
      otherAdultDTO.setLastName(otherAdultDTO.getLastName() + i);
      // Assume that we have only one relationship object
      otherAdultDTO.getRelationshipToApplicants().get(0).setApplicantId(relativeApplicant.getId());
      otherAdultsDTOs.add(postOtherAdult(formId, otherAdultDTO));
    }
    return otherAdultsDTOs;
  }

  public List<MinorChildDTO> createMinorChildren(Long formId, ApplicantDTO reletiveApplicant)
      throws Exception {
    List<MinorChildDTO> minorChildDTOs = new ArrayList<>(2);
    for (int i = 0; i < 2; i++) {
      MinorChildDTO minorChildDTO = clientTestRule.getMapper()
          .readValue(
              fixture(RFA1aMinorChildrenResourceTest.FIXTURES_RFA_RFA_1A_MINOR_CHILDREN_JSON),
              MinorChildDTO.class);
      minorChildDTO.setOtherRelativeFirstName(minorChildDTO.getOtherRelativeFirstName() + i);
      minorChildDTO.setOtherRelativeLastName(minorChildDTO.getOtherRelativeLastName() + i);
      // Assume that we have only one relationship object
      minorChildDTO.getRelationshipToApplicants().get(0).setApplicantId(reletiveApplicant.getId());
      minorChildDTOs.add(postMinorChild(formId, minorChildDTO));
    }
    return minorChildDTOs;
  }

}
