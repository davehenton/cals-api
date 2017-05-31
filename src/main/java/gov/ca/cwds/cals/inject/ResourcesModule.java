package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.ComplaintsCollectionService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.FacilityChildCollectionService;
import gov.ca.cwds.cals.service.FacilityChildService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.FacilityTypeCollectionService;
import gov.ca.cwds.cals.web.rest.ApplicationResource;
import gov.ca.cwds.cals.web.rest.CountiesResource;
import gov.ca.cwds.cals.web.rest.FacilityChildResource;
import gov.ca.cwds.cals.web.rest.FacilityComplaintResource;
import gov.ca.cwds.cals.web.rest.FacilityInspectionsResource;
import gov.ca.cwds.cals.web.rest.FacilityResource;
import gov.ca.cwds.cals.web.rest.FacilityTypeResource;
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
        // Do nothing - Default Constructor
    }

    @Override
    protected void configure() {
        bind(ApplicationResource.class);
        bind(FacilityResource.class);
        bind(FacilityChildResource.class);
        bind(FacilityComplaintResource.class);
        bind(FacilityInspectionsResource.class);
        bind(CountiesResource.class);
        bind(FacilityTypeResource.class);
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


    @Provides
    @FacilityTypeCollectionServiceBackendResource
    public ResourceDelegate facilityTypeServiceCollectionBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityTypeCollectionService.class));
    }


    @Provides
    @ComplaintsCollectionServiceBackedResource
    public ResourceDelegate complaintsCollectionServiceBackedResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(ComplaintsCollectionService.class));
    }

    @Provides
    @ComplaintServiceBackedResource
    public ResourceDelegate complaintServiceBackedResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(ComplaintService.class));
    }

    @Provides
    @FacilityInspectionCollectionServiceBackendResource
    public ResourceDelegate facilityInspectionCollectionServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityInspectionCollectionService.class));
    }

    @Provides
    @FacilityInspectionServiceBackendResource
    public ResourceDelegate facilityInspectionServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(FacilityInspectionService.class));
    }


    @Provides
    @CountiesServiceBackendResource
    public ResourceDelegate countiesServiceBackendResource(Injector injector) {
        return new ServiceBackedResourceDelegate(injector.getInstance(CountiesService.class));
    }

}
