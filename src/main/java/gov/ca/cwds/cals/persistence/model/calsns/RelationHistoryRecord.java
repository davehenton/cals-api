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
import javax.validation.constraints.NotNull;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelationHistoryRecord.
 */
@Entity
@Table(name = "relation_history_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelationHistoryRecord extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @OneToOne(optional = false)
  @NotNull
  @JoinColumn(unique = true)
  private RelationshipEvent startEvent;

  @OneToOne(optional = false)
  @NotNull
  @JoinColumn(unique = true)
  private RelationshipEvent endEvent;

  @ManyToOne
  private RelationshipType relationshipType;

  @ManyToOne
  private Applicant applicant;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RelationshipEvent getStartEvent() {
    return startEvent;
  }

  public RelationHistoryRecord startEvent(RelationshipEvent relationshipEvent) {
    this.startEvent = relationshipEvent;
    return this;
  }

  public void setStartEvent(RelationshipEvent relationshipEvent) {
    this.startEvent = relationshipEvent;
  }

  public RelationshipEvent getEndEvent() {
    return endEvent;
  }

  public RelationHistoryRecord endEvent(RelationshipEvent relationshipEvent) {
    this.endEvent = relationshipEvent;
    return this;
  }

  public void setEndEvent(RelationshipEvent relationshipEvent) {
    this.endEvent = relationshipEvent;
  }

  public RelationshipType getRelationshipType() {
    return relationshipType;
  }

  public RelationHistoryRecord relationshipType(RelationshipType relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  public void setRelationshipType(RelationshipType relationshipType) {
    this.relationshipType = relationshipType;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public RelationHistoryRecord applicant(Applicant applicant) {
    this.applicant = applicant;
    return this;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
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
    RelationHistoryRecord relationHistoryRecord = (RelationHistoryRecord) o;
    if (relationHistoryRecord.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), relationHistoryRecord.getId());
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
