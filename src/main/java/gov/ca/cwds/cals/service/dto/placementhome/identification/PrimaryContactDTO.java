
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
    "fax"
})
public class PrimaryContactDTO {

  @JsonProperty("phone")
  private PhoneDTO phone;
  @RemoveTrailingSpaces
  @JsonProperty("fax")
  private String fax;

  @JsonProperty("phone")
  public PhoneDTO getPhone() {
    return phone;
  }

  @JsonProperty("phone")
  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  @JsonProperty("fax")
  public String getFax() {
    return fax;
  }

  @JsonProperty("fax")
  public void setFax(String fax) {
    this.fax = fax;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this).append("phone", phone).append("fax", fax).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(phone).append(fax).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof PrimaryContactDTO)) {
      return false;
    }
    PrimaryContactDTO rhs = ((PrimaryContactDTO) other);
    return new EqualsBuilder().append(phone, rhs.phone).append(fax, rhs.fax).isEquals();
  }

}
