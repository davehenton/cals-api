package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "individual_documents",
    "trainings",
    "clearances"
})
public class PersonDocumentsDTO implements Serializable {

  private final static long serialVersionUID = 6801461199896333708L;

  @JsonProperty("individual_documents")
  private IndividualDocumentsDTO individualDocuments;
  @JsonProperty("trainings")
  private TrainingsDTO trainings;
  @JsonProperty("clearances")
  private ClearancesDTO clearances;

  public IndividualDocumentsDTO getIndividualDocuments() {
    return individualDocuments;
  }

  public void setIndividualDocuments(IndividualDocumentsDTO individualDocuments) {
    this.individualDocuments = individualDocuments;
  }

  public TrainingsDTO getTrainings() {
    return trainings;
  }

  public void setTrainings(TrainingsDTO trainings) {
    this.trainings = trainings;
  }

  public ClearancesDTO getClearances() {
    return clearances;
  }

  public void setClearances(ClearancesDTO clearances) {
    this.clearances = clearances;
  }

}
