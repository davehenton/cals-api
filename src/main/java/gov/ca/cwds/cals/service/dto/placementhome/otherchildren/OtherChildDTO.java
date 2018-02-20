package gov.ca.cwds.cals.service.dto.placementhome.otherchildren;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.dto.BaseDTO;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Placement Home Other Child
 * <p>
 *
 *   JSON example:
 *
 *
 {
 "name": "Child Name",
 "date_of_birth": "2001-02-25",
 "gender": {
 "id": 2,
 "value": "Female"
 },
 "annual_unearned_income": 0.00,
 "spec_characteristics": "Lorem Ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.",
 "relationships": [
 {
 "scp_name": "Some Name",
 "relationship": {
 "id": 1,
 "value": "Child"
 }
 },
 {
 "scp_name": "Other Name",
 "relationship": {
 "id": 2,
 "value": "Sibling"
 }
 }
 ]
 }

 *
 *
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "date_of_birth",
    "gender",
    "annual_unearned_income",
    "spec_characteristics",
    "relationships"
})
@SuppressWarnings({"squid:S3437", "squid:S2160"}) //LocalDate is serializable
public class OtherChildDTO extends BaseDTO {

  private static final long serialVersionUID = -3582081005282844696L;
  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  private String name;
  /**
   * Date of Birth
   * <p>
   */
  @JsonProperty("date_of_birth")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date(format = DATE_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate dateOfBirth;
  /**
   * Gender
   * <p>
   */
  @JsonProperty("gender")
  private GenderType gender;
  /**
   * Annual Unearned Income
   * <p>
   */
  @JsonProperty("annual_unearned_income")
  private Double annualUnearnedIncome;
  /**
   * Special Characteristics
   * <p>
   */
  @JsonProperty("spec_characteristics")
  private String specCharacteristics;
  /**
   * Relationships to Substitute Care Providers
   * <p>
   */
  @JsonProperty("relationships")
  private ArrayList<RelationshipDTO> relationships = null;

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Date of Birth
   * <p>
   */
  @JsonProperty("date_of_birth")
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Date of Birth
   * <p>
   */
  @JsonProperty("date_of_birth")
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Gender
   * <p>
   */
  @JsonProperty("gender")
  public GenderType getGender() {
    return gender;
  }

  /**
   * Gender
   * <p>
   */
  @JsonProperty("gender")
  public void setGender(GenderType gender) {
    this.gender = gender;
  }

  /**
   * Annual Unearned Income
   * <p>
   */
  @JsonProperty("annual_unearned_income")
  public Double getAnnualUnearnedIncome() {
    return annualUnearnedIncome;
  }

  /**
   * Annual Unearned Income
   * <p>
   */
  @JsonProperty("annual_unearned_income")
  public void setAnnualUnearnedIncome(Double annualUnearnedIncome) {
    this.annualUnearnedIncome = annualUnearnedIncome;
  }

  /**
   * Special Characteristics
   * <p>
   */
  @JsonProperty("spec_characteristics")
  public String getSpecCharacteristics() {
    return specCharacteristics;
  }

  /**
   * Special Characteristics
   * <p>
   */
  @JsonProperty("spec_characteristics")
  public void setSpecCharacteristics(String specCharacteristics) {
    this.specCharacteristics = specCharacteristics;
  }

  /**
   * Relationships to Substitute Care Providers
   * <p>
   */
  @JsonProperty("relationships")
  public ArrayList<RelationshipDTO> getRelationships() {
    return relationships;
  }

  /**
   * Relationships to Substitute Care Providers
   * <p>
   */
  @JsonProperty("relationships")
  public void setRelationships(
      ArrayList<RelationshipDTO> relationships) {
    this.relationships = relationships;
  }
    
}
