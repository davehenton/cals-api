package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.LIS;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

    private static final String FACILITY_ID = "193600001";
    private static final String WRONG_FACILITY_ID = "1";

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpLis();
        getLisDatabaseHelper().runScript("liquibase/lis/dml/lis-data.xml", LIS);
    }

    @Test
    public void testGetFacilityById() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + FACILITY_ID);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

    @Test
    public void testWrongFasilityId() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + WRONG_FACILITY_ID);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        Response response = invocation.get();
        assertEquals(404, response.getStatus());
    }


}