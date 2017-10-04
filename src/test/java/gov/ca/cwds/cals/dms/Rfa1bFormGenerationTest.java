package gov.ca.cwds.cals.dms;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import org.junit.Test;

/**
 * @author TPT-2 team
 */
public class Rfa1bFormGenerationTest extends AbstractFormGenerationTest {

  @Test
  public void testPdfGeneration() throws Exception {
    String templatePath = "dms/rfa1b-case/RFA-01B Criminal_Record_Statement.pdf";
    String scriptPath = "dms/rfa1b-form/RFA-1B-form.groovy";
    String request = fixture("fixtures/rfa/rfa-1b-form.json");

    generatePdf(templatePath, scriptPath, request);
  }
}
