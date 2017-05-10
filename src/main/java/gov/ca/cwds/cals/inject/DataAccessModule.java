package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.model.cms.*;
import gov.ca.cwds.cals.model.fas.County;
import gov.ca.cwds.cals.model.fas.FacilityStatusType;
import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.cals.model.fas.LisDoFile;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.model.fas.LisTableFile;
import gov.ca.cwds.cals.model.fas.VisitReasonType;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileDao;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CmsSessionFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class DataAccessModule extends AbstractModule {

    private final HibernateBundle<CalsApiConfiguration> fasHibernateBundle =
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
                    return configuration.getFasDataSourceFactory();
                }

                @Override
                public String name() {
                    return "fas";
                }
            };

    private final HibernateBundle<CalsApiConfiguration> cmsHibernateBundle =
            new HibernateBundle<CalsApiConfiguration>(
                    ChildClient.class,
                    OutOfHomePlacement.class,
                    PlacementEpisode.class,
                    PlcacementHome.class,
                    StaffPerson.class
                    ) {
                @Override
                public DataSourceFactory getDataSourceFactory(CalsApiConfiguration configuration) {
                    return configuration.getCmsDataSourceFactory();
                }

                @Override
                public String name() {
                    return "cms";
                }
            };

    public DataAccessModule(Bootstrap<CalsApiConfiguration> bootstrap) {
        bootstrap.addBundle(fasHibernateBundle);
        bootstrap.addBundle(cmsHibernateBundle);
    }

    @Override
    protected void configure() {
        bind(LisFacFileDao.class);
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
    @FasHibernateBundle
    HibernateBundle<CalsApiConfiguration> fasHibernateBundle() {
        return fasHibernateBundle;
    }

    @Provides
    @CmsHibernateBundle
    public HibernateBundle<CalsApiConfiguration> getCmsHibernateBundle() {
        return fasHibernateBundle;
    }

}
