package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.web.rest.rfa.LoggingFilter;
import gov.ca.cwds.security.jwt.JwtConfiguration;
import gov.ca.cwds.security.jwt.JwtService;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.File;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
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
public class RestClientTestRule implements TestRule {

  private static final Logger LOG = LoggerFactory.getLogger(RestClientTestRule.class);

  public static final String CALS_API_URL = "cals.api.url";
  private final DropwizardAppRule<CalsApiConfiguration> dropWizardApplication;

  private Client client;
  private ObjectMapper mapper;
  private String token;

  public RestClientTestRule(DropwizardAppRule<CalsApiConfiguration> dropWizardApplication) {
    this.dropWizardApplication = dropWizardApplication;
    if (isIntegrationTestsRunning()) {
      try {
        token = generateToken();
      } catch (Exception e) {
        LOG.warn("Cannot generate token");
      }
    }
  }

  public static boolean isIntegrationTestsRunning() {
    return System.getProperty(CALS_API_URL) != null;
  }

  public String generateToken() throws Exception {
    JwtConfiguration configuration = getJwtConfiguration();
    JwtService jwtService = new JwtService(configuration);
    return jwtService.generate("id", "subject", "identity");
  }

  private JwtConfiguration getJwtConfiguration() {
    JwtConfiguration configuration = new JwtConfiguration();
    configuration = new JwtConfiguration();
    //JWT
    configuration.setTimeout(30);
    configuration.setIssuer("issuer");
    configuration.setKeyStore(new JwtConfiguration.KeyStoreConfiguration());
    //KeyStore
    configuration.getKeyStore().setPath(new File("config/enc.jceks").getPath());
    configuration.getKeyStore().setPassword("test");
    //Sign/Validate Key
    configuration.getKeyStore().setAlias("test");
    configuration.getKeyStore().setKeyPassword("test");
    //Enc Key
    configuration.setEncryptionEnabled(true);
    configuration.getKeyStore().setEncKeyPassword("test");
    configuration.getKeyStore().setEncAlias("enc128");
    configuration.setEncryptionMethod("A128GCM");
    return configuration;
  }

  public WebTarget target(String pathInfo) {
    String restUrl = getUriString() + pathInfo;
    WebTarget webTarget = client.target(restUrl);
    webTarget.queryParam("token", token);
    webTarget.register(new LoggingFilter());
    return webTarget;
  }

  protected String getUriString() {
    String serverUrlStr = System.getProperty(CALS_API_URL);
    if (StringUtils.isEmpty(serverUrlStr)) {
      serverUrlStr = composeUriString();
    }
    return serverUrlStr;
  }

  protected URI getServerUrl() {
    return dropWizardApplication.getEnvironment().getApplicationContext().getServer().getURI();
  }

  protected String composeUriString() {
    String serverUrlStr =
        String.format("http://localhost:%s/", dropWizardApplication.getLocalPort());
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

        JerseyClientBuilder clientBuilder = new JerseyClientBuilder()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 20000).hostnameVerifier(new HostnameVerifier() {
              @Override
              public boolean verify(String hostName, SSLSession sslSession) {
                // Just ignore host verification for test purposes
                return true;
              }
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
        statement.evaluate();
      }
    };
  }
}
