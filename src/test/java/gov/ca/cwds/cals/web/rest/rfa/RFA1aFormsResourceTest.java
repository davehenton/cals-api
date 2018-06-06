package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aAdoptionHistoryResourceTest.ADOPTION_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANT_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest.APPLICANT_FIXTURE2;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsDeclarationResourceTest.APPLICANTS_DECLARATION_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsHistoryResourceTest.APPLICANTS_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsRelationshipResourceTest.APPLICANTS_RELATIONSHIP_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aChildDesiredResourceTest.CHILD_DESIRED_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResourceTest.MINOR_CHILDREN_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResourceTest.OTHER_ADULTS_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aReferencesResourceTest.REFERENCES_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResourceTest.RESIDENCE_FIXTURE;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationshipDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1aFormCollectionDTO;
import gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils;
import io.dropwizard.testing.FixtureHelpers;
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
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsResourceTest extends BaseRFAIntegrationTest {

  private static final String PRINCIPAL_STAFF_ID_0X6_JSON = "security/principal-staffId-0X6.json";

  public static final String RFA_1A_FIXTURE = FixtureHelpers
      .fixture("fixtures/rfa/rfa-1a-form-meta-data.json");

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test()
  public void testApplicationWithParts() throws Exception {
    RFA1aFormDTO postFormRequest = clientTestRule.getMapper()
        .readValue(RFA_1A_FIXTURE, RFA1aFormDTO.class);
    RFA1aFormDTO postFormResponse = clientTestRule.target(API.RFA_1A_FORMS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(postFormRequest, MediaType.APPLICATION_JSON_TYPE), RFA1aFormDTO.class);
    Long formId = postFormResponse.getId();
    postFormRequest.setId(formId);
    assertEqualsResponse(transformDTOtoJSON(postFormRequest), transformDTOtoJSON(postFormResponse));

    ApplicantDTO applicantDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(APPLICANT_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ApplicantDTO.class);

    ApplicantDTO applicantDTO2 = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(APPLICANT_FIXTURE2, MediaType.APPLICATION_JSON_TYPE),
            ApplicantDTO.class);

    ResidenceDTO residenceDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_RESIDENCE)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(RESIDENCE_FIXTURE, MediaType.APPLICATION_JSON_TYPE), ResidenceDTO.class);

    ApplicantsRelationshipDTO applicantsRelationshipDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS_RELATIONSHIP)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(APPLICANTS_RELATIONSHIP_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ApplicantsRelationshipDTO.class);

    MinorChildDTO minorChildDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_MINOR_CHILDREN)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(MINOR_CHILDREN_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            MinorChildDTO.class);

    OtherAdultDTO otherAdultDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_OTHER_ADULTS)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(OTHER_ADULTS_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            OtherAdultDTO.class);

    ApplicantsHistoryDTO applicantsHistoryDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS_HISTORY)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(APPLICANTS_HISTORY_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ApplicantsHistoryDTO.class);

    ChildDesiredDTO childDesiredDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_CHILD_DESIRED)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(CHILD_DESIRED_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ChildDesiredDTO.class);

    AdoptionHistoryDTO adoptionHistoryDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_ADOPTION_HISTORY)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(ADOPTION_HISTORY_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            AdoptionHistoryDTO.class);

    ReferencesDTO referencesDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_REFERENCES)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(REFERENCES_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ReferencesDTO.class);

    ApplicantsDeclarationDTO applicantsDeclarationDTO = clientTestRule
        .target(API.RFA_1A_FORMS + "/" + formId + "/" + API.RFA_1A_APPLICANTS_DECLARATION)
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(APPLICANTS_DECLARATION_FIXTURE, MediaType.APPLICATION_JSON_TYPE),
            ApplicantsDeclarationDTO.class);

    RFA1aFormDTO getFormResponse = clientTestRule.target(API.RFA_1A_FORMS + "/" + formId)
        .request(MediaType.APPLICATION_JSON)
        .get(RFA1aFormDTO.class);

    AssertFixtureUtils.assertResponseByFixture(transformDTOtoJSON(getFormResponse),
        transformDTOtoJSON(postFormResponse),
        JSONCompareMode.LENIENT);

    RFA1aFormDTO expectedRfa1aFormDTO = clientTestRule.getMapper()
        .readValue(RFA_1A_FIXTURE, RFA1aFormDTO.class);
    expectedRfa1aFormDTO.setId(formId);

    List<ApplicantDTO> applicants = new ArrayList<>();
    applicants.add(applicantDTO);
    applicants.add(applicantDTO2);
    expectedRfa1aFormDTO.setApplicants(applicants);

    expectedRfa1aFormDTO.setResidence(residenceDTO);
    expectedRfa1aFormDTO.setApplicantsRelationship(applicantsRelationshipDTO);

    List<MinorChildDTO> minorChildren = new ArrayList<>();
    minorChildren.add(minorChildDTO);
    expectedRfa1aFormDTO.setMinorChildren(minorChildren);

    List<OtherAdultDTO> otherAdults = new ArrayList<>();
    otherAdults.add(otherAdultDTO);
    expectedRfa1aFormDTO.setOtherAdults(otherAdults);

    expectedRfa1aFormDTO.setApplicantsHistory(applicantsHistoryDTO);
    expectedRfa1aFormDTO.setChildDesired(childDesiredDTO);
    expectedRfa1aFormDTO.setAdoptionHistory(adoptionHistoryDTO);
    expectedRfa1aFormDTO.setReferences(referencesDTO);
    expectedRfa1aFormDTO.setApplicantsDeclaration(applicantsDeclarationDTO);

    RFA1aFormDTO getExpandedFormResponse =
        clientTestRule
            .target(API.RFA_1A_FORMS + "/" + formId + "?" + API.QueryParams.EXPANDED + "=true")
            .request(MediaType.APPLICATION_JSON)
            .get(RFA1aFormDTO.class);

    AssertFixtureUtils.assertResponseByFixture(transformDTOtoJSON(getExpandedFormResponse),
        transformDTOtoJSON(expectedRfa1aFormDTO), JSONCompareMode.LENIENT);

    WebTarget getExpandedCollectionTarget =
        clientTestRule.target(API.RFA_1A_FORMS + "?" + API.QueryParams.EXPANDED + "=true");
    CollectionDTO<RFA1aFormDTO> getExpandedCollectionResponseForm =
        getExpandedCollectionTarget.request(MediaType.APPLICATION_JSON)
            .get(new GenericType<CollectionDTO<RFA1aFormDTO>>() {
            });

    Collection<RFA1aFormDTO> filtered = getExpandedCollectionResponseForm.getCollection().stream()
        .filter(b -> b.getId().equals(formId))
        .collect(Collectors.toCollection(ArrayList::new));
    getExpandedCollectionResponseForm.getCollection().clear();
    getExpandedCollectionResponseForm.getCollection().addAll(filtered);

    List<RFA1aFormDTO> items = new ArrayList<>();
    items.add(expectedRfa1aFormDTO);
    RFA1aFormCollectionDTO expectedExpandedRfa1aFormCollectionResponse = new RFA1aFormCollectionDTO(
        items);

    // TODO: figure out why this assertion failing
//    AssertFixtureUtils
//        .assertResponseByFixture(transformDTOtoJSON(getExpandedCollectionResponseForm),
//            transformDTOtoJSON(expectedExpandedRfa1aFormCollectionResponse),
//            JSONCompareMode.LENIENT);
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

}
