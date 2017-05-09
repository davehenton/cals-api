package gov.ca.cwds.cals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author CWDS CALS API Team
 */

@Ignore
public class ApplicationTest {
    private static DatabaseHelper fasDatabaseHelper;

    @ClassRule
    public static final DropwizardAppRule<CalsApiConfiguration> appRule =
            new DropwizardAppRule<CalsApiConfiguration>(CalsApiApplication.class, ResourceHelpers.resourceFilePath("config/test-cals-api.yml"));

    @BeforeClass
    public static void setUp() throws Exception {
        DataSourceFactory fasDataSourceFactory = appRule.getConfiguration().getFasDataSourceFactory();
        fasDatabaseHelper = new DatabaseHelper(
                fasDataSourceFactory.getUrl(),
                fasDataSourceFactory.getUser(),
                fasDataSourceFactory.getPassword());

        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_schema.xml");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_structure.xml", "fas");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_constraints.xml", "fas");
    }

    @Test
    public void testGetFacilityById() throws Exception {
        fasDatabaseHelper.runScript("liquibase/fas-data.xml", "fas");

        Client client = appRule.client();
        ObjectMapper mapper = appRule.getObjectMapper();
        client.register(new JacksonJsonProvider(mapper));

        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + Constants.API.FACILITIES + "/1";
        WebTarget target = client.target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-1-response.json");
        assertThat(mapper.writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

    @After
    public void tearDown() throws Exception {

    }
}