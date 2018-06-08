package gov.ca.cwds.cals.persistence.hibernate.dialect;

/**
 * CONNX 12 version Hibernate dialect.
 * @author CWDS CALS API Team
 */
public class CONNX12Dialect extends CONNXDialect {

  /**
   * Get limit string.
   */
  @Override
  public String getLimitString(String querySelect, int offset, int limit) {
    return new StringBuffer(querySelect.length() + 22)
        .append(querySelect)
        .append("{maxrows ").append(limit).append(",").append(offset + 1).append("}")
        .toString();
  }

}
