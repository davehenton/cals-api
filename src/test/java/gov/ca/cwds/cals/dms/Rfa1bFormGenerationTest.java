package gov.ca.cwds.cals.dms;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import org.junit.Test;

/**
 * @author TPT-2 team
 */
public class Rfa1bFormGenerationTest extends AbstractFormGenerationTest {

  private static final String pdfTemplatePath = "dms/rfa1b-case/RFA-01B Criminal_Record_Statement.pdf";
  private static final String groovyMappingPath = "dms/rfa1b-form/RFA-1B-form.groovy";
  private static final String jsonDataPath = "fixtures/rfa/rfa-1b-form.json";

  @Test
  public void testPdfGeneration() throws Exception {
    generateAndAssertPdf(pdfTemplatePath, fixture(groovyMappingPath), fixture(jsonDataPath));
  }
}
