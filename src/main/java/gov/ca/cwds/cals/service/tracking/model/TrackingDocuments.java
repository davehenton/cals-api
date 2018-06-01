package gov.ca.cwds.cals.service.tracking.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

public class TrackingDocuments {
  private JsonNode facilityDocuments;
  private List<JsonNode> personTrackingDocuments = new ArrayList<>();

  public JsonNode getFacilityDocuments() {
    return facilityDocuments;
  }

  public void setFacilityDocuments(JsonNode facilityDocuments) {
    this.facilityDocuments = facilityDocuments;
  }

  public List<JsonNode> getPersonTrackingDocuments() {
    return personTrackingDocuments;
  }

  public void setPersonTrackingDocuments(List<JsonNode> personTrackingDocuments) {
    this.personTrackingDocuments = personTrackingDocuments;
  }
}
