package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.CheckReferentialIntegrity;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhoneDTO extends BaseDTO {

  private static final long serialVersionUID = 3691096439228739229L;

  @CheckReferentialIntegrity
  private PhoneNumberType phoneType;

  private String number;

  private boolean preferred;

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

  public boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(boolean preferred) {
    this.preferred = preferred;
  }

}
