package gov.ca.cwds.cals.persistence;

import gov.ca.cwds.cals.CalsApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

/**
 * @author CWDS CALS API Team
 */
public class DBUnitSupportBuilder {

  private DataSourceFactory dataSourceFactory;
  private String drivaerClassName;
  private String jdbcUrl;
  private String dbUser;
  private String dbPassword;
  private String schema;

  public void drivaerClassName(String drivaerClassName) {
    this.drivaerClassName = drivaerClassName;
  }

  public void jdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public void dbUser(String dbUser) {
    this.dbUser = dbUser;
  }

  public void dbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }

  public void schema(String schema) {
    this.schema = schema;
  }

  public void dataSourceFactory(DataSourceFactory dsf) {
    this.dataSourceFactory = dsf;
  }

  public DBUnitSupport buildForCMS(CalsApiConfiguration configuration) {
    this.dataSourceFactory(configuration.getXaCmsDataSourceFactory());
    this.schema("CWSCMS");
    return build();
  }

  public DBUnitSupport build() {
    DBUnitSupport dbUnitSupport = new DBUnitSupport();

    if (dataSourceFactory != null) {
      dbUnitSupport.setDriverClassName(dataSourceFactory.getDriverClass());
      dbUnitSupport.setJdbcUrl(dataSourceFactory.getUrl());
      dbUnitSupport.setDbUser(dataSourceFactory.getUser());
      dbUnitSupport.setDbPassword(dataSourceFactory.getPassword());
    }
    if (drivaerClassName != null) {
      dbUnitSupport.setDriverClassName(drivaerClassName);
    }

    if (jdbcUrl != null) {
      dbUnitSupport.setJdbcUrl(jdbcUrl);
    }

    if (dbUser != null) {
      dbUnitSupport.setDbUser(dbUser);
    }

    if (dbPassword != null) {
      dbUnitSupport.setDbPassword(dbPassword);
    }

    if (schema != null) {
      dbUnitSupport.setSchema(schema);
    }

    dbUnitSupport.init();

    return dbUnitSupport;
  }
}
