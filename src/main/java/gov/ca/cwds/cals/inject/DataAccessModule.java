package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.model.cms.OutOfHomePlacement;
import gov.ca.cwds.cals.model.cms.PlacementEpisode;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.cals.model.lis.County;
import gov.ca.cwds.cals.model.lis.FacilityStatusType;
import gov.ca.cwds.cals.model.lis.FacilityType;
import gov.ca.cwds.cals.model.lis.LisDoFile;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.model.lis.LisTableFile;
import gov.ca.cwds.cals.model.lis.VisitReasonType;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityTypeDao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CmsSessionFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.*;

/**
 * @author CWDS CALS API Team
 */

public class DataAccessModule extends AbstractModule {

    private final HibernateBundle<CalsApiConfiguration> lisHibernateBundle =
            new HibernateBundle<CalsApiConfiguration>(
                    LisFacFile.class,
                    LisTableFile.class,
                    FacilityType.class,
                    LisDoFile.class,
                    FacilityStatusType.class,
                    VisitReasonType.class,
                    County.class
            ) {
                @Override
                public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
                    return configuration.getLisDataSourceFactory();
                }

                @Override
                public String name() {
                    return LIS;
                }
            };

    private final HibernateBundle<CalsApiConfiguration> fasHibernateBundle =
            new HibernateBundle<CalsApiConfiguration>(
                    ComplaintReportLic802.class,
                    Rrcpoc.class
                    ) {
                @Override
                public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
                    return configuration.getFasDataSourceFactory();
                }

                @Override
                public String name() {
                    return FAS;
                }
            };

    private final HibernateBundle<CalsApiConfiguration> cmsHibernateBundle =
            new HibernateBundle<CalsApiConfiguration>(
                    Client.class,
                    OutOfHomePlacement.class,
                    PlacementEpisode.class,
                    PlacementHome.class,
                    StaffPerson.class,
                    gov.ca.cwds.cals.model.cms.County.class
                    ) {
                @Override
                public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
                    return configuration.getCmsDataSourceFactory();
                }

                @Override
                public String name() {
                    return CMS;
                }
            };

    public DataAccessModule(Bootstrap<CalsApiConfiguration> bootstrap) {
        bootstrap.addBundle(fasHibernateBundle);
        bootstrap.addBundle(cmsHibernateBundle);
        bootstrap.addBundle(lisHibernateBundle);
    }

    @Override
    protected void configure() {
        bind(ComplaintReportLic802Dao.class);
        bind(FacilityTypeDao.class);
        bind(CountiesDao.class);
        bind(ClientDao.class);
        bind(PlacementHomeDao.class);
        bind(InspectionDao.class);
    }

    @Provides
    @FasSessionFactory
    SessionFactory fasSessionFactory() {
        return fasHibernateBundle.getSessionFactory();
    }

    @Provides
    @CmsSessionFactory
    SessionFactory cmsSessionFactory() {
        return cmsHibernateBundle.getSessionFactory();
    }

    @Provides
    @LisSessionFactory
    SessionFactory lisSessionFactory() {
        return lisHibernateBundle.getSessionFactory();
    }

    @Provides
    @FasHibernateBundle
    HibernateBundle<CalsApiConfiguration> fasHibernateBundle() {
        return fasHibernateBundle;
    }

    @Provides
    @CmsHibernateBundle
    public HibernateBundle<CalsApiConfiguration> getCmsHibernateBundle() {
        return cmsHibernateBundle;
    }

    @Provides
    @LisHibernateBundle
    public HibernateBundle<CalsApiConfiguration> getLisHibernateBundle() {
        return lisHibernateBundle;
    }

    @Provides
    UnitOfWorkAwareProxyFactory lisUnitOfWorkAwareProxyFactory() {
        return new UnitOfWorkAwareProxyFactory(lisHibernateBundle, fasHibernateBundle, cmsHibernateBundle);
    }

}
