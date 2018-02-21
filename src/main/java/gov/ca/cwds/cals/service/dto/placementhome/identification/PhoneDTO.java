
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phone",
    "ext"
})
public class PhoneDTO {
  @RemoveTrailingSpaces
  @JsonProperty("phone")
  private String phone;
  /**
   * Ext.
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("ext")
  private String ext;

  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  @JsonProperty("phone")
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Ext.
   * <p>
   */
  @JsonProperty("ext")
  public String getExt() {
    return ext;
  }

  /**
   * Ext.
   * <p>
   */
  @JsonProperty("ext")
  public void setExt(String ext) {
    this.ext = ext;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("phone", phone).append("ext", ext).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(phone).append(ext).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof PhoneDTO)) {
      return false;
    }
    PhoneDTO rhs = ((PhoneDTO) other);
    return new EqualsBuilder().append(phone, rhs.phone).append(ext, rhs.ext).isEquals();
  }

}
