package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BriefPerson.
 */
@Entity
@Table(name = "brief_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BriefPerson extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToOne
  private HouseholdAddress householdAddress;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public BriefPerson firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public BriefPerson middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public BriefPerson lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public HouseholdAddress getHouseholdAddress() {
    return householdAddress;
  }

  public BriefPerson householdAddress(HouseholdAddress householdAddress) {
    this.householdAddress = householdAddress;
    return this;
  }

  public void setHouseholdAddress(HouseholdAddress householdAddress) {
    this.householdAddress = householdAddress;
  }

  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BriefPerson briefPerson = (BriefPerson) o;
    if (briefPerson.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), briefPerson.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
