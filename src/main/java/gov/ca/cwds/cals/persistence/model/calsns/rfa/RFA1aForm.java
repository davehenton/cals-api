package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationshipDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.rfa.RFAApplicationStatus;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") //LocalDateTime is serializable
@NamedQuery(name = RFA1aForm.NAMED_QUERY_FIND_ALL, query = "FROM RFA1aForm WHERE status <> 'DRAFT' ORDER BY id DESC")
@NamedQuery(name = RFA1aForm.NAMED_QUERY_FIND_UPDATED_AFTER,
    query = "SELECT form FROM RFA1aForm form"
        + " LEFT JOIN RFA1aApplicant applicant ON applicant.formId = form.id"
        + " LEFT JOIN RFA1aMinorChild minorChild ON minorChild.formId = form.id"
        + " LEFT JOIN RFA1aOtherAdult otherAdult ON otherAdult.formId = form.id"
        + " LEFT JOIN RFA1bForm _1bForm ON _1bForm.formId = form.id"
        + " LEFT JOIN RFA1cForm _1cForm ON _1cForm.formId = form.id"
        + " WHERE form.createDateTime > :dateAfter OR form.updateDateTime > :dateAfter"
        + " OR applicant.createDateTime > :dateAfter OR applicant.updateDateTime > :dateAfter"
        + " OR minorChild.createDateTime > :dateAfter OR minorChild.updateDateTime > :dateAfter"
        + " OR otherAdult.createDateTime > :dateAfter OR otherAdult.updateDateTime > :dateAfter"
        + " OR _1bForm.createDateTime > :dateAfter OR _1bForm.updateDateTime > :dateAfter"
        + " OR _1cForm.createDateTime > :dateAfter OR _1cForm.updateDateTime > :dateAfter"
)
@Entity
@Table(name = "rfa_1a")
public class RFA1aForm extends RFABaseEntity implements PersistentObject {

  private static final long serialVersionUID = -6201382973500280112L;
  public static final String NAMED_QUERY_FIND_ALL =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm.find.all";
  public static final String NAMED_QUERY_FIND_UPDATED_AFTER =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm.find.updated.after";

  @NotNull
  @Enumerated(EnumType.STRING)
  private RFAApplicationStatus status;

  @Column(name = "placement_home_id", length = 10)
  private String placementHomeId;

  @Type(type = "ApplicationJsonType")
  private ApplicationDTO application;

  @Type(type = "ResidenceJsonType")
  private ResidenceDTO residence;

  @Type(type = "ApplicantsRelationshipJsonType")
  @Column(name = "relationships")
  private ApplicantsRelationshipDTO applicantsRelationship;

  @Type(type = "ApplicantHistoryJsonType")
  @Column(name = "applicants_history")
  private ApplicantsHistoryDTO applicantsHistory;

  @OneToMany
  @JoinColumn(name = "application_id")
  @OrderBy("id")
  private List<RFA1aApplicant> applicants;

  @OneToMany
  @JoinColumn(name = "application_id")
  @OrderBy("id")
  private List<RFA1aMinorChild> minorChildren;

  @OneToMany
  @JoinColumn(name = "application_id")
  @OrderBy("id")
  private List<RFA1aOtherAdult> otherAdults;

  @OneToMany
  @JoinColumn(name = "application_id")
  @OrderBy("id")
  private List<RFA1bForm> rfa1bForms;

  @OneToMany
  @JoinColumn(name = "application_id")
  @OrderBy("id")
  private List<RFA1cForm> rfa1cForms;

  @Type(type = "AdoptionHistoryJsonType")
  @Column(name = "adoption_history")
  private AdoptionHistoryDTO adoptionHistory;

  @Type(type = "ChildDesiredJsonType")
  @Column(name = "child_desired")
  private ChildDesiredDTO childDesired;

  @Type(type = "ReferencesJsonType")
  @Column(name = "jhi_references")
  private ReferencesDTO references;

  @Type(type = "ApplicantsDeclaration")
  @Column(name = "applicants_declaration")
  private ApplicantsDeclarationDTO applicantsDeclaration;

  public String getPlacementHomeId() {
    return placementHomeId;
  }

  public void setPlacementHomeId(String placementHomeId) {
    this.placementHomeId = placementHomeId;
  }

  public RFAApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(RFAApplicationStatus status) {
    this.status = status;
  }

  public ApplicationDTO getApplication() {
    return application;
  }

  public void setApplication(ApplicationDTO application) {
    this.application = application;
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

  public List<RFA1aApplicant> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<RFA1aApplicant> applicants) {
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

  public ApplicantsHistoryDTO getApplicantsHistory() {
    return applicantsHistory;
  }

  public void setApplicantsHistory(ApplicantsHistoryDTO applicantsHistory) {
    this.applicantsHistory = applicantsHistory;
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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder
        .reflectionEquals(this, o, "applicants", "minorChildren", "otherAdults", "rfa1bForms",
            "rfa1cForms");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder
        .reflectionHashCode(this, "applicants", "minorChildren", "otherAdults", "rfa1bForms",
            "rfa1cForms");
  }


}
