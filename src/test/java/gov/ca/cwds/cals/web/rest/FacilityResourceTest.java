package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

    private static final String FACILITY_ID = "193600001";

    @Test
    public void testGetFacilityById() throws Exception {
        fasDatabaseHelper.runScript("liquibase/fas-data.xml", "fas");

        Client client = appRule.client();
        ObjectMapper mapper = appRule.getObjectMapper();
        client.register(new JacksonJsonProvider(mapper));

        String restUrl = getServerUri() + Constants.API.FACILITIES + "/" + FACILITY_ID;
        WebTarget target = client.target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(mapper.writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

}