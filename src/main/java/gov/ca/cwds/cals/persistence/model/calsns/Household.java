package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Household.
 */
@Entity
@Table(name = "household")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Household extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @OneToMany(mappedBy = "household")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Person> persons = new HashSet<>();

  @OneToMany(mappedBy = "household")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<HouseholdAddress> householdAddresses = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JoinTable(name = "household_languages",
      joinColumns = @JoinColumn(name = "households_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "languages_id", referencedColumnName = "id"))
  private Set<LanguageType> languages = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<Person> getPersons() {
    return persons;
  }

  public Household persons(Set<Person> people) {
    this.persons = people;
    return this;
  }

  public Household addPersons(Person person) {
    this.persons.add(person);
    person.setHousehold(this);
    return this;
  }

  public Household removePersons(Person person) {
    this.persons.remove(person);
    person.setHousehold(null);
    return this;
  }

  public void setPersons(Set<Person> people) {
    this.persons = people;
  }

  public Set<HouseholdAddress> getHouseholdAddresses() {
    return householdAddresses;
  }

  public Household householdAddresses(Set<HouseholdAddress> householdAddresses) {
    this.householdAddresses = householdAddresses;
    return this;
  }

  public Household addHouseholdAddresses(HouseholdAddress householdAddress) {
    this.householdAddresses.add(householdAddress);
    householdAddress.setHousehold(this);
    return this;
  }

  public Household removeHouseholdAddresses(HouseholdAddress householdAddress) {
    this.householdAddresses.remove(householdAddress);
    householdAddress.setHousehold(null);
    return this;
  }

  public void setHouseholdAddresses(Set<HouseholdAddress> householdAddresses) {
    this.householdAddresses = householdAddresses;
  }

  public Set<LanguageType> getLanguages() {
    return languages;
  }

  public Household languages(Set<LanguageType> languageTypes) {
    this.languages = languageTypes;
    return this;
  }

  public Household addLanguages(LanguageType languageType) {
    this.languages.add(languageType);
    return this;
  }

  public Household removeLanguages(LanguageType languageType) {
    this.languages.remove(languageType);
    return this;
  }

  public void setLanguages(Set<LanguageType> languageTypes) {
    this.languages = languageTypes;
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
    Household household = (Household) o;
    if (household.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), household.getId());
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
