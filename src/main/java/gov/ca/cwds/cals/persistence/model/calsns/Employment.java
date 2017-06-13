package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Employment.
 */
@Entity
@Table(name = "employment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employment extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "occupation", nullable = false)
  private String occupation;

  @NotNull
  @Column(name = "annual_income", precision = 10, scale = 2, nullable = false)
  private BigDecimal annualIncome;

  @SuppressWarnings("squid:S3437") //LocalDate is serializable
  @NotNull
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @SuppressWarnings("squid:S3437") //LocalDate is serializable
  @Column(name = "end_date")
  private LocalDate endDate;

  @ManyToOne
  private Employer employer;

  @ManyToOne
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOccupation() {
    return occupation;
  }

  public Employment occupation(String occupation) {
    this.occupation = occupation;
    return this;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public BigDecimal getAnnualIncome() {
    return annualIncome;
  }

  public Employment annualIncome(BigDecimal annualIncome) {
    this.annualIncome = annualIncome;
    return this;
  }

  public void setAnnualIncome(BigDecimal annualIncome) {
    this.annualIncome = annualIncome;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public Employment startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public Employment endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Employer getEmployer() {
    return employer;
  }

  public Employment employer(Employer employer) {
    this.employer = employer;
    return this;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public Person getPerson() {
    return person;
  }

  public Employment person(Person person) {
    this.person = person;
    return this;
  }

  public void setPerson(Person person) {
    this.person = person;
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
    Employment employment = (Employment) o;
    if (employment.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), employment.getId());
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
