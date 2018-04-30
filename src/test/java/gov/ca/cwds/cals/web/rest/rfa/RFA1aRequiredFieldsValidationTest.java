package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_DATE_OF_BIRTH;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_DRIVER_LICENSE_NUMBER;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_DRIVER_LICENSE_STATE;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_OTHER_NAMES_FIRST_NAME;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_OTHER_NAMES_LAST_NAME;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_APPLICANT_PHONE_NUMBER;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_BODY_OF_WATER_EXIST_FIELD;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_HOME_LANGUAGES;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_MINOR_CHILD_ADOPTED;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_MINOR_CHILD_DATE_OF_BIRTH;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_MINOR_CHILD_FINANCIALLY_SUPPORTED;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_MINOR_CHILD_GENDER;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_MINOR_CHILD_RELATIONSHIP_APPLICANT_ID;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_OTHER_PEOPLE_IN_RESIDENCE;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_PHYSICAL_MAILING_THE_SAME;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_RESIDENCE_ADDRESS_CITY;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_RESIDENCE_ADDRESS_STATE;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_RESIDENCE_ADDRESS_ZIP;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_RESIDENCE_OWNERSHIP;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_RESIDENCE_STREET_ADDRESS;
import static gov.ca.cwds.cals.Constants.Validation.Business.Code.REQUIRED_WEAPON_IN_HOME_FIELD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.TypedPersonNameDTO;
import gov.ca.cwds.rest.exception.IssueDetails;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aRequiredFieldsValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void requiredFieldsValidationOnSubmitTest() throws Exception {

    RFA1aFormDTO form = prepareEmptyForm();

    List<IssueDetails> cvIssues = getIssueDetails(form);

    // First and Last Names are validated on save progress stage 
    //Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_FIRST_NAME)));// "CV000002";
    //Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_LAST_NAME )));// "CV000003";

    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_OTHER_NAMES_FIRST_NAME)));// "CV000004
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_OTHER_NAMES_LAST_NAME)));// "CV000005"
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_DATE_OF_BIRTH)));// "CV000006";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_DRIVER_LICENSE_STATE)));// "CV000007";
    //Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_DRIVER_LICENSE_NUMBER)));// "CV000008"); //see checkDriverLicenseNumberFormTest()
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_PHONE_NUMBER)));// "CV000009";

    // Residence Card
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_RESIDENCE_STREET_ADDRESS)));// "CV000010";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_CITY)));// "CV000011";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_STATE)));// "CV000012";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_ZIP)));// "CV000013";

    List<IssueDetails> streetIssues = cvIssues.stream()
        .filter(issueDetails -> issueDetails.getCode().equals(REQUIRED_RESIDENCE_STREET_ADDRESS))
        .collect(Collectors.toList());
    Assert.assertEquals(1, streetIssues.size());

    List<IssueDetails> cityIssues = cvIssues.stream()
        .filter(issueDetails -> issueDetails.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_CITY))
        .collect(Collectors.toList());
    Assert.assertEquals(1, cityIssues.size());

    List<IssueDetails> stateIssues = cvIssues.stream()
        .filter(issueDetails -> issueDetails.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_STATE))
        .collect(Collectors.toList());
    Assert.assertEquals(1, stateIssues.size());

    List<IssueDetails> zipIssues = cvIssues.stream()
        .filter(issueDetails -> issueDetails.getCode().equals(REQUIRED_RESIDENCE_ADDRESS_ZIP))
        .collect(Collectors.toList());
    Assert.assertEquals(1, zipIssues.size());


    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_PHYSICAL_MAILING_THE_SAME)));// "CV000014";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_RESIDENCE_OWNERSHIP)));// "CV000015";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_WEAPON_IN_HOME_FIELD)));// "CV000016";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_BODY_OF_WATER_EXIST_FIELD)));// "CV000017";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_OTHER_PEOPLE_IN_RESIDENCE)));// "CV000018";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_HOME_LANGUAGES)));// "CV000019";

  }

  private List<IssueDetails> getIssueDetails(RFA1aFormDTO form) {
    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(422, response.getStatus());

    IssueDetailesList issueDetailesList = response.readEntity(IssueDetailesList.class);
    return issueDetailesList.getIssueDetails().stream()
        .filter(issueDetails -> issueDetails.getCode().startsWith("CV")).collect(
            Collectors.toList());
  }

  @Test
  public void checkDriverLicenseNumberFormTest() throws Exception {
    RFA1aFormDTO form = prepareValidForm();

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicant.setFirstName("Some");
    applicant.setDriverLicenseNumber("");

    StateType driverLicenseState = new StateType();
    driverLicenseState.setId("CA");
    driverLicenseState.setValue("California");
    applicant.setDriverLicenseState(driverLicenseState);

    applicantHelper.postApplicant(form.getId(), applicant);

    List<IssueDetails> cvIssues = getIssueDetails(form);

    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_APPLICANT_DRIVER_LICENSE_NUMBER)));// "CV000008");
  }

  private RFA1aFormDTO prepareValidForm() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(form.getId(), applicant);
    ResidenceDTO residenceDTO = residenceHelper.buildResidenceDTO();
    residenceHelper.putResidence(form.getId(), residenceDTO);
    return formAHelper.getRFA1aForm(form.getId());
  }

  private RFA1aFormDTO prepareEmptyForm() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = applicantHelper.getValidApplicant();

    applicant.setOtherNames(Collections.singletonList(new TypedPersonNameDTO()));

    applicant.setDateOfBirth(null);

    applicant.setDriverLicenseNumber("11111111");
    applicant.setDriverLicenseState(null);

    PhoneDTO phoneDTO = new PhoneDTO();
    PhoneNumberType phoneNumberType = new PhoneNumberType();
    phoneNumberType.setId(1L);
    phoneNumberType.setValue("Cell");
    phoneDTO.setPhoneType(phoneNumberType);

    PhoneDTO secondaryPhoneDTO = new PhoneDTO();
    secondaryPhoneDTO.setPhoneType(phoneNumberType);

    applicant.setPhones(Arrays.asList(phoneDTO, secondaryPhoneDTO));
    applicantHelper.postApplicant(form.getId(), applicant);

    ResidenceDTO residenceDTO = residenceHelper.buildResidenceDTO();
    residenceDTO.setResidenceOwnership(null);
    residenceDTO.setBodyOfWaterExist(null);
    residenceDTO.setOthersUsingResidenceAsMailing(null);
    residenceDTO.setPhysicalMailingSimilar(null);
    residenceDTO.setWeaponInHome(null);
    residenceDTO.setHomeLanguages(null);

    //Create residential address
    RFAAddressDTO residentialAddress = new RFAAddressDTO();
    AddressType addressType = new AddressType();
    addressType.setId(1L);
    addressType.setValue("Residential");
    residentialAddress.setType(addressType);

    residenceDTO.setAddresses(
        Arrays.asList(
            // Resiadential address
            residentialAddress,
            // Empty Address object
            new RFAAddressDTO()
        )
    );
    residenceHelper.putResidence(form.getId(), residenceDTO);

    return form;
  }

  @Test
  public void validateRequiredFieldsForMinorChildWhenRelationshipsIsNull() throws Exception {
    RFA1aFormDTO rfa1aForm = prepareValidForm();
    ApplicantDTO firstApplicant = rfa1aForm.getFirstApplicant();
    MinorChildDTO minorChild = buildEmptyMinorChild(firstApplicant);

    minorChild.setRelationshipToApplicants(null);
    minorChildHelper.postMinorChild(rfa1aForm.getId(), minorChild);

    Response response = statusHelper.submitApplication(rfa1aForm.getId());
    assertNotEquals(422, response.getStatus());
  }

  @Test
  public void validateRequiredFieldsForMinorChildWhenRelationshipsIsEmpty() throws Exception {
    RFA1aFormDTO rfa1aForm = prepareValidForm();
    ApplicantDTO firstApplicant = rfa1aForm.getFirstApplicant();
    MinorChildDTO minorChild = buildEmptyMinorChild(firstApplicant);

    minorChild.setRelationshipToApplicants(Collections.emptyList());
    minorChildHelper.postMinorChild(rfa1aForm.getId(), minorChild);

    Response response = statusHelper.submitApplication(rfa1aForm.getId());
    assertNotEquals(422, response.getStatus());
  }

  @Test
  public void validateRequiredFieldsForMinorChild() throws Exception {
    RFA1aFormDTO rfa1aForm = prepareValidForm();
    ApplicantDTO firstApplicant = rfa1aForm.getFirstApplicant();
    MinorChildDTO minorChild = buildEmptyMinorChild(firstApplicant);
    minorChild.getRelationshipToApplicants().get(0).setApplicantId(null);
    minorChild = minorChildHelper.postMinorChild(rfa1aForm.getId(), minorChild);

    List<IssueDetails> cvIssues = getIssueDetails(rfa1aForm);

    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_MINOR_CHILD_DATE_OF_BIRTH)));// "CV000020";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_MINOR_CHILD_GENDER)));// "CV000021";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_MINOR_CHILD_FINANCIALLY_SUPPORTED)));// "CV000022";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_MINOR_CHILD_ADOPTED)));// "CV000023";
    Assert.assertTrue(cvIssues.stream().anyMatch(id -> id.getCode().equals(REQUIRED_MINOR_CHILD_RELATIONSHIP_APPLICANT_ID)));// "CV000024";
  }

  @Test
  public void validateRequiredFieldsForMinorChildFulfilled() throws Exception {
    RFA1aFormDTO rfa1aForm = prepareValidForm();
    ApplicantDTO firstApplicant = rfa1aForm.getFirstApplicant();
    MinorChildDTO minorChild = minorChildHelper.buildNewMinorChildDTO(firstApplicant);
    minorChild = minorChildHelper.postMinorChild(rfa1aForm.getId(), minorChild);

    Response response = statusHelper.submitApplication(rfa1aForm.getId());
    assertNotEquals(422, response.getStatus());
  }


  private MinorChildDTO buildEmptyMinorChild(ApplicantDTO firstApplicant) throws IOException {
    MinorChildDTO minorChild = minorChildHelper.buildNewMinorChildDTO(firstApplicant);
    minorChild.setDateOfBirth(null);
    minorChild.setGender(null);
    minorChild.setChildFinanciallySupported(null);
    minorChild.setChildAdopted(null);
    return minorChild;
  }


  public static class IssueDetailesList {
    @JsonProperty("issue_details")
    List<IssueDetails> issueDetails;
    public List<IssueDetails> getIssueDetails() {
      return issueDetails;
    }
    public void setIssueDetails(List<IssueDetails> issueDetails) {
      this.issueDetails = issueDetails;
    }
  }

}

