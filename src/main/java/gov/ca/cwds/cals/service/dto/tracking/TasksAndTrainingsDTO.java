package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasksAndTrainingsDTO implements Serializable {

  private final static long serialVersionUID = -2750680293790675474L;

  @JsonProperty("items")
  private List<TasksAndTrainingsItemDTO> items;

  public List<TasksAndTrainingsItemDTO> getItems() {
    return items;
  }

  public void setItems(List<TasksAndTrainingsItemDTO> items) {
    this.items = items;
  }

}
