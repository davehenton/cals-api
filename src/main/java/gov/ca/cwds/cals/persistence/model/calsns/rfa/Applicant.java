package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") // Dates should be serialized
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Applicant extends BaseDTO implements Serializable, Request, Response {

  private static final long serialVersionUID = -4333411852074578122L;

  private Long id;

  private String firstName;

  private String middleName;

  private String lastName;

  private List<TypedPersonName> otherNames = new ArrayList<>();

  private EducationLevelType highestEducationLevel;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  private GenderType gender;

  private RaceType race;

  private EthnicityType ethnicity;

  private String driverLicenseNumber;

  private StateType driverLicenseState;

  private String email;

  private Employment employment;

  private List<Phone> phones = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public List<TypedPersonName> getOtherNames() {
    return otherNames;
  }

  public void setOtherNames(List<TypedPersonName> otherNames) {
    this.otherNames = otherNames;
  }

  public EducationLevelType getHighestEducationLevel() {
    return highestEducationLevel;
  }

  public void setHighestEducationLevel(EducationLevelType highestEducationLevel) {
    this.highestEducationLevel = highestEducationLevel;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public GenderType getGender() {
    return gender;
  }

  public void setGender(GenderType gender) {
    this.gender = gender;
  }

  public RaceType getRace() {
    return race;
  }

  public void setRace(RaceType race) {
    this.race = race;
  }

  public EthnicityType getEthnicity() {
    return ethnicity;
  }

  public void setEthnicity(EthnicityType ethnicity) {
    this.ethnicity = ethnicity;
  }

  public String getDriverLicenseNumber() {
    return driverLicenseNumber;
  }

  public void setDriverLicenseNumber(String driverLicenseNumber) {
    this.driverLicenseNumber = driverLicenseNumber;
  }

  public StateType getDriverLicenseState() {
    return driverLicenseState;
  }

  public void setDriverLicenseState(StateType driverLicenseState) {
    this.driverLicenseState = driverLicenseState;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Employment getEmployment() {
    return employment;
  }

  public void setEmployment(Employment employment) {
    this.employment = employment;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Applicant)) {
      return false;
    }
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "Applicant{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", middleName='"
        + middleName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", otherNames="
        + otherNames
        + ", highestEducationLevel="
        + highestEducationLevel
        + ", dateOfBirth="
        + dateOfBirth
        + ", gender="
        + gender
        + ", race="
        + race
        + ", ethnicity="
        + ethnicity
        + ", driverLicenseNumber='"
        + driverLicenseNumber
        + '\''
        + ", driverLicenseState="
        + driverLicenseState
        + ", email='"
        + email
        + '\''
        + ", employment="
        + employment
        + ", phones="
        + phones
        + '}';
  }
}
