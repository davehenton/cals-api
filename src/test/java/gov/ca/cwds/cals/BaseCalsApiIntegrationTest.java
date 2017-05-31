package gov.ca.cwds.cals;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.rest.api.Response;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.Map;

import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CWDS CALS API Team
 */

public abstract class BaseCalsApiIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<CalsApiConfiguration> appRule = new DropwizardAppRule<CalsApiConfiguration>(
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

    @Rule
    public RestClientTestRule clientTestRule = new RestClientTestRule(appRule);

    protected static DatabaseHelper getFasDatabaseHelper() {
        DataSourceFactory dataSourceFactory = appRule.getConfiguration().getFasDataSourceFactory();
        return new DatabaseHelper(dataSourceFactory.getUrl(),
                dataSourceFactory.getUser(), dataSourceFactory.getPassword());
    }

    protected static DatabaseHelper getCmsDatabaseHelper() {
        DataSourceFactory dataSourceFactory = appRule.getConfiguration().getCmsDataSourceFactory();
        return new DatabaseHelper(dataSourceFactory.getUrl(),
                dataSourceFactory.getUser(), dataSourceFactory.getPassword());
    }

    protected static DatabaseHelper getLisDatabaseHelper() {
        DataSourceFactory dataSourceFactory = appRule.getConfiguration().getLisDataSourceFactory();
        return new DatabaseHelper(dataSourceFactory.getUrl(),
                dataSourceFactory.getUser(), dataSourceFactory.getPassword());
    }

    public static void setUpFas() throws Exception {
        getFasDatabaseHelper().runScript("liquibase/fas/fas-create-schema-ddl.xml");
        getFasDatabaseHelper().runScript("liquibase/fas/fas-ddl-master.xml", FAS);
    }

    public static void setUpLis() throws Exception {
        getLisDatabaseHelper().runScript("liquibase/lis/lis-create-schema-ddl.xml");
        getLisDatabaseHelper().runScript("liquibase/lis/lis-ddl-master.xml", LIS);
    }

    public static void setUpCms() throws Exception {
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-ddl-master.xml");
    }

    @After
    public void tearDown() throws Exception {

    }

    protected void assertEqualsResponse(String fixture, Response response) throws IOException {
        ObjectMapper om = new ObjectMapper();
        String actual = clientTestRule.getMapper().writeValueAsString(response);
        Map<String, String> map1 = (Map<String, String>) om.readValue(fixture, Map.class);
        Map<String, String> map2 = (Map<String, String>) om.readValue(actual, Map.class);
        assertThat(map1).isEqualTo(map2);
    }
}