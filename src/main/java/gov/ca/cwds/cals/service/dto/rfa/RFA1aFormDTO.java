package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RFA1aFormDTO extends RFA1aForm {
  private static final long serialVersionUID = 1L;

  @JsonProperty("is_initial_application")
  @ApiModelProperty(value = "Is Initial Application Id", example = "false")
  private boolean initialApplication;

  @JsonProperty("is_other_type")
  @ApiModelProperty(value = "Is Other Type", example = "false")
  private boolean otherType;

  @ApiModelProperty(value = "Other Type Description", example = "Description")
  private String otherTypeDescription;

  @ApiModelProperty(value = "County Type")
  private CountyType applicationCounty;

  public boolean isInitialApplication() {
    return initialApplication;
  }

  public void setInitialApplication(boolean initialApplication) {
    this.initialApplication = initialApplication;
  }

  public boolean isOtherType() {
    return otherType;
  }

  public void setOtherType(boolean otherType) {
    this.otherType = otherType;
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

  public void setApplicationCounty(CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
