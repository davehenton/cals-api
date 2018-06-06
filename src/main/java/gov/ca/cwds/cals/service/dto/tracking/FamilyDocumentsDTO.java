package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyDocumentsDTO implements Serializable {

  private final static long serialVersionUID = 6942978633494556686L;

  @JsonProperty("items")
  private List<FamilyDocumentsItemDTO> items;

  public List<FamilyDocumentsItemDTO> getItems() {
    return items;
  }

  public void setItems(List<FamilyDocumentsItemDTO> items) {
    this.items = items;
  }

}
