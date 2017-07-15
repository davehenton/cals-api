package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import gov.ca.cwds.rest.api.Request;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/** @author CWDS CALS API Team */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseDictionary extends BaseDTO implements PersistentObject, Request {

  private static final long serialVersionUID = 1405907682848102125L;

  static final String NAMED_QUERY_PREFIX = "gov.ca.cwds.cals.persistence.model.calsns.dictionaries";
  static final String NAMED_QUERY_FIND_ALL_SUFFIX = ".find.all";

  @ApiModelProperty()
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @ApiModelProperty()
  @NotNull
  @Size(max = 100)
  @Column(name = "description", length = 100, nullable = false)
  private String value;

  @JsonIgnore
  @Column(name = "cws_id")
  private Integer cwsId;

  @JsonIgnore
  @Column(name = "cws_short_code", length = 2)
  private String cwsShortCode;

  @JsonIgnore
  @Column(name = "lis_id", length = 4)
  private String lisId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getCwsId() {
    return cwsId;
  }

  public void setCwsId(int cwsId) {
    this.cwsId = cwsId;
  }

  public String getCwsShortCode() {
    return cwsShortCode;
  }

  public void setCwsShortCode(String cwsShortCode) {
    this.cwsShortCode = cwsShortCode;
  }

  public String getLisId() {
    return lisId;
  }

  public void setLisId(String lisId) {
    this.lisId = lisId;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, "cwsId", "cwsShortCode", "lisId");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "cwsId", "cwsShortCode", "lisId");
  }

  @Override
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return id;
  }

  public static String buildFindAllQueryName(Class<? extends BaseDictionary> dictionaryClass) {
    return dictionaryClass.getName() + NAMED_QUERY_FIND_ALL_SUFFIX;
  }
}
