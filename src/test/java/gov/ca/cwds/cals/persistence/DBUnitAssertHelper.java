package gov.ca.cwds.cals.persistence;

import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * @author CWDS CALS API Team
 */

public class DBUnitAssertHelper {

  private String tableName;
  private DBUnitSupport dbUnitSupport;
  private List<DataFilter> filters = new LinkedList<>();
  private ReplacementDataSet expectedDataSet;
  private String templatePath;
  private Map<String, Object> templateParams = new HashMap<>();

  private DBUnitAssertHelper(DBUnitSupport dbUnitSupport) {

    this.dbUnitSupport = dbUnitSupport;
    try {
      this.templateParams = prepareInitialParametersMap();
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }

  }

  private void setFixture(String templatePath) throws Exception {
    this.templatePath = templatePath;
  }

  private void processTemplate() {
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameters(templateParams);
    try {
      expectedDataSet = getReplacementDataSet(velocityHelper.process(templatePath));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private Map<String, Object> prepareInitialParametersMap() throws URISyntaxException {
    Map<String, Object> parameters = new HashMap<>();
    Path path = Paths.get(getClass().getClassLoader().getResource("dbunit/CWSCMS.dtd").toURI());
    parameters.put("schemaPath", path.toString());
    return parameters;
  }

  private void setTableName(String tableName) {
    this.tableName = tableName;
  }

  private void addFilter(String filterColumnName, String filterColumnValue) {
    filters.add(new DataFilter(filterColumnName, filterColumnValue));
  }

  public void assertEqualsIgnoreCols(String[] ignoreCols) throws Exception {
    ITable expectedData = dbUnitSupport.getTableFromDataSet(expectedDataSet, tableName);
    ITable actualData = dbUnitSupport.getTableFromDB(tableName);
    if (!filters.isEmpty()) {
      actualData = dbUnitSupport
          .doFilter(actualData, filters.toArray(new DataFilter[filters.size()]));
    }
    Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignoreCols);
  }

  private ReplacementDataSet getReplacementDataSet(IDataSet dataSet) throws Exception {
    ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
    replacementDataSet.addReplacementObject("[NULL]", null);
    return replacementDataSet;
  }

  private ReplacementDataSet getReplacementDataSet(String fixture) throws Exception {
    InputStream is = IOUtils.toInputStream(fixture, "UTF-8");
    return getReplacementDataSet(new FlatXmlDataSetBuilder().build(is));
  }

  public ReplacementDataSet getExpectedDataSet() {
    return expectedDataSet;
  }

  public static DBUnitAssertHelperBuilder builder(DBUnitSupport dbUnitSupport) {
    return new DBUnitAssertHelperBuilder(dbUnitSupport);
  }

  public static class DBUnitAssertHelperBuilder {

    private DBUnitAssertHelper helper;

    public DBUnitAssertHelperBuilder(DBUnitSupport dbUnitSupport) {
      this.helper = new DBUnitAssertHelper(dbUnitSupport);
    }

    public DBUnitAssertHelperBuilder appendTemplateParameter(String name, Object value) {
      helper.templateParams.put(name, value);
      return this;
    }

    public DBUnitAssertHelperBuilder setExpectedResultTemplatePath(String fixturePath) {
      try {
        helper.setFixture(fixturePath);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
      return this;
    }

    public DBUnitAssertHelperBuilder setTestedTableName(String tableName) {
      helper.setTableName(tableName);
      return this;
    }

    public DBUnitAssertHelperBuilder appendTableFilter(String filterColumnName,
        String filterColumnValue) {
      helper.addFilter(filterColumnName, filterColumnValue);
      return this;
    }

    public DBUnitAssertHelper build() {
      helper.processTemplate();
      return helper;
    }

  }
}
