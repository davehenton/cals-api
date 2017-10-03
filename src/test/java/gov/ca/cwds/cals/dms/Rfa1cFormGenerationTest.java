package gov.ca.cwds.cals.dms;

import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author TPT-2 team
 */
public class Rfa1cFormGenerationTest extends AbstractFormGenerationTest {

  @Test
  public void testPdfGeneration() throws Exception {
    String templatePath = "dms/rfa1c-case/RFA-01C - Resource Family Application-Confidential.pdf";
    String scriptPath = "dms/rfa1c-form/RFA-1C-form.groovy";
    String request = fixture("fixtures/rfa/rfa-1c-form.json");

    generatePdf(templatePath, scriptPath, request);
  }

}
