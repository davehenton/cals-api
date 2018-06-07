package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.dto.BaseDTO;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "individual_documents",
    "trainings",
    "clearances"
})
public class PersonDocumentsDTO extends BaseDTO implements Serializable {

  private final static long serialVersionUID = 6801461199896333708L;

  @JsonProperty("individual_documents")
  private CollectionDTO<IndividualDocumentsItemDTO> individualDocuments;
  @JsonProperty("trainings")
  private CollectionDTO<TrainingItemDTO> trainings;
  @JsonProperty("clearances")
  private CollectionDTO<ClearancesItemDTO> clearances;

  public CollectionDTO<IndividualDocumentsItemDTO> getIndividualDocuments() {
    return individualDocuments;
  }

  public void setIndividualDocuments(
      CollectionDTO<IndividualDocumentsItemDTO> individualDocuments) {
    this.individualDocuments = individualDocuments;
  }

  public CollectionDTO<TrainingItemDTO> getTrainings() {
    return trainings;
  }

  public void setTrainings(
      CollectionDTO<TrainingItemDTO> trainings) {
    this.trainings = trainings;
  }

  public CollectionDTO<ClearancesItemDTO> getClearances() {
    return clearances;
  }

  public void setClearances(
      CollectionDTO<ClearancesItemDTO> clearances) {
    this.clearances = clearances;
  }
}
