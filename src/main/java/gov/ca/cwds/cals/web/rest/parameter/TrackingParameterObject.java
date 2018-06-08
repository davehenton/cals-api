package gov.ca.cwds.cals.web.rest.parameter;

import java.io.Serializable;

public class TrackingParameterObject implements Serializable {

  private Long formId;
  private Long trackingId;

  public TrackingParameterObject(Long formId, Long trackingId) {
    this.formId = formId;
    this.trackingId = trackingId;
  }

  public Long getFormId() {
    return formId;
  }

  public Long getTrackingId() {
    return trackingId;
  }
}
