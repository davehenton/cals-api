package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
public class ApplicantsHistoryDTO extends BaseDTO implements Request, Response {

  @ApiModelProperty("List of Former Spouses and Domestic Partners")
  private List<FormerSpouseDTO> formerSpouses = new ArrayList<>();

  @ApiModelProperty("List of Adult Children")
  private List<AdultChildDTO> adultChildren = new ArrayList<>();

  public List<FormerSpouseDTO> getFormerSpouses() {
    return formerSpouses;
  }

  public void setFormerSpouses(
      List<FormerSpouseDTO> formerSpouses) {
    this.formerSpouses = formerSpouses;
  }

  public List<AdultChildDTO> getAdultChildren() {
    return adultChildren;
  }

  public void setAdultChildren(
      List<AdultChildDTO> adultChildren) {
    this.adultChildren = adultChildren;
  }

}
