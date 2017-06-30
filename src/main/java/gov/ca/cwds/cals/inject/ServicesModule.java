package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileFasDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.service.rfa.ApplicantsRelationshipService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildrenCollectionService;
import gov.ca.cwds.cals.service.rfa.ResidenceService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

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
    bind(DictionariesService.class);

    // RFA
    bind(RFA1aFormService.class);
    bind(RFA1aFormsCollectionService.class);
    bind(RFA1aApplicantService.class);
    bind(RFA1aApplicantsCollectionService.class);
    bind(RFA1aMinorChildService.class);
    bind(RFA1aMinorChildrenCollectionService.class);
    bind(ResidenceService.class);
    bind(ApplicantsRelationshipService.class);
  }

  @Provides
  @Inject
  @SuppressWarnings("squid:S00107")
    // Configuration
  FacilityService provideFacilityService(
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory,
      LisFacFileLisDao lisFacFileLisDao,
      LisFacFileFasDao lisFacFileFasDao,
      PlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao,
      FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper,
      ClientDao clientDao,
      FacilityChildMapper facilityChildMapper) {
    return unitOfWorkAwareProxyFactory.create(
        FacilityService.class,
        new Class[]{
            LisFacFileLisDao.class,
            LisFacFileFasDao.class,
            PlacementHomeDao.class,
            LpaInformationDao.class,
            CountiesDao.class,
            FacilityMapper.class,
            FasFacilityMapper.class,
            ClientDao.class,
            FacilityChildMapper.class
        },
        new Object[]{
            lisFacFileLisDao,
            lisFacFileFasDao,
            placementHomeDao,
            lpaInformationDao,
            countiesDao,
            facilityMapper,
            fasFacilityMapper,
            clientDao,
            facilityChildMapper
        });
  }
}
