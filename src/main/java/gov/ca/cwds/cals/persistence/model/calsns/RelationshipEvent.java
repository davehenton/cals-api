package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelationshipEvent.
 */
@Entity
@Table(name = "relationship_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelationshipEvent extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @Column(name = "event_place")
  private String eventPlace;

  @SuppressWarnings("squid:S3437") //LocalDate is serializable
  @Column(name = "event_date")
  private LocalDate eventDate;

  @ManyToOne
  private RelationshipEventType eventType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEventPlace() {
    return eventPlace;
  }

  public RelationshipEvent eventPlace(String eventPlace) {
    this.eventPlace = eventPlace;
    return this;
  }

  public void setEventPlace(String eventPlace) {
    this.eventPlace = eventPlace;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public RelationshipEvent eventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }

  public RelationshipEventType getEventType() {
    return eventType;
  }

  public RelationshipEvent eventType(RelationshipEventType relationshipEventType) {
    this.eventType = relationshipEventType;
    return this;
  }

  public void setEventType(RelationshipEventType relationshipEventType) {
    this.eventType = relationshipEventType;
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
    RelationshipEvent relationshipEvent = (RelationshipEvent) o;
    if (relationshipEvent.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), relationshipEvent.getId());
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
