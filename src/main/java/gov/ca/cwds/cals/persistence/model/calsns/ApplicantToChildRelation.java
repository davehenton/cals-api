package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ApplicantToChildRelation.
 */
@Entity
@Table(name = "applicant_to_child_relation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicantToChildRelation extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @OneToOne
  @JoinColumn(unique = true)
  private RelationshipType relationtype;

  @ManyToOne
  private Applicant applicant;

  @ManyToOne
  private HouseholdChild child;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RelationshipType getRelationtype() {
    return relationtype;
  }

  public ApplicantToChildRelation relationtype(RelationshipType relationshipType) {
    this.relationtype = relationshipType;
    return this;
  }

  public void setRelationtype(RelationshipType relationshipType) {
    this.relationtype = relationshipType;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public ApplicantToChildRelation applicant(Applicant applicant) {
    this.applicant = applicant;
    return this;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }

  public HouseholdChild getChild() {
    return child;
  }

  public ApplicantToChildRelation child(HouseholdChild householdChild) {
    this.child = householdChild;
    return this;
  }

  public void setChild(HouseholdChild householdChild) {
    this.child = householdChild;
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
    ApplicantToChildRelation applicantToChildRelation = (ApplicantToChildRelation) o;
    if (applicantToChildRelation.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), applicantToChildRelation.getId());
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
