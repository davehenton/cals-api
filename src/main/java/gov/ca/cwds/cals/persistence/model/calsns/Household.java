package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Household.
 */
@Entity
@Table(name = "household")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Household implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "create_user_id", length = 50, nullable = false)
    private String createUserId;

    @NotNull
    @Column(name = "create_date_time", nullable = false)
    private ZonedDateTime createDateTime;

    @NotNull
    @Size(max = 50)
    @Column(name = "update_user_id", length = 50, nullable = false)
    private String updateUserId;

    @NotNull
    @Column(name = "update_date_time", nullable = false)
    private ZonedDateTime updateDateTime;

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
               joinColumns = @JoinColumn(name="households_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="languages_id", referencedColumnName="id"))
    private Set<LanguageType> languages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public Household createUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Household createDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public void setCreateDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public Household updateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public ZonedDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public Household updateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    public void setUpdateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
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
        return "Household{" +
            "id=" + getId() +
            ", createUserId='" + getCreateUserId() + "'" +
            ", createDateTime='" + getCreateDateTime() + "'" +
            ", updateUserId='" + getUpdateUserId() + "'" +
            ", updateDateTime='" + getUpdateDateTime() + "'" +
            "}";
    }
}
