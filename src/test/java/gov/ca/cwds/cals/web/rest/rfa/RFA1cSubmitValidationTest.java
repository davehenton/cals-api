package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixtureTemplate;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.IdentifiedChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author CWDS CALS API Team
 */
public class RFA1cSubmitValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void validateRFA1cFirstname() throws Exception {
    test(child -> child.setFirstName(null),
        "fixtures/rfa/validation/rfa1c/fra1c-first-name-is-required-response.json");
  }

  @Test
  public void validateRFA1cLastname() throws Exception {
    test(child -> child.setLastName(null),
        "fixtures/rfa/validation/rfa1c/fra1c-last-name-is-required-response.json");
  }

  @Test
  public void validateRFA1cDOB() throws Exception {
    test(child -> child.setDateOfBirth(null),
        "fixtures/rfa/validation/rfa1c/fra1c-dob-is-required-response.json");
  }

  @Test
  public void validateRFA1cInHome() throws Exception {
    test(child -> child.setChildInHome(null),
        "fixtures/rfa/validation/rfa1c/fra1c-in-home-is-required-response.json");
  }



  private void test(Consumer<IdentifiedChildDTO> childDTOConsumer, String fixture) throws Exception {
    RFA1aFormDTO rfa1aForm = formAHelper.createRfa1aForm();
    rfa1aForm = formAHelper.postRfa1aForm(rfa1aForm);

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicantHelper.postApplicant(rfa1aForm.getId(), applicant);

    ResidenceDTO residence = residenceHelper.buildResidenceDTO();
    residenceHelper.putResidence(rfa1aForm.getId(), residence);

    RFA1cFormDTO rfa1cFormDTO = formCHelper.postRfa1cForm(rfa1aForm.getId(), rfa1cForm(childDTOConsumer));

    Map<String, Object> params = new HashMap<>();
    params.put("id", rfa1cFormDTO.getId());

    String response = statusHelper.submitApplication(rfa1aForm.getId()).readEntity(String.class);
    System.out.println(response);
    assertResponseByFixtureTemplate(
        response,
        fixture,
        params, JSONCompareMode.LENIENT);
  }



  private RFA1cFormDTO rfa1cForm(Consumer<IdentifiedChildDTO> childDTOConsumer) {
    IdentifiedChildDTO identifiedChildDTO = new IdentifiedChildDTO();
    identifiedChildDTO.setFirstName("firstName");
    identifiedChildDTO.setLastName("lastName");
    identifiedChildDTO.setChildInHome(false);
    identifiedChildDTO.setDateOfBirth(LocalDate.now().minusYears(5));
    childDTOConsumer.accept(identifiedChildDTO);
    RFA1cFormDTO rfa1cFormDTO = new RFA1cFormDTO();
    rfa1cFormDTO.setIdentifiedChildren(Collections.singletonList(identifiedChildDTO));
    return rfa1cFormDTO;
  }

}

