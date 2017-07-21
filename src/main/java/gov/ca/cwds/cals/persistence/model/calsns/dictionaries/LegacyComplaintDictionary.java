package gov.ca.cwds.cals.persistence.model.calsns.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LegacyComplaintDictionary extends BaseDictionary {

  @JsonIgnore
  @Column(name = "cws_id")
  private Integer cwsId;

  @JsonIgnore
  @Column(name = "lis_id", length = 4)
  private String lisId;

  public int getCwsId() {
    return cwsId;
  }

  public void setCwsId(int cwsId) {
    this.cwsId = cwsId;
  }

  public String getLisId() {
    return lisId;
  }

  public void setLisId(String lisId) {
    this.lisId = lisId;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, "cwsId", "lisId");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "cwsId", "lisId");
  }
}
