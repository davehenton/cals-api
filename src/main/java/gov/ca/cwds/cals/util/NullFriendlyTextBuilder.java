package gov.ca.cwds.cals.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Alexander Serbin on 5/22/2018.
 */
public class NullFriendlyTextBuilder {

  private List<String> notEmptyFields = new ArrayList<>(4);

  /**
   * Join fields using space(' ') as split character.
   * Null and empty fields are ignored
   */
  public String buildText(String... fields) {
    for (String field : fields) {
      addFileds(field);
    }
    return StringUtils.join(notEmptyFields, ' ');
  }

  private void addFileds(String field) {
    if (StringUtils.isNotEmpty(field)) {
      notEmptyFields.add(field);
    }
  }

}
