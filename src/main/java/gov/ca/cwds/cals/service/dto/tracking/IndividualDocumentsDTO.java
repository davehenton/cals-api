package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualDocumentsDTO implements Serializable {

  private final static long serialVersionUID = 7902297902326928614L;

  @JsonProperty("items")
  private List<IndividualDocumentsItemDTO> items;

  public List<IndividualDocumentsItemDTO> getItems() {
    return items;
  }

  public void setItems(List<IndividualDocumentsItemDTO> items) {
    this.items = items;
  }

}
