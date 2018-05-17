package gov.ca.cwds.cals.web.rest;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.web.rest.rfa.LoggingFilter;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import gov.ca.cwds.test.support.AuthParams;
import gov.ca.cwds.test.support.JsonIdentityAuthParams;
import gov.ca.cwds.test.support.PerryV2DevModeTokenProvider;
import gov.ca.cwds.test.support.TokenProvider;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Optional;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class RestClientTestRule<T extends CalsApiConfiguration> implements TestRule {

  private static final Logger LOG = LoggerFactory.getLogger(RestClientTestRule.class);

  private final DropwizardAppRule<T> dropWizardApplication;


  private Client client;
  private ObjectMapper mapper;
  private TokenProvider tokenProvider = new EmptyTokenProvider();
  private AuthParams defaultAuthParams = new JsonIdentityAuthParams("{}");


  private static class EmptyTokenProvider implements TokenProvider {

    @Override
    public String doGetToken(AuthParams params) {
      return "";
    }
  }

  ;

  public RestClientTestRule(DropwizardAppRule<T> dropWizardApplication) {
    this.dropWizardApplication = dropWizardApplication;
  }

  public WebTarget target(String pathInfo) {
    return target(pathInfo, defaultAuthParams);
  }

  public WebTarget target(String pathInfo, AuthParams authParams) {
    String restUrl = getUriString() + pathInfo;
    return client.target(restUrl).queryParam("token",
        tokenProvider.doGetToken(Optional.ofNullable(authParams).orElse(defaultAuthParams)))
        .register(new LoggingFilter());
  }

  protected String getUriString() {
    String serverUrlStr = System.getProperty(TestModeUtils.CALS_API_URL);
    if (StringUtils.isEmpty(serverUrlStr)) {
      serverUrlStr = composeUriString();
    }
    return serverUrlStr;
  }

  protected URI getServerUrl() {
    return dropWizardApplication.getEnvironment().getApplicationContext().getServer().getURI();
  }

  protected String composeUriString() {
    return String.format("http://localhost:%s/", dropWizardApplication.getLocalPort());
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {

        JerseyClientBuilder clientBuilder = new JerseyClientBuilder()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 60000)
            .hostnameVerifier((hostName, sslSession) -> {
              // Just ignore host verification for test purposes
              return true;
            });

        client = clientBuilder.build();

        // Trust All certificates for test purposes
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }

          public void checkClientTrusted(X509Certificate[] certs, String authType) {
          }

          public void checkServerTrusted(X509Certificate[] certs, String authType) {
          }
        }};

        client.getSslContext().init(null, trustAllCerts, new SecureRandom());

        mapper = dropWizardApplication.getObjectMapper();
        client.register(new JacksonJsonProvider(mapper));

        prepareForTesting();

        statement.evaluate();
      }
    };
  }

  private void prepareForTesting() {
    if (TestModeUtils.isIntegrationTestsMode()) {
      try {
        String principal = fixture("security/default-login-principal.json");
        defaultAuthParams = new JsonIdentityAuthParams(principal);
        tokenProvider = new PerryV2DevModeTokenProvider(
            client,
            TestModeUtils.perryUrl(),
            TestModeUtils.loginFormTargetUrl());
      } catch (Exception e) {
        LOG.warn("Cannot generate token");
        LOG.error(e.getMessage(), e);
      }
    }
  }
}
