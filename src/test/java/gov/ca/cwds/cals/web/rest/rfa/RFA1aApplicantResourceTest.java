package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixture;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aApplicantResourceTest extends
    BaseExternalEntityApiTest<ApplicantDTO> {

  public static String APPLICANTS_FIXTURE_PATH = "fixtures/rfa/rfa-1a-applicant.json";

  @Override
  protected BaseExternalEntityApiHelper<ApplicantDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<ApplicantDTO> configuration =
        new TestExternalEntityConfiguration<ApplicantDTO>(
            clientTestRule, ApplicantDTO.class, API.RFA_1A_APPLICANTS) {

          @Override
          protected String getCreateFixture() {
            return APPLICANTS_FIXTURE_PATH;
          }

          @Override
          public GenericType<CollectionDTO<ApplicantDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<ApplicantDTO>>() {
            };
          }

          @Override
          public void modifyEntity(ApplicantDTO entity) {
            entity.setFirstName("testFirstName");
          }
        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

  @Test(expected = ClientErrorException.class) //it's forbidden to create several equal applicants
  @Override
  public void getEntitiesByFormId() throws Exception {
    getExternalEntityApiHelper().getEntitiesByFormId();
  }

  @Test
  public void checkMaxFirstNameSizeTest() throws IOException, JSONException {
    try {
      ApplicantDTO applicantDTO = getApplicantDTO();
      applicantDTO.setFirstName("12345678901234567890x");
      RFA1aFormDTO form = createForm(clientTestRule);
      postApplicant(form, applicantDTO);
      fail();
    } catch (ClientErrorException e) {
      Map<String, Object> parameters = new HashMap<>();
      parameters
          .put("user_message", "firstName 12345678901234567890x exceeds maximum length of 20");
      parameters.put("code", "?");
      checkValidationResponse(e, parameters);
    }
  }

  @Test
  public void checkFirstNameAlphanumericTest() throws IOException, JSONException {
    try {
      ApplicantDTO applicantDTO = getApplicantDTO();
      applicantDTO.setFirstName("l@4");
      RFA1aFormDTO form = createForm(clientTestRule);
      postApplicant(form, applicantDTO);
      fail();
    } catch (ClientErrorException e) {
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("user_message",
          "firstName l@4 is invalid. Only alphanumerical characters and spaces are allowed");
      parameters.put("code", "?");
      checkValidationResponse(e, parameters);
    }
  }

  @Test
  public void postDuplicateApplicantsValidationTest() throws IOException {
    try {
      RFA1aFormDTO form = createForm(clientTestRule);
      postApplicant(form, getApplicantDTO());
      postApplicant(form, getApplicantDTO());
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
    }
  }

  private ApplicantDTO getApplicantDTO() throws IOException {
    return clientTestRule.getMapper()
        .readValue(fixture(APPLICANTS_FIXTURE_PATH), ApplicantDTO.class);
  }

  @Test
  public void postDuplicateApplicantLastNameValidationTest() throws IOException {
    try {
      RFA1aFormDTO form = createForm(clientTestRule);

      ApplicantDTO firstApplicant = postApplicant(form, getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setLastName("differentName");
      secondApplicant = postApplicant(form, secondApplicant);
      secondApplicant.setLastName(firstApplicant.getLastName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      assertEqualsResponse(
          fixture("fixtures/rfa/validation/applicant-duplicate-name-violation-response.json"),
          getEntityFromException(e));
    }
  }

  @Test
  public void postDuplicateApplicantFirstNameValidationTest() throws IOException {
    try {
      RFA1aFormDTO form = createForm(clientTestRule);

      ApplicantDTO firstApplicant = postApplicant(form, getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setFirstName("differentName");
      secondApplicant = postApplicant(form, secondApplicant);
      secondApplicant.setFirstName(firstApplicant.getFirstName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      assertEqualsResponse(
          fixture("fixtures/rfa/validation/applicant-duplicate-name-violation-response.json"),
          getEntityFromException(e));
    }
  }

  @Test
  public void postDuplicateApplicantMiddleNameValidationTest() throws IOException {
    try {
      RFA1aFormDTO form = createForm(clientTestRule);

      ApplicantDTO firstApplicant = postApplicant(form, getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setMiddleName("differentName");
      secondApplicant = postApplicant(form, secondApplicant);
      secondApplicant.setMiddleName(firstApplicant.getMiddleName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      assertEqualsResponse(
          fixture("fixtures/rfa/validation/applicant-duplicate-name-violation-response.json"),
          getEntityFromException(e));
    }
  }

  @Test
  public void moreThenOnePreferredNumberInApplicantValidationTest() throws IOException {
    RFA1aFormDTO form = createForm(clientTestRule);
    ApplicantDTO applicant = getApplicantDTO();
    applicant.getPhones().forEach(p -> p.setPreferred(true));
    try {
      applicant = postApplicant(form, applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      assertEqualsResponse(
          fixture("fixtures/rfa/validation/applicant-more-then-one-preferred-number-response.json"),
          getEntityFromException(e));
    }

    // Update test
    applicant.getPhones().forEach(p -> p.setPreferred(false));
    applicant = postApplicant(form, applicant);

    try {
      applicant.getPhones().forEach(p -> p.setPreferred(true));
      putApplicant(form, applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      assertEqualsResponse(
          fixture("fixtures/rfa/validation/applicant-more-then-one-preferred-number-response.json"),
          getEntityFromException(e));
    }
  }


  private ApplicantDTO postApplicant(RFA1aFormDTO form, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS);
    return target.request(MediaType.APPLICATION_JSON).post(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);
  }

  private ApplicantDTO putApplicant(RFA1aFormDTO form, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS + "/" + applicantDTO
                .getId());
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);
  }

  private String getEntityFromException(ClientErrorException e) throws IOException {
    return IOUtils.toString((InputStream) e.getResponse().getEntity(), "UTF-8");
  }

  private void checkValidationResponse(ClientErrorException e, Map<String, Object> parameters)
      throws IOException, JSONException {
    assertEquals(422, e.getResponse().getStatus());
    String entity = e.getResponse().readEntity(String.class);
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameters(parameters);
    assertResponseByFixture(entity,
        velocityHelper.process("fixtures/rfa/validation/validation_error_response.json"));
  }

}
