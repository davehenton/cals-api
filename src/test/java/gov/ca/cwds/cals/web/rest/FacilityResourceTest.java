package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.junit.Ignore;
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

        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + Constants.API.FACILITIES + "/" + FACILITY_ID;
        WebTarget target = client.target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(mapper.writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

    @Test
    @Ignore
    public void testGetFacilityChildren() throws Exception {
        Client client = appRule.client();
        ObjectMapper mapper = appRule.getObjectMapper();
        client.register(new JacksonJsonProvider(mapper));

        String pathInfo = Constants.API.FACILITY_CHILD;
//        String pathInfo = Constants.API.FACILITY_CHILD + "/1";
        pathInfo = pathInfo.replace("{facility_id}", "MH12AE541");
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = client.target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(mapper.writeValueAsString(facilityChildDTO)).isEqualTo(fixture);
    }

    @Test
    @Ignore
    public void testGetFacilityChild() throws Exception {
        Client client = appRule.client();
        ObjectMapper mapper = appRule.getObjectMapper();
        client.register(new JacksonJsonProvider(mapper));

        String pathInfo = Constants.API.FACILITY_CHILD;
//        String pathInfo = Constants.API.FACILITY_CHILD + "/1";
        pathInfo = pathInfo.replace("{facility_id}", "MH12AE541") + "/SQkWeH80Mf";
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = client.target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(mapper.writeValueAsString(facilityChildDTO)).isEqualTo(fixture);
    }

}