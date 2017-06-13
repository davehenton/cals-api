package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PersonPhone.
 */
@Entity
@Table(name = "person_phone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PersonPhone extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @Column(name = "is_preferred_contact")
  private Boolean isPreferredContact;

  @OneToOne
  @JoinColumn(unique = true)
  private PhoneNumber phoneNumber;

  @ManyToOne
  private PhoneNumberType phoneType;

  @ManyToOne
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean isIsPreferredContact() {
    return isPreferredContact;
  }

  public PersonPhone isPreferredContact(Boolean isPreferredContact) {
    this.isPreferredContact = isPreferredContact;
    return this;
  }

  public void setIsPreferredContact(Boolean isPreferredContact) {
    this.isPreferredContact = isPreferredContact;
  }

  public PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }

  public PersonPhone phoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public void setPhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PhoneNumberType getPhoneType() {
    return phoneType;
  }

  public PersonPhone phoneType(PhoneNumberType phoneNumberType) {
    this.phoneType = phoneNumberType;
    return this;
  }

  public void setPhoneType(PhoneNumberType phoneNumberType) {
    this.phoneType = phoneNumberType;
  }

  public Person getPerson() {
    return person;
  }

  public PersonPhone person(Person person) {
    this.person = person;
    return this;
  }

  public void setPerson(Person person) {
    this.person = person;
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
    PersonPhone personPhone = (PersonPhone) o;
    if (personPhone.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), personPhone.getId());
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
