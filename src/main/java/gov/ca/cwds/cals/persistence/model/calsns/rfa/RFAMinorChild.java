package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.NAMED_QUERY_FIND_ALL_BY_FORM;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.PARAM_CHILD_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.PARAM_FORM_ID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(
    name = NAMED_QUERY_FIND_ALL_BY_FORM,
    query = "FROM RFAMinorChild mc WHERE mc.applicationId = :" + PARAM_FORM_ID
)
@NamedQuery(
    name = NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID,
    query =
        "FROM RFAMinorChild mc WHERE mc.applicationId = :"
            + PARAM_FORM_ID
            + " AND mc.id = :"
            + PARAM_CHILD_ID
)
@Entity
@Table(name = "rfa_1a_minor_child")
public class RFAMinorChild extends RFABaseEntity {

  private static final long serialVersionUID = -2264013085327411067L;

  public static final java.lang.String NAMED_QUERY_FIND_ALL_BY_FORM =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.find.all.forFormId";
  public static final String NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild.find.by.id";
  public static final String PARAM_FORM_ID = "applicationId";
  public static final String PARAM_CHILD_ID = "minorChildId";

  @Basic
  @Column(name = "application_id")
  private Long applicationId;

  @Column(name = "minor_child")
  @Type(type = "MinorChildJsonType")
  private MinorChild minorChild;

  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }

  public void setMinorChild(MinorChild minorChild) {
    this.minorChild = minorChild;
  }

  public MinorChild getMinorChild() {
    return minorChild;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "RFA1aMinorChild{"
        + "applicationId="
        + applicationId
        + ", minorChild="
        + minorChild
        + '}';
  }
}
