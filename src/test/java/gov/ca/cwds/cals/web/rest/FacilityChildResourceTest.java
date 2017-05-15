package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildResourceTest  extends BaseCalsApiIntegrationTest {

    @Test
    public void testGetFacilityChildren() throws Exception {
        String pathInfo = Constants.API.FACILITY_CHILD;
//        String pathInfo = Constants.API.FACILITY_CHILD + "/1";
        pathInfo = pathInfo.replace("{facility_id}", "MH12AE541");
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityChildDTO)).isEqualTo(fixture);
    }

    @Test
    public void testGetFacilityChild() throws Exception {
        String pathInfo = Constants.API.FACILITY_CHILD;
//        String pathInfo = Constants.API.FACILITY_CHILD + "/1";
        pathInfo = pathInfo.replace("{facility_id}", "MH12AE541") + "/SQkWeH80Mf";
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityChildDTO)).isEqualTo(fixture);
    }
}

