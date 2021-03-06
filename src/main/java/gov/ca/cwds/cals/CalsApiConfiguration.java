package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.data.persistence.XADataSourceFactory;
import gov.ca.cwds.rest.BaseApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class CalsApiConfiguration extends BaseApiConfiguration {

  private DataSourceFactory fasDataSourceFactory;
  private DataSourceFactory fasFfaDataSourceFactory;
  private DataSourceFactory lisDataSourceFactory;
  private DataSourceFactory calsnsDataSourceFactory;
  private DataSourceFactory cmsRsDataSourceFactory;

  private XADataSourceFactory xaCalsnsDataSourceFactory;
  private XADataSourceFactory xaCmsDataSourceFactory;

  private boolean upgardeDbOnStart = false;

  private String dmsURI;

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
  public XADataSourceFactory getXaCalsnsDataSourceFactory() {
    return xaCalsnsDataSourceFactory;
  }

  public void setXaCalsnsDataSourceFactory(
      XADataSourceFactory xaCalsnsDataSourceFactory) {
    this.xaCalsnsDataSourceFactory = xaCalsnsDataSourceFactory;
  }

  @JsonProperty
  @Override
  public XADataSourceFactory getXaCmsDataSourceFactory() {
    return xaCmsDataSourceFactory;
  }

  @Override
  public void setXaCmsDataSourceFactory(
      XADataSourceFactory xaCmsDataSourceFactory) {
    this.xaCmsDataSourceFactory = xaCmsDataSourceFactory;
  }

  @JsonProperty
  public boolean isUpgradeDbOnStart() {
    return upgardeDbOnStart;
  }

  public void setUpgardeDbOnStart(boolean upgardeDbOnStart) {
    this.upgardeDbOnStart = upgardeDbOnStart;
  }

  @JsonProperty
  public String getDmsURI() {
    return dmsURI;
  }

  public void setDmsURI(String dmsURI) {
    this.dmsURI = dmsURI;
  }

  @JsonProperty
  public DataSourceFactory getCmsRsDataSourceFactory() {
    return cmsRsDataSourceFactory;
  }

  @JsonProperty
  public void setCmsRsDataSourceFactory(DataSourceFactory dataSourceFactory) {
    this.cmsRsDataSourceFactory = dataSourceFactory;
  }

  @JsonProperty
  public DataSourceFactory getFasFfaDataSourceFactory() {
    return fasFfaDataSourceFactory;
  }

  @JsonProperty
  public void setFasFfaDataSourceFactory(
      DataSourceFactory fasFfaDataSourceFactory) {
    this.fasFfaDataSourceFactory = fasFfaDataSourceFactory;
  }
}
