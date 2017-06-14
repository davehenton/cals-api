package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.IPlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.service.AgeGroupTypesService;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.data.CrudsDao;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Identifies all CALS API business layer (services) classes available for dependency injection by
 * Guice.
 *
 * @author CALS API Team
 */
public class ServicesModule extends AbstractModule {

  /** Default constructor */
  public ServicesModule() {
    // Do nothing - Default constructor
  }

  @Override
  protected void configure() {
    bind(ComplaintService.class);
    bind(FacilityInspectionCollectionService.class);
    bind(FacilityInspectionService.class);
    bind(CountiesService.class);
    bind(AgeGroupTypesService.class);
    bind(DictionariesService.class);
  }

  @Provides
  @Inject
  @SuppressWarnings("squid:S00107") // Configuration
  FacilityService provideFacilityService(
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory,
      gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao lisDsLisFacFileDao,
      gov.ca.cwds.cals.persistence.dao.fas.LisFacFileDao fasDsLisFacFileDao,
      IPlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao,
      FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper) {
    return unitOfWorkAwareProxyFactory.create(
        FacilityService.class,
        new Class[] {
          CrudsDao.class,
          CrudsDao.class,
          IPlacementHomeDao.class,
          LpaInformationDao.class,
          CountiesDao.class,
          FacilityMapper.class,
          FasFacilityMapper.class
        },
        new Object[] {
          lisDsLisFacFileDao,
          fasDsLisFacFileDao,
          placementHomeDao,
          lpaInformationDao,
          countiesDao,
          facilityMapper,
          fasFacilityMapper
        });
  }
}
