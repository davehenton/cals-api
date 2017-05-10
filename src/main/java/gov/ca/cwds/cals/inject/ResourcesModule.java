package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import gov.ca.cwds.cals.CalcApiConfiguration;
import gov.ca.cwds.cals.rest.resources.ApplicationResource;
import gov.ca.cwds.cals.rest.resources.FacilityResource;
import gov.ca.cwds.cals.rest.services.FacilityService;
import gov.ca.cwds.cals.web.rest.FacilityChildResource;
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
    public String appName(CalcApiConfiguration calsApiConfiguration) {
        return calsApiConfiguration.getApplicationName();
    }

    @Provides
    @Named("app.version")
    public String appVersion(CalcApiConfiguration calsApiConfiguration) {
        return calsApiConfiguration.getVersion();
    }

    @Provides
    @FacilityServiceBackendResource
    public ResourceDelegate facilityServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityService.class));
    }

    @Provides
    @FacilityChildServiceBackendResource
    public CollectionResourceDelegate facilityChildServiceBackendResource(Injector injector) {
        return new ServiceBackedCollectionResourceDelegate(injector.getInstance(FacilityChildService.class));
    }

}
