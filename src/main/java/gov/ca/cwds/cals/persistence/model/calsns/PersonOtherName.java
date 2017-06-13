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
import javax.validation.constraints.NotNull;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PersonOtherName.
 */
@Entity
@Table(name = "person_other_name")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PersonOtherName extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotNull
  @Column(name = "middle_name", nullable = false)
  private String middleName;

  @NotNull
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @ManyToOne
  private Person person;

  @OneToOne
  @JoinColumn(unique = true)
  private OtherNameType nameType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public PersonOtherName firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public PersonOtherName middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public PersonOtherName lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Person getPerson() {
    return person;
  }

  public PersonOtherName person(Person person) {
    this.person = person;
    return this;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public OtherNameType getNameType() {
    return nameType;
  }

  public PersonOtherName nameType(OtherNameType otherNameType) {
    this.nameType = otherNameType;
    return this;
  }

  public void setNameType(OtherNameType otherNameType) {
    this.nameType = otherNameType;
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
    PersonOtherName personOtherName = (PersonOtherName) o;
    if (personOtherName.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), personOtherName.getId());
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
