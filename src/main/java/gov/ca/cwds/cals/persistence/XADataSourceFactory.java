package gov.ca.cwds.cals.persistence;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.persistence.hibernate.AtomikosPooledManagedDataSource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import java.util.Properties;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS CALS API Team
 */
public class XADataSourceFactory extends DataSourceFactory {

  @NotNull
  private String xaDataSourceClassName;

  @JsonProperty
  public String getXaDataSourceClassName() {
    return xaDataSourceClassName;
  }

  public void setXaDataSourceClassName(String xaDataSourceClassName) {
    this.xaDataSourceClassName = xaDataSourceClassName;
  }

  @Override
  public ManagedDataSource build(MetricRegistry metricRegistry, String name) {
    AtomikosPooledManagedDataSource ds = new AtomikosPooledManagedDataSource(metricRegistry);
    ds.setUniqueResourceName(name);
    ds.setXaDataSourceClassName(xaDataSourceClassName);
    Properties props = new Properties();
    getProperties().forEach(props::setProperty);
    ds.setXaProperties(props);
    ds.setPoolSize(3);

    // Init on start ManagedDataSource.start

    return ds;
  }
}
