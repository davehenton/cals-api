package gov.ca.cwds.cals;

import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.ws.rs.client.Client;
import java.net.URI;

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

    public static void setUpFas() throws Exception {
        getFasDatabaseHelper().runScript("liquibase/fas/drop_fas_schema.xml");
        getFasDatabaseHelper().runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_schema.xml");
        getFasDatabaseHelper().runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_structure.xml", "fas");
        getFasDatabaseHelper().runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_constraints.xml", "fas");
        getFasDatabaseHelper().runScript("gov/ca/cwds/cals/model/fas/liquibase/complaints_structure.xml", "fas");
    }

    public static void setUpCms() throws Exception {
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-ddl-master.xml");
    }

    @After
    public void tearDown() throws Exception {

    }
}