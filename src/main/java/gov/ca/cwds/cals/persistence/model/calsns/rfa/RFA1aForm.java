package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cals.persistence.model.calsns.Dictionary;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author CWDS CALS API Team
 */
@Entity
public class RFA1aForm extends BaseDTO implements PersistentObject, Response {

  private static final long serialVersionUID = -6201382973500280111L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }

}
