package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
public class RFAApplicationStatusDTO extends BaseDTO {

  private static final long serialVersionUID = -2599914441619419689L;

  private RFAApplicationStatus status;

  public RFAApplicationStatusDTO() {
  }

  public RFAApplicationStatusDTO(RFAApplicationStatus status) {
    this.status = status;
  }

  public RFAApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(RFAApplicationStatus status) {
    this.status = status;
  }

}
