package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import gov.ca.cwds.cals.CalcApiConfiguration;
import gov.ca.cwds.inject.AuditingModule;
import io.dropwizard.setup.Bootstrap;

/**
 * Bootstraps and configures the CWDS RESTful CALS-API application.
 *
 * @author CWDS CALS-API Team
 */
public class ApplicationModule extends AbstractModule {

    private Bootstrap<CalcApiConfiguration> bootstrap;

    public ApplicationModule(Bootstrap<CalcApiConfiguration> bootstrap) {
        super();
        this.bootstrap = bootstrap;
    }

    /**
     * Configure and initialize API components, including services, rest, data access objects
     * (DAO), web service filters, and auditing.
     *
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        install(new ServicesModule());
        install(new ResourcesModule());
        install(new AuditingModule());
        install(new MappingModule());
    }

}
