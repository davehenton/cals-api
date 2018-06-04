package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.service.ChildAssignedWorkerService;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.CwsFacilityService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityChildService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.FasFacilityService;
import gov.ca.cwds.cals.service.LegacyDictionariesCache;
import gov.ca.cwds.cals.service.LegacyDictionariesCache.LegacyDictionariesCacheBuilder;
import gov.ca.cwds.cals.service.LisDictionariesCache;
import gov.ca.cwds.cals.service.LisFacilityService;
import gov.ca.cwds.cals.service.builder.FacilityParameterObjectBuilder;
import gov.ca.cwds.cals.service.rfa.LIC198bCollectionService;
import gov.ca.cwds.cals.service.rfa.LIC198bService;
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
import gov.ca.cwds.cals.service.rfa.RFA1aPDFGenerationService;
import gov.ca.cwds.cals.service.rfa.RFA1aReferencesService;
import gov.ca.cwds.cals.service.rfa.RFA1aResidenceService;
import gov.ca.cwds.cals.service.rfa.RFA1bApplicantAwareService;
import gov.ca.cwds.cals.service.rfa.RFA1bBaseService;
import gov.ca.cwds.cals.service.rfa.RFA1bCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1bOtherAdultAwareService;
import gov.ca.cwds.cals.service.rfa.RFA1cCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1cService;
import gov.ca.cwds.cals.service.tracking.TrackingService;
import gov.ca.cwds.cals.service.tracking.TrackingTemplateService;
import gov.ca.cwds.cals.service.validation.business.configuration.ValidationConfigurationRegistryImpl;
import gov.ca.cwds.data.cms.SystemCodeDao;
import gov.ca.cwds.data.cms.SystemMetaDao;
import gov.ca.cwds.data.legacy.cms.dao.CountiesDao;
import gov.ca.cwds.data.legacy.cms.dao.LicenseStatusDao;
import gov.ca.cwds.data.legacy.cms.dao.StateDao;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import gov.ca.cwds.drools.DroolsService;
import gov.ca.cwds.drools.validation.ValidationConfigurationRegistry;
import gov.ca.cwds.rest.api.domain.cms.SystemCodeCache;
import gov.ca.cwds.rest.services.cms.CachingSystemCodeService;
import gov.ca.cwds.rest.services.cms.SystemCodeService;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;


/**
 * Identifies all CALS API business layer (services) classes available for dependency injection by
 * Guice.
 *
 * @author CALS API Team
 */
public class ServicesModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ServicesModule() {
    // Do nothing - Default constructor
  }

  @Override
  protected void configure() {
    bind(ComplaintService.class);
    bind(FacilityInspectionCollectionService.class);
    bind(FacilityInspectionService.class);
    bind(CountiesService.class);
    bind(FacilityChildService.class);
    bind(DictionariesService.class);
    bind(FacilityService.class);
    bind(FasFacilityService.class).toProvider(FasFacilityServiceProvider.class);
    bind(CwsFacilityService.class).toProvider(CwsFacilityServiceProvider.class);
    bind(LisDictionariesCache.class);
    bind(LisFacilityService.class).toProvider(LisFacilityServiceProvider.class);
    bind(FacilityParameterObjectBuilder.class);
    bind(ChildAssignedWorkerService.class);

    // RFA
    bind(RFA1aFormService.class).toProvider(RFA1aFormServiceProvider.class);
    bind(RFA1aFormsCollectionService.class);
    bind(RFA1aPDFGenerationService.class);
    bind(RFA1aApplicantService.class);
    bind(RFA1aApplicantsCollectionService.class);
    bind(RFA1aMinorChildService.class);
    bind(RFA1aMinorChildrenCollectionService.class);
    bind(RFA1bBaseService.class);
    bind(RFA1bApplicantAwareService.class);
    bind(RFA1bOtherAdultAwareService.class);
    bind(RFA1bCollectionService.class);
    bind(LIC198bService.class);
    bind(LIC198bCollectionService.class);
    bind(RFA1cService.class);
    bind(RFA1cCollectionService.class);
    bind(RFA1aOtherAdultService.class);
    bind(RFA1aOtherAdultsCollectionService.class);
    bind(RFA1aResidenceService.class);
    bind(RFA1aApplicantsRelationshipService.class);
    bind(RFA1aApplicantsHistoryService.class);
    bind(RFA1aReferencesService.class);
    bind(RFA1aChildDesiredService.class);
    bind(RFA1aApplicantsDeclarationService.class);
    bind(DroolsService.class);
    bind(ValidationConfigurationRegistry.class).to(ValidationConfigurationRegistryImpl.class)
        .asEagerSingleton();

    //Tracking
    bind(TrackingService.class);
    bind(TrackingTemplateService.class);
  }

  @Provides
  public SystemCodeService provideSystemCodeService(SystemCodeDao systemCodeDao,
      SystemMetaDao systemMetaDao) {
    final long secondsToRefreshCache = 15L * 24 * 60 * 60; // 15 days
    return new CachingSystemCodeService(systemCodeDao, systemMetaDao, secondsToRefreshCache, false);
  }

  @Provides
  public SystemCodeCache provideSystemCodeCache(SystemCodeService systemCodeService) {
    SystemCodeCache systemCodeCache = (SystemCodeCache) systemCodeService;
    systemCodeCache.register();
    return systemCodeCache;
  }

  @Provides
  public Client provideClient() {
    JerseyClientBuilder clientBuilder = new JerseyClientBuilder()
        .property(ClientProperties.CONNECT_TIMEOUT, 20000)
        .property(ClientProperties.READ_TIMEOUT, 60000)
        // Just ignore host verification, client will call trusted resources only
        .hostnameVerifier((hostName, sslSession) -> true);
    return clientBuilder.build();
  }

  @Provides
  public LegacyDictionariesCache provideLegacyDictionariesCache(
      CountiesDao countiesDao,
      StateDao stateDao,
      LicenseStatusDao licenseStatusDao
  ) {
    LegacyDictionariesCacheBuilder builder = new LegacyDictionariesCacheBuilder();
    return builder
        .add(County.class, countiesDao)
        .add(State.class, stateDao)
        .add(LicenseStatus.class, licenseStatusDao)
        .build();
  }
}
