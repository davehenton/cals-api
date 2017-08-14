package gov.ca.cwds.cals.persistence;

import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * @author CWDS CALS API Team
 */

public class DBUnitAssertHelper {

  private String tableName;
  private DBUnitSupport dbUnitSupport;
  private String fixture;
  private DataFilter filter;

  public DBUnitAssertHelper(DBUnitSupport dbUnitSupport) {
    this.dbUnitSupport = dbUnitSupport;
  }

  public void addFixture(String fixturePath) throws URISyntaxException {
    processFixture(fixturePath, prepareInitialParametersMap());
  }

  private void processFixture(String fixturePath, Map<String, Object> parameters) {
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameters(parameters);
    this.fixture = velocityHelper.process(fixturePath);
  }

  private Map<String, Object> prepareInitialParametersMap() throws URISyntaxException {
    Map<String, Object> parameters = new HashMap<>();
    Path path = Paths.get(getClass().getClassLoader().getResource("dbunit/CWSCMS.dtd").toURI());
    parameters.put("schemaPath", path.toString());
    return parameters;
  }

  public void addFixture(String fixturePath, Map<String, Object> parameters)
      throws URISyntaxException {
    Map<String, Object> initialParametersMap = prepareInitialParametersMap();
    initialParametersMap.putAll(parameters);
    processFixture(fixturePath, initialParametersMap);
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public void addFilter(String filterColumnName, String filterColumnValue) {
    this.filter = new DataFilter(filterColumnName, filterColumnValue);
  }

  public void assertEqualsIgnoreCols(String[] ignoreCols) throws Exception {
    try (InputStream is = IOUtils.toInputStream(fixture, "UTF-8")) {
      ReplacementDataSet expectedDataset = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(is));
      expectedDataset.addReplacementObject("[NULL]", null);
      ITable expectedData = dbUnitSupport.getTableFromDataSet(expectedDataset, tableName);
      ITable actualData = dbUnitSupport.getTableFromDB(tableName);
      if (filter != null) {
        actualData = dbUnitSupport.filterByColumnAndValue(
            actualData, filter.getFilterColumnName(), filter.getFilterColumnValue());
      }

      Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignoreCols);
    }
  }

}
