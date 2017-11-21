package gov.ca.cwds.cals.service.dto.rfa.lic198b;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.rfa.PhysicalAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LIC198bFormDTO extends RFAExternalEntityDTO {

  private static final long serialVersionUID = 5247613817837188460L;

  @ApiModelProperty("Requestor information")
  @Valid
  private RequestorInfoDTO requestorInfo;

  @ApiModelProperty("Identifying data")
  @Valid
  private IdentifyingDataDTO identifyingData;

  @ApiModelProperty("Addresses for past 5 years")
  @Valid
  private List<PhysicalAddressDTO> lastFiveYearsAddresses;

  @ApiModelProperty("Have you ever been substantiated as a perpetrator in any child abuse or neglect report in this state or any state?")
  private Boolean hasBeenPerpetrator;

  @ApiModelProperty("Circumstances")
  @Valid
  private List<PerpetrationEpisodeDTO> perpetrationEpisodes;

  @ApiModelProperty("Signatures")
  @Valid
  private SignaturesDTO signatures;

  @ApiModelProperty("Responding State")
  @Valid
  private RespondingStateDTO respondingState;

  public RequestorInfoDTO getRequestorInfo() {
    return requestorInfo;
  }

  public void setRequestorInfo(RequestorInfoDTO requestorInfo) {
    this.requestorInfo = requestorInfo;
  }

  public IdentifyingDataDTO getIdentifyingData() {
    return identifyingData;
  }

  public void setIdentifyingData(IdentifyingDataDTO identifyingData) {
    this.identifyingData = identifyingData;
  }

  public List<PhysicalAddressDTO> getLastFiveYearsAddresses() {
    return lastFiveYearsAddresses;
  }

  public void setLastFiveYearsAddresses(
      List<PhysicalAddressDTO> lastFiveYearsAddresses) {
    this.lastFiveYearsAddresses = lastFiveYearsAddresses;
  }

  public Boolean getHasBeenPerpetrator() {
    return hasBeenPerpetrator;
  }

  public void setHasBeenPerpetrator(Boolean hasBeenPerpetrator) {
    this.hasBeenPerpetrator = hasBeenPerpetrator;
  }

  public List<PerpetrationEpisodeDTO> getPerpetrationEpisodes() {
    return perpetrationEpisodes;
  }

  public void setPerpetrationEpisodes(List<PerpetrationEpisodeDTO> perpetrationEpisodes) {
    this.perpetrationEpisodes = perpetrationEpisodes;
  }

  public SignaturesDTO getSignatures() {
    return signatures;
  }

  public void setSignatures(SignaturesDTO signatures) {
    this.signatures = signatures;
  }

  public RespondingStateDTO getRespondingState() {
    return respondingState;
  }

  public void setRespondingState(RespondingStateDTO respondingState) {
    this.respondingState = respondingState;
  }
}
