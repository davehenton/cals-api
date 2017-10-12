package gov.ca.cwds.cals.service.dto.rfa;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings({"squid:S3437", "squid:S2160"}) // Dates should be serialized
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicantsRelationshipDTO extends BaseDTO implements RequestResponse {

  @ApiModelProperty("Relationship between applicants")
  @CheckReferentialIntegrity(enrich = true)
  private ApplicantRelationshipType relationshipType;

  @ApiModelProperty(value = "Other type of relationship if it's not in the list", example = "")
  private String otherRelationship;

  @ApiModelProperty(value = "Date of current marriage/Domestic partnership", example = "2015-03-25")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate dateOfRelationship;

  @ApiModelProperty(value = "Place of current marriage/Domestic partnership: city", example = "Sacramento")
  private String placeOfRelationshipCity;

  @ApiModelProperty("Place of current marriage/Domestic partnership: state")
  @CheckStateReferentialIntegrity(enrich = true)
  private StateType placeOfRelationshipState;

  public ApplicantRelationshipType getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(ApplicantRelationshipType relationshipType) {
    this.relationshipType = relationshipType;
  }

  public String getOtherRelationship() {
    return otherRelationship;
  }

  public void setOtherRelationship(String otherRelationship) {
    this.otherRelationship = otherRelationship;
  }

  public LocalDate getDateOfRelationship() {
    return dateOfRelationship;
  }

  public void setDateOfRelationship(LocalDate dateOfRelationship) {
    this.dateOfRelationship = dateOfRelationship;
  }

  public String getPlaceOfRelationshipCity() {
    return placeOfRelationshipCity;
  }

  public void setPlaceOfRelationshipCity(String placeOfRelationshipCity) {
    this.placeOfRelationshipCity = placeOfRelationshipCity;
  }

  public StateType getPlaceOfRelationshipState() {
    return placeOfRelationshipState;
  }

  public void setPlaceOfRelationshipState(
      StateType placeOfRelationshipState) {
    this.placeOfRelationshipState = placeOfRelationshipState;
  }

}
