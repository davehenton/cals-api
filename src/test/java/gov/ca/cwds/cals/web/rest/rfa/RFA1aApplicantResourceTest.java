package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.Validation.Business.Code.APPLICANT_PHONE_NUMBERS_DUPLICATION;
import static gov.ca.cwds.cals.web.rest.rfa.helper.PhoneDTOHelper.createPhone;
import static gov.ca.cwds.cals.web.rest.rfa.helper.PhoneDTOHelper.createPhoneNoExtension;
import static gov.ca.cwds.cals.web.rest.rfa.helper.PhoneDTOHelper.createPhoneNumberType;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixture;
import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixtureTemplate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import gov.ca.cwds.rest.exception.BaseExceptionResponse;
import gov.ca.cwds.rest.exception.IssueDetails;
import io.dropwizard.testing.FixtureHelpers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aApplicantResourceTest extends BaseExternalEntityApiTest<ApplicantDTO> {

  public static String APPLICANT_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-applicant.json");
  public static String APPLICANT_FIXTURE2 = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-applicant2.json");


  @Override
  protected BaseExternalEntityApiHelper<ApplicantDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<ApplicantDTO> configuration =
        new TestExternalEntityConfiguration<ApplicantDTO>(
            clientTestRule, ApplicantDTO.class, API.RFA_1A_APPLICANTS) {

          @Override
          protected String getFixture() {
            return APPLICANT_FIXTURE;
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

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

  @Test(expected = ClientErrorException.class) //it's forbidden to create several equal applicants
  @Override
  public void getEntitiesByFormId() throws Exception {
    getExternalEntityApiHelper().getEntitiesByFormId();
  }

  @Test
  public void checkMaxFirstNameSizeTest() throws Exception {
    try {
      ApplicantDTO applicantDTO = getApplicantDTO();
      applicantDTO.setFirstName("12345678901234567890x");
      RFA1aFormDTO form = formAHelper.createRFA1aForm();
      applicantHelper.postApplicant(form.getId(), applicantDTO);
      fail();
    } catch (ClientErrorException e) {
      Map<String, Object> parameters = new HashMap<>();

      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());
      parameters.put("property", "firstName");
      parameters.put("invalid_value", "12345678901234567890x");

      parameters
          .put("user_message", "12345678901234567890x exceeds maximum length of 20");
      checkValidationResponse(e, parameters);
    }
  }

  @Test
  public void checkMaxLastNameSizeTest() throws Exception {
    try {
      ApplicantDTO applicantDTO = getApplicantDTO();
      applicantDTO.setLastName("1234567890123456789012345X");
      RFA1aFormDTO form = formAHelper.createRFA1aForm();
      applicantHelper.postApplicant(form.getId(), applicantDTO);
      fail();
    } catch (ClientErrorException e) {
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());
      parameters.put("property", "lastName");
      parameters.put("invalid_value", "1234567890123456789012345X");
      parameters
          .put("user_message", "1234567890123456789012345X exceeds maximum length of 25");
      checkValidationResponse(e, parameters);
    }
  }

  @Test
  public void postDuplicateApplicantsValidationTest() throws Exception {
    try {
      RFA1aFormDTO form = formAHelper.createRFA1aForm();
      applicantHelper.postApplicant(form.getId(), getApplicantDTO());
      applicantHelper.postApplicant(form.getId(), getApplicantDTO());
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
    }
  }

  private ApplicantDTO getApplicantDTO() throws IOException {
    return clientTestRule.getMapper()
        .readValue(APPLICANT_FIXTURE, ApplicantDTO.class);
  }

  @Test
  public void postDuplicateApplicantLastNameValidationTest() throws Exception {
    try {
      RFA1aFormDTO form = formAHelper.createRFA1aForm();

      ApplicantDTO firstApplicant = applicantHelper.postApplicant(form.getId(), getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setLastName("differentName");
      secondApplicant = applicantHelper.postApplicant(form.getId(), secondApplicant);
      secondApplicant.setLastName(firstApplicant.getLastName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-duplicate-name-violation-response.json",
          parameters);

    }
  }

  @Test
  public void postDuplicateApplicantFirstNameValidationTest() throws Exception {
    try {
      RFA1aFormDTO form = formAHelper.createRFA1aForm();

      ApplicantDTO firstApplicant = applicantHelper.postApplicant(form.getId(), getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setFirstName("differentName");
      secondApplicant = applicantHelper.postApplicant(form.getId(), secondApplicant);
      secondApplicant.setFirstName(firstApplicant.getFirstName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-duplicate-name-violation-response.json",
          parameters);
    }
  }

  @Test
  public void postDuplicateApplicantMiddleNameValidationTest() throws Exception {
    try {
      RFA1aFormDTO form = formAHelper.createRFA1aForm();

      ApplicantDTO firstApplicant = applicantHelper.postApplicant(form.getId(), getApplicantDTO());
      ApplicantDTO secondApplicant = getApplicantDTO();
      secondApplicant.setMiddleName("differentName");
      secondApplicant = applicantHelper.postApplicant(form.getId(), secondApplicant);
      secondApplicant.setMiddleName(firstApplicant.getMiddleName());
      putApplicant(form, secondApplicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-duplicate-name-violation-response.json",
          parameters);
    }
  }

  @Test
  public void moreThenOnePreferredNumberInApplicantValidationTest() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();
    applicant.getPhones().forEach(p -> p.setPreferred(true));
    try {
      applicant = applicantHelper.postApplicant(form.getId(), applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-more-then-one-preferred-number-response.json",
          parameters);

    }

    // Update test
    applicant.getPhones().forEach(p -> p.setPreferred(false));
    applicant = applicantHelper.postApplicant(form.getId(), applicant);

    try {
      applicant.getPhones().forEach(p -> p.setPreferred(true));
      putApplicant(form, applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-more-then-one-preferred-number-response.json",
          parameters);
    }
  }

  @Test
  public void testDuplicatePhoneNumbersWithExtension() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();

    applicant.getPhones().clear();
    applicant.getPhones().add(createPhone());
    applicant.getPhones().add(createPhone());

    try {
      applicantHelper.postApplicant(form.getId(), applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-duplicate-phone-numbers-with-extensions-response.json",
          parameters);
    }
  }

  @Test
  public void testDuplicatePhoneNumbers() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();

    applicant.getPhones().clear();
    applicant.getPhones().add(createPhoneNoExtension());
    applicant.getPhones().add(createPhoneNoExtension());

    try {
      applicantHelper.postApplicant(form.getId(), applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
          "fixtures/rfa/validation/applicant-duplicate-phone-numbers-response.json",
          parameters);
    }
  }

  @Test
  public void testDuplicatePhoneNumbersButExtension() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();
    applicant.getPhones().clear();
    applicant.getPhones().add(createPhone());
    applicant.getPhones().add(createPhoneNoExtension());
    assertNoSuchError(() -> applicantHelper.postApplicant(form.getId(), applicant), APPLICANT_PHONE_NUMBERS_DUPLICATION);
  }

  @Test
  public void testBlankPhonesAreNotDuplicated() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();
    applicant.getPhones().clear();
    applicant.getPhones().add(createPhone(" ", "   ", false, createPhoneNumberType()));
    applicant.getPhones().add(createPhone(null, "     ", false, createPhoneNumberType()));
    assertNoSuchError(() -> applicantHelper.postApplicant(form.getId(), applicant), APPLICANT_PHONE_NUMBERS_DUPLICATION);
  }

  private void assertNoSuchError(Runnable runnable, String errorCode) {
    try {
      runnable.run();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());
      BaseExceptionResponse exceptionResponse = e.getResponse()
          .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      assert issueDetails.stream().noneMatch(issueDetail -> errorCode.equals(issueDetail.getCode()));
    }
  }

  @Test
  public void testEmptyFirstNameValidation() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();

    applicant.setFirstName(" ");
    applicant.setLastName("setLastName");

    try {
      applicantHelper.postApplicant(form.getId(), applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
              .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
              "fixtures/rfa/validation/applicant-empty-first-name-response.json",
              parameters);
    }
  }

  @Test
  public void testEmptyLastNameValidation() throws Exception {
    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    ApplicantDTO applicant = getApplicantDTO();

    applicant.setFirstName("setFirstName");
    applicant.setLastName(" ");

    try {
      applicantHelper.postApplicant(form.getId(), applicant);
      fail();
    } catch (ClientErrorException e) {
      assertEquals(422, e.getResponse().getStatus());

      String entity = e.getResponse().readEntity(String.class);
      Map<String, Object> parameters = new HashMap<>();
      BaseExceptionResponse exceptionResponse = e.getResponse()
              .readEntity(BaseExceptionResponse.class);
      Set<IssueDetails> issueDetails = exceptionResponse.getIssueDetails();
      IssueDetails detail = issueDetails.iterator().next();
      parameters.put("incident_id", detail.getIncidentId());

      assertResponseByFixtureTemplate(entity,
              "fixtures/rfa/validation/applicant-empty-last-name-response.json",
              parameters);
    }
  }

  private ApplicantDTO putApplicant(RFA1aFormDTO form, ApplicantDTO applicantDTO) {
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_APPLICANTS + "/" + applicantDTO
                .getId());
    return target.request(MediaType.APPLICATION_JSON).put(
        Entity.entity(applicantDTO, MediaType.APPLICATION_JSON_TYPE), ApplicantDTO.class);
  }

  private void checkValidationResponse(ClientErrorException e, Map<String, Object> parameters)
      throws IOException, JSONException {
    assertEquals(422, e.getResponse().getStatus());
    String entity = e.getResponse().readEntity(String.class);
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameters(parameters);
    assertResponseByFixture(entity,
        velocityHelper.process("fixtures/rfa/validation/validation-error-response.json"));
  }

}
