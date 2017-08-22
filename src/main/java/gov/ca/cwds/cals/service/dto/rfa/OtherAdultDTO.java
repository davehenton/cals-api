package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OtherAdultDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 3030580167004311781L;

  @ApiModelProperty(value = "Prefix")
  @CheckReferentialIntegrity
  private NamePrefixType namePrefix;

  @ApiModelProperty(value = "First Name", example = "Anna")
  private String firstName;

  @ApiModelProperty(value = "Middle Name", example = "L.")
  private String middleName;

  @ApiModelProperty(value = "Last Name", example = "Pollen")
  private String lastName;

  @ApiModelProperty(value = "Suffix")
  @CheckReferentialIntegrity
  private NameSuffixType nameSuffix;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "Date of Birth", example = "1995-07-14")
  private LocalDate dateOfBirth;

  @ApiModelProperty("Relationship to Applicants")
  @Valid
  private List<RelationshipToApplicantDTO> relationshipToApplicants = new ArrayList<>();

  @ApiModelProperty(hidden = true)
  private RFA1bFormDTO rfa1bForm;

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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<RelationshipToApplicantDTO> getRelationshipToApplicants() {
    return relationshipToApplicants;
  }

  public void setRelationshipToApplicants(
      List<RelationshipToApplicantDTO> relationshipToApplicants) {
    this.relationshipToApplicants = relationshipToApplicants;
  }

  public NameSuffixType getNameSuffix() {
    return nameSuffix;
  }

  public void setNameSuffix(NameSuffixType nameSuffix) {
    this.nameSuffix = nameSuffix;
  }

  public RFA1bFormDTO getRfa1bForm() {
    return rfa1bForm;
  }

  public void setRfa1bForm(RFA1bFormDTO rfa1bForm) {
    this.rfa1bForm = rfa1bForm;
  }
  
}
