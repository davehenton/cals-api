package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * Identifies all CALS API business layer (services) classes available for dependency injection by Guice.
 *
 * @author CALS API Team
 *
 */
public class ServicesModule extends AbstractModule{

    /**
     *  Default constructor
     */
    public ServicesModule() {

    }

    @Override
    protected void configure() {
        bind(ComplaintService.class);
        bind(FacilityInspectionCollectionService.class);
        bind(FacilityInspectionService.class);
        bind(CountiesService.class);
    }

    @Provides
    @Inject
    FacilityService provideFacilityService(UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory,
            LisFacFileDao lisFacFileDao, PlacementHomeDao placementHomeDao, LpaInformationDao lpaInformationDao,
            FacilityMapper facilityMapper) {
        return unitOfWorkAwareProxyFactory.create(FacilityService.class,
                new Class[]{LisFacFileDao.class, PlacementHomeDao.class, LpaInformationDao.class, FacilityMapper.class},
                new Object[]{lisFacFileDao, placementHomeDao, lpaInformationDao, facilityMapper});
    }

}
