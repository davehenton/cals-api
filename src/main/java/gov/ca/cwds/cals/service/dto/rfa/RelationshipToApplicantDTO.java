package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RelationshipToApplicantDTO extends BaseDTO {

  private static final long serialVersionUID = 8554818249973630219L;

  @JsonProperty("relationship_to_applicant")
  @ApiModelProperty(value = "Relationship to Applicant")
  @CheckReferentialIntegrity(enrich = true)
  private RelationshipToApplicantType relationshipToApplicantType;

  @ApiModelProperty(value = "Applicant Id", example = "1234567")
  private Long applicantId;

  public RelationshipToApplicantType getRelationshipToApplicantType() {
    return relationshipToApplicantType;
  }

  public void setRelationshipToApplicantType(
      RelationshipToApplicantType relationshipToApplicantType) {
    this.relationshipToApplicantType = relationshipToApplicantType;
  }

  public Long getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(Long applicantId) {
    this.applicantId = applicantId;
  }

}

