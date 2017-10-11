package gov.ca.cwds.cals.dms;

import gov.ca.cwds.dms.services.PdfService;
import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author TPT-2 team
 */
public abstract class AbstractFormGenerationTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFormGenerationTest.class);

  private static DateTimeFormatter dateTimeFormatter;
  private static PdfService pdfService;

  @BeforeClass
  public static void setUpClass() {
    dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    pdfService = new PdfService();
  }

  void generatePdf(String templatePath, String scriptPath, String request) throws Exception {
    /*
     * map json
     */
    Binding binding = new Binding();
    binding.setVariable("jsonMap", new JsonSlurper().parseText(request));
    GroovyShell shell = new GroovyShell(binding);
    Map<String, String> outputMap = (Map<String, String>) shell.evaluate(fixture(scriptPath));

    /*
     * generate PDF and save to file
     */
    String templateFileName = templatePath.replaceAll("^.*/|\\.pdf$", "");
    String outputFileName = String.format("%s_%s.pdf", templateFileName, dateTimeFormatter.format(LocalDateTime.now()));

    FileOutputStream pdfOutputStream = new FileOutputStream(new File(outputFileName));
    pdfService.generatePdf(getClass().getResourceAsStream("/" + templatePath), outputMap, pdfOutputStream);

    LOGGER.info("File is generated: {}", outputFileName);

    /*
     * read the generated PDF file and log its fields
     */
    PDDocument generatedPdf = PDDocument.load(new File(outputFileName));
    List<PDField> fieldList = pdfService.getFields(generatedPdf);
    for (PDField field : fieldList) {
      LOGGER.info("placeholder = '{}', value = {}", field.getFullyQualifiedName(), field.getValueAsString());
    }
    generatedPdf.close();
  }
}
