package gov.ca.cwds.cals.web.rest.rfa;

import java.util.HashMap;
import java.util.Map;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
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
    RFA1aFormDTO form = formAHelper.createRfa1aForm();
    RFA1aFormDTO persistentForm = formAHelper.postRfa1aForm(form);

    ApplicantDTO applicant = applicantHelper.getValidApplicant();
    applicant.getRfa1bForm().setApplicationCounty(null);
    applicantHelper.postApplicant(persistentForm.getId(), applicant);

    ResidenceDTO residence = residenceHelper.getResidenceDTO();
    residenceHelper.putResidence(persistentForm.getId(), residence);
    Map<String, Object> params = new HashMap<>();
    params.put("id", persistentForm.getId());
    String response = statusHelper.submitApplication(persistentForm.getId()).readEntity(String.class);
    assertResponseByFixtureTemplate(
        response,
        "fixtures/rfa/validation/rfa1b/fra1b-has-no-county-response.json",
        params, JSONCompareMode.LENIENT);
  }

}

