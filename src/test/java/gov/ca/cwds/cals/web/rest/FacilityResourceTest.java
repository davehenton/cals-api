package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.net.URLEncoder;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

    private static final String FACILITY_ID = "193600001";

    //@Ignore
    @Test
    public void testGetFacilityById() throws Exception {
        fasDatabaseHelper.runScript("liquibase/fas-data.xml", "fas");

        String restUrl = URLEncoder.encode(getServerUrl() + Constants.API.FACILITIES + "/" + FACILITY_ID);
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

}