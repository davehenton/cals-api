package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
public class PersonName implements Serializable {

  private static final long serialVersionUID = -8654631136147609963L;

  private String firstName;
  private String middleName;
  private String lastName;
  private NameType nameType;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public NameType getNameType() {
    return nameType;
  }

  public void setNameType(NameType nameType) {
    this.nameType = nameType;
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
    return "PersonName{"
        + "firstName='"
        + firstName
        + '\''
        + ", middleName='"
        + middleName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", nameType="
        + nameType
        + '}';
  }
}
