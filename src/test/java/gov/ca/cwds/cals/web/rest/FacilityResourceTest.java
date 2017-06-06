package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class FacilityResourceTest extends BaseCalsApiIntegrationTest {

    private static final String LICENSE_NUMBER = "193600110";
    private static final String WRONG_LICENSE_NUMBER = "1";

    private static final String FACILITY_ID = "E6tloOO0Ql";

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpLis();
        getLisDatabaseHelper().runScript("liquibase/lis/dml/lis-data.xml", LIS);

        setUpCms();
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-dml-master.xml", CMS);

        setUpFas();
        getFasDatabaseHelper().runScript("liquibase/fas/dml/lpa-information-data.xml", FAS);
    }

    @Ignore
    @Test
    public void testGetFacilityByLicenseNumber() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.FACILITIES + "/" + LICENSE_NUMBER);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityDTO facilityDTO = invocation.get(FacilityDTO.class);

        String fixture = fixture("fixtures/facility-by-license-number-response.json");
        assertEqualsResponse(fixture, facilityDTO);
    }

    @Ignore
    @Test
    public void testWrongFacilityId() throws Exception {
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

        String fixture = fixture("fixtures/facility-by-id-response.json");
        assertEqualsResponse(fixture, facilityDTO);
    }

}