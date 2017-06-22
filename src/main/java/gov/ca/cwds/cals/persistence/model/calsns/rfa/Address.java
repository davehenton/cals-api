package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Address extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(required = false, readOnly = false, value = "Address Type")
  private AddressType addressType;

  @ApiModelProperty(required = false, readOnly = false, value = "Street Address", example = "1702 Redoak Ct.")
  private String streetAddress;

  @ApiModelProperty(required = false, readOnly = false, value = "zip", example = "98123")
  private String zip;

  @ApiModelProperty(required = false, readOnly = false, value = "City", example = "Rocklin")
  private String city;

  @ApiModelProperty(required = false, readOnly = false, value = "State Type")
  private StateType state;
}