
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Backup Contact
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "phone"
})
public class BackupContactDTO {

  /**
   * Name
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("name")
  private String name;
  @JsonProperty("phone")
  private PhoneDTO phone;

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("phone")
  public PhoneDTO getPhone() {
    return phone;
  }

  @JsonProperty("phone")
  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("name", name).append("phone", phone).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(phone).append(name).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof BackupContactDTO)) {
      return false;
    }
    BackupContactDTO rhs = ((BackupContactDTO) other);
    return new EqualsBuilder().append(phone, rhs.phone).append(name, rhs.name).isEquals();
  }

}
