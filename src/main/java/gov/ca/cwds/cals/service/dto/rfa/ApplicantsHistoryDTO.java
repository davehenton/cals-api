package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
public class ApplicantsHistoryDTO extends BaseDTO implements RequestResponse {

  @ApiModelProperty("List of Former Spouses and Domestic Partners")
  private List<FormerSpouseDTO> formerSpouses = new ArrayList<>();

  @ApiModelProperty("List of Adult Children")
  private List<AdultChildDTO> adultChildren = new ArrayList<>();

  @Valid
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
