package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityTypesDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CWDS CALS API Team
 */
public class FacilityTypesResourceTest extends BaseCalsApiIntegrationTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpLis();
        getLisDatabaseHelper().runScript("liquibase/lis/dml/lis-data.xml", LIS);
    }

    @Test
    public void testGetFacilityTypes() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.DICTIONARY + "/" + Constants.API.FACILITY_TYPES);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityTypesDTO facilityDTOs = invocation.get(FacilityTypesDTO.class);

        String fixture = fixture("fixtures/facility-types-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityDTOs)).isEqualTo(fixture);
    }
}