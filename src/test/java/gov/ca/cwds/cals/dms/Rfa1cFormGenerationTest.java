package gov.ca.cwds.cals.dms;

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

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author TPT-2 team
 */
public class Rfa1cFormGenerationTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(Rfa1cFormGenerationTest.class);

  @Test
  public void testPdfGeneration() throws Exception {
    String templateFileName = "RFA-01C - Resource Family Application-Confidential";
    String request = fixture("fixtures/rfa/rfa-1c-form.json");
    String script = fixture("dms/rfa1c-form/RFA-1C-form.groovy");

    Map jsonMap = (Map) new JsonSlurper().parseText(request);
    Binding binding = new Binding();
    binding.setVariable("jsonMap", jsonMap);
    GroovyShell shell = new GroovyShell(binding);
    Map<String, String> outputMap = (Map<String, String>) shell.evaluate(script);

    PDDocument pdf = PDDocument.load(getClass().getResourceAsStream(String.format("/dms/rfa1c-case/%s.pdf", templateFileName)));
    pdf.setAllSecurityToBeRemoved(true);
    List<PDField> fields = PdfTestHelper.getFields(pdf);
    PdfTestHelper.populatePlaceholders(fields, outputMap);
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());

    String outputFileName = String.format("%s_%s.pdf", templateFileName, dateTime);
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
