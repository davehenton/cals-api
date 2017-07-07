package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
/**
 * Do not rename class to AddressDTO.
 * In this case swagger will not work properly for some APIs
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
public class RFAAddressDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "Street Address", example = "1702 Redoak Ct.")
  private String streetAddress;

  @ApiModelProperty(value = "zip", example = "98123")
  private String zip;

  @ApiModelProperty(value = "City", example = "Rocklin")
  private String city;

  @ApiModelProperty(value = "State Type")
  @CheckReferentialIntegrity
  private StateType state;

  @ApiModelProperty(value = "Address Type")
  @CheckReferentialIntegrity
  private AddressType type;

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public AddressType getType() {
    return type;
  }

  public void setType(AddressType type) {
    this.type = type;
  }

}