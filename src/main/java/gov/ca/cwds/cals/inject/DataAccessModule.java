package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.config.CalcApiConfiguration;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.LisFacFileDao;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

public class DataAccessModule extends AbstractModule {

    private final HibernateBundle<CalcApiConfiguration> lisHibernateBundle =
            new HibernateBundle<CalcApiConfiguration>(LisFacFile.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(CalcApiConfiguration configuration) {
                    return configuration.getLisDataSourceFactory();
                }

                @Override
                public String name() {
                    return "lis";
                }
            };

    public DataAccessModule(Bootstrap<CalcApiConfiguration> bootstrap) {
        bootstrap.addBundle(lisHibernateBundle);
    }

    @Override
    protected void configure() {
        bind(LisFacFileDao.class);
    }

    @Provides
    @LisSessionFactory
    SessionFactory lisSessionFactory() {
        return lisHibernateBundle.getSessionFactory();
    }

    @Provides
    @LisHibernateBundle
    HibernateBundle<CalcApiConfiguration> cmsHibernateBundle() {
        return lisHibernateBundle;
    }

}
