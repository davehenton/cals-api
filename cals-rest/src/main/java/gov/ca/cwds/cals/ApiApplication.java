package gov.ca.cwds.cals;


import gov.ca.cwds.cals.services.PingService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception{
        new ApiApplication().run(args);
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new PingService());
    }
}
