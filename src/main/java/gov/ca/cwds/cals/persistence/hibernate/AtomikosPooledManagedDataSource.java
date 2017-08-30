package gov.ca.cwds.cals.persistence.hibernate;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.codahale.metrics.MetricRegistry;
import io.dropwizard.db.ManagedDataSource;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.Reference;

/**
 * @author CWDS CALS API Team
 */
public class AtomikosPooledManagedDataSource extends AtomikosDataSourceBean implements
    ManagedDataSource {


  private final MetricRegistry metricRegistry;

  public AtomikosPooledManagedDataSource(MetricRegistry metricRegistry) {
    this.metricRegistry = metricRegistry;
  }


  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    throw new SQLFeatureNotSupportedException("Doesn't use java.util.logging");
  }

  @Override
  public void start() throws Exception {
    init();
    /*//final ConnectionPool connectionPool = createPool();
    metricRegistry.register(name(getClass(), connectionPool.getName(), "active"),
        (Gauge<Integer>) connectionPool::getActive);

    metricRegistry.register(name(getClass(), connectionPool.getName(), "idle"),
        (Gauge<Integer>) connectionPool::getIdle);

    metricRegistry.register(name(getClass(), connectionPool.getName(), "waiting"),
        (Gauge<Integer>) connectionPool::getWaitCount);

    metricRegistry.register(name(getClass(), connectionPool.getName(), "size"),
        (Gauge<Integer>) connectionPool::getSize);*/
  }

  @Override
  public void stop() throws Exception {
    close();
  }

  @Override
  public Reference getReference() throws NamingException {
    // Do nothing
    return null;
  }
}
