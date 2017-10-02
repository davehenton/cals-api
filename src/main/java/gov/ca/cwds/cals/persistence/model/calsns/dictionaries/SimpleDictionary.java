package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.Request;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** @author CWDS CALS API Team */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@Cacheable
public abstract class SimpleDictionary extends BaseDictionary {

  private static final long serialVersionUID = 1405907682848102125L;

  @ApiModelProperty()
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
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }

  @Override
  public String toString() {
    return String
        .format(getClass().getSimpleName() + "{id = %s, value = %s})", getId(), getValue());
  }

}
