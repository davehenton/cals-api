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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author TPT-2 team
 */
abstract class AbstractFormGenerationTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFormGenerationTest.class);

  protected void generatePdf(String templatePath, String scriptPath, String request) throws Exception {
    String script = fixture(scriptPath);

    Map jsonMap = (Map) new JsonSlurper().parseText(request);
    Binding binding = new Binding();
    binding.setVariable("jsonMap", jsonMap);
    GroovyShell shell = new GroovyShell(binding);
    Map<String, String> outputMap = (Map<String, String>) shell.evaluate(script);

    PDDocument pdf = PDDocument.load(getClass().getResourceAsStream("/" + templatePath));
    pdf.setAllSecurityToBeRemoved(true);
    List<PDField> fields = PdfTestHelper.getFields(pdf);
    PdfTestHelper.populatePlaceholders(fields, outputMap);
    String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());

    String templateFileName = templatePath.replaceAll("^.*/|\\.pdf$", "");
    String outputFileName = String.format("%s_%s.pdf", templateFileName, dateTime);
    pdf.save(outputFileName);
    pdf.close();
    LOGGER.info("File is generated: {}", outputFileName);

    PDDocument generatedPdf = PDDocument.load(new File(outputFileName));
    List<PDField> fieldList = PdfTestHelper.getFields(generatedPdf);
    for (PDField field : fieldList) {
      LOGGER.info("placeholder = '{}', value = {}", field.getFullyQualifiedName(), field.getValueAsString());
    }
    generatedPdf.close();
  }
}
