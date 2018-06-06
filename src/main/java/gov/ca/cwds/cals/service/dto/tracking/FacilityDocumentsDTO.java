package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
  private FamilyDocumentsDTO familyDocuments;
  @JsonProperty("tasks_and_trainings")
  private TasksAndTrainingsDTO tasksAndTrainings;
  @JsonProperty("assessments")
  private AssessmentsDTO assessments;

  public FamilyDocumentsDTO getFamilyDocuments() {
    return familyDocuments;
  }

  public void setFamilyDocuments(FamilyDocumentsDTO familyDocuments) {
    this.familyDocuments = familyDocuments;
  }

  public TasksAndTrainingsDTO getTasksAndTrainings() {
    return tasksAndTrainings;
  }

  public void setTasksAndTrainings(TasksAndTrainingsDTO tasksAndTrainings) {
    this.tasksAndTrainings = tasksAndTrainings;
  }

  public AssessmentsDTO getAssessments() {
    return assessments;
  }

  public void setAssessments(AssessmentsDTO assessments) {
    this.assessments = assessments;
  }

}
