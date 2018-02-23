package gov.ca.cwds.cals.persistence;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.persistence.hibernate.AtomikosPooledManagedDataSource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import java.util.Optional;
import java.util.Properties;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS CALS API Team
 */
public class XADataSourceFactory extends DataSourceFactory {

  @NotNull
  private String xaDataSourceClassName;

  private Properties xaProperties;

  @JsonProperty
  public String getXaDataSourceClassName() {
    return xaDataSourceClassName;
  }

  public void setXaDataSourceClassName(String xaDataSourceClassName) {
    this.xaDataSourceClassName = xaDataSourceClassName;
  }

  @JsonProperty
  public Properties getXaProperties() {
    return xaProperties;
  }

  public void setXaProperties(Properties xaProperties) {
    this.xaProperties = xaProperties;
  }

  @Override
  public ManagedDataSource build(MetricRegistry metricRegistry, String name) {

    AtomikosPooledManagedDataSource ds = new AtomikosPooledManagedDataSource();
    ds.setUniqueResourceName(name);
    ds.setXaDataSourceClassName(xaDataSourceClassName);

    Properties props = new Properties();
    Optional.ofNullable(getUser()).ifPresent(user -> props.put("user", user));
    Optional.ofNullable(getPassword()).ifPresent(password -> props.put("password", password));
    Optional.ofNullable(getXaProperties()).ifPresent(props::putAll);

    ds.setXaProperties(props);
    ds.setMinPoolSize(0);
    ds.setMaxPoolSize(50);

    // Init on start ManagedDataSource.start

    return ds;
  }
}
