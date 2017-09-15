package gov.ca.cwds.cals.web.rest.rfa;

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
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import io.dropwizard.jackson.Jackson;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
    Long formId = formAHelper.createRFA1aForm().getId();
    statusHelper.assertDraft(formId);
  }

  @Test
  public void submitApplicationTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicantDTO = applicantHelper
        .postApplicant(form.getId(), applicantHelper.getValidApplicant());
    ApplicantDTO secondApplicant = applicantHelper.getValidApplicant();
    secondApplicant.setFirstName("John");
    StateType driverLicenseState = new StateType();
    driverLicenseState.setId(25L);
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

    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId());
    form = target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    String placementHomeId = form.getPlacementHomeId();
    assertNotNull(placementHomeId);
    String[] substituteCareProviderIds = getSubstituteCareProviderIds(placementHomeId);

    testIfPlacementHomeWasCreatedProperly(placementHomeId);
    testIfPlacementHomeUCWasCreatedProperly();

    testIfPlacementHomeProfileWasCreatedProperly(placementHomeId);

    testIfEmergencyContactDetailWasCreatedProperly(placementHomeId);

    testIfExternalInterfaceWasCreatedProperly();

    testIfBackgroundCheckWasCreatedProperly();

    testIfCountyOwnershipWasCreatedProperly(placementHomeId, substituteCareProviderIds);

    testIfSubstituteCareProviderRelatedEntitiesWasCreatedProperly(placementHomeId, substituteCareProviderIds);

    testIfOtherAdultsWasCreatedProperly(form.getPlacementHomeId());
    testIfOtherChildrenWasCreatedProperly(form.getPlacementHomeId());

    testIfOtherPeopleScpRelationshipWasCreatedProperly(substituteCareProviderIds[0]);
    testIfOtherPeopleScpRelationshipWasCreatedProperly(substituteCareProviderIds[1]);

  }

  private void testIfSubstituteCareProviderRelatedEntitiesWasCreatedProperly(
      String placementHomeId, String[] substituteCareProviderIds) throws Exception {

    testIfPlacementHomeInformationWasCreatedProperly(placementHomeId, substituteCareProviderIds[0],
        substituteCareProviderIds[1]);
    testIfSubstituteCareProviderWasCreatedProperly(substituteCareProviderIds[0],
        substituteCareProviderIds[1]);
    testIfSubstituteCareProviderUCWasCreatedProperly(substituteCareProviderIds[0],
        substituteCareProviderIds[1]);

    testIfPhoneContactDetailsWasCreatedProperly(substituteCareProviderIds[0]);
    testIfPhoneContactDetailsWasCreatedProperly(substituteCareProviderIds[1]);

    testIfClientScpEthnicityWasCreatedProperly(substituteCareProviderIds[0], "820");
    testIfClientScpEthnicityWasCreatedProperly(substituteCareProviderIds[1], "821");

    testIfOutOfStateCheckWasCreatedProperly(substituteCareProviderIds[0], true);

  }

  private void testIfOutOfStateCheckWasCreatedProperly(String recipientId,
      boolean isSubstituteCareProvider) throws Exception {
    DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/OutOfStateCheck.xml")
        .appendTemplateParameter("rcpntId", recipientId)
        .appendTemplateParameter("rcpntCd", isSubstituteCareProvider ? "S" : "O")
        .setTestedTableName("OST_CHKT")
        .appendTableFilter("RCPNT_ID", recipientId)
        .build()
        .assertEquals(new String[]{"IDENTIFIER", "LST_UPD_TS"});
  }

  private String[] getSubstituteCareProviderIds(String placementHomeId) throws Exception {
    String[] ids = new String[2];
    ITable placementHomeInformation = dbUnitSupport.getTableFromDB("HM_SCP_T");
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(placementHomeInformation, "FKPLC_HM_T", placementHomeId);
    for (int i = 0; i < ids.length; i++) {
      ids[i] = (String) placementHomeRow.getValue(i, "FKSB_PVDRT");
    }
    return ids;
  }

  private String getPrimarySubstituteCareProviderIdByApplicantFirstName(String applicantFirstName) throws Exception {
    ITable substituteCareProvider = dbUnitSupport.getTableFromDB("SB_PVDRT");
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(substituteCareProvider, "FIRST_NM", applicantFirstName);
      return (String) placementHomeRow.getValue(0, "IDENTIFIER");
  }


  private void testIfPlacementHomeWasCreatedProperly(String placementHomeId) throws Exception {
    String primarySubstituteCareProviderId = getPrimarySubstituteCareProviderIdByApplicantFirstName("Anna");

    DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHome.xml")
        .appendTemplateParameter("placementHomeId", placementHomeId)
        .appendTemplateParameter("primarySubstituteCareProviderId", primarySubstituteCareProviderId)
        .setTestedTableName("PLC_HM_T")
        .appendTableFilter("IDENTIFIER", placementHomeId)
        .build()
        .assertEquals(
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
    DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHomeUC.xml")
        .setTestedTableName("PLCHM_UC")
        .build()
        .assertEquals(new String[]{"PKPLC_HMT", "LST_UPD_TS"});
  }

  private void testIfPlacementHomeProfileWasCreatedProperly(String placementHomeId) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHomeProfile.xml")
        .setTestedTableName("HM_PRFT")
        .appendTableFilter("FKPLC_HM_T", placementHomeId)
        .build();
    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$placementHomeId", placementHomeId);

    helper.assertEquals(new String[]{"THIRD_ID", "LST_UPD_TS"});
  }

  private void testIfEmergencyContactDetailWasCreatedProperly(String placementHomeId) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/EmergencyContactDetail.xml")
        .setTestedTableName("EM_CNT_T")
        .appendTableFilter("ESTBLSH_ID", placementHomeId)
        .build();
    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$placementHomeId", placementHomeId);

    helper.assertEquals(new String[]{"IDENTIFIER", "LST_UPD_TS"});
  }

  private void testIfExternalInterfaceWasCreatedProperly() throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/ExternalInterface.xml")
        .setTestedTableName("EXTINF_T")
        .build();
    helper.assertEquals(new String[]{"SUBMTL_TS"});
  }

  private void testIfBackgroundCheckWasCreatedProperly() throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/BackgroundCheck.xml")
        .setTestedTableName("BKGRCHKT")
        .build();
    helper.assertEquals(new String[]{"IDENTIFIER", "BKGRCHK_DT", "LST_UPD_TS"});
  }

  private void testIfCountyOwnershipWasCreatedProperly(
      String placementHomeId, String[] substituteCareProviderIds) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/CountyOwnership.xml")
        .setTestedTableName("CNTYOWNT")
        .build();
    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$placementHomeId", placementHomeId);
    expectedDataSet.addReplacementObject("$substituteCareProviderId1", substituteCareProviderIds[0]);
    expectedDataSet.addReplacementObject("$substituteCareProviderId2", substituteCareProviderIds[1]);
    helper.assertEquals(new String[]{"IDENTIFIER", "BKGRCHK_DT", "LST_UPD_TS"}, new String[] {"ENTITY_ID"});
  }

  private void testIfPlacementHomeInformationWasCreatedProperly(String placementHomeId,
      String substituteCareProviderId1, String substituteCareProviderId2)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHomeInformation.xml")
        .setTestedTableName("HM_SCP_T")
        .build();

    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$placementHomeId", placementHomeId);
    expectedDataSet.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataSet.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);

    helper.assertEquals(new String[]{"THIRD_ID", "LST_UPD_TS"}, new String[]{"PRPRVDR_CD"});
  }

  private void testIfSubstituteCareProviderWasCreatedProperly(String substituteCareProviderId1,
      String substituteCareProviderId2) throws Exception {

    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/SubstituteCareProvider.xml")
        .setTestedTableName("SB_PVDRT")
        .build();

    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataSet.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);

    helper.assertEquals(
        new String[]{"IDENTIFIER", "LST_UPD_TS", "LIS_PER_ID",
            "ETH_UD_CD", "HISP_UD_CD", "PASSBC_CD"});
  }

  private void testIfSubstituteCareProviderUCWasCreatedProperly(String substituteCareProviderId1,
      String substituteCareProviderId2) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/SubstituteCareProviderUC.xml")
        .setTestedTableName("SBPVD_UC")
        .build();

    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$substituteCareProviderId1", substituteCareProviderId1);
    expectedDataSet.addReplacementObject("$substituteCareProviderId2", substituteCareProviderId2);
    helper.assertEquals(new String[]{"PKSB_PVDRT", "LST_UPD_TS"});
  }

  private void testIfPhoneContactDetailsWasCreatedProperly(String substituteCareProviderId)
      throws Exception {

    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PhoneContactDetail.xml")
        .setTestedTableName("PH_CNT_T")
        .appendTableFilter("ESTBLSH_ID", substituteCareProviderId)
        .build();

    String[] ignoreCols = {"THIRD_ID", "ESTBLSH_ID", "LST_UPD_TS"};
    String[] sortedCols = {"PHONE_NO"};
    helper.assertEquals(ignoreCols, sortedCols);
    
  }

  private void testIfClientScpEthnicityWasCreatedProperly(String substituteCareProviderId,
      String ethnicityId)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/ClientScpEthnicity.xml")
        .appendTemplateParameter("scpId", substituteCareProviderId)
        .appendTemplateParameter("ethnicityId", ethnicityId)
        .setTestedTableName("CLSCP_ET")
        .appendTableFilter("ESTBLSH_ID", substituteCareProviderId)
        .build();

    helper
        .assertEquals(new String[]{"IDENTIFIER", "LST_UPD_TS"});
  }

  private void testIfOtherAdultsWasCreatedProperly(String placementHomeId) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/OtherAdultsInPlacementHome.xml")
        .setTestedTableName("OTH_ADLT")
        .appendTableFilter("FKPLC_HM_T", placementHomeId)
        .build();

    helper.assertEquals(new String[]{"IDENTIFIER", "FKPLC_HM_T", "LST_UPD_TS"}, new String[] {"OTH_ADLTNM"});

    ITable actualTable = helper.getActualTable();

    int rowCount = actualTable.getRowCount();

    for (int i = 0; i < rowCount; i++) {
      String identifier = String.valueOf(actualTable.getValue(i, "IDENTIFIER"));
      testIfOutOfStateCheckWasCreatedProperly(identifier, false);
    }
  }

  private void testIfOtherChildrenWasCreatedProperly(String placementHomeId) throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/MinorChildrenInPlacementHome.xml")
        .setTestedTableName("OTH_KIDT")
        .appendTableFilter("FKPLC_HM_T", placementHomeId)
        .build();

    helper.assertEquals(
        new String[]{"IDENTIFIER", "FKPLC_HM_T", "LST_UPD_TS"});
  }

  private void testIfOtherPeopleScpRelationshipWasCreatedProperly(String substituteCareProviderId)
      throws Exception {

    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/OtherPeopleScpRelationship.xml")
        .setTestedTableName("OPSCPRLT")
        .appendTableFilter("FKSB_PVDRT", substituteCareProviderId)
        .build();

    ITable actualTable = helper.getActualTable();
    assertEquals(2, actualTable.getRowCount());
  }


  @Test
  public void unChangedDraftStatusTest() throws Exception {
    Long formId = formAHelper.createRFA1aForm().getId();
    statusHelper.assertDraft(formId);
    Response response = statusHelper.changeApplicationStatusTo(RFAApplicationStatus.DRAFT, formId);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertDraft(formId);
  }

  @Test
  public void unChangedSubmitStatusTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(form.getId(), applicantHelper.getValidApplicant());
    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
    response = statusHelper.submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
  }

  @Test
  public void changeStatusBackToDraftTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(form.getId(), applicantHelper.getValidApplicant());
    residenceHelper.putResidence(form.getId(), residenceHelper.getResidenceDTO());
    Response response = statusHelper.submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
    response = statusHelper.changeApplicationStatusTo(RFAApplicationStatus.DRAFT, form.getId());
    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    statusHelper.assertSubmitted(form.getId());
  }

}
