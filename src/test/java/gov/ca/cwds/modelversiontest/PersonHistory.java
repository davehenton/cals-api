package gov.ca.cwds.modelversiontest;

import gov.ca.cwds.cals.modelversion.ModelHistory;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by TPT2 on 1/2/2018.
 */
public class PersonHistory extends ModelHistory {
  public static final String CURRENT_VERSION = "2";

  public PersonHistory() {
    change("1", objectNode -> {
      String fullName = objectNode.get("fullName").asText();
      if (StringUtils.isNotBlank(fullName)) {
        String[] fullNameSplitted = fullName.split("\\s+");
        objectNode.put("firstName", fullNameSplitted[0]);
        if (fullNameSplitted.length > 1) {
          objectNode.put("lastName", fullNameSplitted[1]);
        }
      }
      objectNode.remove("fullName");
    });

    change("2", objectNode -> objectNode.remove("lastName"));
  }
}
