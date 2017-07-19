package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * @author CWDS CALS API Team.
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RFA1aFormDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = -3006256329006945860L;

  @ApiModelProperty(hidden = true)
  private Long id;

  @JsonProperty("is_initial_application")
  @ApiModelProperty(value = "Is Initial Application Id", example = "false")
  private boolean initialApplication;

  @JsonProperty("is_other_type")
  @ApiModelProperty(value = "Is Other Type", example = "false")
  private boolean otherType;

  @ApiModelProperty(value = "Other Type Description", example = "Description")
  private String otherTypeDescription;

  @ApiModelProperty(value = "County Type")
  private CountyType applicationCounty;

  @ApiModelProperty(hidden = true)
  private ResidenceDTO residence;

  @ApiModelProperty(hidden = true)
  private ApplicantsRelationshipDTO applicantsRelationship;

  @ApiModelProperty(hidden = true)
  private ApplicantsHistoryDTO applicantsHistory;

  @ApiModelProperty(hidden = true)
  private List<RFA1aApplicant> applicants;

  @ApiModelProperty(hidden = true)
  private List<RFA1aMinorChild> minorChildren;

  @ApiModelProperty(hidden = true)
  private List<RFA1aOtherAdult> otherAdults;

  @ApiModelProperty(hidden = true)
  private List<RFA1bForm> rfa1bForms;

  @ApiModelProperty(hidden = true)
  private List<RFA1cForm> rfa1cForms;

  @ApiModelProperty(hidden = true)
  private AdoptionHistoryDTO adoptionHistory;

  @ApiModelProperty(hidden = true)
  private ChildDesiredDTO childDesired;

  @ApiModelProperty(hidden = true)
  private ReferencesDTO references;

  @ApiModelProperty(hidden = true)
  private ApplicantsDeclarationDTO applicantsDeclaration;

  @ApiModelProperty(hidden = true)
  private String placementHomeId;

  public boolean isInitialApplication() {
    return initialApplication;
  }

  public void setInitialApplication(boolean initialApplication) {
    this.initialApplication = initialApplication;
  }

  public ResidenceDTO getResidence() {
    return residence;
  }

  public void setResidence(ResidenceDTO residence) {
    this.residence = residence;
  }

  public ApplicantsRelationshipDTO getApplicantsRelationship() {
    return applicantsRelationship;
  }

  public void setApplicantsRelationship(
      ApplicantsRelationshipDTO applicantsRelationship) {
    this.applicantsRelationship = applicantsRelationship;
  }

  public ApplicantsHistoryDTO getApplicantsHistory() {
    return applicantsHistory;
  }

  public void setApplicantsHistory(ApplicantsHistoryDTO applicantsHistory) {
    this.applicantsHistory = applicantsHistory;
  }

  public List<RFA1aApplicant> getApplicants() {
    return applicants;
  }

  public void setApplicants(
      List<RFA1aApplicant> applicants) {
    this.applicants = applicants;
  }

  public List<RFA1aMinorChild> getMinorChildren() {
    return minorChildren;
  }

  public void setMinorChildren(
      List<RFA1aMinorChild> minorChildren) {
    this.minorChildren = minorChildren;
  }

  public List<RFA1aOtherAdult> getOtherAdults() {
    return otherAdults;
  }

  public void setOtherAdults(
      List<RFA1aOtherAdult> otherAdults) {
    this.otherAdults = otherAdults;
  }

  public List<RFA1bForm> getRfa1bForms() {
    return rfa1bForms;
  }

  public void setRfa1bForms(
      List<RFA1bForm> rfa1bForms) {
    this.rfa1bForms = rfa1bForms;
  }

  public List<RFA1cForm> getRfa1cForms() {
    return rfa1cForms;
  }

  public void setRfa1cForms(
      List<RFA1cForm> rfa1cForms) {
    this.rfa1cForms = rfa1cForms;
  }

  public AdoptionHistoryDTO getAdoptionHistory() {
    return adoptionHistory;
  }

  public void setAdoptionHistory(AdoptionHistoryDTO adoptionHistory) {
    this.adoptionHistory = adoptionHistory;
  }

  public ChildDesiredDTO getChildDesired() {
    return childDesired;
  }

  public void setChildDesired(ChildDesiredDTO childDesired) {
    this.childDesired = childDesired;
  }

  public ReferencesDTO getReferences() {
    return references;
  }

  public void setReferences(ReferencesDTO references) {
    this.references = references;
  }

  public ApplicantsDeclarationDTO getApplicantsDeclaration() {
    return applicantsDeclaration;
  }

  public void setApplicantsDeclaration(
      ApplicantsDeclarationDTO applicantsDeclaration) {
    this.applicantsDeclaration = applicantsDeclaration;
  }

  public boolean isOtherType() {
    return otherType;
  }

  public void setOtherType(boolean otherType) {
    this.otherType = otherType;
  }

  public String getOtherTypeDescription() {
    return otherTypeDescription;
  }

  public void setOtherTypeDescription(String otherTypeDescription) {
    this.otherTypeDescription = otherTypeDescription;
  }

  public CountyType getApplicationCounty() {
    return applicationCounty;
  }

  public void setApplicationCounty(CountyType applicationCounty) {
    this.applicationCounty = applicationCounty;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getPlacementHomeId() {
    return placementHomeId;
  }

  public void setPlacementHomeId(String placementHomeId) {
    this.placementHomeId = placementHomeId;
  }

}
