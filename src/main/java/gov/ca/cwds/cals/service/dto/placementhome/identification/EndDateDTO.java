
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SimpleDictionary;
import java.time.LocalDate;


/**
 * Placement Home End Date
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "end_date",
    "reason_type",
    "comments"
})
public class EndDateDTO {

  /**
   * End Date
   * <p>
   */
  @JsonProperty("end_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate endDate;
  @JsonProperty("reason_type")
  private SimpleDictionary reasonType;
  /**
   * Comments
   * <p>
   */
  @JsonProperty("comments")
  private String comments;

  /**
   * End Date
   * <p>
   */
  @JsonProperty("name")
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * End Date
   * <p>
   */
  @JsonProperty("name")
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @JsonProperty("reason_type")
  public SimpleDictionary getReasonType() {
    return reasonType;
  }

  @JsonProperty("reason_type")
  public void setReasonType(SimpleDictionary reasonType) {
    this.reasonType = reasonType;
  }

  /**
   * Comments
   * <p>
   */
  @JsonProperty("comments")
  public String getComments() {
    return comments;
  }

  /**
   * Comments
   * <p>
   */
  @JsonProperty("comments")
  public void setComments(String comments) {
    this.comments = comments;
  }

}
