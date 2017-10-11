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
import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author TPT-2 team
 */
public class Rfa1aFormGenerationTest extends AbstractFormGenerationTest {

  private static final String pdfTemplatePath = "dms/rfa1a-case/RFA-01A Resource Family Application.pdf";
  private static final String groovyMappingPath = "dms/rfa1a-form/RFA-1A-form.groovy";
  private static final String jsonDataPath = "fixtures/rfa/rfa-1a-form-get-expanded-response.json";

  @Test
  public void testPdfGeneration() throws Exception {
    VelocityHelper getVelocityExpandedResponseHelper = new VelocityHelper();
    getVelocityExpandedResponseHelper.setParameter("id", "-1");
    getVelocityExpandedResponseHelper.setParameter("residence", PUT_RESIDENCE_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("applicantsHistory", PUT_APPLICANTS_HISTORY_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("adoptionHistory", PUT_ADOPTION_HISTORY_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("childDesired", PUT_CHILD_DESIRED_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("references", PUT_REFERENCES_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("applicantsDeclaration", PUT_APPLICANTS_DECLARATION_FIXTURE);
    getVelocityExpandedResponseHelper.setParameter("applicant", APPLICANT_FIXTURE);

    String request = getVelocityExpandedResponseHelper.process(jsonDataPath);
    System.out.println("request = " + request);

    generateAndAssertPdf(pdfTemplatePath, fixture(groovyMappingPath), request);
  }
}
