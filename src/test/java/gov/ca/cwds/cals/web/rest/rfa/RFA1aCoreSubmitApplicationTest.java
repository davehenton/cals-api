package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.persistence.DBUnitAssertHelper;
import gov.ca.cwds.cals.persistence.DBUnitSupport;
import gov.ca.cwds.cals.persistence.DBUnitSupportBuilder;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.helper.RfaSubmitHelper;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import java.time.format.DateTimeFormatter;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aCoreSubmitApplicationTest extends BaseRFAIntegrationTest {

  private static final String FIXTURE_PATH_TO_PRINCIPAL = "security/cals-api-principal.json";

  RfaSubmitHelper rfaSubmitHelper = new RfaSubmitHelper(rfaHelpersHolder);

  DBUnitSupport dbUnitSupport =
      new DBUnitSupportBuilder().buildForCMS(appRule.getConfiguration());

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
    setUpCms();
    setUpCmsRs();
  }

  @Test
  @Ignore
  public void submitApplicationTest() throws Exception {

    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }

    RFA1aFormDTO form = rfaSubmitHelper.submitApplication(FIXTURE_PATH_TO_PRINCIPAL);
    rfaHelpersHolder.getStatusHelper().assertSubmitted(form.getId());
  }

  @Test
  public void submitApplicationExtendedTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }

    RFA1aFormDTO form = rfaSubmitHelper.submitApplication(FIXTURE_PATH_TO_PRINCIPAL);

    rfaHelpersHolder.getStatusHelper().assertSubmitted(form.getId());

    String placementHomeId = form.getPlacementHomeId();
    assertNotNull(placementHomeId);
    String[] substituteCareProviderIds = getSubstituteCareProviderIds(placementHomeId);
    String countyLicenseCase = getCountyLicenseCaseId(placementHomeId);

    testIfPlacementHomeWasCreatedProperly(placementHomeId, countyLicenseCase, String.valueOf(form.getId()));
    testIfPlacementHomeUCWasCreatedProperly();

    testIfPlacementHomeProfileWasCreatedProperly(placementHomeId);

    testIfEmergencyContactDetailWasCreatedProperly(placementHomeId);

    testIfExternalInterfaceWasCreatedProperly();

    testIfBackgroundCheckWasCreatedProperly();

    testIfCountyOwnershipWasCreatedProperly(placementHomeId, substituteCareProviderIds);

    testIfSubstituteCareProviderRelatedEntitiesWasCreatedProperly(placementHomeId,
        substituteCareProviderIds);

    testIfOtherAdultsWasCreatedProperly(form.getPlacementHomeId());
    testIfOtherChildrenWasCreatedProperly(form.getPlacementHomeId(), form);

    testIfPlacementFacilityTypeHistoryWasCreatedProperly(form.getPlacementHomeId());
    testIfCountyLicenseCaseWasCreatedProperly(countyLicenseCase);
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
        .assertEquals(new String[]{"IDENTIFIER", "LST_UPD_TS"}, new String[]{"STATE_C"});
  }

  private String[] getSubstituteCareProviderIds(String placementHomeId) throws Exception {
    String[] ids = new String[2];
    ITable placementHomeInformation =
        new SortedTable(dbUnitSupport.getTableFromDB("HM_SCP_T"), new String[]{"SCPRVD_IND"});
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(placementHomeInformation, "FKPLC_HM_T", placementHomeId);
    for (int i = 0; i < ids.length; i++) {
      ids[i] = (String) placementHomeRow.getValue(i, "FKSB_PVDRT");
    }
    return ids;
  }

  private String getCountyLicenseCaseId(String placementHomeId) throws Exception {
    ITable placementHomeInformation =
        new SortedTable(dbUnitSupport.getTableFromDB("PLC_HM_T"), new String[]{"FKCNTY_CST"});
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(placementHomeInformation, "IDENTIFIER", placementHomeId);
    String id = (String) placementHomeRow.getValue(0, "FKCNTY_CST");
    return id;
  }

  private String getPrimarySubstituteCareProviderIdByApplicantFirstName(String applicantFirstName)
      throws Exception {
    ITable substituteCareProvider = dbUnitSupport.getTableFromDB("SB_PVDRT");
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(substituteCareProvider, "FIRST_NM", applicantFirstName);
    return (String) placementHomeRow.getValue(0, "IDENTIFIER");
  }


  private void testIfPlacementHomeWasCreatedProperly(String placementHomeId, String countyLicenseCase, String formId) throws Exception {
    String primarySubstituteCareProviderId = getPrimarySubstituteCareProviderIdByApplicantFirstName(
        "AFn");

    DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHome.xml")
        .appendTemplateParameter("placementHomeId", placementHomeId)
        .appendTemplateParameter("primarySubstituteCareProviderId", primarySubstituteCareProviderId)
        .appendTemplateParameter("countyLicenseCaseId", countyLicenseCase)
        .appendTemplateParameter("rfaId", formId)
        .setTestedTableName("PLC_HM_T")
        .appendTableFilter("IDENTIFIER", placementHomeId)
        .build()
        .assertEquals(
            new String[]{
                "OLDFAC_ID", "OPRTD_BYCD", "OPRTD_BYID", "PH_ENDC", "CERTF_PNDT", "AP_STAT_DT",
                "LST_UPD_TS", "BCK_PERSNM", "GNDR_ACPCD", "GEO_RGNTCD", "LA_VNDR_ID", "LICNSEE_NM",
                "P_CITY_NM", "PYE_FSTNM", "PYE_LSTNM", "PYE_MIDNM", "PSTREET_NM", "PSTREET_NO",
                "SPCHAR_DSC", "CTYPRF_DSC", "ED_PVR_DSC", "ENV_FCTDSC", "LIS_PRFDSC",
                "PETS_DSC", "RLG_ACTDSC", "CERT_CMPLT", "LA_P_CTYNM", "LA_P_FSTNM", "LA_P_LSTNM",
                "LA_P_MIDNM", "LA_P_STNM", "LA_P_STNO", "LA_P_BSNSS", "ADHMONLY", "END_COMDSC",
                "END_DT", "FKCNTY_CST", "LIC_APL_DT", "LIC_EFCTDT", "LIC_EXP_DT", "LIC_STATDT",
                "NEWLIC_NO", "LICENSR_CD", "FKCNTY_CST"
            });
  }

  private void testIfPlacementHomeUCWasCreatedProperly() throws Exception {
    DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHomeUC.xml")
        .setTestedTableName("PLCHM_UC")
        .build()
        .assertEquals(new String[]{"PKPLC_HMT", "LST_UPD_TS"});
  }

  private void testIfPlacementHomeProfileWasCreatedProperly(String placementHomeId)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementHomeProfile.xml")
        .setTestedTableName("HM_PRFT")
        .appendTableFilter("FKPLC_HM_T", placementHomeId)
        .build();
    ReplacementDataSet expectedDataSet = helper.getExpectedDataSet();
    expectedDataSet.addReplacementObject("$placementHomeId", placementHomeId);

    helper.assertEquals(new String[]{"THIRD_ID", "LST_UPD_TS"});
  }

  private void testIfEmergencyContactDetailWasCreatedProperly(String placementHomeId)
      throws Exception {
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
    expectedDataSet
        .addReplacementObject("$substituteCareProviderId1", substituteCareProviderIds[0]);
    expectedDataSet
        .addReplacementObject("$substituteCareProviderId2", substituteCareProviderIds[1]);
    helper.assertEquals(new String[]{"IDENTIFIER", "BKGRCHK_DT", "LST_UPD_TS"},
        new String[]{"ENTITY_ID"});
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

    helper.assertEquals(new String[]{"THIRD_ID", "LST_UPD_TS"}, new String[]{"SCPRVD_IND"});
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
            "ETH_UD_CD", "HISP_UD_CD", "PASSBC_CD"}, new String[]{"FIRST_NM"});
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
    helper.assertEquals(new String[]{"PKSB_PVDRT", "LST_UPD_TS"}, new String[]{"FIRST_NM"});
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

    helper.assertEquals(new String[]{"IDENTIFIER", "FKPLC_HM_T", "LST_UPD_TS"},
        new String[]{"OTH_ADLTNM"});
  }

  private void testIfOtherChildrenWasCreatedProperly(String placementHomeId, RFA1aFormDTO form)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/MinorChildrenInPlacementHome.xml")
        .appendTemplateParameter("birthDate", form.getMinorChildren().get(0)
            .getDateOfBirth().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
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

  private void testIfPlacementFacilityTypeHistoryWasCreatedProperly(String placementHomeId)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/PlacementFacilityTypeHistory.xml")
        .setTestedTableName("PFACHIST")
        .appendTableFilter("FKPLC_HM_T", placementHomeId)
        .appendTemplateParameter("faciltiyType", getFacilityType(placementHomeId))
        .appendTemplateParameter("placementHomeId", placementHomeId)
        .build();

    ITable actualTable = helper.getActualTable();
    assertEquals(1, actualTable.getRowCount());
    helper.assertEquals(new String[]{
        "THIRD_ID",
        "CREATN_TS",
        "LST_UPD_ID",
        "LST_UPD_TS",
        "START_TS"});
  }

  private void testIfCountyLicenseCaseWasCreatedProperly(String countyLicenseCaseId)
      throws Exception {
    DBUnitAssertHelper helper = DBUnitAssertHelper.builder(dbUnitSupport)
        .setExpectedResultTemplatePath("/dbunit/CountyLicenseCase.xml")
        .setTestedTableName("CNTY_CST")
        .appendTableFilter("IDENTIFIER", countyLicenseCaseId)
        .appendTemplateParameter("countyLicenseCaseId", countyLicenseCaseId)
        .build();

    ITable actualTable = helper.getActualTable();
    assertEquals(1, actualTable.getRowCount());
    helper.assertEquals(
        new String[]{"LST_UPD_TS"}, new String[]{"IDENTIFIER"});
  }

  private Object getFacilityType(String placementHomeId) throws Exception {
    ITable placementHomeInformation =
        new SortedTable(dbUnitSupport.getTableFromDB("PLC_HM_T"), new String[]{"IDENTIFIER"});
    ITable placementHomeRow = dbUnitSupport
        .filterByColumnAndValue(placementHomeInformation, "IDENTIFIER", placementHomeId);
    return placementHomeRow.getValue(0, "PLC_FCLC");
  }

}
