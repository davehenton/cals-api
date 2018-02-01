package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cms.data.access.CWSIdentifier;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = StateType.NAMED_QUERY_FIND_ALL, query = "FROM StateType ORDER BY value ASC")

@NamedQuery(
    name = StateType.NAMED_QUERY_FIND_STATE_BY_CODE,
    query =
        "FROM StateType f WHERE f.id = :stateCode"
)
@Entity
@Cacheable
@Table(name = "state_type")
public class StateType extends BaseDictionary implements CWSIdentifier {

  public static final String NAMED_QUERY_FIND_ALL =
      NAMED_QUERY_PREFIX + ".StateType" + NAMED_QUERY_FIND_ALL_SUFFIX;

  public static final String NAMED_QUERY_FIND_STATE_BY_CODE = "gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType.find.by.lisId";


  private static final long serialVersionUID = -3799114345234018083L;

  @ApiModelProperty()
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  @Column(name = "id")
  @JsonIgnore
  private Long technicalId;

  @ApiModelProperty()
  @Column(name = "lis_id")
  private String id;

  @JsonIgnore
  @Column(name = "cws_id")
  private Integer cwsId;

  public int getCwsId() {
    return cwsId;
  }

  public void setCwsId(int cwsId) {
    this.cwsId = cwsId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getTechnicalId() {
    return technicalId;
  }

  public void setTechnicalId(Long technicalId) {
    this.technicalId = technicalId;
  }

  @Override
  @JsonIgnore
  public Serializable getPrimaryKey() {
    return getId();
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, "cwsId", "technicalId");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "cwsId", "technicalId");
  }

}
