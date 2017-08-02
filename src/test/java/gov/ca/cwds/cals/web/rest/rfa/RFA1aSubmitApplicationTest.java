package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.DBUnitSupport;
import gov.ca.cwds.cals.persistence.DBUnitSupportBuilder;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import io.dropwizard.jackson.Jackson;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author CWDS CALS API Team
 */

public class RFA1aSubmitApplicationTest extends BaseCalsApiIntegrationTest {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

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
    Long formId = createForm(clientTestRule).getId();
    assertDraft(formId);
  }

  @Test
  public void submitApplicationTest() throws Exception {
    if (TestModeUtils.isIntegrationTestsMode()) {
      return;
    }
    RFA1aFormDTO form = createForm(clientTestRule);
    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + form.getId());
    form = target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);
    assertNotNull(form.getPlacementHomeId());

    testIfPlacementHomeUCWasCreatedProperly();

  }

  private void testIfPlacementHomeUCWasCreatedProperly() throws Exception {
    // DataBase testing
    DBUnitSupportBuilder dbUnitSupportBuilder = new DBUnitSupportBuilder();
    DBUnitSupport dbUnitSupport = dbUnitSupportBuilder.buildForCMS(appRule.getConfiguration());

    String pathToFlatXMLDataSet = "/dbunit/PlacementHomeUc-test-dataset.xml";
    String PlacementHomeUCTableName = "PLCHM_UC";

    ITable expected = dbUnitSupport.getTableFromXML(pathToFlatXMLDataSet, PlacementHomeUCTableName);
    ITable actual = dbUnitSupport.getTableFromDB(PlacementHomeUCTableName);

    Assertion.assertEqualsIgnoreCols(expected, actual,
        new String[]{"PKPLC_HMT", "LST_UPD_ID", "LST_UPD_TS"});
  }

  @Test
  public void unChangedDraftStatusTest() throws Exception {
    Long formId = createForm(clientTestRule).getId();
    assertDraft(formId);
    Response response = changeApplicationStatusTo(RFAApplicationStatus.DRAFT.toDTO(), formId);
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertDraft(formId);
  }

  @Test
  public void unChangedSubmitStatusTest() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
    response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
  }

  @Test
  public void changeStatusBackToDraftTest() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    Response response = submitApplication(form.getId());
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
    response = changeApplicationStatusTo(RFAApplicationStatus.DRAFT.toDTO(), form.getId());
    assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    assertSubmitted(form.getId());
  }

  private String getStatus(Long formId) throws Exception {
    WebTarget getTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + Constants.API.STATUS);
    return getTarget.request(MediaType.APPLICATION_JSON).get(String.class);
  }

  private Response submitApplication(Long formId) {
    return changeApplicationStatusTo(RFAApplicationStatus.SUBMITTED.toDTO(), formId);
  }

  private Response changeApplicationStatusTo(RFAApplicationStatusDTO newStatus, Long formId) {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.STATUS);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(newStatus, MediaType.APPLICATION_JSON));
  }


  private void assertSubmitted(Long formId) throws Exception {
    assertStatus("fixtures/rfa/submitted-status.json", formId);
  }

  private void assertDraft(Long formId) throws Exception {
    assertStatus("fixtures/rfa/draft-status.json", formId);
  }

  private void assertStatus(String statusFixture, Long formId) throws Exception {
    assertEqualsResponse(
        fixture(statusFixture), getStatus(formId));
  }

}
