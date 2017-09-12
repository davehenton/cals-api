package gov.ca.cwds.cals;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

/**
 * @author CWDS CALS API Team
 */

public class TestCalsApiConfiguration extends CalsApiConfiguration {

  private DataSourceFactory cmsrsDataSourceFactory;

  @JsonProperty
  public DataSourceFactory getCmsrsDataSourceFactory() {
    return cmsrsDataSourceFactory;
  }

  public void setCmsnsDataSourceFactory(DataSourceFactory cmsnsDataSourceFactory) {
    this.cmsrsDataSourceFactory = cmsnsDataSourceFactory;
  }
}
