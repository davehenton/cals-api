package gov.ca.cwds.cals;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.rest.api.Response;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.io.IOException;
import java.util.Map;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;

/** @author CWDS CALS API Team */
public abstract class BaseCalsApiIntegrationTest {

  @ClassRule
  public static final DropwizardAppRule<CalsApiConfiguration> appRule =
      new DropwizardAppRule<CalsApiConfiguration>(
          CalsApiApplication.class, ResourceHelpers.resourceFilePath("config/test-cals-api.yml")) {

        @Override
        public Client client() {
          Client client = super.client();
          if (((JerseyClient) client).isClosed()) {
            client = clientBuilder().build();
          }
          return client;
        }
      };

  @Rule public RestClientTestRule clientTestRule = new RestClientTestRule(appRule);

  protected static DatabaseHelper getFasDatabaseHelper() {
    DataSourceFactory dataSourceFactory = appRule.getConfiguration().getFasDataSourceFactory();
    return new DatabaseHelper(
        dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
  }

  protected static DatabaseHelper getCmsDatabaseHelper() {
    DataSourceFactory dataSourceFactory = appRule.getConfiguration().getCmsDataSourceFactory();
    return new DatabaseHelper(
        dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
  }

  protected static DatabaseHelper getCalsnsDatabaseHelper() {
    DataSourceFactory dataSourceFactory = appRule.getConfiguration().getCalsnsDataSourceFactory();
    return new DatabaseHelper(
        dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
  }

  protected static DatabaseHelper getLisDatabaseHelper() {
    DataSourceFactory dataSourceFactory = appRule.getConfiguration().getLisDataSourceFactory();
    return new DatabaseHelper(
        dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
  }

  public static void setUpFas() throws Exception {
    getFasDatabaseHelper().runScript("liquibase/fas_database_master.xml");
  }

  public static void setUpLis() throws Exception {
    getLisDatabaseHelper().runScript("liquibase/lis_database_master.xml");
  }

  public static void setUpCms() throws Exception {
    getCmsDatabaseHelper().runScript("liquibase/cwscms_database_master.xml");
  }

  public static void setUpCalsns() throws Exception {
    getCalsnsDatabaseHelper().runScript("liquibase/calsns_database_master.xml");
  }

  @After
  public void tearDown() throws Exception {}

  protected void assertEqualsResponse(String fixture, Response response) throws IOException {
    String actualString = clientTestRule.getMapper().writeValueAsString(response);
    assertEqualsResponse(fixture, actualString);
  }

  @SuppressWarnings("unchecked")
  protected void assertEqualsResponse(String fixture, String actualString) throws IOException {
    ObjectMapper om = new ObjectMapper();
    Map<String, String> expectedMap = (Map<String, String>) om.readValue(fixture, Map.class);
    Map<String, String> actualMap = (Map<String, String>) om.readValue(actualString, Map.class);
    assertThat(actualMap).isEqualTo(expectedMap);
  }
}
