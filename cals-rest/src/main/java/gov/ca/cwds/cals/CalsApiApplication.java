package gov.ca.cwds.cals;


import gov.ca.cwds.cals.rest.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CalsApiApplication extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception{
        new CalsApiApplication().run(args);
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new PingResource());
    }
}
