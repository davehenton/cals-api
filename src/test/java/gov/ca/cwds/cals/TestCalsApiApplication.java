package gov.ca.cwds.cals;

import com.google.inject.Module;
import com.google.inject.Provides;
import gov.ca.cwds.cals.inject.ApplicationModule;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;

/**
 * @author CWDS CALS API Team
 */

public class TestCalsApiApplication extends BaseCalsApiApplication<TestCalsApiConfiguration> {

  @Override
  public Module applicationModule(Bootstrap<TestCalsApiConfiguration> bootstrap) {
    return new ApplicationModule<TestCalsApiConfiguration>(bootstrap) {

      @Override
      protected void configure() {
        super.configure();
        install(new CWSRSDataAccessModule(getBootstrap()) {

          @Provides
          UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
            return new UnitOfWorkAwareProxyFactory(
                lisHibernateBundle,
                fasHibernateBundle,
                cmsHibernateBundle,
                calsnsHibernateBundle,
                xaCmsHibernateBundle,
                xaCalsnsHibernateBundle,
                cmsrsHibernateBundle
            );
          }

        });
      }

    };
  }

}
