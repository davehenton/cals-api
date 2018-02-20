package gov.ca.cwds.cals.service.dto.placementhome.otherchildren;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.dto.BaseDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "scp_name",
    "relationship"
})
public class RelationshipDTO extends BaseDTO {

  private static final long serialVersionUID = 3404984530405235490L;

  @JsonProperty("scp_name")
  private String scpName;

  @JsonProperty("relationship")
  private RelationshipToApplicantType relationshipType;


  public String getScpName() {
    return scpName;
  }

  public void setScpName(String scpName) {
    this.scpName = scpName;
  }

  public RelationshipToApplicantType getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(
      RelationshipToApplicantType relationshipType) {
    this.relationshipType = relationshipType;
  }
}
