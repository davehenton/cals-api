package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team.
 */
public class Address extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @JsonProperty("address_type")
  @ApiModelProperty(required = false, readOnly = false, value = "Address Type")
  private AddressType addressType;

  @JsonProperty("street_address")
  @ApiModelProperty(required = false, readOnly = false, value = "Street Address", example = "1702 Redoak Ct.")
  private String streetAddress;

  @ApiModelProperty(required = false, readOnly = false, value = "zip", example = "98123")
  private String zip;

  @ApiModelProperty(required = false, readOnly = false, value = "City", example = "Rocklin")
  private String city;

  @ApiModelProperty(required = false, readOnly = false, value = "State Type")
  private StateType state;
}