package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.AssertResponseHelper.assertEqualsResponse;
import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.VelocityHelper;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsResourceTest extends BaseCalsApiIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void createApplicationForm() throws Exception {
    createForm(clientTestRule);
  }

  @Test
  public void testApplicationWithParts() throws Exception {
    String postRequest = fixture("fixtures/rfa/rfa-1a-form-post-request.json");
    WebTarget postTarget = clientTestRule.target(API.RFA_1A_FORMS);
    RFA1aFormDTO postResponseForm = postTarget.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(postRequest, MediaType.APPLICATION_JSON_TYPE), RFA1aFormDTO.class);
    Long id = postResponseForm.getId();

    VelocityHelper postVelocityResponseHelper = new VelocityHelper("fixtures/rfa/rfa-1a-form-post-response.json");
    postVelocityResponseHelper.setParameter("id", id);
    String expectedPostResponse = postVelocityResponseHelper.process();

    assertEqualsResponse(expectedPostResponse, transformDTOtoJSON(postResponseForm));


    String putResidenceRequest = fixture("fixtures/rfa/rfa-1a-residence-request.json");
    WebTarget putResidenceTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/residence");
    putResidenceTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putResidenceRequest, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);


    String putApplicantsHistoryRequest = fixture("fixtures/rfa/rfa-1a-applicants-history.json");
    WebTarget putApplicantsHistoryTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/applicants-history");
    putApplicantsHistoryTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putApplicantsHistoryRequest, MediaType.APPLICATION_JSON_TYPE), ApplicantsHistoryDTO.class);


    String putChildDesiredRequest = fixture("fixtures/rfa/rfa-1a-child-desired.json");
    WebTarget putChildDesiredTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/child-desired");
    putChildDesiredTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putChildDesiredRequest, MediaType.APPLICATION_JSON_TYPE), ChildDesiredDTO.class);


    String putAdoptionHistoryRequest = fixture("fixtures/rfa/rfa-1a-adoption-history.json");
    WebTarget putAdoptionHistoryTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/adoption-history");
    putAdoptionHistoryTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putAdoptionHistoryRequest, MediaType.APPLICATION_JSON_TYPE), AdoptionHistoryDTO.class);


    String putReferencesRequest = fixture("fixtures/rfa/rfa-1a-references.json");
    WebTarget putReferencesTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/references");
    putReferencesTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putReferencesRequest, MediaType.APPLICATION_JSON_TYPE), ReferencesDTO.class);


    String putApplicantsDeclarationRequest = fixture("fixtures/rfa/rfa-1a-applicants-declaration.json");
    WebTarget putApplicantsDeclarationTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/applicants-declaration");
    putApplicantsDeclarationTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putApplicantsDeclarationRequest, MediaType.APPLICATION_JSON_TYPE), ApplicantsDeclarationDTO.class);


    WebTarget getTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id);
    RFA1aFormDTO getResponseForm = getTarget.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    VelocityHelper getVelocityResponseHelper = new VelocityHelper("fixtures/rfa/rfa-1a-form-get-response.json");
    getVelocityResponseHelper.setParameter("id", id);
    String expectedGetResponse = getVelocityResponseHelper.process();

    assertEqualsResponse(expectedGetResponse, transformDTOtoJSON(getResponseForm));


    WebTarget getExpandedTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "?" + API.QueryParams.EXPANDED + "=true");
    RFA1aFormDTO getExpandedResponseForm = getExpandedTarget.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    VelocityHelper getVelocityExpandedResponseHelper = new VelocityHelper("fixtures/rfa/rfa-1a-form-get-expanded-response.json");
    getVelocityExpandedResponseHelper.setParameter("id", id);
    String expectedGetExpandedResponse = getVelocityExpandedResponseHelper.process();

    assertEqualsResponse(expectedGetExpandedResponse, transformDTOtoJSON(getExpandedResponseForm));

    WebTarget getExpandedCollectionTarget = clientTestRule.target(API.RFA_1A_FORMS + "?" + API.QueryParams.EXPANDED + "=true");
    CollectionDTO<RFA1aFormDTO> getExpandedCollectionResponseForm = getExpandedCollectionTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<CollectionDTO<RFA1aFormDTO>>() {});

    VelocityHelper getVelocityExpandedCollectionResponseHelper = new VelocityHelper("fixtures/rfa/rfa-1a-form-get-expanded-collection-response.json");
    getVelocityExpandedCollectionResponseHelper.setParameter("id", id);
    String expectedGetExpandedCollectionResponse = getVelocityExpandedCollectionResponseHelper.process();

    assertEqualsResponse(expectedGetExpandedCollectionResponse, transformDTOtoJSON(getExpandedCollectionResponseForm));
  }

  @Test
  public void getApplicationFormNotFound() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    target = clientTestRule.target(API.RFA_1A_FORMS + "/9999999");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getApplicationFormTest() throws Exception {
    RFA1aFormDTO rfaFormCreate = createForm(clientTestRule);

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    target = clientTestRule.target(API.RFA_1A_FORMS + "/" + rfaFormCreate.getId());
    RFA1aFormDTO rfaFormGet = target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    assertNotNull(rfaFormGet);
    assertEquals(rfaFormCreate, rfaFormGet);
  }

  @Test
  public void updateApplicationFormTest() throws Exception {
    RFA1aFormDTO rfaFormCreate = createForm(clientTestRule);

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    target = clientTestRule.target(API.RFA_1A_FORMS + "/" + rfaFormCreate.getId());
    rfaFormCreate.setOtherTypeDescription("newOtherTypeDescription");
    RFA1aFormDTO rfaFormGet =
        target
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(rfaFormCreate, MediaType.APPLICATION_JSON_TYPE), RFA1aFormDTO.class);

    assertNotNull(rfaFormGet);
    assertEquals(rfaFormCreate, rfaFormGet);
  }

  @Test
  public void getAllApplicationFormsTest() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    RFA1aFormDTO rfaFormCreate1 = createForm(clientTestRule);
    RFA1aFormDTO rfaFormCreate2 = createForm(clientTestRule);
    RFA1aFormDTO rfaFormCreate3 = createForm(clientTestRule);

    assertNotEquals(rfaFormCreate1, rfaFormCreate2);
    assertNotEquals(rfaFormCreate2, rfaFormCreate3);
    assertNotEquals(rfaFormCreate3, rfaFormCreate1);

    target = clientTestRule.target(API.RFA_1A_FORMS);
    invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<RFA1aFormDTO> rfaForms =
        invocation.get(new GenericType<CollectionDTO<RFA1aFormDTO>>() {});

    assertTrue(rfaForms.getCollection().size() >= 3);
  }
}
