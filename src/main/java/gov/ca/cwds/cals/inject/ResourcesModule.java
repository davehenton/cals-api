package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import gov.ca.cwds.cals.Constants.DictionaryType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.ComplaintsCollectionService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityChildCollectionService;
import gov.ca.cwds.cals.service.FacilityChildService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationshipDTO;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.cals.service.rfa.LIC198bCollectionService;
import gov.ca.cwds.cals.service.rfa.LIC198bService;
import gov.ca.cwds.cals.service.rfa.RFA1aAdoptionHistoryService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsDeclarationService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsHistoryService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsRelationshipService;
import gov.ca.cwds.cals.service.rfa.RFA1aChildDesiredService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildrenCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aReferencesService;
import gov.ca.cwds.cals.service.rfa.RFA1aResidenceService;
import gov.ca.cwds.cals.service.rfa.RFA1bApplicantAwareService;
import gov.ca.cwds.cals.service.rfa.RFA1bBaseService;
import gov.ca.cwds.cals.service.rfa.RFA1bCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1bOtherAdultAwareService;
import gov.ca.cwds.cals.service.rfa.RFA1cCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1cService;
import gov.ca.cwds.cals.service.rfa.RFAFormsPackageService;
import gov.ca.cwds.cals.service.tracking.RFA1aTrackingService;
import gov.ca.cwds.cals.service.tracking.TrackingService;
import gov.ca.cwds.cals.service.tracking.TrackingTemplateService;
import gov.ca.cwds.cals.web.rest.DictionariesResource;
import gov.ca.cwds.cals.web.rest.FacilityChildResource;
import gov.ca.cwds.cals.web.rest.FacilityComplaintResource;
import gov.ca.cwds.cals.web.rest.FacilityInspectionsResource;
import gov.ca.cwds.cals.web.rest.FacilityResource;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.TrackingParameterObject;
import gov.ca.cwds.cals.web.rest.rfa.LIC198bFormsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aAdoptionHistoryResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsDeclarationResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsHistoryResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsRelationshipResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aChildDesiredResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aReferencesResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1bFormsResource;
import gov.ca.cwds.cals.web.rest.rfa.RFA1cFormsResource;
import gov.ca.cwds.cals.web.rest.system.SystemInformationResource;
import gov.ca.cwds.cals.web.rest.tracking.TrackingResource;
import gov.ca.cwds.cals.web.rest.tracking.TrackingTemplateResource;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import gov.ca.cwds.rest.resources.TypedServiceBackedResourceDelegate;

