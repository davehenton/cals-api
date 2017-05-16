package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.model.fas.County;
import gov.ca.cwds.cals.model.fas.FacilityStatusType;
import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.cals.model.fas.LisDoFile;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.model.fas.LisTableFile;
import gov.ca.cwds.cals.model.fas.VisitReasonType;
import gov.ca.cwds.cals.persistence.dao.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.LisFacFileDao;
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
                    County.class,
                    ComplaintReportLic802.class
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

    //todo: define classes
    private final HibernateBundle<CalsApiConfiguration> cmsHibernateBundle =
            new HibernateBundle<CalsApiConfiguration>(
                    ChldClt.class,
                    OHmPlt.class,
                    PlcEpst.class,
                    PlcHmT.class,
                    Stfperst.class
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
        bind(ComplaintReportLic802Dao.class);
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
