package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearancesItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = 6025708349518937375L;

  @JsonProperty("start_date")
  private LocalDate startDate;
  @JsonProperty("completion_date")
  private LocalDate completionDate;
  @JsonProperty("completed_date")
  private LocalDate completedDate;

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getCompletionDate() {
    return completionDate;
  }

  public void setCompletionDate(LocalDate completionDate) {
    this.completionDate = completionDate;
  }

  public LocalDate getCompletedDate() {
    return completedDate;
  }

  public void setCompletedDate(LocalDate completedDate) {
    this.completedDate = completedDate;
  }

}
