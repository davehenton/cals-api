package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RFA1cFormDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 6758484594804680344L;

  @CheckReferentialIntegrity(enrich = true)
  private CountyType applicationCounty;

  @ApiModelProperty(example = "true")
  private boolean childIdentified;

  @Valid
  private List<IdentifiedChildDTO> identifiedChildren;

  public List<IdentifiedChildDTO> getIdentifiedChildren() {
    return identifiedChildren;
  }

  public void setIdentifiedChildren(
      List<IdentifiedChildDTO> identifiedChildren) {
    this.identifiedChildren = identifiedChildren;
  }

  public boolean isChildIdentified() {
    return childIdentified;
  }

  public void setChildIdentified(boolean childIdentified) {
    this.childIdentified = childIdentified;
  }

  public CountyType getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(
      CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

}
