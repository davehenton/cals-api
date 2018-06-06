package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = -3417933532583971325L;

  @JsonProperty("submitted_date")
  private LocalDate submittedDate;
  @JsonProperty("approved_date")
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
