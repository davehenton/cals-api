package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.Constants.AddressTypes;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.rest.exception.BaseExceptionResponse;
import gov.ca.cwds.rest.exception.IssueDetails;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aSubmitValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void applicantDriverLicenseNumberIsNullValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicant.setDriverLicenseNumber(null);
    applicantHelper.postApplicant(form.getId(), applicant);

    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());

    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

  @Test
  public void applicantDriverLicenseStateIsNullValidationSubmissionTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();

    applicant.setDriverLicenseState(null);
    applicantHelper.postApplicant(form.getId(), applicant);

    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());

    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-driver-license-violation-response.json");
  }

  @Test
  public void validateApplicationHasNoApplicant() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    ApplicantDTO applicantDto = applicantHelper.postApplicant(form.getId(), applicantHelper.getApplicant());
    applicantHelper.deleteApplicant(form.getId(), applicantDto.getId());

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-applicant-response.json");
  }

  @Test
  public void validateApplicantHasNoRFA1bForm() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(form.getId(), residence);
    ApplicantDTO applicant = applicantHelper.getApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/applicant-has-no-rfa1b-form-response.json");
  }

  @Test
  public void validateApplicationHasNoResidence() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-residence-response.json");
  }

  @Test
  public void validateOtherAdultHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    ApplicantDTO persistentApplicant = applicantHelper.postApplicant(form.getId(), applicant);

    OtherAdultDTO otherAdult = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdult.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult);
    OtherAdultDTO otherAdult2 = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult2);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/other-adult-has-no-reference-to-applicant.json");
  }

  @Test
  public void validateOtherAdultHasNoFirstName() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    ApplicantDTO persistentApplicant = applicantHelper.postApplicant(form.getId(), applicant);

    OtherAdultDTO otherAdult = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdult.setFirstName(" ");
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult);
    OtherAdultDTO otherAdult2 = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult2);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/other-adult-has-no-first-name.json");
  }

  @Test
  public void validateOtherAdultHasNoLastName() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    ApplicantDTO persistentApplicant = applicantHelper.postApplicant(form.getId(), applicant);

    OtherAdultDTO otherAdult = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdult.setLastName(" ");
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult);
    OtherAdultDTO otherAdult2 = otherAdultHelper.getOtherAdultDTO(persistentApplicant);
    otherAdultHelper.postOtherAdult(form.getId(), otherAdult2);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/other-adult-has-no-last-name.json");
  }

  @Test
  public void validateMinorChildHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    ApplicantDTO persistentApplicant = applicantHelper.postApplicant(form.getId(), applicant);

    MinorChildDTO minorChild = minorChildHelper.getMinorChildDTO(persistentApplicant);
    minorChild.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    minorChildHelper.postMinorChild(form.getId(), minorChild);
    MinorChildDTO minorChild2 = minorChildHelper.getMinorChildDTO(persistentApplicant);
    minorChildHelper.postMinorChild(form.getId(), minorChild2);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/minor-child-has-no-reference-to-applicant.json");
  }

  @Test
  public void validateApplicationHasNoResidentialAddress() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();

    residence.getAddresses().remove(getResidentialAddress(residence));
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-residential-address-response.json");
  }

  @Test
  public void validateResidentialAddressHasNoStreetName() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    getResidentialAddress(residence).setStreetAddress("100");
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/residential-address-has-no-street-name-response.json");
  }

  @Test
  public void validateResidentialAddressHasNoState() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    getResidentialAddress(residence).setState(null);
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/residential-address-has-no-state-response.json");
  }

  @Test
  public void validateResidentialAddressHasNoZipCode() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    getResidentialAddress(residence).setZip(" ");
    residenceHelper.putResidence(form.getId(), residence);

    Response response = statusHelper.submitApplication(form.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/residential-address-has-no-zip-code-response.json");
  }

  @Test
  public void validateApplicationHasNoCounty() throws Exception {
    RFA1aFormDTO form = formAHelper.createRfa1aForm();
    form.setApplicationCounty(null);
    RFA1aFormDTO persistentForm = formAHelper.postRfa1aForm(form);

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(persistentForm.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(persistentForm.getId(), residence);

    Response response = statusHelper.submitApplication(persistentForm.getId());
    assertResponseByFixturePath(
        response, "fixtures/rfa/validation/application-has-no-county-response.json");
  }

  @Test
  public void validateAdultChildrenAndOtherAdultsCorrelation() throws Exception {
    // There are Other Adults-children added to Other Adults Section
    // and not all of them added to Adult Children Section

    // There are grown-up children living in home (Adult Children Section)
    // and not all of them is added to Other Adults section

    Long formId = formAHelper.createRFA1aForm().getId();
    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(formId, residence);
    ApplicantDTO applicantDTO = applicantHelper.postApplicant(formId);
    otherAdultHelper.postOtherAdult(formId,
        otherAdultHelper.getOtherAdultDTO(applicantDTO,
            FixtureHelpers.fixture(
                "fixtures/rfa/validation/adultchildren/rfa-1a-other-adults1.json")));
    otherAdultHelper.postOtherAdult(formId,
        otherAdultHelper.getOtherAdultDTO(applicantDTO,
            FixtureHelpers.fixture(
                "fixtures/rfa/validation/adultchildren/rfa-1a-other-adults2.json")));
    otherAdultHelper.postOtherAdult(formId,
        otherAdultHelper.getOtherAdultDTO(applicantDTO,
            FixtureHelpers.fixture(
                "fixtures/rfa/validation/adultchildren/rfa-1a-other-adults3.json")));
    otherAdultHelper.postOtherAdult(formId,
        otherAdultHelper.getOtherAdultDTO(applicantDTO,
            FixtureHelpers.fixture(
                "fixtures/rfa/validation/adultchildren/rfa-1a-other-adults4.json")));
    formBHelper.postRfa1bForm(formId, applicantDTO.getId(), formBHelper.getRfa1bForm());
    /*formBHelper.postRfa1bForm(formId, applicantDTO.getId(), formBHelper.getRfa1bForm());
    formBHelper.postRfa1bForm(formId, applicantDTO.getId(), formBHelper.getRfa1bForm());
    formBHelper.postRfa1bForm(formId, applicantDTO.getId(), formBHelper.getRfa1bForm());
    formBHelper.postRfa1bForm(formId, applicantDTO.getId(), formBHelper.getRfa1bForm());*/

    applicantsHistoryHelper.putApplicantsHistory(
        formId, applicantsHistoryHelper.getApplicantHistoryDTO(
            "fixtures/rfa/validation/adultchildren/rfa-1a-applicants-history.json"));

    BaseExceptionResponse actualResponse = statusHelper.submitApplication(formId)
        .readEntity(BaseExceptionResponse.class);
    BaseExceptionResponse expectedResponse = Jackson.newObjectMapper().readValue(
        FixtureHelpers.fixture(
            "fixtures/rfa/validation/adultchildren/adult-children-validation-violation-response.json"),
        BaseExceptionResponse.class);
    assertTrue(CollectionUtils.isEqualCollection(
        expectedResponse.getIssueDetails(),
        actualResponse.getIssueDetails(), new CustomEquator()));
  }

  private RFAAddressDTO getResidentialAddress(ResidenceDTO residence) {
    List<RFAAddressDTO> addresses = residence.getAddresses();
    for (Iterator<RFAAddressDTO> iterator = addresses.listIterator(); iterator.hasNext(); ) {
      RFAAddressDTO address = iterator.next();
      if (AddressTypes.RESIDENTIAL.equals(address.getType().getValue())) {
        return address;
      }
    }
    return null;
  }

  /*TODO remove CustomEquator when api-core published correctly*/
  private static class CustomEquator implements Equator<IssueDetails> {

    @Override
    public boolean equate(IssueDetails o1, IssueDetails o2) {
      return o1 == o2 || o1 != null && customEquals(o1, o2);
    }

    @Override
    public int hash(IssueDetails o) {
      return o == null ? -1 : customHashCode(o);
    }

    private int customHashCode(IssueDetails o) {
      return new HashCodeBuilder(17, 37)
          .append(o.getCode()).append(o.getCode()).append(o.getType()).append(o.getUserMessage())
          .toHashCode();
    }

    private boolean customEquals(IssueDetails o1, IssueDetails o2) {
      EqualsBuilder equalsBuilder = new EqualsBuilder();
      equalsBuilder.append(o1.getCode(), o2.getCode());
      equalsBuilder.append(o1.getType(), o2.getType());
      equalsBuilder.append(o1.getUserMessage(), o2.getUserMessage());
      return equalsBuilder.isEquals();
    }

  }

}

