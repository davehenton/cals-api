package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.dto.BaseDTO;
import java.io.Serializable;

public abstract class BaseItemDTO extends BaseDTO implements Serializable {

  @JsonProperty("title")
  private String title;
  @JsonProperty("notes")
  private String notes;
  @JsonProperty("checked")
  private Boolean checked;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }
}
