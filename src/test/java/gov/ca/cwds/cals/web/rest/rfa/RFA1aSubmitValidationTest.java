package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.AddressTypes;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import io.dropwizard.testing.FixtureHelpers;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

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
  public void validateApplicationHasNoApplicant() throws IOException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/application-has-no-applicant-response.json");
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validateApplicantHasNoRFA1bForm() throws IOException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);
    ApplicantDTO applicant = rfaHelper.getApplicantDTO();
    rfaHelper.postApplicant(form.getId(), applicant);

    Response response = rfaHelper.submitApplication(form.getId());
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/applicant-has-no-rfa1b-form-response.json");
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validateApplicationHasNoResidence() throws IOException {
    RFA1aFormDTO form = rfaHelper.createForm();
    ApplicantDTO applicant = rfaHelper.getApplicantDTO();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    rfaHelper.postApplicant(form.getId(), applicant);

    Response response = rfaHelper.submitApplication(form.getId());
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/application-has-no-residence-response.json");
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validateOtherAdultHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = rfaHelper.createForm();

    ApplicantDTO applicant = rfaHelper.getApplicantDTO();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    ApplicantDTO persistentApplicant = rfaHelper.postApplicant(form.getId(), applicant);

    OtherAdultDTO otherAdult = rfaHelper.getOtherAdultDTO(persistentApplicant);
    otherAdult.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    rfaHelper.postOtherAdult(form.getId(), otherAdult);

    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/other-adult-has-no-reference-to-applicant.json");
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validateMinorChildHasNoReferenceToApplicant() throws Exception {
    RFA1aFormDTO form = rfaHelper.createForm();

    ApplicantDTO applicant = rfaHelper.getApplicantDTO();
    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    applicant.setRfa1bForm(rfa1bForm);
    ApplicantDTO persistentApplicant = rfaHelper.postApplicant(form.getId(), applicant);

    MinorChildDTO minorChild = rfaHelper.getMinorChildDTO(persistentApplicant);
    minorChild.getRelationshipToApplicants().iterator().next().setApplicantId(-1L);
    rfaHelper.postMinorChild(form.getId(), minorChild);

    ResidenceDTO residence = rfaHelper.getResidenceDTO();
    rfaHelper.putResidence(form.getId(), residence);

    Response response = rfaHelper.submitApplication(form.getId());
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/minor-child-has-no-reference-to-applicant.json");
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validateApplicationHasNoResidentialAddress() throws IOException {
    RFA1aFormDTO form = rfaHelper.createForm();

    ApplicantDTO applicant = rfaHelper.getApplicantDTO();
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
    String actualData = getDataFromRawResponse(response);
    String expectedData = FixtureHelpers.fixture("fixtures/rfa/validation/application-has-no-residential-address-response.json");
    assertEquals(expectedData, actualData);
  }
}
