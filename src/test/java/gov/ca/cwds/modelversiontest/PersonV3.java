package gov.ca.cwds.modelversiontest;

import com.github.jonpeterson.jackson.module.versioning.JsonVersionedModel;
import gov.ca.cwds.cals.modelversion.ModelHistory;

/**
 * Created by TPT2 on 1/2/2018.
 */
@JsonVersionedModel(currentVersion = PersonHistory.CURRENT_VERSION, toCurrentConverterClass = PersonHistory.class, defaultDeserializeToVersion = ModelHistory.BASE_VERSION)
public class PersonV3 {
  private String firstName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
