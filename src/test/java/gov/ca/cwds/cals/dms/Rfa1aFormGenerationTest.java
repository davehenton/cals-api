package gov.ca.cwds.cals.dms;

import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import org.junit.Test;

import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.APPLICANT_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_ADOPTION_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_APPLICANTS_DECLARATION_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_APPLICANTS_HISTORY_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_CHILD_DESIRED_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_REFERENCES_FIXTURE;
import static gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest.PUT_RESIDENCE_FIXTURE;

/**
 * @author TPT-2 team
 */
public class Rfa1aFormGenerationTest extends AbstractFormGenerationTest {

  @Test
  public void testPdfGeneration() throws Exception {
    String templatePath = "dms/rfa1a-case/RFA-01A Resource Family Application.pdf";
    String scriptPath = "dms/rfa1a-form/RFA-1A-form.groovy";

    VelocityHelper getVelocityExpandedResponseHelper = new VelocityHelper();
    getVelocityExpandedResponseHelper.setParameter("id", "-1");
    getVelocityExpandedResponseHelper.setParameter("residence", PUT_RESIDENCE_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("applicantsHistory", PUT_APPLICANTS_HISTORY_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("adoptionHistory", PUT_ADOPTION_HISTORY_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("childDesired", PUT_CHILD_DESIRED_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("references", PUT_REFERENCES_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("applicantsDeclaration", PUT_APPLICANTS_DECLARATION_FIXTURE);
        getVelocityExpandedResponseHelper.setParameter("applicant", APPLICANT_FIXTURE);

    String request =
        getVelocityExpandedResponseHelper.process("fixtures/rfa/rfa-1a-form-get-expanded-response.json");

    System.out.println("request = " + request);
    generatePdf(templatePath, scriptPath, request);
  }
}