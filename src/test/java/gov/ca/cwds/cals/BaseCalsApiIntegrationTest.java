package gov.ca.cwds.cals;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import javax.ws.rs.client.Client;
import java.net.URI;

/**
 * @author CWDS CALS API Team
 */

public class BaseCalsApiIntegrationTest {

    protected static DatabaseHelper fasDatabaseHelper;

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

    @BeforeClass
    public static void setUp() throws Exception {
        DataSourceFactory fasDataSourceFactory = appRule.getConfiguration()
                .getFasDataSourceFactory();
        fasDatabaseHelper = new DatabaseHelper(
                fasDataSourceFactory.getUrl(),
                fasDataSourceFactory.getUser(),
                fasDataSourceFactory.getPassword());

        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_schema.xml");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_structure.xml", "fas");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_constraints.xml", "fas");
    }

    protected static URI getServerUri() {
        return appRule.getEnvironment().getApplicationContext().getServer().getURI();
    }

    @After
    public void tearDown() throws Exception {

    }
}