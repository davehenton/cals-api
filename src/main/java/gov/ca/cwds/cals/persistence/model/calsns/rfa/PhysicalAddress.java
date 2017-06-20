package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
public class PhysicalAddress implements Serializable {

  private static final long serialVersionUID = 9003280821142481526L;

  private String streeAddress;

  private String city;

  private StateType state;

  private String zip;

  public String getStreeAddress() {
    return streeAddress;
  }

  public void setStreeAddress(String streeAddress) {
    this.streeAddress = streeAddress;
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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "PhysicalAddress{"
        + "streeAddress='"
        + streeAddress
        + '\''
        + ", city='"
        + city
        + '\''
        + ", state="
        + state
        + ", zip='"
        + zip
        + '\''
        + '}';
  }
}
