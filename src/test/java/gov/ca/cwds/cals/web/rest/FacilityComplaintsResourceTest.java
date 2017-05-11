package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FacilityComplaintsResourceTest extends BaseCalsApiIntegrationTest {

    public static final String FACILITY_ID = "1";
    public static final String COMPLAINT_ID = "1";

    @Test
    public void testComplaints() throws Exception {
        assertGetAllFacilityComplaints();
        assertGetFacilityComplaint();
    }

    private void assertGetAllFacilityComplaints() {
        String restUrl = getServerUrl() + FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.TEXT_PLAIN);
        String responseText = invocation.get(String.class);

        String fixture = fixture("fixtures/complaints-response.json");
        assertThat(responseText).isEqualTo(fixture);
    }

    private void assertGetFacilityComplaint() {
        String restUrl =
                getServerUrl() + FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS + "/" + COMPLAINT_ID;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.TEXT_PLAIN);
        String responseText = invocation.get(String.class);

        String fixture = fixture("fixtures/complaint-response.json");
        assertThat(responseText).isEqualTo(fixture);
    }

}