package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.model.fas.FacilityStatusType;
import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.cals.model.fas.LisDoFile;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.model.fas.LisTableFile;
import gov.ca.cwds.cals.model.fas.VisitReasonType;
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
                    VisitReasonType.class
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

    public DataAccessModule(Bootstrap<CalsApiConfiguration> bootstrap) {
        bootstrap.addBundle(fasHibernateBundle);
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
    @FasHibernateBundle
    HibernateBundle<CalsApiConfiguration> fasHibernateBundle() {
        return fasHibernateBundle;
    }

}
