package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentsDTO implements Serializable {

  private final static long serialVersionUID = 8824392911417413487L;

  @JsonProperty("items")
  private List<AssessmentItemDTO> items;

  public List<AssessmentItemDTO> getItems() {
    return items;
  }

  public void setItems(List<AssessmentItemDTO> items) {
    this.items = items;
  }

}
