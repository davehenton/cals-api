package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.CalsApiConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.ws.rs.client.Client;

/**
 * @author CWDS CALS API Team
 */

public class RestClientTestRule implements TestRule {

    private final DropwizardAppRule<CalsApiConfiguration> appRule;

    private Client client;

    private ObjectMapper mapper;

    public RestClientTestRule(DropwizardAppRule<CalsApiConfiguration> appRule) {
        this.appRule = appRule;
    }

    public Client getClient() {
        return client;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                client = appRule.client();
                mapper = appRule.getObjectMapper();
                client.register(new JacksonJsonProvider(mapper));
                statement.evaluate();
            }
        };
    }

}
