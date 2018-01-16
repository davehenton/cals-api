package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.util.Utils;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
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
  private Boolean initialApplication;

  @ApiModelProperty(value = "Other Type Description", example = "Description")
  private String otherTypeDescription;

  @ApiModelProperty(value = "County Type")
  @CheckReferentialIntegrity(enrich = true)
  private CountyType applicationCounty;

  @ApiModelProperty(hidden = true)
  @Valid
  private ResidenceDTO residence;

  @ApiModelProperty(hidden = true)
  @Valid
  private ApplicantsRelationshipDTO applicantsRelationship;

  @ApiModelProperty(hidden = true)
  @Valid
  private ApplicantsHistoryDTO applicantsHistory;

  @ApiModelProperty(hidden = true)
  @Valid
  private List<ApplicantDTO> applicants;

  @ApiModelProperty(hidden = true)
  @Valid
  private List<MinorChildDTO> minorChildren = new ArrayList<>();

  @ApiModelProperty(hidden = true)
  @Valid
  private List<OtherAdultDTO> otherAdults;

  @ApiModelProperty(hidden = true)
  private List<RFA1cFormDTO> rfa1cForms;

  @ApiModelProperty(hidden = true)
  @Valid
  private AdoptionHistoryDTO adoptionHistory;

  @ApiModelProperty(hidden = true)
  @Valid
  private ChildDesiredDTO childDesired;

  @ApiModelProperty(hidden = true)
  @Valid
  private ReferencesDTO references;

  @ApiModelProperty(hidden = true)
  @Valid
  private ApplicantsDeclarationDTO applicantsDeclaration;

  @ApiModelProperty(hidden = true)
  private String placementHomeId;

  public Boolean isInitialApplication() {
    return initialApplication;
  }

  public void setInitialApplication(Boolean initialApplication) {
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

  public void setApplicantsRelationship(ApplicantsRelationshipDTO applicantsRelationship) {
    this.applicantsRelationship = applicantsRelationship;
  }

  public ApplicantsHistoryDTO getApplicantsHistory() {
    return applicantsHistory;
  }

  public void setApplicantsHistory(ApplicantsHistoryDTO applicantsHistory) {
    this.applicantsHistory = applicantsHistory;
  }

  public List<ApplicantDTO> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<ApplicantDTO> applicants) {
    this.applicants = applicants;
  }

  public List<MinorChildDTO> getMinorChildren() {
    return minorChildren;
  }

  public void setMinorChildren(List<MinorChildDTO> minorChildren) {
    this.minorChildren = minorChildren;
  }

  public List<OtherAdultDTO> getOtherAdults() {
    return otherAdults;
  }

  public void setOtherAdults(List<OtherAdultDTO> otherAdults) {
    this.otherAdults = otherAdults;
  }

  public List<RFA1cFormDTO> getRfa1cForms() {
    return rfa1cForms;
  }

  public void setRfa1cForms(List<RFA1cFormDTO> rfa1cForms) {
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

  public void setApplicantsDeclaration(ApplicantsDeclarationDTO applicantsDeclaration) {
    this.applicantsDeclaration = applicantsDeclaration;
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

  @JsonIgnore
  public ApplicantDTO getFirstApplicant() {
    return Utils.Applicant.getPrimary(this);
  }

}
