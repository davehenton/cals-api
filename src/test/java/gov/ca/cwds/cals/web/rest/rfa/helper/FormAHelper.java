package gov.ca.cwds.cals.web.rest.rfa.helper;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.security.test.TestSecurityFilter;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import java.util.Optional;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

public class FormAHelper {

  private RestClientTestRule clientTestRule;

  public FormAHelper(RestClientTestRule clientTestRule) {
    this.clientTestRule = clientTestRule;
  }

  public CollectionDTO<RFA1aFormDTO> getRFA1aForms() {
    return getRFA1aForms(null);
  }

  public CollectionDTO<RFA1aFormDTO> getRFA1aForms(String pathToPrincipalJson) {
    WebTarget target = getWebTarget(API.RFA_1A_FORMS, pathToPrincipalJson);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    return invocation.get(new GenericType<CollectionDTO<RFA1aFormDTO>>() {
    });
  }

  public RFA1aFormDTO getRFA1aForm(Long formId) {
    return getRFA1aForm(formId, null);
  }

  public RFA1aFormDTO getRFA1aForm(Long formId, String pathToPrincipalJson) {
    WebTarget target = getWebTarget(API.RFA_1A_FORMS + "/" + formId, pathToPrincipalJson)
        .queryParam(API.QueryParams.EXPANDED, "true");
    return target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);
  }

  public RFA1aFormDTO createRFA1aForm() throws Exception {
    return createRFA1aForm(null);
  }


  public RFA1aFormDTO createRFA1aForm(String pathToPrincipalJson) throws Exception {
    RFA1aFormDTO rfaFormDTOBefore = createRfa1aForm();
    RFA1aFormDTO rfaFormDTOAfter = postRfa1aForm(rfaFormDTOBefore, pathToPrincipalJson);

    assertNotNull(rfaFormDTOAfter);
    assertNotNull(rfaFormDTOAfter.getId());
    assertFalse(rfaFormDTOAfter.isInitialApplication());
    assertEquals("otherDescription", rfaFormDTOAfter.getOtherTypeDescription());
    assertEquals(rfaFormDTOBefore.getApplicationCounty(), rfaFormDTOAfter.getApplicationCounty());
    return rfaFormDTOAfter;
  }

  public RFA1aFormDTO postRfa1aForm(RFA1aFormDTO rfa1aForm, String pathToPrincipalJson) {
    WebTarget target = getWebTarget(API.RFA_1A_FORMS, pathToPrincipalJson);
    return target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(rfa1aForm, MediaType.APPLICATION_JSON_TYPE), RFA1aFormDTO.class);
  }

  private WebTarget getWebTarget(String targetPath, String pathToPrincipalJson) {
    JsonIdentityAuthParams params = Optional.ofNullable(pathToPrincipalJson)
        .map(s -> new JsonIdentityAuthParams(fixture(s))).orElse(null);
    StringBuilder pathInfo = new StringBuilder(targetPath);
    Optional.ofNullable(pathToPrincipalJson).ifPresent(
        s -> pathInfo
            .append('?')
            .append(TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE)
            .append('=')
            .append(s));
    return clientTestRule.target(pathInfo.toString(), params);
  }

  public RFA1aFormDTO postRfa1aForm(RFA1aFormDTO rfa1aForm) {
    return postRfa1aForm(rfa1aForm, null);
  }

  public RFA1aFormDTO createRfa1aForm() throws Exception {
    RFA1aFormDTO form = new RFA1aFormDTO();
    CountyType county = new CountyType();
    county.setId(34L);
    county.setValue("Sacramento");
    form.setApplicationCounty(county);
    form.setInitialApplication(false);
    form.setOtherTypeDescription("otherDescription");
    return form;
  }

}
