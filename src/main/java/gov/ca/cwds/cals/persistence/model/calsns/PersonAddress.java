package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
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
 * A PersonAddress.
 */
@Entity
@Table(name = "person_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PersonAddress extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @ManyToOne
  private Person person;

  @ManyToOne
  private AddressType addressType;

  @ManyToOne
  private Address address;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public PersonAddress person(Person person) {
    this.person = person;
    return this;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public AddressType getAddressType() {
    return addressType;
  }

  public PersonAddress addressType(AddressType addressType) {
    this.addressType = addressType;
    return this;
  }

  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  public Address getAddress() {
    return address;
  }

  public PersonAddress address(Address address) {
    this.address = address;
    return this;
  }

  public void setAddress(Address address) {
    this.address = address;
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
    PersonAddress personAddress = (PersonAddress) o;
    if (personAddress.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), personAddress.getId());
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
