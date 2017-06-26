package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RFA1aFormDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "Application Id", hidden = true)
  private Long id;

  @ApiModelProperty(value = "Is Initial Application Id", example = "false")
  private boolean isInitialApplication;

  @ApiModelProperty(value = "Is Other Type", example = "false")
  private boolean isOtherType;

  @ApiModelProperty(value = "Other Type Description", example = "Description")
  private String otherTypeDescription;

  @ApiModelProperty(value = "County Type")
  private CountyType applicationCounty;

  @ApiModelProperty(required = true, readOnly = true, value = "Residence hyperlink", example = Constants.API.RFA_1A_FORMS + "/1/" + Constants.API.RESIDENCE)
  private Residence residence;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isInitialApplication() {
    return isInitialApplication;
  }

  public void setInitialApplication(boolean initialApplication) {
    this.isInitialApplication = initialApplication;
  }

  public boolean isOtherType() {
    return isOtherType;
  }

  public void setOtherType(boolean otherType) {
    isOtherType = otherType;
  }

  public String getOtherTypeDescription() {
    return otherTypeDescription;
  }

  public void setOtherTypeDescription(String otherTypeDescription) {
    this.otherTypeDescription = otherTypeDescription;
  }

  public CountyType getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(
      CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }
}
