package gov.ca.cwds.cals.web.rest.rfa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormAHelper;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team
 */

public class BaseExternalEntityApiHelper<T extends RFAExternalEntityDTO> implements
    ExternalEntityApiHelper {

  private RestClientTestRule clientTestRule;
  private TestExternalEntityConfiguration<T> configuration;
  private FormAHelper helper;

  public BaseExternalEntityApiHelper(RestClientTestRule clientTestRule,
      TestExternalEntityConfiguration<T> configuration, FormAHelper helper) {
    this.clientTestRule = clientTestRule;
    this.configuration = configuration;
    this.helper = helper;
  }

  public void createEntity() throws Exception {
    RFA1aFormDTO form = helper.createRFA1aForm();
    T created = createEntity(form);

    assertNotNull(created);
    assertNotNull(created.getId());
    Long createdEntityId = created.getId();

    T found = findEntity(form, createdEntityId);
    assertThat(found).isEqualTo(created);
  }

  public void updateEntity() throws Exception {
    RFA1aFormDTO form = helper.createRFA1aForm();

    T created = createEntity(form);
    Long createdEntityId = created.getId();

    T found = findEntity(form, createdEntityId);

    configuration.modifyEntity(found);

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
        .put(Entity.entity(found, MediaType.APPLICATION_JSON_TYPE),
            configuration.getEntityClass());

    found = findEntity(form, createdEntityId);

    assertThat(found).isEqualTo(updated);
    assertThat(found).isNotEqualTo(created);

  }

  public void getEntityById() throws Exception {
    RFA1aFormDTO form = helper.createRFA1aForm();
    T created = createEntity(form);
    Long createdEntityId = created.getId();
    T found = findEntity(form, createdEntityId);
    assertThat(found).isEqualTo(created);
  }

  public void getEntitiesByFormId() throws Exception {
    RFA1aFormDTO form = helper.createRFA1aForm();
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
        .get(configuration.getCollectionDTOGenericType());

    assertThat(entityCollectionDTO.getCollection().size()).isEqualTo(3);
  }

  public void deleteEntity() throws Exception {
    RFA1aFormDTO form = helper.createRFA1aForm();
    T created = createEntity(form);
    Long createdEntityId = created.getId();
    T found = findEntity(form, createdEntityId);
    Long foundId = found.getId();

    assertThat(found).isEqualTo(created);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS
                + "/"
                + form.getId()
                + "/"
                + configuration.getApiPath()
                + "/"
                + foundId);
    Response response = target.request(MediaType.APPLICATION_JSON).delete();
    assertThat(response.getStatus()).isEqualTo(200);
    response = target.request(MediaType.APPLICATION_JSON).delete();
    assertThat(response.getStatus()).isEqualTo(404);
  }

  protected T createEntity(RFA1aFormDTO form) throws Exception {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath());
    T entity = configuration.createEntity();
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
  }

  protected T findEntity(RFA1aFormDTO form, Long entityId) throws Exception {
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
