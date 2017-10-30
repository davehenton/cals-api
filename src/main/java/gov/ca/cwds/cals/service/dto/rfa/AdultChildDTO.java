package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;

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

  @ApiModelProperty("Relationship to Applicants")
  @Valid
  private List<RelationshipToApplicantDTO> relationshipToApplicants;

  @ApiModelProperty(value = "Is adult child lives in the home?", example = "false")
  private Boolean livesInHome;

  @ApiModelProperty("Adult Child phone number")
  @Valid
  private PhoneDTO phone;

  @ApiModelProperty("Adult Child address")
  @Valid
  private RFAAddressDTO address;

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

  public NameSuffixType getNameSuffix() {
    return nameSuffix;
  }

  public void setNameSuffix(NameSuffixType nameSuffix) {
    this.nameSuffix = nameSuffix;
  }

  public List<RelationshipToApplicantDTO> getRelationshipToApplicants() {
    return relationshipToApplicants;
  }

  public void setRelationshipToApplicants(
      List<RelationshipToApplicantDTO> relationshipToApplicants) {
    this.relationshipToApplicants = relationshipToApplicants;
  }

  public Boolean isLivesInHome() {
    return livesInHome;
  }

  public Boolean livesInHome() {
    return livesInHome;
  }

  public void setLivesInHome(Boolean livesInHome) {
    this.livesInHome = livesInHome;
  }

  public PhoneDTO getPhone() {
    return phone;
  }

  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  public RFAAddressDTO getAddress() {
    return address;
  }

  public void setAddress(RFAAddressDTO address) {
    this.address = address;
  }
}
