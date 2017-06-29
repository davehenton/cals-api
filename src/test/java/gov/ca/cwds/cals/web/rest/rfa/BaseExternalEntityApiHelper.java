package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.Identified;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.configuration.ExternalEntityConfiguration;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */

public class BaseExternalEntityApiHelper<T extends BaseDTO> implements ExternalEntityApiHelper {

  private RestClientTestRule clientTestRule;
  private ExternalEntityConfiguration<T> configuration;

  public BaseExternalEntityApiHelper(RestClientTestRule clientTestRule,
      ExternalEntityConfiguration<T> configuration) {
    this.clientTestRule = clientTestRule;
    this.configuration = configuration;
  }

  public void createEntity() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    T created = createEntity(form);

    assertNotNull(created);
    assertNotNull(((Identified) created).getId());
    Long createdEntityId = ((Identified<Long>) created).getId();

    T founded = findEntity(form, createdEntityId);
    assertThat(founded).isEqualTo(created);
  }

  public void updateEntity() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);

    T created = createEntity(form);
    Long createdEntityId = ((Identified<Long>) created).getId();

    T founded = findEntity(form, createdEntityId);

    configuration.updateEntity(founded);
    //founded.setOtherRelativeFirstName("FIRST_NAME");

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + configuration.getApiPath()
                + "/"
                + createdEntityId);

    T updated = target.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(founded, MediaType.APPLICATION_JSON_TYPE),
            configuration.getEntityClass());

    founded = findEntity(form, createdEntityId);

    assertThat(founded).isEqualTo(updated);
    assertThat(founded).isNotEqualTo(created);

  }

  public void getEntityById() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    T created = createEntity(form);
    Long createdEntityId = ((Identified<Long>) created).getId();
    T founded = findEntity(form, createdEntityId);
    assertThat(founded).isEqualTo(created);
  }

  public void getEntitiesByFormId() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    createEntity(form);
    createEntity(form);
    createEntity(form);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + configuration.getApiPath());
    CollectionDTO<T> entityCollectionDTO = target.request(MediaType.APPLICATION_JSON)
        .get(configuration.getEntityCollectionGenericType());

    assertThat(entityCollectionDTO.getCollection().size()).isEqualTo(3);
  }

  public void deleteEntity() throws Exception {
    RFA1aFormDTO form = createForm(clientTestRule);
    T created = createEntity(form);
    Long createdEntityId = ((Identified<Long>) created).getId();
    T founded = findEntity(form, createdEntityId);
    Long foundedId = ((Identified<Long>) founded).getId();

    assertThat(founded).isEqualTo(created);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + configuration.getApiPath()
                + "/"
                + foundedId);
    Response response = target.request(MediaType.APPLICATION_JSON).delete();
    assertThat(response.getStatus()).isEqualTo(200);
    response = target.request(MediaType.APPLICATION_JSON).delete();
    assertThat(response.getStatus()).isEqualTo(404);
  }

  private T createEntity(RFA1aFormDTO form) throws IOException {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath());
    T entity = configuration.createEntity();
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
  }

  private T findEntity(RFA1aFormDTO form, Long entityId) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + configuration.getApiPath()
                + "/"
                + entityId);
    return target.request(MediaType.APPLICATION_JSON).get(configuration.getEntityClass());
  }

}
