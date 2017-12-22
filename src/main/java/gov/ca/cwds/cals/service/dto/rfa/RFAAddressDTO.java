package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;

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

  @ApiModelProperty(value = "Zip Extension", example = "123")
  @Max(99999)
  private String zipExtension;

  @ApiModelProperty(value = "City", example = "Rocklin")
  private String city;

  @ApiModelProperty(value = "State Type")
  @CheckStateReferentialIntegrity(enrich = true)
  private StateType state;

  @ApiModelProperty(example = "-121.25118")
  private Double longitude;

  @ApiModelProperty(example = "38.74037")
  private Double lattitude;

  @ApiModelProperty(example = "true")
  private Boolean deliverable;

  @ApiModelProperty(value = "Address Type")
  @CheckReferentialIntegrity(enrich = true)
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

  public String getZipExtension() {
    return zipExtension;
  }

  public void setZipExtension(String zipExtension) {
    this.zipExtension = zipExtension;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLattitude() {
    return lattitude;
  }

  public void setLattitude(Double lattitude) {
    this.lattitude = lattitude;
  }

  public Boolean getDeliverable() {
    return deliverable;
  }

  public void setDeliverable(Boolean deliverable) {
    this.deliverable = deliverable;
  }
}