package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingsDTO implements Serializable {

  private final static long serialVersionUID = -359208118996156115L;

  @JsonProperty("items")
  private List<TrainingItemDTO> items;

  public List<TrainingItemDTO> getItems() {
    return items;
  }

  public void setItems(List<TrainingItemDTO> items) {
    this.items = items;
  }

}
