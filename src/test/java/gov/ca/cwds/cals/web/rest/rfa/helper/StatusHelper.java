package gov.ca.cwds.cals.web.rest.rfa.helper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.security.test.TestSecurityFilter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Consumer;

import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author CWDS CALS API Team
 */

public class StatusHelper {

  private RestClientTestRule clientTestRule;

  public StatusHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  private void assertStatus(String statusFixture, Long formId) throws Exception {
    WebTarget getTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + Constants.API.STATUS);
    String status = getTarget.request(MediaType.APPLICATION_JSON).get(String.class);
    assertEqualsResponse(fixture(statusFixture), status);
  }

  public void assertSubmitted(Long formId) throws Exception {
    assertStatus("fixtures/rfa/submitted-status.json", formId);
  }

  public void assertDraft(Long formId) throws Exception {
    assertStatus("fixtures/rfa/draft-status.json", formId);
  }

  public void assertInProgress(Long formId) throws Exception {
    assertStatus("fixtures/rfa/in-progress-status.json", formId);
  }

  public Response changeApplicationStatusTo(RFAApplicationStatus newStatus, Long formId) {
    return changeApplicationStatusTo(newStatus, formId, (target) -> {
    });
  }

  public Response changeApplicationStatusTo(RFAApplicationStatus newStatus, Long formId, String pathToSecurityFixture) {
    return changeApplicationStatusTo(newStatus, formId, (target) -> {
      target.queryParam(TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE, pathToSecurityFixture);
    });
  }

  private Response changeApplicationStatusTo(RFAApplicationStatus newStatus, Long formId, Consumer<WebTarget> webTargetEnhancement) {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + formId + "/" + API.STATUS);
    webTargetEnhancement.accept(target);
    return target.request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(newStatus.toDTO(), MediaType.APPLICATION_JSON));
  }

  public Response submitApplication(long formId, String pathToSecurityFixture) {
    return changeApplicationStatusTo(RFAApplicationStatus.SUBMITTED, formId, pathToSecurityFixture);
  }

  public Response submitApplication(long formId) {
    return changeApplicationStatusTo(RFAApplicationStatus.SUBMITTED, formId);
  }

}
