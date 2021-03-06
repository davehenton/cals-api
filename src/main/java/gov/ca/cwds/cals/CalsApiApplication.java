package gov.ca.cwds.cals;

import static gov.ca.cwds.authorizer.ClientResultReadAuthorizer.CLIENT_RESULT_READ;
import static gov.ca.cwds.authorizer.PlacementHomeResultReadAuthorizer.PLACEMENT_HOME_RESULT_READ;

import com.google.inject.Module;
import gov.ca.cwds.authorizer.ClientResultReadAuthorizer;
import gov.ca.cwds.authorizer.FacilityReadStaticAuthorizer;
import gov.ca.cwds.authorizer.PlacementHomeCreateAuthorizer;
import gov.ca.cwds.authorizer.PlacementHomeResultReadAuthorizer;
import gov.ca.cwds.authorizer.SubstituteCareProviderCreateAuthorizer;
import gov.ca.cwds.cals.Constants.Authorize;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.rest.BaseApiApplication;
import gov.ca.cwds.security.module.SecurityModule;
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
        install(new SecurityModule(BaseApiApplication::getInjector)
            .addAuthorizer(Authorize.PLACEMENT_HOME_CREATE, PlacementHomeCreateAuthorizer.class)
            .addAuthorizer(Authorize.SUBSTITUTE_CARE_PROVIDER_CREATE,
                SubstituteCareProviderCreateAuthorizer.class)
            .addAuthorizer(CLIENT_RESULT_READ, ClientResultReadAuthorizer.class)
            .addAuthorizer(PLACEMENT_HOME_RESULT_READ, PlacementHomeResultReadAuthorizer.class)
            .addStaticAuthorizer(FacilityReadStaticAuthorizer.class)
        );
      }

    };
  }

}
