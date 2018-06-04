package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

/**
 * Tracking Update Parameters.
 *
 * @author CWDS CALS API Team
 */
public class RFATrackingUpdateParams implements Serializable {

  private Long rfa1aId;
  private Long trackingId;

  public RFATrackingUpdateParams(Long rfa1aId, Long trackingId) {
    this.rfa1aId = rfa1aId;
    this.trackingId = trackingId;
  }

  public Long getRfa1aId() {
    return rfa1aId;
  }

  public Long getTrackingId() {
    return trackingId;
  }
}

