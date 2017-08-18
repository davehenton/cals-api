package gov.ca.cwds.cals.persistence;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.RowFilterTable;
import org.dbunit.dataset.filter.IRowFilter;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * @author CWDS CALS API Team
 */
public class DBUnitSupport {

  private String driverClassName;
  private String jdbcUrl;
  private String dbUser;
  private String dbPassword;
  private String schema;

  DBUnitSupport() {

  }

  void init() {
    try {
      Class.forName(driverClassName);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private IDatabaseConnection getConnection() throws DatabaseUnitException {
    Connection jdbcConnection;
    try {
      jdbcConnection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    } catch (SQLException e) {
      throw new DatabaseUnitException(e);
    }
    return new DatabaseConnection(jdbcConnection, schema);
  }

  public ITable getTableFromDataSet(IDataSet dataSet, String table) {
    try {
      return dataSet.getTable(table);
    } catch (DatabaseUnitException e) {
      throw new IllegalStateException(e);
    }
  }

  public ITable getTableFromDB(String table) {
    try {
      IDataSet dataSet = getSchemaDataSet();
      return dataSet.getTable(table);
    } catch (DatabaseUnitException e) {
      throw new IllegalStateException(e);
    }
  }

  public IDataSet getXMLDataSet(String dataSetXMLFile) {
    URL datasetURL = getClass().getResource(dataSetXMLFile);
    try {
      return new FlatXmlDataSetBuilder().build(datasetURL);
    } catch (DataSetException e) {
      throw new IllegalStateException(e);
    }
  }

  protected IDataSet getSchemaDataSet() throws DatabaseUnitException {
    try {
      return getConnection().createDataSet();
    } catch (SQLException e) {
      throw new DatabaseUnitException(e);
    }
  }

  public String generateDTD() {
    StringWriter out = new StringWriter();
    try {
      FlatDtdDataSet.write(getSchemaDataSet(), out);
    } catch (IOException | DatabaseUnitException e) {
      throw new IllegalStateException(e);
    }
    return out.toString();
  }

  public ITable getTableFromXML(String pathToFlatXMLDataSet, String tableName) {
    IDataSet xmlDataSet = getXMLDataSet(pathToFlatXMLDataSet);
    return getTableFromDataSet(xmlDataSet, tableName);
  }

  void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  void setDbUser(String dbUser) {
    this.dbUser = dbUser;
  }

  void setDbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }

  void setSchema(String schema) {
    this.schema = schema;
  }

  public ITable filterByColumnAndValue(ITable table, String column, Object filterValue)
      throws DataSetException {
    return doFilter(table, new DataFilter(column, filterValue));
  }

  public ITable doFilter(ITable table, DataFilter... filters)
      throws DataSetException {
    IRowFilter rowFilter =
        rowValueProvider ->
            Arrays.stream(filters).allMatch(filter -> {
              try {
                Object columnValue = rowValueProvider.getColumnValue(filter.getFilterColumnName());
                return columnValue.equals(filter.getFilterColumnValue());
              } catch (DataSetException e) {
                throw new IllegalStateException(e);
              }
            });

    return new RowFilterTable(table, rowFilter);
  }

}
