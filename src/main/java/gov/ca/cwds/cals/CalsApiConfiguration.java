package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CalsApiConfiguration extends BaseApiConfiguration {

  private DataSourceFactory fasDataSourceFactory;
  private DataSourceFactory lisDataSourceFactory;
  private DataSourceFactory calsnsDataSourceFactory;

  private boolean upgardeDb = false;

  @JsonProperty
  public DataSourceFactory getFasDataSourceFactory() {
    return fasDataSourceFactory;
  }

  @JsonProperty
  public DataSourceFactory getLisDataSourceFactory() {
    return lisDataSourceFactory;
  }

  public void setFasDataSourceFactory(DataSourceFactory fasDataSourceFactory) {
    this.fasDataSourceFactory = fasDataSourceFactory;
  }

  public void setLisDataSourceFactory(DataSourceFactory lisDataSourceFactory) {
    this.lisDataSourceFactory = lisDataSourceFactory;
  }

  @JsonProperty
  public DataSourceFactory getCalsnsDataSourceFactory() {
    return calsnsDataSourceFactory;
  }

  public void setCalsnsDataSourceFactory(DataSourceFactory calsnsDataSourceFactory) {
    this.calsnsDataSourceFactory = calsnsDataSourceFactory;
  }

  @JsonProperty
  public boolean isUpgradeDb() {
    return upgardeDb;
  }

  public void setUpgardeDb(boolean upgardeDb) {
    this.upgardeDb = upgardeDb;
  }
}
