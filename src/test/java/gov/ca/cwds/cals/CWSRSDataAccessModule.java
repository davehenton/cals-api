package gov.ca.cwds.cals;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMSRS;

import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;
import gov.ca.cwds.cals.inject.CmsRsHibernateBundle;
import gov.ca.cwds.cals.inject.CwsRsSessionFactory;
import gov.ca.cwds.cals.inject.DataAccessModule;
import gov.ca.cwds.cals.persistence.model.RecordChange;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class CWSRSDataAccessModule extends DataAccessModule {

  private final ImmutableList<Class<?>> cmsrsEntities =
      ImmutableList.<Class<?>>builder().add(RecordChange.class).build();

  private final HibernateBundle<TestCalsApiConfiguration> cmsrsHibernateBundle =
      new HibernateBundle<TestCalsApiConfiguration>(cmsrsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(
            TestCalsApiConfiguration configuration) {
          return configuration.getCmsrsDataSourceFactory();
        }

        @Override
        public String name() {
          return CMSRS;
        }
      };

  public CWSRSDataAccessModule(Bootstrap<TestCalsApiConfiguration> bootstrap) {
    super(bootstrap);
    bootstrap.addBundle(cmsrsHibernateBundle);
  }

  @Override
  protected void configure() {
    //empty
  }

  @Provides
  @CmsRsHibernateBundle
  public HibernateBundle<TestCalsApiConfiguration> getCmsRsHibernateBundle() {
    return cmsrsHibernateBundle;
  }

  @Provides
  @CwsRsSessionFactory
  SessionFactory cmwrsSessionFactory() {
    return cmsrsHibernateBundle.getSessionFactory();
  }

}
