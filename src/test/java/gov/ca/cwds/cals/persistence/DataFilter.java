package gov.ca.cwds.cals.persistence;

/**
 * @author CWDS CALS API Team
 */
public class DataFilter {

  private String filterColumnName;
  private String filterColumnValue;

  public DataFilter(String filterColumnName, String filterColumnValue) {
    this.filterColumnName = filterColumnName;
    this.filterColumnValue = filterColumnValue;
  }

  public String getFilterColumnName() {
    return filterColumnName;
  }

  public String getFilterColumnValue() {
    return filterColumnValue;
  }
}
