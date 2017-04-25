package gov.ca.cwds.cals;

import com.google.inject.Module;
import gov.ca.cwds.cals.inject.ApplicationModule;
import gov.ca.cwds.rest.BaseApiApplication;
import io.dropwizard.setup.Bootstrap;

public class CalsApiApplication extends BaseApiApplication<CalcApiConfiguration> {

    public static void main(String[] args) throws Exception{
        new CalsApiApplication().run(args);
    }

    @Override
    public Module applicationModule(Bootstrap<CalcApiConfiguration> bootstrap) {
        return new ApplicationModule(bootstrap);
    }
}
