package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class IndividualDocumentsItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = -229112743099602680L;

  @JsonProperty("completed_date")
  private LocalDate completedDate;
  @JsonProperty("start_date")
  private LocalDate startDate;

  public LocalDate getCompletedDate() {
    return completedDate;
  }

  public void setCompletedDate(LocalDate completedDate) {
    this.completedDate = completedDate;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

}
