package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.dto.BaseDTO;

public class Tracking extends BaseDTO implements RequestResponse {
  private JsonNode trackingDocuments;
  private Long applicationId;

  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }

  public JsonNode getTrackingDocuments() {
    return trackingDocuments;
  }

  public void setTrackingDocuments(JsonNode trackingDocuments) {
    this.trackingDocuments = trackingDocuments;
  }
}
