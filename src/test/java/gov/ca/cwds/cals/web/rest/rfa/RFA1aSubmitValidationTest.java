package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.AddressTypes;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aSubmitValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void firstNameValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getValidApplicant();
    applicant.setFirstName(null);
    rfaHelper.postApplicant(form.getId(), applicant);

    rfaHelper.putResidence(form.getId(), rfaHelper.getResidenceDTO());

    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/first_name_form_submission_validation.json");
  }

  @Test
  public void lastNameValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getValidApplicant();
    applicant.setLastName(null);
    rfaHelper.postApplicant(form.getId(), applicant);

    rfaHelper.putResidence(form.getId(), rfaHelper.getResidenceDTO());

    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/last_name_form_submission_validation.json");
  }

  @Test
  public void applicantDriverLicenseNumberIsNullValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getValidApplicant();
    applicant.setDriverLicenseNumber(null);
    rfaHelper.postApplicant(form.getId(), applicant);

    rfaHelper.putResidence(form.getId(), rfaHelper.getResidenceDTO());

    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

  @Test
  public void applicantDriverLicenseStateIsNullValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();
    ApplicantDTO applicant = rfaHelper.getValidApplicant();

    applicant.setDriverLicenseState(null);
    rfaHelper.postApplicant(form.getId(), applicant);

    rfaHelper.putResidence(form.getId(), rfaHelper.getResidenceDTO());

    Response response = rfaHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

  @Test
  public void validateApplicationHasNoApplicant() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    rfaHelper.putResidence(form.getId(), rfaHelper.getResidenceDTO());

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-applicant-response.json");
  }

  @Test
  public void validateApplicantHasNoRFA1bForm() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();
    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);
    ApplicantDTO applicant = rfaHelper.getApplicant();
    rfaHelper.postApplicant(form.getId(), applicant);

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-has-no-rfa1b-form-response.json");
  }

  @Test
  public void validateApplicationHasNoResidence() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();
    ApplicantDTO applicant = rfaHelper.getApplicant();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    rfaHelper.postApplicant(form.getId(), applicant);

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-residence-response.json");
  }

  @Test
  public void validateOtherAdultHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getApplicant();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    ApplicantDTO persistentApplicant = rfaHelper.postApplicant(form.getId(), applicant);

    OtherAdultDTO otherAdult = rfaHelper.getOtherAdultDTO(persistentApplicant);
    otherAdult.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    rfaHelper.postOtherAdult(form.getId(), otherAdult);
    OtherAdultDTO otherAdult2 = rfaHelper.getOtherAdultDTO(persistentApplicant);
    rfaHelper.postOtherAdult(form.getId(), otherAdult2);

    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/other-adult-has-no-reference-to-applicant.json");
  }

  @Test
  public void validateMinorChildHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getApplicant();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    ApplicantDTO persistentApplicant = rfaHelper.postApplicant(form.getId(), applicant);

    MinorChildDTO minorChild = rfaHelper.getMinorChildDTO(persistentApplicant);
    minorChild.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    rfaHelper.postMinorChild(form.getId(), minorChild);
    MinorChildDTO minorChild2 = rfaHelper.getMinorChildDTO(persistentApplicant);
    rfaHelper.postMinorChild(form.getId(), minorChild2);

    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/minor-child-has-no-reference-to-applicant.json");
  }

  @Test
  public void validateApplicationHasNoResidentialAddress() throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();

    ApplicantDTO applicant = rfaHelper.getApplicant();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    rfaHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    List<RFAAddressDTO> addresses = residence.getAddresses();
    for (Iterator<RFAAddressDTO> iterator = addresses.listIterator(); iterator.hasNext(); ) {
      RFAAddressDTO address =  iterator.next();
      if (AddressTypes.RESIDENTIAL.equals(address.getType().getValue())) {
        addresses.remove(address);
      }
    }
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-residential-address-response.json");
  }
}
