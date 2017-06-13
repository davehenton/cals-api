package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HouseholdAddress.
 */
@Entity
@Table(name = "household_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HouseholdAddress extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "is_weapons_in_home", nullable = false)
  private Boolean isWeaponsInHome;

  @Size(max = 255)
  @Column(name = "directions_to_home", length = 255)
  private String directionsToHome;

  @Lob
  @Column(name = "body_of_water")
  private String bodyOfWater;

  @OneToOne
  @JoinColumn(unique = true)
  private Address address;

  @OneToMany(mappedBy = "householdAddress")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<BriefPerson> personsUsingResidenceAsMailingAddresses = new HashSet<>();

  @ManyToOne
  private AddressType addressType;

  @ManyToOne
  private ResidenceOwnershipType residenceOwnershipType;

  @ManyToOne
  private Household household;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean isIsWeaponsInHome() {
    return isWeaponsInHome;
  }

  public HouseholdAddress isWeaponsInHome(Boolean isWeaponsInHome) {
    this.isWeaponsInHome = isWeaponsInHome;
    return this;
  }

  public void setIsWeaponsInHome(Boolean isWeaponsInHome) {
    this.isWeaponsInHome = isWeaponsInHome;
  }

  public String getDirectionsToHome() {
    return directionsToHome;
  }

  public HouseholdAddress directionsToHome(String directionsToHome) {
    this.directionsToHome = directionsToHome;
    return this;
  }

  public void setDirectionsToHome(String directionsToHome) {
    this.directionsToHome = directionsToHome;
  }

  public String getBodyOfWater() {
    return bodyOfWater;
  }

  public HouseholdAddress bodyOfWater(String bodyOfWater) {
    this.bodyOfWater = bodyOfWater;
    return this;
  }

  public void setBodyOfWater(String bodyOfWater) {
    this.bodyOfWater = bodyOfWater;
  }

  public Address getAddress() {
    return address;
  }

  public HouseholdAddress address(Address address) {
    this.address = address;
    return this;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Set<BriefPerson> getPersonsUsingResidenceAsMailingAddresses() {
    return personsUsingResidenceAsMailingAddresses;
  }

  public HouseholdAddress personsUsingResidenceAsMailingAddresses(Set<BriefPerson> briefPeople) {
    this.personsUsingResidenceAsMailingAddresses = briefPeople;
    return this;
  }

  public HouseholdAddress addPersonsUsingResidenceAsMailingAddress(BriefPerson briefPerson) {
    this.personsUsingResidenceAsMailingAddresses.add(briefPerson);
    briefPerson.setHouseholdAddress(this);
    return this;
  }

  public HouseholdAddress removePersonsUsingResidenceAsMailingAddress(BriefPerson briefPerson) {
    this.personsUsingResidenceAsMailingAddresses.remove(briefPerson);
    briefPerson.setHouseholdAddress(null);
    return this;
  }

  public void setPersonsUsingResidenceAsMailingAddresses(Set<BriefPerson> briefPeople) {
    this.personsUsingResidenceAsMailingAddresses = briefPeople;
  }

  public AddressType getAddressType() {
    return addressType;
  }

  public HouseholdAddress addressType(AddressType addressType) {
    this.addressType = addressType;
    return this;
  }

  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  public ResidenceOwnershipType getResidenceOwnershipType() {
    return residenceOwnershipType;
  }

  public HouseholdAddress residenceOwnershipType(ResidenceOwnershipType residenceOwnershipType) {
    this.residenceOwnershipType = residenceOwnershipType;
    return this;
  }

  public void setResidenceOwnershipType(ResidenceOwnershipType residenceOwnershipType) {
    this.residenceOwnershipType = residenceOwnershipType;
  }

  public Household getHousehold() {
    return household;
  }

  public HouseholdAddress household(Household household) {
    this.household = household;
    return this;
  }

  public void setHousehold(Household household) {
    this.household = household;
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
    HouseholdAddress householdAddress = (HouseholdAddress) o;
    if (householdAddress.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), householdAddress.getId());
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
