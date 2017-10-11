package gov.ca.cwds.cals.dms;

import gov.ca.cwds.dms.services.pdf.PdfServiceTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TPT-2 team
 */
public abstract class AbstractFormGenerationTest extends PdfServiceTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFormGenerationTest.class);

  private static DateTimeFormatter dateTimeFormatter;

  @BeforeClass
  public static void setUpClass() {
    PdfServiceTest.setUpClass();
    dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
  }

  void generateAndAssertPdf(String pdfTemplatePath, String groovyMapping, String jsonData)
      throws IOException {

    // calculate output file name
    String templateFileName = pdfTemplatePath.replaceAll("^.*/|\\.pdf$", "");
    String outputFileName = String
        .format("%s_%s.pdf", templateFileName, dateTimeFormatter.format(LocalDateTime.now()));

    // generate PDF to file
    FileOutputStream pdfOutputStream = new FileOutputStream(new File(outputFileName));
    Map<String, String> expectedFieldsMap = generatePdf(pdfTemplatePath, pdfOutputStream,
        groovyMapping, jsonData);
    LOGGER.info("File is generated: {}", outputFileName);

    assertGeneratedPdf(expectedFieldsMap, FileUtils.readFileToByteArray(new File(outputFileName)));

    for (Entry<String, String> entry : expectedFieldsMap.entrySet()) {
      LOGGER.info("placeholder = '{}', value = {}", entry.getKey(), entry.getValue());
    }
  }
}
