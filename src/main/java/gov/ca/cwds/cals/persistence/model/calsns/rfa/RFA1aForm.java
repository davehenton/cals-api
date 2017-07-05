package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.Response;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") //LocalDateTime is serializable
@NamedQuery(name = RFA1aForm.NAMED_QUERY_FIND_ALL, query = "FROM RFA1aForm ORDER BY id ASC")
@Entity
@Table(name = "rfa_1a")
public class RFA1aForm extends RFABaseEntity implements PersistentObject, Response {

  private static final long serialVersionUID = -6201382973500280111L;
  public static final String NAMED_QUERY_FIND_ALL =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm.find.all";


  @Type(type = "ApplicationJsonType")
  private Application application;

  @Type(type = "ResidenceJsonType")
  private Residence residence;

  @Type(type = "ApplicantsRelationshipJsonType")
  private ApplicantsRelationship relationships;

  @Type(type = "ApplicantHistoryJsonType")
  @Column(name = "applicants_history")
  private ApplicantsHistory applicantsHistory;

  @OneToMany
  @JoinColumn(name = "application_id")
  private List<RFA1aApplicant> applicantEntities;

  @Type(type = "AdoptionHistoryJsonType")
  @Column(name = "adoption_history")
  private AdoptionHistory adoptionHistory;

  @Type(type = "ChildDesiredJsonType")
  @Column(name = "child_desired")
  private ChildDesired childDesired;

  @Type(type = "ReferencesJsonType")
  @Column(name = "jhi_references")
  private ReferencesDTO references;

  @Type(type = "ApplicantsDeclaration")
  @Column(name = "applicants_declaration")
  private ApplicantsDeclaration applicantsDeclaration;

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public Residence getResidence() {
    return residence;
  }

  public void setResidence(Residence residence) {
    this.residence = residence;
  }

  public ApplicantsRelationship getRelationships() {
    return relationships;
  }

  public void setRelationships(
      ApplicantsRelationship relationships) {
    this.relationships = relationships;
  }

  public List<RFA1aApplicant> getApplicantEntities() {
    return applicantEntities;
  }

  public void setApplicantEntities(
      List<RFA1aApplicant> applicantEntities) {
    this.applicantEntities = applicantEntities;
  }

  public AdoptionHistory getAdoptionHistory() {
    return adoptionHistory;
  }

  public void setAdoptionHistory(
      AdoptionHistory adoptionHistory) {
    this.adoptionHistory = adoptionHistory;
  }

  public ApplicantsHistory getApplicantsHistory() {
    return applicantsHistory;
  }

  public void setApplicantsHistory(
      ApplicantsHistory applicantsHistory) {
    this.applicantsHistory = applicantsHistory;
  }

  public ChildDesired getChildDesired() {
    return childDesired;
  }

  public void setChildDesired(ChildDesired childDesired) {
    this.childDesired = childDesired;
  }

  public ReferencesDTO getReferences() {
    return references;
  }

  public void setReferences(ReferencesDTO references) {
    this.references = references;
  }

  public ApplicantsDeclaration getApplicantsDeclaration() {
    return applicantsDeclaration;
  }

  public void setApplicantsDeclaration(ApplicantsDeclaration applicantsDeclaration) {
    this.applicantsDeclaration = applicantsDeclaration;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, "applicantEntities");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "applicantEntities");
  }


}
