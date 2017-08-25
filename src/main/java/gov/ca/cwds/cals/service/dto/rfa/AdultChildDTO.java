package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AdultChildDTO extends BaseDTO {

  private static final long serialVersionUID = -5382998437450053251L;

  @ApiModelProperty(value = "Prefix")
  @CheckReferentialIntegrity(enrich = true)
  private NamePrefixType namePrefix;

  @ApiModelProperty(value = "First Name", example = "Andrew")
  private String firstName;

  @ApiModelProperty(value = "Middle Name", example = "")
  private String middleName;

  @ApiModelProperty(value = "Last Name", example = "Pollen")
  private String lastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity(enrich = true)
  private NameSuffixType nameSuffix;

  @ApiModelProperty("Relationship to applicant")
  @CheckReferentialIntegrity(enrich = true)
  private RelationshipToApplicantType relationshipToApplicant;

  private List<Long> adultChildRelatedTo = new ArrayList<>();

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

  public RelationshipToApplicantType getRelationshipToApplicant() {
    return relationshipToApplicant;
  }

  public void setRelationshipToApplicant(
      RelationshipToApplicantType relationshipToApplicant) {
    this.relationshipToApplicant = relationshipToApplicant;
  }

  public List<Long> getAdultChildRelatedTo() {
    return adultChildRelatedTo;
  }

  public void setAdultChildRelatedTo(List<Long> adultChildRelatedTo) {
    this.adultChildRelatedTo = adultChildRelatedTo;
  }

  public NameSuffixType getNameSuffix() {
    return nameSuffix;
  }

  public void setNameSuffix(NameSuffixType nameSuffix) {
    this.nameSuffix = nameSuffix;
  }
}
