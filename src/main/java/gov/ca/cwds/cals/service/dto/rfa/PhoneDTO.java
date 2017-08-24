package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhoneDTO extends BaseDTO {

  private static final long serialVersionUID = 3691096439228739229L;

  @CheckReferentialIntegrity(enrich = true)
  @NotNull
  private PhoneNumberType phoneType;

  @Size(min = 10, max = 10)
  @Pattern(regexp = "^\\d*")
  private String number;

  @Size(max = 7)
  @Pattern(regexp = "^\\d*")
  private String extension;

  private boolean preferred;

  @JsonIgnore
  private UUID uuid = UUID.randomUUID();

  public PhoneNumberType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneNumberType phoneType) {
    this.phoneType = phoneType;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(boolean preferred) {
    this.preferred = preferred;
  }

  public UUID getUuid() {
    return uuid;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, "uuid");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "uuid");
  }
}
