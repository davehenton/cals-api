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

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

    private static final String LICENSE_NUMBER = "193600001";
    private static final String WRONG_LICENSE_NUMBER = "1";

    private static final String FACILITY_ID = "E6tloOO0Ql";

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpFas();
        getFasDatabaseHelper().runScript("liquibase/fas/fas-data.xml", "fas");

        setUpCms();
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-dml-master.xml", "cms");
    }

    @Test
    public void testGetFacilityByLicenseNumber() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + LICENSE_NUMBER);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityDTO)).isEqualTo(fixture);
    }

    @Test
    public void testWrongFasilityId() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + WRONG_LICENSE_NUMBER);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        Response response = invocation.get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetFacilityByFacilityId() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + FACILITY_ID);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        assertNotNull(facilityDTO);
    }

}