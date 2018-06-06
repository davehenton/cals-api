package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyDocumentsItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = 3270040110791902219L;

  @JsonProperty("received_date")
  private LocalDate receivedDate;

  public LocalDate getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(LocalDate receivedDate) {
    this.receivedDate = receivedDate;
  }

}
