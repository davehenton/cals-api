package gov.ca.cwds.cals;

import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseCalsApiIntegrationTest {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");

  private static final String configFile = "config/test-cals-api.yml";

  @ClassRule
  public static final DropwizardAppRule<CalsApiConfiguration> appRule =
      new DropwizardAppRule<CalsApiConfiguration>(
          CalsApiApplication.class, ResourceHelpers.resourceFilePath(configFile)) {

        @Override
        public Client client() {
          Client client = super.client();
          if (((JerseyClient) client).isClosed()) {
            client = clientBuilder().build();
          }
          return client;
        }
      };

  @Rule
  public RestClientTestRule<CalsApiConfiguration> clientTestRule
      = new RestClientTestRule<>(appRule);

  protected static DatabaseHelper getFasFfaDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getFasFfaDataSourceFactory());
  }

  protected static DatabaseHelper getFasDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getFasDataSourceFactory());
  }

  protected static DatabaseHelper getCmsDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getXaCmsDataSourceFactory());
  }

  protected static DatabaseHelper getCmsRsDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getCmsRsDataSourceFactory());
  }

  protected static DatabaseHelper getCalsnsDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getCalsnsDataSourceFactory());
  }

  protected static DatabaseHelper getLisDatabaseHelper() {
    return createDatabaseHelper(appRule.getConfiguration().getLisDataSourceFactory());
  }

  private static DatabaseHelper createDatabaseHelper(DataSourceFactory dataSourceFactory) {
    return new DatabaseHelper(
        dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
  }

  public static void setUpFas() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getFasDatabaseHelper().runScript("liquibase/fas_database_master.xml");
    }
  }

  public static void setUpFasFfa() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getFasFfaDatabaseHelper().runScript(
          "liquibase/fas_ffa_database_master.xml");
    }
  }

  public static void setUpLis() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getLisDatabaseHelper().runScript("liquibase/lis_database_master.xml");
    }
  }

  public static void setUpCms() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getCmsDatabaseHelper().runScript("liquibase/cwscms_database_master.xml");
    }
  }

  public static void setUpCmsRs() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getCmsRsDatabaseHelper().runScript("liquibase/cwscmsrs_database_master.xml");
    }
  }

  public static void setUpCalsns() throws Exception {
    if (!TestModeUtils.isIntegrationTestsMode()) {
      getCalsnsDatabaseHelper().runScript("liquibase/calsns_database_master_for_tests.xml");
    }
  }

  public String transformDTOtoJSON(Object o) throws Exception {
    return clientTestRule.getMapper().writeValueAsString(o);
  }

  public static LocalDateTime toLocalDateTime(String dateTime) {
    return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
  }

  public static String getDataFromRawResponse(Response response) throws IOException {
    return IOUtils.toString((InputStream) response.getEntity(), "UTF-8");
  }

  @After
  public void tearDown() throws Exception {
  }

}
