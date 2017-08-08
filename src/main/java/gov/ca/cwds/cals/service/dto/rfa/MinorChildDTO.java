package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MinorChildDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 1367746149537559411L;

  @ApiModelProperty("Relationship to Applicants")
  @Valid
  private List<RelationshipToApplicantDTO> relationshipToApplicants;

  private Boolean otherRelative;

  @ApiModelProperty(value = "Other Relative Prefix")
  @CheckReferentialIntegrity
  private NamePrefixType otherRelativeNamePrefix;

  private String otherRelativeFirstName;

  private String otherRelativeMiddleName;

  private String otherRelativeLastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity
  private NameSuffixType otherRelativeNameSuffix;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @CheckReferentialIntegrity
  private GenderType gender;

  private Boolean childFinanciallySupported;

  private Boolean childAdopted;

  public List<RelationshipToApplicantDTO> getRelationshipToApplicants() {
    return relationshipToApplicants;
  }

  public void setRelationshipToApplicants(
      List<RelationshipToApplicantDTO> relationshipToApplicants) {
    this.relationshipToApplicants = relationshipToApplicants;
  }

  public NamePrefixType getOtherRelativeNamePrefix() {
    return otherRelativeNamePrefix;
  }

  public void setOtherRelativeNamePrefix(
      NamePrefixType otherRelativeNamePrefix) {
    this.otherRelativeNamePrefix = otherRelativeNamePrefix;
  }

  public String getOtherRelativeFirstName() {
    return otherRelativeFirstName;
  }

  public void setOtherRelativeFirstName(String otherRelativeFirstName) {
    this.otherRelativeFirstName = otherRelativeFirstName;
  }

  public String getOtherRelativeMiddleName() {
    return otherRelativeMiddleName;
  }

  public void setOtherRelativeMiddleName(String otherRelativeMiddleName) {
    this.otherRelativeMiddleName = otherRelativeMiddleName;
  }

  public String getOtherRelativeLastName() {
    return otherRelativeLastName;
  }

  public void setOtherRelativeLastName(String otherRelativeLastName) {
    this.otherRelativeLastName = otherRelativeLastName;
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

  public NameSuffixType getOtherRelativeNameSuffix() {
    return otherRelativeNameSuffix;
  }

  public void setOtherRelativeNameSuffix(
      NameSuffixType otherRelativeNameSuffix) {
    this.otherRelativeNameSuffix = otherRelativeNameSuffix;
  }

  public Boolean getOtherRelative() {
    return otherRelative;
  }

  public void setOtherRelative(Boolean otherRelative) {
    this.otherRelative = otherRelative;
  }

  public Boolean getChildFinanciallySupported() {
    return childFinanciallySupported;
  }

  public void setChildFinanciallySupported(Boolean childFinanciallySupported) {
    this.childFinanciallySupported = childFinanciallySupported;
  }

  public Boolean getChildAdopted() {
    return childAdopted;
  }

  public void setChildAdopted(Boolean childAdopted) {
    this.childAdopted = childAdopted;
  }
}
