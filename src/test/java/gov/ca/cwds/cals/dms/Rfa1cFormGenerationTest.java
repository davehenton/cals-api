package gov.ca.cwds.cals.dms;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import java.util.HashMap;
import org.junit.Test;

/**
 * @author TPT-2 team
 */
public class Rfa1cFormGenerationTest extends AbstractFormGenerationTest {

  private static final String pdfTemplatePath = "dms/rfa1c-case/RFA-01C - Resource Family Application-Confidential.pdf";
  private static final String groovyMappingPath = "dms/rfa1c-form/RFA-1C-form.groovy";
  private static final String jsonDataPath = "fixtures/rfa/rfa-1c-form.json";

  @Test
  public void testPdfGeneration() throws Exception {
    generateAndAssertPdf(pdfTemplatePath, fixture(groovyMappingPath), fixture(jsonDataPath),
        new HashMap<>());
  }
}
