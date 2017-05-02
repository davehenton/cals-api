package gov.ca.cwds.cals;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.google.inject.Module;
import gov.ca.cwds.cals.health.DataSourceHealthCheck;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CalsApiApplication extends BaseApiApplication<CalsApiConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(CalsApiApplication.class);

    public static void main(String[] args) throws Exception{
        new CalsApiApplication().run(args);
    }

    @Override
    public Module applicationModule(Bootstrap<CalsApiConfiguration> bootstrap) {
        return new ApplicationModule(bootstrap);
    }

    @Override
    public void runInternal(CalsApiConfiguration configuration, Environment environment) {
        HealthCheckRegistry healthCheckRegistry = environment.healthChecks();
        healthCheckRegistry.register("nsDataSource", new DataSourceHealthCheck(configuration.getNsDataSourceFactory()));
        healthCheckRegistry.register("cmsDataSource", new DataSourceHealthCheck(configuration.getCmsDataSourceFactory()));

        runHealthChecks(environment);
    }

    private void runHealthChecks(Environment environment) {
        for (Map.Entry<String, HealthCheck.Result> entry : environment.healthChecks().runHealthChecks().entrySet()) {
            if (! entry.getValue().isHealthy()) {
                LOG.error("Fail - {}: {}", entry.getKey(), entry.getValue().getMessage());
            }
        }
    }
}
