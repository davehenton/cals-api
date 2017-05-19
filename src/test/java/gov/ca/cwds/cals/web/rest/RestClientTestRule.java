package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.CalsApiConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

/**
 * @author CWDS CALS API Team
 */

public class RestClientTestRule implements TestRule {

    private final DropwizardAppRule<CalsApiConfiguration> dropWizardApplication;

    private Client client;

    private ObjectMapper mapper;

    public RestClientTestRule(DropwizardAppRule<CalsApiConfiguration> dropWizardApplication) {
        this.dropWizardApplication = dropWizardApplication;
    }

    public WebTarget target(String pathInfo) {
        String restUrl = getUriString() + pathInfo;
        return client.target(restUrl);
    }

    protected URI getServerUrl() {
        return dropWizardApplication.getEnvironment().getApplicationContext().getServer().getURI();
    }

    protected String getUriString() {
        URI serverUri = getServerUrl();
        String serverUrlStr = "http://localhost:8090/"; //port defined in src/test/resources/config/test-cals-api.yml
        if (serverUri != null) {
            serverUrlStr = serverUri.toString();
        }
        return serverUrlStr;
    }


    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                client = dropWizardApplication.client();
                mapper = dropWizardApplication.getObjectMapper();
                client.register(new JacksonJsonProvider(mapper));
                statement.evaluate();
            }
        };
    }

}
