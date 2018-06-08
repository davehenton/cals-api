package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "family_documents",
    "tasks_and_trainings",
    "assessments"
})
public class FacilityDocumentsDTO implements Serializable {

  private final static long serialVersionUID = -7322876444705030639L;

  @JsonProperty("family_documents")
  private CollectionDTO<FamilyDocumentsItemDTO> familyDocuments;
  @JsonProperty("tasks_and_trainings")
  private CollectionDTO<TasksAndTrainingsItemDTO> tasksAndTrainings;
  @JsonProperty("assessments")
  private CollectionDTO<AssessmentItemDTO> assessments;

  public CollectionDTO<FamilyDocumentsItemDTO> getFamilyDocuments() {
    return familyDocuments;
  }

  public void setFamilyDocuments(
      CollectionDTO<FamilyDocumentsItemDTO> familyDocuments) {
    this.familyDocuments = familyDocuments;
  }

  public CollectionDTO<TasksAndTrainingsItemDTO> getTasksAndTrainings() {
    return tasksAndTrainings;
  }

  public void setTasksAndTrainings(
      CollectionDTO<TasksAndTrainingsItemDTO> tasksAndTrainings) {
    this.tasksAndTrainings = tasksAndTrainings;
  }

  public CollectionDTO<AssessmentItemDTO> getAssessments() {
    return assessments;
  }

  public void setAssessments(
      CollectionDTO<AssessmentItemDTO> assessments) {
    this.assessments = assessments;
  }
}
