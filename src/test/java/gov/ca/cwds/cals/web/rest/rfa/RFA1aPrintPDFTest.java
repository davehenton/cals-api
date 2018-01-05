package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.Constants.API.QueryParams;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author CWDS TPT-2 Team
 */
public class RFA1aPrintPDFTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Ignore
  @Test
  public void getPDFTest() throws Exception {
    // For integration test only
    if (!TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    RFA1aFormDTO form = createRfa1aFormDTO();
    WebTarget target = clientTestRule.target(
        API.RFA_1A_FORMS + "/" + form.getId() + "?" + QueryParams.EXPANDED + "=true");

    RFA1aFormDTO getExpandedFormResponse = target.request(MediaType.APPLICATION_JSON)
        .get(RFA1aFormDTO.class);
    assertNotNull(getExpandedFormResponse);

    Response response = target.request("application/pdf").get();
    assertNotNull(response);
    System.out.println(response.getLength());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }

  @Ignore
  @Test
  public void getDocumentIdTest() throws Exception {
    // For integration test only
    if (!TestModeUtils.isIntegrationTestsMode()) {
      return;
    }

    RFA1aFormDTO form = createRfa1aFormDTO();
    WebTarget target = clientTestRule.target(
        API.RFA_1A_FORMS + "/" + form.getId() + "/documentId");
    String documentId = target.request().get(String.class);
    assertNotNull(documentId);
  }

  private RFA1aFormDTO createRfa1aFormDTO() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicantDTO = applicantHelper
        .postApplicant(form.getId(), applicantHelper.getValidApplicant());
    ApplicantDTO secondApplicant = applicantHelper.getValidApplicant();
    secondApplicant.setFirstName("John");
    StateType driverLicenseState = new StateType();
    driverLicenseState.setId("MD");
    driverLicenseState.setValue("Maryland");
    secondApplicant.setDriverLicenseState(driverLicenseState);
    secondApplicant.getEthnicity().setId(2L);
    secondApplicant.getEthnicity().setValue("American Indian");
    secondApplicant = applicantHelper.postApplicant(form.getId(), secondApplicant);
    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    RFA1bFormDTO rfa1bForm = formBHelper.getRfa1bForm();
    formBHelper.postRfa1bForm(form.getId(), applicantDTO.getId(), rfa1bForm);
    otherAdultHelper.createOtherAdults(form.getId(), secondApplicant);
    minorChildHelper.createMinorChildren(form.getId(), applicantDTO);
    return form;
  }

}
