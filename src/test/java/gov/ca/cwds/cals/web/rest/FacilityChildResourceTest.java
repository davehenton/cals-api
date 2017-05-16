package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.CHILDREN;
import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.API.PATH_PARAMS.FACILITY_ID;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildResourceTest  extends BaseCalsApiIntegrationTest {

    @Test
    @Ignore
    public void testGetFacilityChildren() throws Exception {
        String pathInfo = FACILITIES + "/{"+ FACILITY_ID + "}/" + CHILDREN;
//        String pathInfo = Constants.API.FACILITY_CHILD + "/1";
        pathInfo = pathInfo.replace("{facility_id}", "MH12AE541");
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildrenDTO facilityChildDTO = invocation.get(FacilityChildrenDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityChildDTO)).isEqualTo(fixture);
    }

    @Test
    @Ignore
    public void testGetFacilityChild() throws Exception {
        String pathInfo = FACILITIES + "/{"+ FACILITY_ID + "}/" + CHILDREN;
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

