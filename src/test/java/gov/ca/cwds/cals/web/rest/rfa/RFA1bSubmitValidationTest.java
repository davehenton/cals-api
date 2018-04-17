package gov.ca.cwds.cals.web.rest.rfa;

import java.util.*;
import java.util.function.Consumer;
import gov.ca.cwds.cals.service.dto.rfa.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;


import static gov.ca.cwds.cals.web.rest.utils.AssertFixtureUtils.assertResponseByFixtureTemplate;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bSubmitValidationTest extends BaseRFAIntegrationTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void validateRFA1bCounty() throws Exception {
    test(rfa1bForm -> rfa1bForm.setApplicationCounty(null),
        "fixtures/rfa/validation/rfa1b/fra1b-has-no-county-response.json");
  }

  @Test
  public void validateRFA1bFirstName() throws Exception {
    test(rfa1bForm -> rfa1bForm.setApplicantFirstName(null),
        "fixtures/rfa/validation/rfa1b/fra1b-has-no-first-name-response.json");
  }

  @Test
  public void validateRFA1bLastName() throws Exception {
    test(rfa1bForm -> rfa1bForm.setApplicantLastName(null),
        "fixtures/rfa/validation/rfa1b/fra1b-has-no-last-name-response.json");
  }

  @Test
  public void validateRFA1bConvictedCAOffense() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInCalifornia(true);
          rfa1bForm.setConvictedInCaliforniaDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffense(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-CA-offense-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedCAWhen() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInCalifornia(true);
          rfa1bForm.setConvictedInCaliforniaDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setWhenOffenseHappen(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-CA-when-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedCADetails() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInCalifornia(true);
          rfa1bForm.setConvictedInCaliforniaDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffenseDetails(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-CA-details-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedCADisclosure() throws Exception {
    test(rfa1bForm -> {
      rfa1bForm.setConvictedInCalifornia(true);
      rfa1bForm.setConvictedInCaliforniaDisclosures(null);
      },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-CA-disclosure-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedOutsideCAOffense() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInAnotherState(true);
          rfa1bForm.setConvictedInAnotherStateDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffense(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-outside-CA-offense-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedOutsideCAWhen() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInAnotherState(true);
          rfa1bForm.setConvictedInAnotherStateDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setWhenOffenseHappen(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-outside-CA-when-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedOutsideCADetails() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInAnotherState(true);
          rfa1bForm.setConvictedInAnotherStateDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffenseDetails(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-outside-CA-details-is-required-response.json");
  }

  @Test
  public void validateRFA1bConvictedOutsideCADisclosure() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setConvictedInAnotherState(true);
          rfa1bForm.setConvictedInAnotherStateDisclosures(null);
        },
        "fixtures/rfa/validation/rfa1b/fra1b-conviction-outside-CA-disclosure-is-required-response.json");
  }

  @Test
  public void validateRFA1bCrimeOffense() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setArrestedForCrime(true);
          rfa1bForm.setArrestedForCrimeDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffense(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-crime-offense-is-required-response.json");
  }

  @Test
  public void validateRFA1bCrimeWhen() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setArrestedForCrime(true);
          rfa1bForm.setArrestedForCrimeDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setWhenOffenseHappen(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-crime-when-is-required-response.json");
  }

  @Test
  public void validateRFA1bCrimeDetails() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setArrestedForCrime(true);
          rfa1bForm.setArrestedForCrimeDisclosures(
              disclosure(disclosureDTO -> disclosureDTO.setOffenseDetails(null)));
        },
        "fixtures/rfa/validation/rfa1b/fra1b-crime-details-is-required-response.json");
  }

  @Test
  public void validateRFA1bCrimeDisclosure() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setArrestedForCrime(true);
          rfa1bForm.setArrestedForCrimeDisclosures(null);
        },
        "fixtures/rfa/validation/rfa1b/fra1b-crime-disclosure-is-required-response.json");
  }

  @Test
  public void validateRFA1bOtherStates() throws Exception {
    test(rfa1bForm -> {
          rfa1bForm.setLivedInOtherState(true);
          rfa1bForm.setOtherStatesOfLiving(null);
        },
        "fixtures/rfa/validation/rfa1b/fra1b-other-states-is-required-response.json");
  }

  private void test(Consumer<RFA1bFormDTO> rfa1bFormDTO, String fixture) throws Exception {
    RFA1aFormDTO form = formAHelper.createRfa1aForm();
    RFA1aFormDTO persistentForm = formAHelper.postRfa1aForm(form);

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    rfa1bFormDTO.accept(applicant.getRfa1bForm());
    applicantHelper.postApplicant(persistentForm.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(persistentForm.getId(), residence);
    Map<String, Object> params = new HashMap<>();
    params.put("id", persistentForm.getId());
    String response = statusHelper.submitApplication(persistentForm.getId()).readEntity(String.class);
    assertResponseByFixtureTemplate(
        response,
        fixture,
        params, JSONCompareMode.LENIENT);
  }

  private List<DisclosureDTO> disclosure(Consumer<DisclosureDTO> disclosureDTOConsumer) {
    DisclosureDTO disclosureDTO = new DisclosureDTO();
    disclosureDTO.setOffense("offense");
    disclosureDTO.setOffenseDetails("offenseDetails");
    disclosureDTO.setWhenOffenseHappen("whenOffenseHappen");
    //TODO 'where'???
    disclosureDTOConsumer.accept(disclosureDTO);
    return Collections.singletonList(disclosureDTO);
  }

}

