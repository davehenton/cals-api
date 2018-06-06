package gov.ca.cwds.cals.service.dto.tracking;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearancesItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = 6025708349518937375L;

  @JsonProperty("start_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate startDate;
  @JsonProperty("completion_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate completionDate;
  @JsonProperty("completed_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
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
