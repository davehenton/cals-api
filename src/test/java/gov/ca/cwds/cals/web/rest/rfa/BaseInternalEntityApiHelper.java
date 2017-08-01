package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import exception.BaseExceptionResponse;
import exception.ExpectedExceptionInfo;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;

/**
 * @author CWDS CALS API Team
 */

public class BaseInternalEntityApiHelper<T extends BaseDTO> implements InternalEntityApiHelper {

  private RestClientTestRule clientTestRule;
  private TestInternalEntityConfiguration<T> configuration;

  public BaseInternalEntityApiHelper(RestClientTestRule clientTestRule,
      TestInternalEntityConfiguration<T> configuration) {
    this.clientTestRule = clientTestRule;
    this.configuration = configuration;
  }

  @Override
  public void getApplicationNotFound() throws Exception {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -1 + "/" + configuration.getApiPath());
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
    checkExpectedExceptionResponse(response);
  }

  @Override
  public void putApplicationNotFound() throws Exception {
    WebTarget target =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + -1 + "/" + configuration.getApiPath());
    T entity = configuration.createEntity();

    Response response = target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(entity, MediaType.APPLICATION_JSON));
    assertEquals(404, response.getStatus());

    checkExpectedExceptionResponse(response);

  }

  private void checkExpectedExceptionResponse(Response response) throws IOException, JSONException {
    BaseExceptionResponse expectedExceptionResponse =
        response.readEntity(BaseExceptionResponse.class);
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameter("incident_id", expectedExceptionResponse.getIncidentId());
    velocityHelper.setParameter("user_message", ErrorMessages.BASE_ERROR_MESSAGE);
    velocityHelper.setParameter("technical_message",
        ExpectedExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID.getMessage());
    velocityHelper
        .setParameter("code", ExpectedExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID.getCode());
    assertResponseByFixture(
        clientTestRule.getMapper().writeValueAsString(expectedExceptionResponse),
        velocityHelper.process("fixtures/rfa/rfa-1a-application-not-found.json"));
  }

  @Override
  public void getEntityNotFound() throws Exception {
    RFA1aFormDTO rfa1aForm = createForm(clientTestRule);
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + rfa1aForm.getId() + "/" + configuration.getApiPath());
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Override
  public void putAndGetEntity() throws Exception {
    RFA1aFormDTO rfa1aForm = createForm(clientTestRule);
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + rfa1aForm.getId() + "/" + configuration.getApiPath());
    T putEntity =
        target.request(MediaType.APPLICATION_JSON).put(
            Entity.entity(configuration.createEntity(), MediaType.APPLICATION_JSON_TYPE),
            configuration.getEntityClass());
    T getEntity = target.request(MediaType.APPLICATION_JSON)
        .get(configuration.getEntityClass());
    assertThat(getEntity).isEqualTo(putEntity);
  }

}
