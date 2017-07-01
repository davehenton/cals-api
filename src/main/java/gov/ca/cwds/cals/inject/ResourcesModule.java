package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.ComplaintsCollectionService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityChildCollectionService;
import gov.ca.cwds.cals.service.FacilityChildService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.FacilityTypeCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsHistoryService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsRelationshipService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildrenCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aResidenceService;
import gov.ca.cwds.cals.web.rest.ApplicationResource;
import gov.ca.cwds.cals.web.rest.CountiesResource;
import gov.ca.cwds.cals.web.rest.DictionariesResource;
import gov.ca.cwds.cals.web.rest.FacilityChildResource;
import gov.ca.cwds.cals.web.rest.FacilityComplaintResource;
import gov.ca.cwds.cals.web.rest.FacilityInspectionsResource;
import gov.ca.cwds.cals.web.rest.FacilityResource;
import gov.ca.cwds.cals.web.rest.FacilityTypeResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsHistoryResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsRelationshipResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResource;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;

/**
 * Identifies all CALS API domain resource classes available for dependency injection by Guice.
 *
 * @author CALS API Team
 */
public class ResourcesModule extends AbstractModule {

  /** Default constructor */
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
    bind(DictionariesResource.class);

    // RFA
    bind(RFA1aFormsResource.class);
    bind(RFA1aApplicantsResource.class);
    bind(RFA1aMinorChildrenResource.class);
    bind(RFA1aResidenceResource.class);
    bind(RFA1aApplicantsRelationshipResource.class);
    bind(RFA1aOtherAdultsResource.class);
    bind(RFA1aApplicantsHistoryResource.class);
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
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FacilityChildCollectionService.class));
  }

  @Provides
  @FacilityTypeCollectionServiceBackendResource
  public ResourceDelegate facilityTypeServiceCollectionBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FacilityTypeCollectionService.class));
  }

  @Provides
  @ComplaintsCollectionServiceBackedResource
  public ResourceDelegate complaintsCollectionServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(ComplaintsCollectionService.class));
  }

  @Provides
  @ComplaintServiceBackedResource
  public ResourceDelegate complaintServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(ComplaintService.class));
  }

  @Provides
  @FacilityInspectionCollectionServiceBackendResource
  public ResourceDelegate facilityInspectionCollectionServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FacilityInspectionCollectionService.class));
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

  @Provides
  @DictionariesServiceBackendResource
  public ResourceDelegate dictionariesServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(DictionariesService.class));
  }

  @Provides
  @ResidenceServiceBackedResource
  public ResourceDelegate residenceServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(RFA1aResidenceService.class));
  }

  @Provides
  @ApplicantsHistoryServiceBackedResource
  public ResourceDelegate applicantsHistoryServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aApplicantsHistoryService.class));
  }

  @Provides
  @ApplicantsRelationshipServiceBackedResource
  public ResourceDelegate applicantsRelationshipServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aApplicantsRelationshipService.class));
  }

  @Provides
  @RFA1aFormServiceBackendResource
  public ResourceDelegate rfa1aFormsServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(RFA1aFormService.class));
  }

  @Provides
  @RFA1aFormCollectionServiceBackendResource
  public ResourceDelegate rfa1aFormsCollectionServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aFormsCollectionService.class));
  }

  @Provides
  @RFA1aApplicantServiceBackendResource
  public ResourceDelegate rfa1aApplicantServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(RFA1aApplicantService.class));
  }

  @Provides
  @RFA1aApplicantsCollectionServiceBackendResource
  public ResourceDelegate rfa1aApplicantsCollectionServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aApplicantsCollectionService.class));
  }

  @Provides
  @RFA1aMinorChildrenServiceBackendResource
  public ResourceDelegate rfa1aMinorChildServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(RFA1aMinorChildService.class));
  }

  @Provides
  @RFA1aMinorChildrenCollectionServiceBackendResource
  public ResourceDelegate rfa1aMinorChildrenCollectionServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aMinorChildrenCollectionService.class));
  }

  @Provides
  @RFA1aOtherAdultsServiceBackendResource
  public ResourceDelegate rfa1aOtherAdultServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(RFA1aOtherAdultService.class));
  }

  @Provides
  @RFA1aOtherAdultsCollectionServiceBackendResource
  public ResourceDelegate rfa1aOtherAdultsCollectionServiceBackendResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(RFA1aOtherAdultsCollectionService.class));
  }

}
