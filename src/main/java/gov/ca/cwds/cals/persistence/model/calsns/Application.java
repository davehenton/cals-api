package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Application extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @Column(name = "is_initial_application")
  private Boolean isInitialApplication;

  @Column(name = "other_application_type")
  private String otherApplicationType;

  @Column(name = "is_child_identified")
  private Boolean isChildIdentified;

  @Column(name = "signature_city")
  private String signatureCity;

  @SuppressWarnings("squid:S3437") //LocalDate is serializable
  @Column(name = "signature_date")
  private LocalDate signatureDate;

  @Column(name = "is_child_currently_in_your_home")
  private Boolean isChildCurrentlyInYourHome;

  @OneToOne
  @JoinColumn(unique = true)
  private LicensureHistory licensureHistory;

  @OneToOne
  @JoinColumn(unique = true)
  private ChildPreferences childPreferences;

  @OneToOne
  @JoinColumn(unique = true)
  private CountyType signatureCounty;

  @OneToMany(mappedBy = "application")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<DeterminedChild> determinedChildren = new HashSet<>();

  @OneToMany(mappedBy = "application")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Applicant> applicants = new HashSet<>();

  @ManyToOne
  private CountyType forCountyUseOnly;

  @ManyToOne
  private ApplicationStatusType status;

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JoinTable(name = "application_references",
      joinColumns = @JoinColumn(name = "applications_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "references_id", referencedColumnName = "id"))
  private Set<Person> references = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean isIsInitialApplication() {
    return isInitialApplication;
  }

  public Application isInitialApplication(Boolean isInitialApplication) {
    this.isInitialApplication = isInitialApplication;
    return this;
  }

  public void setIsInitialApplication(Boolean isInitialApplication) {
    this.isInitialApplication = isInitialApplication;
  }

  public String getOtherApplicationType() {
    return otherApplicationType;
  }

  public Application otherApplicationType(String otherApplicationType) {
    this.otherApplicationType = otherApplicationType;
    return this;
  }

  public void setOtherApplicationType(String otherApplicationType) {
    this.otherApplicationType = otherApplicationType;
  }

  public Boolean isIsChildIdentified() {
    return isChildIdentified;
  }

  public Application isChildIdentified(Boolean isChildIdentified) {
    this.isChildIdentified = isChildIdentified;
    return this;
  }

  public void setIsChildIdentified(Boolean isChildIdentified) {
    this.isChildIdentified = isChildIdentified;
  }

  public String getSignatureCity() {
    return signatureCity;
  }

  public Application signatureCity(String signatureCity) {
    this.signatureCity = signatureCity;
    return this;
  }

  public void setSignatureCity(String signatureCity) {
    this.signatureCity = signatureCity;
  }

  public LocalDate getSignatureDate() {
    return signatureDate;
  }

  public Application signatureDate(LocalDate signatureDate) {
    this.signatureDate = signatureDate;
    return this;
  }

  public void setSignatureDate(LocalDate signatureDate) {
    this.signatureDate = signatureDate;
  }

  public Boolean isIsChildCurrentlyInYourHome() {
    return isChildCurrentlyInYourHome;
  }

  public Application isChildCurrentlyInYourHome(Boolean isChildCurrentlyInYourHome) {
    this.isChildCurrentlyInYourHome = isChildCurrentlyInYourHome;
    return this;
  }

  public void setIsChildCurrentlyInYourHome(Boolean isChildCurrentlyInYourHome) {
    this.isChildCurrentlyInYourHome = isChildCurrentlyInYourHome;
  }

  public LicensureHistory getLicensureHistory() {
    return licensureHistory;
  }

  public Application licensureHistory(LicensureHistory licensureHistory) {
    this.licensureHistory = licensureHistory;
    return this;
  }

  public void setLicensureHistory(LicensureHistory licensureHistory) {
    this.licensureHistory = licensureHistory;
  }

  public ChildPreferences getChildPreferences() {
    return childPreferences;
  }

  public Application childPreferences(ChildPreferences childPreferences) {
    this.childPreferences = childPreferences;
    return this;
  }

  public void setChildPreferences(ChildPreferences childPreferences) {
    this.childPreferences = childPreferences;
  }

  public CountyType getSignatureCounty() {
    return signatureCounty;
  }

  public Application signatureCounty(CountyType countyType) {
    this.signatureCounty = countyType;
    return this;
  }

  public void setSignatureCounty(CountyType countyType) {
    this.signatureCounty = countyType;
  }

  public Set<DeterminedChild> getDeterminedChildren() {
    return determinedChildren;
  }

  public Application determinedChildren(Set<DeterminedChild> determinedChildren) {
    this.determinedChildren = determinedChildren;
    return this;
  }

  public Application addDeterminedChild(DeterminedChild determinedChild) {
    this.determinedChildren.add(determinedChild);
    determinedChild.setApplication(this);
    return this;
  }

  public Application removeDeterminedChild(DeterminedChild determinedChild) {
    this.determinedChildren.remove(determinedChild);
    determinedChild.setApplication(null);
    return this;
  }

  public void setDeterminedChildren(Set<DeterminedChild> determinedChildren) {
    this.determinedChildren = determinedChildren;
  }

  public Set<Applicant> getApplicants() {
    return applicants;
  }

  public Application applicants(Set<Applicant> applicants) {
    this.applicants = applicants;
    return this;
  }

  public Application addApplicants(Applicant applicant) {
    this.applicants.add(applicant);
    applicant.setApplication(this);
    return this;
  }

  public Application removeApplicants(Applicant applicant) {
    this.applicants.remove(applicant);
    applicant.setApplication(null);
    return this;
  }

  public void setApplicants(Set<Applicant> applicants) {
    this.applicants = applicants;
  }

  public CountyType getForCountyUseOnly() {
    return forCountyUseOnly;
  }

  public Application forCountyUseOnly(CountyType countyType) {
    this.forCountyUseOnly = countyType;
    return this;
  }

  public void setForCountyUseOnly(CountyType countyType) {
    this.forCountyUseOnly = countyType;
  }

  public ApplicationStatusType getStatus() {
    return status;
  }

  public Application status(ApplicationStatusType applicationStatusType) {
    this.status = applicationStatusType;
    return this;
  }

  public void setStatus(ApplicationStatusType applicationStatusType) {
    this.status = applicationStatusType;
  }

  public Set<Person> getReferences() {
    return references;
  }

  public Application references(Set<Person> people) {
    this.references = people;
    return this;
  }

  public Application addReferences(Person person) {
    this.references.add(person);
    return this;
  }

  public Application removeReferences(Person person) {
    this.references.remove(person);
    return this;
  }

  public void setReferences(Set<Person> people) {
    this.references = people;
  }

  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    if (application.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), application.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
