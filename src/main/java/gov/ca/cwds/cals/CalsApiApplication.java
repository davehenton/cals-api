package gov.ca.cwds.cals;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.google.inject.Injector;
import com.google.inject.Module;
import gov.ca.cwds.cals.exception.CustomExceptionMapperBinder;
import gov.ca.cwds.cals.exception.mapper.BusinessValidationExceptionMapper;
import gov.ca.cwds.cals.exception.mapper.ExpectedExceptionMapperImpl;
import gov.ca.cwds.cals.exception.mapper.UnexpectedExceptionMapperImpl;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.cals.web.rest.filters.RequestExecutionContextFilter;
import gov.ca.cwds.cals.web.rest.filters.RequestResponseLoggingFilter;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class CalsApiApplication extends BaseApiApplication<CalsApiConfiguration> {

  private static final Logger LOG = LoggerFactory.getLogger(CalsApiApplication.class);
  private static final String LIQUIBASE_CALSNS_DATABASE_CREATE_SCHEMA_XML = "liquibase/calsns_schema.xml";
  private static final String LIQUIBASE_CALSNS_DATABASE_MASTER_XML = "liquibase/calsns_database_master.xml";
  private static final String HIBERNATE_DEFAULT_SCHEMA_PROPERTY_NAME = "hibernate.default_schema";

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
    environment.jersey().register(BusinessValidationExceptionMapper.class);
    environment.jersey().register(new CustomExceptionMapperBinder(true));

    if (configuration.isUpgradeDbOnStart()) {
      upgardeCalsNsDB(configuration);
    }

    environment
        .jersey()
        .getResourceConfig()
        .packages(getClass().getPackage().getName())
        .register(DeclarativeLinkingFeature.class);

    runDataSourceHealthChecks(environment);

    // Providing access to the guice injector from external classes such as custom validators
    InjectorHolder.INSTANCE.setInjector(this.guiceBundle.getInjector());

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
      throw new IllegalStateException(e);
    }

    LOG.info("Finish Upgrading CALS_NS DB");
  }
}
