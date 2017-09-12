package gov.ca.cwds.cals;

import com.google.inject.Module;
import com.google.inject.Provides;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.cals.inject.DataAccessModule;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;

/**
 * @author CWDS CALS API Team
 */

public class CalsApiApplication extends BaseCalsApiApplication<CalsApiConfiguration> {

  public static void main(String[] args) throws Exception {
    new CalsApiApplication().run(args);
  }

  @Override
  public Module applicationModule(Bootstrap<CalsApiConfiguration> bootstrap) {
    return new ApplicationModule<CalsApiConfiguration>(bootstrap) {

      @Override
      protected void configure() {
        super.configure();
        install(new DataAccessModule(bootstrap) {

          @Provides
          UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
            return new UnitOfWorkAwareProxyFactory(
                getLisHibernateBundle(),
                getFasHibernateBundle(),
                getCmsHibernateBundle(),
                getCalsnsHibernateBundle(),
                getXaCmsHibernateBundle(),
                getXaCalsnsHibernateBundle());
          }

        });
      }

    };
  }

}
