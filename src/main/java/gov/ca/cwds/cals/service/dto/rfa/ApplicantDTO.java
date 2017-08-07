package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized, default reflecti
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicantDTO extends RFAExternalEntityDTO implements Serializable {

  private static final long serialVersionUID = -4333411852074578122L;
  public static final String ALFANUMERICAL_PATTERN = "^[a-zA-Z0-9\\s]*$";

  @ApiModelProperty(value = "Prefix")
  @CheckReferentialIntegrity
  private NamePrefixType namePrefix;

  @Size(max = 20, message = ErrorMessages.MAX_LENGTH_VIOLATION)
  @Pattern(regexp = ALFANUMERICAL_PATTERN, message = ErrorMessages.ALPHANUMERIC_VIOLATION)
  private String firstName;

  private String middleName;

  @Size(max = 25, message = ErrorMessages.MAX_LENGTH_VIOLATION)
  @Pattern(regexp = ALFANUMERICAL_PATTERN, message = ErrorMessages.ALPHANUMERIC_VIOLATION)
  private String lastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity
  private NameSuffixType nameSuffix;

  private List<TypedPersonNameDTO> otherNames = new ArrayList<>();

  @CheckReferentialIntegrity
  private EducationLevelType highestEducationLevel;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @CheckReferentialIntegrity
  private GenderType gender;

  @CheckReferentialIntegrity
  private RaceType race;

  @CheckReferentialIntegrity
  private EthnicityType ethnicity;

  private String driverLicenseNumber;

  @CheckReferentialIntegrity
  private StateType driverLicenseState;

  private String email;

  @Valid
  private EmploymentDTO employment;

  @Valid
  private List<PhoneDTO> phones = new ArrayList<>();

  public NamePrefixType getNamePrefix() {
    return namePrefix;
  }

  public void setNamePrefix(
      NamePrefixType namePrefix) {
    this.namePrefix = namePrefix;
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

  public List<TypedPersonNameDTO> getOtherNames() {
    return otherNames;
  }

  public void setOtherNames(List<TypedPersonNameDTO> otherNames) {
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

  public EmploymentDTO getEmployment() {
    return employment;
  }

  public void setEmployment(EmploymentDTO employment) {
    this.employment = employment;
  }

  public List<PhoneDTO> getPhones() {
    return phones;
  }

  public void setPhones(List<PhoneDTO> phones) {
    this.phones = phones;
  }

  public NameSuffixType getNameSuffix() {
    return nameSuffix;
  }

  public void setNameSuffix(NameSuffixType nameSuffix) {
    this.nameSuffix = nameSuffix;
  }
}
