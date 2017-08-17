package gov.ca.cwds.cals.persistence;

/**
 * @author CWDS CALS API Team
 */
public class DataFilter {

  private String filterColumnName;
  private Object filterColumnValue;

  public DataFilter(String filterColumnName, Object filterColumnValue) {
    this.filterColumnName = filterColumnName;
    this.filterColumnValue = filterColumnValue;
  }

  public String getFilterColumnName() {
    return filterColumnName;
  }

  public Object getFilterColumnValue() {
    return filterColumnValue;
  }
}
