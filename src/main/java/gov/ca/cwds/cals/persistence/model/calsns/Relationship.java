package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Relationship.
 */
@Entity
@Table(name = "relationship")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Relationship extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @ManyToOne(optional = false)
  @NotNull
  private Person from;

  @ManyToOne(optional = false)
  @NotNull
  private Person to;

  @ManyToOne
  private RelationshipType relationshipType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Person getFrom() {
    return from;
  }

  public Relationship from(Person person) {
    this.from = person;
    return this;
  }

  public void setFrom(Person person) {
    this.from = person;
  }

  public Person getTo() {
    return to;
  }

  public Relationship to(Person person) {
    this.to = person;
    return this;
  }

  public void setTo(Person person) {
    this.to = person;
  }

  public RelationshipType getRelationshipType() {
    return relationshipType;
  }

  public Relationship relationshipType(RelationshipType relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  public void setRelationshipType(RelationshipType relationshipType) {
    this.relationshipType = relationshipType;
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
    Relationship relationship = (Relationship) o;
    if (relationship.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), relationship.getId());
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
