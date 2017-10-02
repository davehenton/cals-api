package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhysicalAddressDTO extends BaseDTO {

  private static final long serialVersionUID = 9003280821142481526L;

  private String streetAddress;

  private String city;

  @CheckStateReferentialIntegrity(enrich = true)
  private StateType state;

  private String zip;

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
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

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

}
