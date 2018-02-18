package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.Constants.Validation.Constraint;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrityForEach;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RFA1bFormDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 2700499740023492461L;

  @CheckReferentialIntegrity(enrich = true)
  private CountyType applicationCounty;

  @ApiModelProperty(example = "true")
  private Boolean livedInOtherState;

  @CheckStateReferentialIntegrityForEach(enrich = true)
  private List<StateType> otherStatesOfLiving;

  @ApiModelProperty(example = "false")
  private Boolean convictedInCalifornia;

  @ApiModelProperty(example = "false")
  private Boolean convictedInAnotherState;

  @ApiModelProperty(example = "false")
  private Boolean arrestedForCrime;

  @ApiModelProperty(example = "Peterson")
  private String resourceFamilyName;

  @ApiModelProperty(value = "Prefix")
  @CheckReferentialIntegrity(enrich = true)
  private NamePrefixType applicantNamePrefix;

  @ApiModelProperty(example = "Anna")
  private String applicantFirstName;

  @ApiModelProperty(example = "M.")
  private String applicantMiddleName;

  @ApiModelProperty(example = "Peterson")
  private String applicantLastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity(enrich = true)
  private NameSuffixType applicantNameSuffix;

  @Valid
  private RFAAddressDTO residenceAddress;

  @ApiModelProperty(example = "464942323")
  private String ssn;

  @ApiModelProperty(example = "1981-12-26")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @ApiModelProperty(name = "Driver's License Number", example = "AS123456789012345678")
  @Size(max = 20, message = Constraint.MAX_LENGTH_MESSAGE)
  private String driverLicense;

  @CheckStateReferentialIntegrity(enrich = true)
  private StateType driverLicenseState;

  @ApiModelProperty(example = "Anna Peterson")
  private String signature;

  @ApiModelProperty(example = "2017-07-02")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate applicationDate;

  @Valid
  private List<DisclosureDTO> convictedInCaliforniaDisclosures;

  @Valid
  private List<DisclosureDTO> convictedInAnotherStateDisclosures;

  @Valid
  private List<DisclosureDTO> arrestedForCrimeDisclosures;

  public CountyType getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

  public Boolean isLivedInOtherState() {
    return livedInOtherState;
  }

  public void setLivedInOtherState(Boolean livedInOtherState) {
    this.livedInOtherState = livedInOtherState;
  }

  public List<StateType> getOtherStatesOfLiving() {
    return otherStatesOfLiving;
  }

  public void setOtherStatesOfLiving(
      List<StateType> otherStatesOfLiving) {
    this.otherStatesOfLiving = otherStatesOfLiving;
  }

  public Boolean isConvictedInCalifornia() {
    return convictedInCalifornia;
  }

  public void setConvictedInCalifornia(Boolean convictedInCalifornia) {
    this.convictedInCalifornia = convictedInCalifornia;
  }

  public Boolean isConvictedInAnotherState() {
    return convictedInAnotherState;
  }

  public void setConvictedInAnotherState(Boolean convictedInAnotherState) {
    this.convictedInAnotherState = convictedInAnotherState;
  }

  public Boolean isArrestedForCrime() {
    return arrestedForCrime;
  }

  public void setArrestedForCrime(Boolean arrestedForCrime) {
    this.arrestedForCrime = arrestedForCrime;
  }

  public String getResourceFamilyName() {
    return resourceFamilyName;
  }

  public void setResourceFamilyName(String resourceFamilyName) {
    this.resourceFamilyName = resourceFamilyName;
  }

  public NamePrefixType getApplicantNamePrefix() {
    return applicantNamePrefix;
  }

  public void setApplicantNamePrefix(NamePrefixType applicantNamePrefix) {
    this.applicantNamePrefix = applicantNamePrefix;
  }

  public String getApplicantFirstName() {
    return applicantFirstName;
  }

  public void setApplicantFirstName(String applicantFirstName) {
    this.applicantFirstName = applicantFirstName;
  }

  public String getApplicantMiddleName() {
    return applicantMiddleName;
  }

  public void setApplicantMiddleName(String applicantMiddleName) {
    this.applicantMiddleName = applicantMiddleName;
  }

  public String getApplicantLastName() {
    return applicantLastName;
  }

  public void setApplicantLastName(String applicantLastName) {
    this.applicantLastName = applicantLastName;
  }

  public RFAAddressDTO getResidenceAddress() {
    return residenceAddress;
  }

  public void setResidenceAddress(RFAAddressDTO residenceAddress) {
    this.residenceAddress = residenceAddress;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getDriverLicense() {
    return driverLicense;
  }

  public void setDriverLicense(String driverLicense) {
    this.driverLicense = driverLicense;
  }

  public StateType getDriverLicenseState() {
    return driverLicenseState;
  }

  public void setDriverLicenseState(StateType driverLicenseState) {
    this.driverLicenseState = driverLicenseState;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public LocalDate getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(LocalDate applicationDate) {
    this.applicationDate = applicationDate;
  }

  public List<DisclosureDTO> getConvictedInCaliforniaDisclosures() {
    return convictedInCaliforniaDisclosures;
  }

  public void setConvictedInCaliforniaDisclosures(
      List<DisclosureDTO> convictedInCaliforniaDisclosures) {
    this.convictedInCaliforniaDisclosures = convictedInCaliforniaDisclosures;
  }

  public List<DisclosureDTO> getConvictedInAnotherStateDisclosures() {
    return convictedInAnotherStateDisclosures;
  }

  public void setConvictedInAnotherStateDisclosures(
      List<DisclosureDTO> convictedInAnotherStateDisclosures) {
    this.convictedInAnotherStateDisclosures = convictedInAnotherStateDisclosures;
  }

  public List<DisclosureDTO> getArrestedForCrimeDisclosures() {
    return arrestedForCrimeDisclosures;
  }

  public void setArrestedForCrimeDisclosures(
      List<DisclosureDTO> arrestedForCrimeDisclosures) {
    this.arrestedForCrimeDisclosures = arrestedForCrimeDisclosures;
  }

  public NameSuffixType getApplicantNameSuffix() {
    return applicantNameSuffix;
  }

  public void setApplicantNameSuffix(NameSuffixType applicantNameSuffix) {
    this.applicantNameSuffix = applicantNameSuffix;
  }
}
