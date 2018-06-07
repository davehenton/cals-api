package gov.ca.cwds.cals.service.dto.tracking;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = -3417933532583971325L;

  @JsonProperty("submitted_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate submittedDate;

  @JsonProperty("approved_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate approvedDate;

  public LocalDate getSubmittedDate() {
    return submittedDate;
  }

  public void setSubmittedDate(LocalDate submittedDate) {
    this.submittedDate = submittedDate;
  }

  public LocalDate getApprovedDate() {
    return approvedDate;
  }

  public void setApprovedDate(LocalDate approvedDate) {
    this.approvedDate = approvedDate;
  }

}
