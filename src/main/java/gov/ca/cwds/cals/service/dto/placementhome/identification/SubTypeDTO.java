
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "value"
})
public class SubTypeDTO {

  @JsonProperty("id")
  private Integer id;
  @JsonProperty("value")
  private String value;

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  @JsonProperty("value")
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("value", value).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(id).append(value).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SubTypeDTO)) {
      return false;
    }
    SubTypeDTO rhs = ((SubTypeDTO) other);
    return new EqualsBuilder().append(id, rhs.id).append(value, rhs.value).isEquals();
  }

}
