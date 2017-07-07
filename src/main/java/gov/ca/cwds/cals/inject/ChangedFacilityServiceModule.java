package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.RecordChangeCwsCmsDao;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileFasDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.RecordChangeLisDao;
import gov.ca.cwds.cals.service.ChangedFacilityService;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * @author CWDS CALS API Team
 */
// TODO reuse in Jobs !!!
public class ChangedFacilityServiceModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ChangedFacilityServiceModule() {
    // no op
  }

  /*
  @Provides
  @Inject
  public ChangedFacilityService provideChangedFacilityService(
      LisFacFileLisDao lisFacFileLisDao, LisFacFileFasDao lisFacFileFasDao,
      PlacementHomeDao placementHomeDao, LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao, FacilityMapper facilityMapper, FasFacilityMapper fasFacilityMapper,
      RecordChangeLisDao recordChangeLisDao,
      RecordChangeCwsCmsDao recordChangeCwsCmsDao,
      ClientDao clientDao, FacilityChildMapper facilityChildMapper) {
    return new ChangedFacilityService(lisFacFileLisDao, lisFacFileFasDao, placementHomeDao,
        lpaInformationDao, countiesDao, facilityMapper, fasFacilityMapper, recordChangeLisDao,
        recordChangeCwsCmsDao, clientDao, facilityChildMapper);
  }*/

  @Provides
  @Inject
  ChangedFacilityService provideFacilityService(
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory,
      LisFacFileLisDao lisFacFileLisDao,
      LisFacFileFasDao lisFacFileFasDao,
      PlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao,
      FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper,
      RecordChangeLisDao recordChangeLisDao,
      RecordChangeCwsCmsDao recordChangeCwsCmsDao,
      ClientDao clientDao,
      FacilityChildMapper facilityChildMapper) {
    return unitOfWorkAwareProxyFactory.create(
        ChangedFacilityService.class,
        new Class[]{
            LisFacFileLisDao.class,
            LisFacFileFasDao.class,
            PlacementHomeDao.class,
            LpaInformationDao.class,
            CountiesDao.class,
            FacilityMapper.class,
            FasFacilityMapper.class,
            RecordChangeLisDao.class,
            RecordChangeCwsCmsDao.class,
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
            recordChangeLisDao,
            recordChangeCwsCmsDao,
            clientDao,
            facilityChildMapper
        });
  }

  @Override
  protected void configure() {
    // no op
  }
}
