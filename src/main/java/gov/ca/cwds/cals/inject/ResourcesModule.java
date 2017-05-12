package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.service.FacilityChildCollectionService;
import gov.ca.cwds.cals.service.FacilityChildService;
import gov.ca.cwds.cals.web.rest.*;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;

/**
 * Identifies all CALS API domain resource classes available for dependency injection by Guice.
 *
 * @author CALS API Team
 *
 */
public class ResourcesModule extends AbstractModule {

    /**
     * Default constructor
     */
    public ResourcesModule() {

    }

    @Override
    protected void configure() {
        bind(ApplicationResource.class);
        bind(FacilityResource.class);
        bind(FacilityChildResource.class);
    }

    @Provides
    @Named("app.name")
    public String appName(CalsApiConfiguration calsApiConfiguration) {
        return calsApiConfiguration.getApplicationName();
    }

    @Provides
    @Named("app.version")
    public String appVersion(CalsApiConfiguration calsApiConfiguration) {
        return calsApiConfiguration.getVersion();
    }

    @Provides
    @FacilityServiceBackendResource
    public ResourceDelegate facilityServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityService.class));
    }

    @Provides
    @FacilityChildServiceBackendResource
    public ResourceDelegate facilityChildServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityChildService.class));
    }

    @Provides
    @FacilityChildCollectionServiceBackendResource
    public ResourceDelegate facilityChildServiceCollectionBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityChildCollectionService.class));
    }

}
