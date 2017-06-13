package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * A Applicant.
 */
@Entity
@Table(name = "applicant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Applicant extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @OneToOne
  @JoinColumn(unique = true)
  private HouseholdAdult householdPerson;

  @OneToOne
  @JoinColumn(unique = true)
  private EducationLevelType educationHighestLevel;

  @OneToMany(mappedBy = "applicant")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<RelationHistoryRecord> relationHistoryRecords = new HashSet<>();

  @ManyToOne
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public HouseholdAdult getHouseholdPerson() {
    return householdPerson;
  }

  public Applicant householdPerson(HouseholdAdult householdAdult) {
    this.householdPerson = householdAdult;
    return this;
  }

  public void setHouseholdPerson(HouseholdAdult householdAdult) {
    this.householdPerson = householdAdult;
  }

  public EducationLevelType getEducationHighestLevel() {
    return educationHighestLevel;
  }

  public Applicant educationHighestLevel(EducationLevelType educationLevelType) {
    this.educationHighestLevel = educationLevelType;
    return this;
  }

  public void setEducationHighestLevel(EducationLevelType educationLevelType) {
    this.educationHighestLevel = educationLevelType;
  }

  public Set<RelationHistoryRecord> getRelationHistoryRecords() {
    return relationHistoryRecords;
  }

  public Applicant relationHistoryRecords(Set<RelationHistoryRecord> relationHistoryRecords) {
    this.relationHistoryRecords = relationHistoryRecords;
    return this;
  }

  public Applicant addRelationHistoryRecords(RelationHistoryRecord relationHistoryRecord) {
    this.relationHistoryRecords.add(relationHistoryRecord);
    relationHistoryRecord.setApplicant(this);
    return this;
  }

  public Applicant removeRelationHistoryRecords(RelationHistoryRecord relationHistoryRecord) {
    this.relationHistoryRecords.remove(relationHistoryRecord);
    relationHistoryRecord.setApplicant(null);
    return this;
  }

  public void setRelationHistoryRecords(Set<RelationHistoryRecord> relationHistoryRecords) {
    this.relationHistoryRecords = relationHistoryRecords;
  }

  public Application getApplication() {
    return application;
  }

  public Applicant application(Application application) {
    this.application = application;
    return this;
  }

  public void setApplication(Application application) {
    this.application = application;
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
    Applicant applicant = (Applicant) o;
    if (applicant.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), applicant.getId());
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
