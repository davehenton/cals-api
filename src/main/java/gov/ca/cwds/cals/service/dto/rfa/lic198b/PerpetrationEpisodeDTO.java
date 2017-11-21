package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.validation.field.CheckStateReferentialIntegrity;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S2160", "squid:S3437"}) //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PerpetrationEpisodeDTO extends BaseDTO {

  private static final long serialVersionUID = 5614960336898076770L;

  @ApiModelProperty("Date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate date;

  @ApiModelProperty("City")
  private String city;

  @ApiModelProperty("State")
  @CheckStateReferentialIntegrity(enrich = true)
  private StateType state;

  @ApiModelProperty("County")
  private String county;

  @ApiModelProperty("Circumstances")
  private String circumstances;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getCircumstances() {
    return circumstances;
  }

  public void setCircumstances(String circumstances) {
    this.circumstances = circumstances;
  }
}