/**
 * Identifies all CALS API domain resource classes available for dependency injection by Guice.
 *
 * @author CALS API Team
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
    bind(SystemInformationResource.class);
    bind(FacilityResource.class);
    bind(FacilityChildResource.class);
    bind(FacilityComplaintResource.class);
    bind(FacilityInspectionsResource.class);
    bind(DictionariesResource.class);

    // RFA
    bind(RFA1aFormsResource.class);
    bind(RFA1aApplicantsResource.class);
    bind(RFA1aMinorChildrenResource.class);
    bind(RFA1aResidenceResource.class);
    bind(RFA1aApplicantsRelationshipResource.class);
    bind(RFA1aOtherAdultsResource.class);
    bind(RFA1aAdoptionHistoryResource.class);
    bind(RFA1aApplicantsHistoryResource.class);
    bind(RFA1aReferencesResource.class);
    bind(RFA1aChildDesiredResource.class);
    bind(RFA1aApplicantsDeclarationResource.class);
    bind(RFA1bFormsResource.class);
    bind(RFA1cFormsResource.class);
    bind(LIC198bFormsResource.class);

    // Tracking
    bind(TrackingResource.class);
    bind(TrackingTemplateResource.class);
  }

  @Provides
  @FacilityServiceBackedResource
  public ResourceDelegate facilityServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(FacilityService.class));
  }

  @Provides
  @FacilityChildServiceBackedResource
  public ResourceDelegate facilityChildServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(FacilityChildService.class));
  }

  @Provides
  @FacilityChildCollectionServiceBackedResource
  public ResourceDelegate facilityChildServiceCollectionBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FacilityChildCollectionService.class));
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
  @FacilityInspectionCollectionServiceBackedResource
  public ResourceDelegate facilityInspectionCollectionServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
        injector.getInstance(FacilityInspectionCollectionService.class));
  }

  @Provides
  @FacilityInspectionServiceBackedResource
  public ResourceDelegate facilityInspectionServiceBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(injector.getInstance(FacilityInspectionService.class));
  }

  @Provides
  @DictionariesServiceBackedResource
  public TypedResourceDelegate<DictionaryType, BaseDictionary>
  dictionariesServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(DictionariesService.class));
  }

  @Provides
  @ResidenceServiceBackedResource
  public TypedResourceDelegate<Long, ResidenceDTO> residenceServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aResidenceService.class));
  }

  @Provides
  @ChildDesiredServiceBackedResource
  public TypedResourceDelegate<Long, ChildDesiredDTO> childDesiredServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aChildDesiredService.class));
  }

  @Provides
  @ApplicantsHistoryServiceBackedResource
  public TypedResourceDelegate<Long, ApplicantsHistoryDTO> applicantsHistoryServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aApplicantsHistoryService.class));
  }

  @Provides
  @ApplicantsRelationshipServiceBackedResource
  public TypedResourceDelegate<Long, ApplicantsRelationshipDTO> applicantsRelationshipServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aApplicantsRelationshipService.class));
  }

  @Provides
  @RFA1aFormServiceBackedResource
  public TypedResourceDelegate<RFA1aFormsParameterObject, RFA1aFormDTO> rfa1aFormsServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(injector.getInstance(RFA1aFormService.class));
  }

  @Provides
  @RFA1aFormCollectionServiceBackedResource
  public TypedResourceDelegate<Boolean, Request> rfa1aFormsCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aFormsCollectionService.class));
  }

  @Provides
  @RFA1aTrackingServiceBackedResource
  public TypedResourceDelegate<TrackingParameterObject, Tracking> trackingServiceBackedResource(
      Injector injector) {
    RFA1aTrackingService trackingService = injector.getInstance(RFA1aTrackingService.class);
    return new TypedServiceBackedResourceDelegate<>(trackingService);
  }

  @Provides
  @RFA1aApplicantServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<ApplicantDTO>>
  rfa1aApplicantServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aApplicantService.class));
  }

  @Provides
  @RFA1aApplicantsCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> rfa1aApplicantsCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aApplicantsCollectionService.class));
  }

  @Provides
  @RFA1aMinorChildrenServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<MinorChildDTO>>
  rfa1aMinorChildServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aMinorChildService.class));
  }

  @Provides
  @RFA1aMinorChildrenCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> rfa1aMinorChildrenCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aMinorChildrenCollectionService.class));
  }

  @Provides
  @RFA1aOtherAdultsServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<OtherAdultDTO>>
  rfa1aOtherAdultServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aOtherAdultService.class));
  }

  @Provides
  @RFA1aOtherAdultsCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> rfa1aOtherAdultsCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aOtherAdultsCollectionService.class));
  }

  @Provides
  @RFA1bBaseServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1bFormDTO>>
  rfa1bBaseServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1bBaseService.class));
  }

  @Provides
  @RFA1bApplicantAwareServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAApplicantAwareEntityUpdateParams<RFA1bFormDTO>>
  rfa1bApplicantAwareServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1bApplicantAwareService.class));
  }

  @Provides
  @RFA1bOtherAdultAwareServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAOtherAdultAwareEntityUpdateParams<RFA1bFormDTO>>
  rfa1bOtherAdultAwareServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1bOtherAdultAwareService.class));
  }

  @Provides
  @RFA1bCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> rfa1bCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1bCollectionService.class));
  }

  @Provides
  @LIC198bServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<LIC198bFormDTO>>
  lis198bServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(injector.getInstance(LIC198bService.class));
  }

  @Provides
  @LIC198bCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> lis198bCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(LIC198bCollectionService.class));
  }

  @Provides
  @RFA1cServiceBackedResource
  public TypedResourceDelegate<
      RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<RFA1cFormDTO>>
  rfa1cServiceBackedResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(injector.getInstance(RFA1cService.class));
  }

  @Provides
  @RFA1cCollectionServiceBackedResource
  public TypedResourceDelegate<Long, Request> rfa1cCollectionServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1cCollectionService.class));
  }

  @Provides
  @RFA1aAdoptionHistoryServiceBackedResource
  public TypedResourceDelegate<Long, AdoptionHistoryDTO> rfa1aAdoptionHistoryServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aAdoptionHistoryService.class));
  }

  @Provides
  @RFAPackageServiceBackedResource
  public TypedResourceDelegate<Long, PacketDTO> rfaPackageServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFAFormsPackageService.class));
  }

  @Provides
  @RFA1aReferencesServiceBackedResource
  public TypedResourceDelegate<Long, ReferencesDTO> rfa1aReferencesServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aReferencesService.class));
  }

  @Provides
  @RFA1aApplicantsDeclarationServiceBackedResource
  public TypedResourceDelegate<Long, ApplicantsDeclarationDTO> rfa1aApplicantsDeclarationServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(RFA1aApplicantsDeclarationService.class));
  }

  @Provides
  @TrackingServiceBackedResource
  public TypedResourceDelegate<Long, Tracking> trackingServiceBackndResource(Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(injector.getInstance(TrackingService.class));
  }

  @Provides
  @TrackingTemplateServiceBackedResource
  public TypedResourceDelegate<Long, TrackingTemplate> trackingTemplateServiceBackedResource(
      Injector injector) {
    return new TypedServiceBackedResourceDelegate<>(
        injector.getInstance(TrackingTemplateService.class));
  }
}
