package gov.ca.cwds.cals;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.google.inject.Module;
import gov.ca.cwds.cals.exception.CustomExceptionMapperBinder;
import gov.ca.cwds.cals.exception.mapper.ExpectedExceptionMapperImpl;
import gov.ca.cwds.cals.exception.mapper.UnexpectedExceptionMapperImpl;
import gov.ca.cwds.cals.health.DataSourceHealthCheck;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.Map;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class CalsApiApplication extends BaseApiApplication<CalsApiConfiguration> {

  private static final Logger LOG = LoggerFactory.getLogger(CalsApiApplication.class);
  public static final String LIQUIBASE_CALSNS_DATABASE_MASTER_XML = "liquibase/calsns_database_master.xml";
  public static final String HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME = "hibernate.default_schema";

  public static void main(String[] args) throws Exception {
    new CalsApiApplication().run(args);
  }

  @Override
  public Module applicationModule(Bootstrap<CalsApiConfiguration> bootstrap) {
    return new ApplicationModule(bootstrap);
  }

  @Override
  public void runInternal(CalsApiConfiguration configuration, Environment environment) {
    environment.jersey().register(UnexpectedExceptionMapperImpl.class);
    environment.jersey().register(ExpectedExceptionMapperImpl.class);
    environment.jersey().register(new CustomExceptionMapperBinder(true));

    HealthCheckRegistry healthCheckRegistry = environment.healthChecks();
    healthCheckRegistry.register(
        "nsDataSource", new DataSourceHealthCheck(configuration.getNsDataSourceFactory()));
    healthCheckRegistry.register(
        "cmsDataSource", new DataSourceHealthCheck(configuration.getCmsDataSourceFactory()));
    healthCheckRegistry.register(
        "lisDataSource", new DataSourceHealthCheck(configuration.getLisDataSourceFactory()));
    healthCheckRegistry.register(
        "calsnsDataSource", new DataSourceHealthCheck(configuration.getCalsnsDataSourceFactory()));
    environment
        .jersey()
        .getResourceConfig()
        .packages(getClass().getPackage().getName())
        .register(DeclarativeLinkingFeature.class);

    runHealthChecks(environment);

    // Providing access to the guice injector from external classes such as custom validators
    InjectorHolder.INSTANCE.setInjector(this.guiceBundle.getInjector());

    if (configuration.isUpgradeDb()) {
      upgardeCalsNsDB(configuration);
    }
  }

  private void runHealthChecks(Environment environment) {
    for (Map.Entry<String, HealthCheck.Result> entry :
        environment.healthChecks().runHealthChecks().entrySet()) {
      if (!entry.getValue().isHealthy()) {
        LOG.error("Fail - {}: {}", entry.getKey(), entry.getValue().getMessage());
      }
    }
  }

  private void upgardeCalsNsDB(CalsApiConfiguration configuration) {
    LOG.info("Upgrading CALS_NS DB...");

    DataSourceFactory calsnsDataSourceFactory = configuration.getCalsnsDataSourceFactory();
    DatabaseHelper databaseHelper = new DatabaseHelper(calsnsDataSourceFactory.getUrl(),
        calsnsDataSourceFactory.getUser(),
        calsnsDataSourceFactory.getPassword());
    try {
      databaseHelper.runScript(LIQUIBASE_CALSNS_DATABASE_MASTER_XML,
          calsnsDataSourceFactory.getProperties().get(HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME));
    } catch (Exception e) {
      LOG.error("Upgarding of CALS_NS DB is failed. ", e);
      throw new IllegalStateException(e);
    }

    LOG.info("Finish Upgrading CALS_NS DB");
  }
}
