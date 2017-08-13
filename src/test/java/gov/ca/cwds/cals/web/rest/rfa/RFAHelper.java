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
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import java.io.IOException;
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
    return clientTestRule.getMapper().readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

  public ApplicantDTO postApplicant(long formId, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);

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

  public static PhoneDTO createPhone(String number, String extension, boolean preferred, PhoneNumberType phoneType) {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber(number);
    phone.setExtension(extension);
    phone.setPreferred(false);
    phone.setPhoneType(phoneType);
    return phone;
  }

  public RFA1bFormDTO getRfa1bForm() throws IOException {
    return clientTestRule.getMapper().readValue(fixture(RFA1bResourceTest.RFA1B_FORM_FIXTURE_PATH), RFA1bFormDTO.class);
  }

  public RFA1bFormDTO postRfa1bForm(Long formId, RFA1bFormDTO rfa1bFormDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1B_FORMS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(rfa1bFormDTO, MediaType.APPLICATION_JSON_TYPE), RFA1bFormDTO.class);
  }
}
