package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.DBUnitAssertHelper;
import gov.ca.cwds.cals.persistence.DBUnitSupport;
import gov.ca.cwds.cals.persistence.DBUnitSupportBuilder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import io.dropwizard.jackson.Jackson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author CWDS CALS API Team
 */

public class RFA1aSubmitApplicationTest extends BaseRFAIntegrationTest {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  DBUnitSupport dbUnitSupport =
      new DBUnitSupportBuilder().buildForCMS(appRule.getConfiguration());

  @BeforeClass
  public static void beforeClass() throws Exception {

    setUpCalsns();
    setUpCms();
  }

  @Test
  public void serializeStatusToJSON() throws Exception {
    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(fixture("fixtures/rfa/submitted-status.json"),
            RFAApplicationStatusDTO.class));

    assertThat(MAPPER.writeValueAsString(RFAApplicationStatus.SUBMITTED.toDTO()))
        .isEqualTo(expected);
  }

  @Test
  public void deserializesFromJSON() throws Exception {
    assertThat(MAPPER
        .readValue(fixture("fixtures/rfa/submitted-status.json"), RFAApplicationStatusDTO.class))
        .isEqualTo(RFAApplicationStatus.SUBMITTED.toDTO());
  }

  @Test
  public void form404NotFoundForGetTest() throws Exception {
    WebTarget getTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -9999 + "/" + Constants.API.STATUS);
    Response response = getTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void form404NotFoundForPostTest() throws Exception {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -9999 + "/" + API.STATUS);
    Response response = target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(RFAApplicationStatus.SUBMITTED.toDTO(), MediaType.APPLICATION_JSON));
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getInitialStatusTest() throws Exception {
    Long formId = rfaHelper.createForm().getId();
    assertDraft(formId);
  }

  @Test
  public void submitApplicationTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    RFA1aFormDTO form = rfaHelper.createForm();
    ApplicantDTO applicantDTO = rfaHelper.postApplicant(form.getId(), getApplicantDTO());
    ApplicantDTO secondApplicant = getApplicantDTO();
    secondApplicant.setFirstName("John");
    StateType driverLicenseState = new StateType();
    driverLicenseState.setId(25L);
    driverLicenseState.setValue("Maryland");
    secondApplicant.setDriverLicenseState(driverLicenseState);

    rfaHelper.postApplicant(form.getId(), secondApplicant);
    rfaHelper.putResidence(form.getId(), getResidenceDTO());

    RFA1bFormDTO rfa1bForm = rfaHelper.getRfa1bForm();
    rfa1bForm.setRfa1aApplicantId(applicantDTO.getId());
    rfaHelper.postRfa1bForm(form.getId(), rfa1bForm);

    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId());
    form = target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    String placementHomeId = form.getPlacementHomeId();
    assertNotNull(placementHomeId);

    testIfPlacementHomeWasCreatedProperly(placementHomeId);
    testIfPlacementHomeUCWasCreatedProperly();

    ITable placementHomeInformation = dbUnitSupport.getTableFromDB("HM_SCP_T");
    ITable placementHomeRow = dbUnitSupport.filterByColumnAndValue(placementHomeInformation, "FKPLC_HM_T", placementHomeId);
    String substituteCareProviderId1 = (String) placementHomeRow.getValue(0, "FKSB_PVDRT");
    String substituteCareProviderId2 = (String) placementHomeRow.getValue(1, "FKSB_PVDRT");

    testIfPlacementHomeInformationWasCreatedProperly(placementHomeId, substituteCareProviderId1, substituteCareProviderId2);
    testIfSubstituteCareProviderWasCreatedProperly(substituteCareProviderId1, substituteCareProviderId2);
    testIfSubstituteCareProviderUCWasCreatedProperly(substituteCareProviderId2, substituteCareProviderId2);
  }

  private void testIfPlacementHomeWasCreatedProperly(String placementHomeId) throws Exception {
    DBUnitAssertHelper dbUnitAssertHelper = new DBUnitAssertHelper(dbUnitSupport);
    dbUnitAssertHelper.setTableName("PLC_HM_T");
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", placementHomeId);
    dbUnitAssertHelper.addFixture("/dbunit/PlacementHome.xml", parameters);
    dbUnitAssertHelper.addFilter("IDENTIFIER", placementHomeId);
    dbUnitAssertHelper.assertEqualsIgnoreCols(
        new String[]{
            "LST_UPD_TS",
            "BCK_PERSNM",
            "GNDR_ACPCD",
            "GEO_RGNTCD",
            "LA_VNDR_ID",
            "LICNSEE_NM",
            "P_CITY_NM",
            "PYE_FSTNM",
            "PYE_LSTNM",
            "PYE_MIDNM",
            "PSTREET_NM",
            "PSTREET_NO",
            "SPCHAR_DSC",
            "CTYPRF_DSC",
            "ED_PVR_DSC",
            "ENV_FCTDSC",
            "HAZRDS_DSC",
            "LIS_PRFDSC",
            "PETS_DSC",
            "RLG_ACTDSC",
            "CERT_CMPLT",
            "LA_P_CTYNM",
            "LA_P_FSTNM",
            "LA_P_LSTNM",
            "LA_P_MIDNM",
            "LA_P_STNM",
            "LA_P_STNO",
            "LA_P_BSNSS",
            "ADHMONLY",
        });
  }

  private void testIfPlacementHomeUCWasCreatedProperly() throws Exception {
    DBUnitAssertHelper dbUnitAssertHelper = new DBUnitAssertHelper(dbUnitSupport);
    dbUnitAssertHelper.setTableName("PLCHM_UC");
    dbUnitAssertHelper.addFixture("/dbunit/PlacementHomeUc.xml");
    dbUnitAssertHelper
        .assertEqualsIgnoreCols(new String[]{"PKPLC_HMT", "LST_UPD_ID", "LST_UPD_TS"});
  }

  private void testIfPlacementHomeInformationWasCreatedProperly(String placementHomeId, String substituteCareProviderId1, String substituteCareProviderId2) throws Exception {
    IDataSet xmlDataSet = dbUnitSupport.getXMLDataSet("/dbunit/PlacementHomeInformation.xml");
    ReplacementDataSet expectedDataset = DBUnitAssertHelper.getReplacementDataset(xmlDataSet);
    expectedDataset.addReplacementObject("$placementHomeId", placementHomeId);
    expectedDataset.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataset.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);
    ITable expectedTable = expectedDataset.getTable("HM_SCP_T");
    ITable actualTable = dbUnitSupport.getTableFromDB("HM_SCP_T");
    Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[]{"THIRD_ID", "START_DT", "END_DT", "LST_UPD_ID", "LST_UPD_TS",});
  }

  private void testIfSubstituteCareProviderWasCreatedProperly(String substituteCareProviderId1, String substituteCareProviderId2) throws Exception {
    IDataSet xmlDataSet = dbUnitSupport.getXMLDataSet("/dbunit/SubstituteCareProvider.xml");
    ReplacementDataSet expectedDataset = DBUnitAssertHelper.getReplacementDataset(xmlDataSet);
    expectedDataset.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataset.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);
    ITable expectedTable = expectedDataset.getTable("SB_PVDRT");
    ITable actualTable = dbUnitSupport.getTableFromDB("SB_PVDRT");
    Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[]{"IDENTIFIER", "LST_UPD_ID", "LST_UPD_TS", "LST_UPD_ID", "LIS_PER_ID", "ETH_UD_CD", "HISP_UD_CD", "PASSBC_CD"});
  }

  private void testIfSubstituteCareProviderUCWasCreatedProperly(String substituteCareProviderId1, String substituteCareProviderId2) throws Exception {
    IDataSet xmlDataSet = dbUnitSupport.getXMLDataSet("/dbunit/SubstituteCareProviderUC.xml");
    ReplacementDataSet expectedDataset = DBUnitAssertHelper.getReplacementDataset(xmlDataSet);
    expectedDataset.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataset.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);
    ITable expectedTable = expectedDataset.getTable("SBPVD_UC");
    ITable actualTable = dbUnitSupport.getTableFromDB("SBPVD_UC");
    Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[]{"PKSB_PVDRT", "LST_UPD_ID", "LST_UPD_TS"});
  }

  @Test
  public void unChangedDraftStatusTest() throws Exception {
    Long formId = rfaHelper.createForm().getId();
    assertDraft(formId);
    Response response = changeApplicationStatusTo(RFAApplicationStatus.DRAFT, formId);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertDraft(formId);
  }

  @Test
  public void unChangedSubmitStatusTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createForm();
    rfaHelper.postApplicant(form.getId(), getApplicantDTO());
    rfaHelper.putResidence(form.getId(), getResidenceDTO());
    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
    response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
  }

  @Test
  public void changeStatusBackToDraftTest() throws Exception {
    RFA1aFormDTO form = rfaHelper.createForm();
    rfaHelper.postApplicant(form.getId(), getApplicantDTO());
    rfaHelper.putResidence(form.getId(), getResidenceDTO());
    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
    response = changeApplicationStatusTo(RFAApplicationStatus.DRAFT, form.getId());
    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
  }

  private String getStatus(Long formId) throws Exception {
    WebTarget getTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + Constants.API.STATUS);
    return getTarget.request(MediaType.APPLICATION_JSON).get(String.class);
  }

  private Response submitApplication(Long formId) {
    return rfaHelper.submitApplication(formId);
  }

  private Response changeApplicationStatusTo(RFAApplicationStatus newStatus, Long formId) {
    return rfaHelper.changeApplicationStatusTo(newStatus, formId);
  }


  private void assertSubmitted(Long formId) throws Exception {
    assertStatus("fixtures/rfa/submitted-status.json", formId);
  }

  private void assertDraft(Long formId) throws Exception {
    assertStatus("fixtures/rfa/draft-status.json", formId);
  }

  private void assertStatus(String statusFixture, Long formId) throws Exception {
    assertEqualsResponse(fixture(statusFixture), getStatus(formId));
  }

  private ResidenceDTO getResidenceDTO() throws IOException {
    String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-residence-request.json";
    return clientTestRule.getMapper().readValue(fixture(APPLICANTS_FIXTURE_PATH), ResidenceDTO.class);
  }

  private ApplicantDTO getApplicantDTO() throws IOException {
    String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-applicant.json";
    return clientTestRule.getMapper().readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

}
