package gov.ca.cwds.cals;


import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ApiConfiguration extends Configuration {

    /**
     * The application name
     */
    @NotEmpty
    private String applicationName;

    /**
     * The version
     */
    @NotEmpty
    private String version;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
