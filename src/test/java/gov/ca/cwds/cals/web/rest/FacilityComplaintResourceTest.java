package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ComplaintsDTO;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FacilityComplaintResourceTest extends BaseCalsApiIntegrationTest {

    public static final String FACILITY_ID = "1";
    public static final String COMPLAINT_ID = "1";

    @Test
    public void testComplaints() throws Exception {
        assertGetAllFacilityComplaints();
        assertGetFacilityComplaint();
    }

    private void assertGetAllFacilityComplaints() throws JsonProcessingException {
        String restUrl = getServerUrl() + FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        ComplaintsDTO complaintsDTO = invocation.get(ComplaintsDTO.class);

        String fixture = fixture("fixtures/complaints-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(complaintsDTO)).isEqualTo(fixture);
    }

    private void assertGetFacilityComplaint() throws JsonProcessingException {
        String restUrl =
                getServerUrl() + FACILITIES + "/" + FACILITY_ID + "/" + Constants.API.COMPLAINTS + "/" + COMPLAINT_ID;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        ComplaintDTO complaintDTO = invocation.get(ComplaintDTO.class);

        String fixture = fixture("fixtures/complaint-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(complaintDTO)).isEqualTo(fixture);
    }

}