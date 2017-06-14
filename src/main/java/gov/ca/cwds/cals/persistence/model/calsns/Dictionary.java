package gov.ca.cwds.cals.persistence.model.calsns;

import static gov.ca.cwds.cals.persistence.model.calsns.Dictionary.NQ_FIND_BY_TYPE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;
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

/** @author CALS API team. */
@NamedQuery(name = NQ_FIND_BY_TYPE, query = "FROM Dictionary as d WHERE d.type = :type")
@ApiModel(description = "@author CALS API team.")
@Entity
@Table(name = "dictionary")
public class Dictionary extends BaseDTO implements Serializable, PersistentObject {

  private static final long serialVersionUID = -3687496807828424795L;

  public static final String NQ_FIND_BY_TYPE =
      "gov.ca.cwds.cals.persistence.model.calsns.Dictionary.find_by_type";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Size(max = 100)
  @Column(name = "value", length = 100, nullable = false)
  private String value;

  @NotNull
  @Size(max = 100)
  @Column(name = "type", length = 100, nullable = false)
  private String type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public Dictionary value(String value) {
    this.value = value;
    return this;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dictionary dictionary = (Dictionary) o;
    if (dictionary.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), dictionary.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Dictionary{"
        + "id="
        + getId()
        + ", value='"
        + getValue()
        + "'"
        + ", type='"
        + getType()
        + "'"
        + "}";
  }

  @Override
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }
}
