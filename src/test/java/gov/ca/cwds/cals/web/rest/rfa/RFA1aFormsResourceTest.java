package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import io.dropwizard.jackson.Jackson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsResourceTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test()
  public void testApplicationWithParts() throws Exception {
    String postRequest = fixture("fixtures/rfa/rfa-1a-form-post-request.json");
    WebTarget postTarget = clientTestRule.target(API.RFA_1A_FORMS);
    RFA1aFormDTO postResponseForm = postTarget.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(postRequest, MediaType.APPLICATION_JSON_TYPE), RFA1aFormDTO.class);
    Long id = postResponseForm.getId();

    VelocityHelper postVelocityResponseHelper = new VelocityHelper();
    postVelocityResponseHelper.setParameter("id", id);
    String expectedPostResponse =
        postVelocityResponseHelper.process("fixtures/rfa/rfa-1a-form-post-response.json");

    assertEqualsResponse(expectedPostResponse, transformDTOtoJSON(postResponseForm));

    WebTarget putApplicantTarget = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + id + "/" + API.RFA_1A_APPLICANTS);
    ApplicantDTO applicantDTO = putApplicantTarget.request(MediaType.APPLICATION_JSON)
        .post(Entity
                .entity(fixture("fixtures/rfa/rfa-1a-applicant.json"), MediaType.APPLICATION_JSON_TYPE),
            ApplicantDTO.class);
    String applicantFixture = Jackson.newObjectMapper().setSerializationInclusion(Include.NON_NULL)
        .writeValueAsString(applicantDTO);

    String putResidenceFixture = fixture("fixtures/rfa/rfa-1a-residence-request.json");
    WebTarget putResidenceTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/residence");
    putResidenceTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putResidenceFixture, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);


    String putApplicantsHistoryFixture = fixture("fixtures/rfa/rfa-1a-applicants-history.json");
    WebTarget putApplicantsHistoryTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/applicants-history");
    putApplicantsHistoryTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putApplicantsHistoryFixture, MediaType.APPLICATION_JSON_TYPE), ApplicantsHistoryDTO.class);


    String putChildDesiredFixture = fixture("fixtures/rfa/rfa-1a-child-desired.json");
    WebTarget putChildDesiredTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/child-desired");
    putChildDesiredTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putChildDesiredFixture, MediaType.APPLICATION_JSON_TYPE), ChildDesiredDTO.class);


    String putAdoptionHistoryFixture = fixture("fixtures/rfa/rfa-1a-adoption-history.json");
    WebTarget putAdoptionHistoryTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/adoption-history");
    putAdoptionHistoryTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putAdoptionHistoryFixture, MediaType.APPLICATION_JSON_TYPE), AdoptionHistoryDTO.class);


    String putReferencesFixture = fixture("fixtures/rfa/rfa-1a-references.json");
    WebTarget putReferencesTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/references");
    putReferencesTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(putReferencesFixture, MediaType.APPLICATION_JSON_TYPE), ReferencesDTO.class);


    String putApplicantsDeclarationFixture = fixture("fixtures/rfa/rfa-1a-applicants-declaration.json");
    WebTarget putApplicantsDeclarationTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "/applicants-declaration");
    putApplicantsDeclarationTarget.request(MediaType.APPLICATION_JSON)
        .put(
            Entity.entity(putApplicantsDeclarationFixture, MediaType.APPLICATION_JSON_TYPE),
            ApplicantsDeclarationDTO.class
        );


    WebTarget getTarget = clientTestRule.target(API.RFA_1A_FORMS + "/" + id);
    RFA1aFormDTO getResponseForm = getTarget.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    VelocityHelper getVelocityResponseHelper = new VelocityHelper();
    getVelocityResponseHelper.setParameter("id", id);
    String expectedGetResponse = getVelocityResponseHelper.process("fixtures/rfa/rfa-1a-form-get-response.json");

    assertEqualsResponse(expectedGetResponse, transformDTOtoJSON(getResponseForm));


    WebTarget getExpandedTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "/" + id + "?" + API.QueryParams.EXPANDED + "=true");
    RFA1aFormDTO getExpandedResponseForm =
        getExpandedTarget.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    VelocityHelper getVelocityExpandedResponseHelper = new VelocityHelper();
    getVelocityExpandedResponseHelper.setParameter("id", id);
    getVelocityExpandedResponseHelper.setParameter("residence", putResidenceFixture);
    getVelocityExpandedResponseHelper.setParameter("applicantsHistory", putApplicantsHistoryFixture);
    getVelocityExpandedResponseHelper.setParameter("adoptionHistory", putAdoptionHistoryFixture);
    getVelocityExpandedResponseHelper.setParameter("childDesired", putChildDesiredFixture);
    getVelocityExpandedResponseHelper.setParameter("references", putReferencesFixture);
    getVelocityExpandedResponseHelper.setParameter("applicantsDeclaration", putApplicantsDeclarationFixture);
    getVelocityExpandedResponseHelper.setParameter("applicant", applicantFixture);
    String expectedGetExpandedResponse =
        getVelocityExpandedResponseHelper.process("fixtures/rfa/rfa-1a-form-get-expanded-response.json");

    //assertEqualsResponse(expectedGetExpandedResponse, transformDTOtoJSON(getExpandedResponseForm));
    JSONAssert
        .assertEquals(expectedGetExpandedResponse, transformDTOtoJSON(getExpandedResponseForm),
            JSONCompareMode.STRICT);


    WebTarget getExpandedCollectionTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "?" + API.QueryParams.EXPANDED + "=true");
    CollectionDTO<RFA1aFormDTO> getExpandedCollectionResponseForm =
        getExpandedCollectionTarget.request(MediaType.APPLICATION_JSON)
            .get(new GenericType<CollectionDTO<RFA1aFormDTO>>() {});

    Collection<RFA1aFormDTO> filtered = getExpandedCollectionResponseForm.getCollection().stream()
        .filter(b -> b.getId().equals(id))
        .collect(Collectors.toCollection(ArrayList::new));
    getExpandedCollectionResponseForm.getCollection().clear();
    getExpandedCollectionResponseForm.getCollection().addAll(filtered);

    VelocityHelper getVelocityExpandedCollectionResponseHelper = new VelocityHelper();
    getVelocityExpandedCollectionResponseHelper.setParameter("form", expectedGetExpandedResponse);
    String expectedGetExpandedCollectionResponse =
        getVelocityExpandedCollectionResponseHelper
            .process("fixtures/rfa/rfa-1a-form-get-expanded-collection-response.json");

    assertEqualsResponse(expectedGetExpandedCollectionResponse, transformDTOtoJSON(getExpandedCollectionResponseForm));
  }

  @Test
  public void getApplicationFormNotFound() throws Exception {
    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/9999999");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
  }

  @Test
  public void getApplicationFormTest() throws Exception {
    RFA1aFormDTO rfaFormCreate = formAHelper.createRFA1aForm();

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + rfaFormCreate.getId());
    RFA1aFormDTO rfaFormGet = target.request(MediaType.APPLICATION_JSON).get(RFA1aFormDTO.class);

    assertNotNull(rfaFormGet);
    assertEquals(rfaFormCreate, rfaFormGet);
  }

  @Test
  public void updateApplicationFormTest() throws Exception {
    RFA1aFormDTO rfaFormCreate = formAHelper.createRFA1aForm();

    WebTarget target = clientTestRule.target(API.RFA_1A_FORMS + "/" + rfaFormCreate.getId());
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
    RFA1aFormDTO rfaFormCreate1 = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(rfaFormCreate1.getId(), new ApplicantDTO());
    RFA1aFormDTO rfaFormCreate2 = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(rfaFormCreate2.getId(), new ApplicantDTO());
    RFA1aFormDTO rfaFormCreate3 = formAHelper.createRFA1aForm();
    applicantHelper.postApplicant(rfaFormCreate3.getId(), new ApplicantDTO());

    assertNotEquals(rfaFormCreate1, rfaFormCreate2);
    assertNotEquals(rfaFormCreate2, rfaFormCreate3);
    assertNotEquals(rfaFormCreate3, rfaFormCreate1);

    CollectionDTO<RFA1aFormDTO> rfaForms = formAHelper.getRFA1aForms();

    assertTrue(rfaForms.getCollection().size() >= 3);

    List<RFA1aFormDTO> list = new ArrayList<>(rfaForms.getCollection());
    assertEquals(rfaFormCreate3, list.get(0));
    assertEquals(rfaFormCreate2, list.get(1));
    assertEquals(rfaFormCreate1, list.get(2));
  }
}
