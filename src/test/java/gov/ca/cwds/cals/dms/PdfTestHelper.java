package gov.ca.cwds.cals.dms;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

/**
 * @author TPT-2 team
 */
public class PdfTestHelper {

  public static List<PDField> getFields(PDDocument pdfDocument) {
    PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();
    if (acroForm == null) {
      return Collections.emptyList();
    }
    return acroForm.getFields();
  }

  public static void populatePlaceholders(List<PDField> fields, Map<String, String> map) {
    for (PDField field : fields) {
      String fieldName = field.getFullyQualifiedName();
      String value = map.get(fieldName);
      if (value == null) {
        continue;
      }
      try {
        field.setValue(value);
      } catch (IOException e) {
        throw new IllegalArgumentException("Cannot set value: " + value + " to field: " + fieldName, e); //NOSONAR passing exception to exception handler
      }
    }
  }
}
