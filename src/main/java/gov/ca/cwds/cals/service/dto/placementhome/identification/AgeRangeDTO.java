
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Age Range
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "from",
    "to"
})
public class AgeRangeDTO {

  /**
   * From
   * <p>
   */
  @JsonProperty("from")
  private Integer from;
  /**
   * To
   * <p>
   */
  @JsonProperty("to")
  private Integer to;


  /**
   * From
   * <p>
   */
  @JsonProperty("from")
  public Integer getFrom() {
    return from;
  }

  /**
   * From
   * <p>
   */
  @JsonProperty("from")
  public void setFrom(Integer from) {
    this.from = from;
  }

  /**
   * To
   * <p>
   */
  @JsonProperty("to")
  public Integer getTo() {
    return to;
  }

  /**
   * To
   * <p>
   */
  @JsonProperty("to")
  public void setTo(Integer to) {
    this.to = to;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("from", from).append("to", to).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(to).append(from).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof AgeRangeDTO)) {
      return false;
    }
    AgeRangeDTO rhs = ((AgeRangeDTO) other);
    return new EqualsBuilder().append(to, rhs.to).append(from, rhs.from).isEquals();
  }

}
