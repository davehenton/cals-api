package gov.ca.cwds.cals.dms;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TPT-2 team
 */
public class Rfa1bFormGenerationTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(Rfa1bFormGenerationTest.class);

  private static final String TEMPLATE_NAME = "RFA-01B Criminal_Record_Statement";

  @Test
  public void testPdfGeneration() throws Exception {
    String request = fixture("fixtures/rfa/rfa-1b-form.json");
    String script = fixture("dms/rfa1b-form/RFA-1B-form.groovy");

    Map jsonMap = (Map) new JsonSlurper().parseText(request);
    Binding binding = new Binding();
    binding.setVariable("jsonMap", jsonMap);
    GroovyShell shell = new GroovyShell(binding);
    Map<String, String> outputMap = (Map<String, String>) shell.evaluate(script);

    PDDocument pdf = PDDocument.load(getClass().getResourceAsStream(String.format("/dms/rfa1b-case/%s.pdf", TEMPLATE_NAME)));
    pdf.setAllSecurityToBeRemoved(true);
    List<PDField> fields = PdfTestHelper.getFields(pdf);

    PdfTestHelper.populatePlaceholders(fields, outputMap);
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());

    String outputFileName = String.format("%s_%s.pdf", TEMPLATE_NAME, dateTime);
    pdf.save(outputFileName);
    pdf.close();
    LOGGER.info("File is generated: {}", outputFileName);

    PDDocument generatedPdf = PDDocument.load(new File(outputFileName));
    List<PDField> fieldList = PdfTestHelper.getFields(pdf);
    for (PDField field : fieldList) {
      LOGGER.info("placeholder = '{}', value = {}", field.getFullyQualifiedName(), field.getValueAsString());
    }
    generatedPdf.close();
  }

}
