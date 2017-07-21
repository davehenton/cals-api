package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SuffixType;
import gov.ca.cwds.cals.service.validation.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MinorChildDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 1367746149537559411L;

  @CheckReferentialIntegrity
  private ApplicantRelationshipType relationshipToApplicant;

  private List<Long> childRelatedTo;

  private String otherRelativeFirstName;

  private String otherRelativeMiddleName;

  private String otherRelativeLastName;

  @ApiModelProperty(value = "Suffix", example = "Jr.")
  @CheckReferentialIntegrity
  private SuffixType otherRelativeSuffix;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfBirth;

  @CheckReferentialIntegrity
  private GenderType gender;

  private boolean childFinanciallySupported;

  private boolean childAdopted;

  public ApplicantRelationshipType getRelationshipToApplicant() {
    return relationshipToApplicant;
  }

  public void setRelationshipToApplicant(ApplicantRelationshipType relationshipToApplicant) {
    this.relationshipToApplicant = relationshipToApplicant;
  }

  public List<Long> getChildRelatedTo() {
    return childRelatedTo;
  }

  public void setChildRelatedTo(List<Long> childRelatedTo) {
    this.childRelatedTo = childRelatedTo;
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

  public boolean isChildFinanciallySupported() {
    return childFinanciallySupported;
  }

  public void setChildFinanciallySupported(boolean childFinanciallySupported) {
    this.childFinanciallySupported = childFinanciallySupported;
  }

  public boolean isChildAdopted() {
    return childAdopted;
  }

  public void setChildAdopted(boolean childAdopted) {
    this.childAdopted = childAdopted;
  }

  public SuffixType getOtherRelativeSuffix() {
    return otherRelativeSuffix;
  }

  public void setOtherRelativeSuffix(
      SuffixType otherRelativeSuffix) {
    this.otherRelativeSuffix = otherRelativeSuffix;
  }
}
