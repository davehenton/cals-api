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
public class ShortCodeComplaintDictionary extends SimpleDictionary {
  private static final long serialVersionUID = 8931766854976564993L;

  @JsonIgnore
  @Column(name = "cws_short_code", length = 2)
  private String cwsShortCode;

  public String getCwsShortCode() {
    return cwsShortCode;
  }

  public void setCwsShortCode(String cwsShortCode) {
    this.cwsShortCode = cwsShortCode;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, "cwsShortCode");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "cwsShortCode");
  }
}
