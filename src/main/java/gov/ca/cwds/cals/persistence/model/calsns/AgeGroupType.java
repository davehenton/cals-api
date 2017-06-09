package gov.ca.cwds.cals.persistence.model.calsns;

import static gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType.NQ_ALL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedQuery;

/** @author CWDS CALS API Team */
@NamedQuery(name = NQ_ALL, query = "FROM AgeGroupType ORDER BY id ASC")
@Entity
@Table(name = "age_group_type")
@Cacheable
public class AgeGroupType implements Serializable, PersistentObject {

  private static final long serialVersionUID = 8140313357955597688L;

  public static final String NQ_ALL =
      "gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType.findAll";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Size(max = 20)
  @Column(name = "name", length = 20, nullable = false)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public AgeGroupType name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AgeGroupType ageGroupType = (AgeGroupType) o;
    if (ageGroupType.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), ageGroupType.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "AgeGroupType{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
  }

  @Override
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }
}
