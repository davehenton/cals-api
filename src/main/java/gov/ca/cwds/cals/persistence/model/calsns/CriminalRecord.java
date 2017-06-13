package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CriminalRecord.
 */
@Entity
@Table(name = "criminal_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CriminalRecord extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Lob
  @Column(name = "offense_description", nullable = false)
  private String offenseDescription;

  @SuppressWarnings("squid:S3437") //LocalDate is serializable
  @Column(name = "offense_date")
  private LocalDate offenseDate;

  @NotNull
  @Lob
  @Column(name = "offense_explanation", nullable = false)
  private String offenseExplanation;

  @Size(max = 100)
  @Column(name = "city", length = 100)
  private String city;

  @OneToOne
  @JoinColumn(unique = true)
  private StateType state;

  @ManyToOne
  private HouseholdAdult person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOffenseDescription() {
    return offenseDescription;
  }

  public CriminalRecord offenseDescription(String offenseDescription) {
    this.offenseDescription = offenseDescription;
    return this;
  }

  public void setOffenseDescription(String offenseDescription) {
    this.offenseDescription = offenseDescription;
  }

  public LocalDate getOffenseDate() {
    return offenseDate;
  }

  public CriminalRecord offenseDate(LocalDate offenseDate) {
    this.offenseDate = offenseDate;
    return this;
  }

  public void setOffenseDate(LocalDate offenseDate) {
    this.offenseDate = offenseDate;
  }

  public String getOffenseExplanation() {
    return offenseExplanation;
  }

  public CriminalRecord offenseExplanation(String offenseExplanation) {
    this.offenseExplanation = offenseExplanation;
    return this;
  }

  public void setOffenseExplanation(String offenseExplanation) {
    this.offenseExplanation = offenseExplanation;
  }

  public String getCity() {
    return city;
  }

  public CriminalRecord city(String city) {
    this.city = city;
    return this;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public StateType getState() {
    return state;
  }

  public CriminalRecord state(StateType stateType) {
    this.state = stateType;
    return this;
  }

  public void setState(StateType stateType) {
    this.state = stateType;
  }

  public HouseholdAdult getPerson() {
    return person;
  }

  public CriminalRecord person(HouseholdAdult householdAdult) {
    this.person = householdAdult;
    return this;
  }

  public void setPerson(HouseholdAdult householdAdult) {
    this.person = householdAdult;
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
    CriminalRecord criminalRecord = (CriminalRecord) o;
    if (criminalRecord.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), criminalRecord.getId());
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
