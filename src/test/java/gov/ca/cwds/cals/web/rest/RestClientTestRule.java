package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.CalsApiConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.apache.commons.lang3.StringUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author CWDS CALS API Team */
public class RestClientTestRule implements TestRule {

  private static final Logger LOG = LoggerFactory.getLogger(RestClientTestRule.class);

  public static final String CALS_API_URL = "cals.api.url";
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
    String serverUrlStr =
        String.format("http://localhost:%s/", dropWizardApplication.getLocalPort());

    if (serverUri != null) {
      serverUrlStr = serverUri.toString();
    }

    String hostForIntegrationTesting = System.getProperty(CALS_API_URL);
    if (StringUtils.isNotEmpty(hostForIntegrationTesting)) {
      serverUrlStr = hostForIntegrationTesting;
    }

    LOG.info("Test server URL: " + serverUrlStr);

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
