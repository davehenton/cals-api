package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.dto.BaseDTO;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "facility_documents",
    "people_documents"
})
public class TrackingDocumentsDTO extends BaseDTO implements Serializable {

  private final static long serialVersionUID = -3746547320709433539L;

  @JsonProperty("facility_documents")
  private FacilityDocumentsDTO facilityDocuments;
  @JsonProperty("people_documents")
  private List<PeopleDocumentsDTO> peopleDocuments;

  public FacilityDocumentsDTO getFacilityDocuments() {
    return facilityDocuments;
  }

  public void setFacilityDocuments(FacilityDocumentsDTO facilityDocuments) {
    this.facilityDocuments = facilityDocuments;
  }

  public List<PeopleDocumentsDTO> getPeopleDocuments() {
    return peopleDocuments;
  }

  public void setPeopleDocuments(List<PeopleDocumentsDTO> peopleDocuments) {
    this.peopleDocuments = peopleDocuments;
  }

}
