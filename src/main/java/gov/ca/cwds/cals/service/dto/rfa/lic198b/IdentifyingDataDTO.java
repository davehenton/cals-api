package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import static gov.ca.cwds.cals.Constants.Validation.Constraint.MAX_LENGTH_MESSAGE;
import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.rfa.PersonNameDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.TypedPersonNameDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S3437"}) //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class IdentifyingDataDTO extends BaseDTO {

  private static final long serialVersionUID = 3149377538662540041L;

  @ApiModelProperty("Applicant's name")
  @Valid
  private PersonNameDTO personName;

  @ApiModelProperty("Applicant's other names")
  @Valid
  private List<TypedPersonNameDTO> otherNames = new ArrayList<>();

  @ApiModelProperty("Phone number")
  @Valid
  private PhoneDTO phoneNumber;

  @ApiModelProperty("Email")
  private String email;

  @ApiModelProperty("Date of birth")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @ApiModelProperty("State of birth")
  @CheckStateReferentialIntegrity(enrich = true)
  private StateType stateOfBirth;

  @ApiModelProperty("Gender")
  @CheckReferentialIntegrity(enrich = true)
  private GenderType gender;

  @ApiModelProperty("Race")
  @CheckReferentialIntegrity(enrich = true)
  private RaceType race;

  @ApiModelProperty("Ethnicity")
  @CheckReferentialIntegrity(enrich = true)
  private EthnicityType ethnicity;

  @ApiModelProperty(value = "Social Security Number", example = "999999999")
  private String ssn;

  @ApiModelProperty(value = "Driver's License Number", example = "AS123456789012345678")
  @Size(max = 20, message = MAX_LENGTH_MESSAGE)
  @Pattern(regexp = "^[A-Za-z0-9]*$")
  private String driverLicenseNumber;

  @ApiModelProperty("Driver's License State")
  @CheckStateReferentialIntegrity(enrich = true)
  private StateType driverLicenseState;

  public PersonNameDTO getPersonName() {
    return personName;
  }

  public void setPersonName(PersonNameDTO personName) {
    this.personName = personName;
  }

  public List<TypedPersonNameDTO> getOtherNames() {
    return otherNames;
  }

  public void setOtherNames(List<TypedPersonNameDTO> otherNames) {
    this.otherNames = otherNames;
  }

  public PhoneDTO getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(PhoneDTO phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public StateType getStateOfBirth() {
    return stateOfBirth;
  }

  public void setStateOfBirth(
      StateType stateOfBirth) {
    this.stateOfBirth = stateOfBirth;
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

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
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

  public void setDriverLicenseState(
      StateType driverLicenseState) {
    this.driverLicenseState = driverLicenseState;
  }
}
