package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearancesDTO implements Serializable {

  private final static long serialVersionUID = -7307524420095663986L;

  @JsonProperty("items")
  private List<ClearancesItemDTO> items;

  public List<ClearancesItemDTO> getItems() {
    return items;
  }

  public void setItems(List<ClearancesItemDTO> items) {
    this.items = items;
  }

}
