package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.AssertResponseHelper.assertEqualsResponse;
import static gov.ca.cwds.cals.web.rest.rfa.RFAHelper.createForm;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
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

  @Test
  public void applicantInvalidPhoneExtensionTest() {

  }

}
