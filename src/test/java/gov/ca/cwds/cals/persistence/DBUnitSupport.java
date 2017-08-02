package gov.ca.cwds.cals.persistence;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * @author CWDS CALS API Team
 */
public class DBUnitSupport {

  private String drivaerClassName;
  private String jdbcUrl;
  private String dbUser;
  private String dbPassword;
  private String schema;

  DBUnitSupport() {

  }

  void init() {
    try {
      Class driverClass = Class.forName(drivaerClassName);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private IDatabaseConnection getConnection() throws DatabaseUnitException {
    Connection jdbcConnection = null;
    try {
      jdbcConnection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    } catch (SQLException e) {
      throw new DatabaseUnitException(e);
    }
    return new DatabaseConnection(jdbcConnection, schema);
  }

  protected ITable getTableFromDataSet(IDataSet dataSet, String table) {
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

  protected IDataSet getXMLDataSet(String dataSetXMLFile) {
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

  void setDrivaerClassName(String drivaerClassName) {
    this.drivaerClassName = drivaerClassName;
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


}