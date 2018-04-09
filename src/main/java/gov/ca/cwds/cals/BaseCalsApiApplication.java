package gov.ca.cwds.cals;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.inject.Injector;
import gov.ca.cwds.cals.web.rest.filters.RequestExecutionContextFilter;
import gov.ca.cwds.cals.web.rest.filters.RequestResponseLoggingFilter;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseCalsApiApplication<T extends CalsApiConfiguration> extends
    BaseApiApplication<T> {

  private static final Logger LOG = LoggerFactory.getLogger(BaseCalsApiApplication.class);
  private static final String LIQUIBASE_CALSNS_DATABASE_CREATE_SCHEMA_XML = "liquibase/calsns_schema.xml";
  private static final String LIQUIBASE_CALSNS_DATABASE_MASTER_XML = "liquibase/calsns_database_master.xml";
  private static final String HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME = "hibernate.default_schema";

  @Override
  public void runInternal(T configuration, Environment environment) {
    environment.getObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    if (configuration.isUpgradeDbOnStart()) {
      upgardeCalsNsDB(configuration);
    }

    environment
        .jersey()
        .getResourceConfig()
        .packages(getClass().getPackage().getName())
        .register(DeclarativeLinkingFeature.class);

    runDataSourceHealthChecks(environment);

    Injector injector = guiceBundle.getInjector();

    environment.servlets()
        .addFilter("RequestExecutionContextManagingFilter",
            injector.getInstance(RequestExecutionContextFilter.class))
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    environment.servlets()
        .addFilter("AuditAndLoggingFilter",
            injector.getInstance(RequestResponseLoggingFilter.class))
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
  }

  private void runDataSourceHealthChecks(Environment environment) {
    HealthCheckRegistry healthCheckRegistry = environment.healthChecks();
    doHealthCheck(healthCheckRegistry, Constants.UnitOfWork.CALSNS);
    doHealthCheck(healthCheckRegistry, Constants.UnitOfWork.FAS);
    doHealthCheck(healthCheckRegistry, Constants.UnitOfWork.LIS);
    doHealthCheck(healthCheckRegistry, Constants.UnitOfWork.CMS);
  }

  private void doHealthCheck(HealthCheckRegistry healthCheckRegistry, String key) {
    HealthCheck.Result result = healthCheckRegistry.runHealthCheck(key);
    if (!result.isHealthy()) {
      LOG.error("Fail - {}: {}", key, result.getMessage());
    }
  }

  private void upgardeCalsNsDB(CalsApiConfiguration configuration) {
    LOG.info("Upgrading CALS_NS DB...");

    DataSourceFactory calsnsDataSourceFactory = configuration.getCalsnsDataSourceFactory();
    DatabaseHelper databaseHelper = new DatabaseHelper(calsnsDataSourceFactory.getUrl(),
        calsnsDataSourceFactory.getUser(),
        calsnsDataSourceFactory.getPassword());
    try {
      databaseHelper.runScript(LIQUIBASE_CALSNS_DATABASE_CREATE_SCHEMA_XML);
      databaseHelper.runScript(LIQUIBASE_CALSNS_DATABASE_MASTER_XML,
          calsnsDataSourceFactory.getProperties().get(HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME));
    } catch (Exception e) {
      LOG.error("Upgarding of CALS_NS DB is failed. ", e);
    }

    LOG.info("Finish Upgrading CALS_NS DB");
  }
}
