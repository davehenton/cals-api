package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpCms();
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-dml-master.xml", "cms");
    }

    @Test
    public void testGetFacilityChildren() throws Exception {
        String pathInfo = FACILITIES + "/{"+ FACILITY_ID + "}/" + CHILDREN;
        pathInfo = pathInfo.replace("{"+ FACILITY_ID + "}", "336400295");
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildrenDTO facilityChildDTO = invocation.get(FacilityChildrenDTO.class);

        String fixture = fixture("fixtures/facility-children-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityChildDTO)).isEqualToIgnoringWhitespace(fixture);
    }

    @Test
    public void testGetFacilityChild() throws Exception {
        String pathInfo = FACILITIES + "/{"+ FACILITY_ID + "}/" + CHILDREN;
        pathInfo = pathInfo.replace("{"+ FACILITY_ID + "}", "336400295") + "/AazXkWY06s";
        String restUrl = appRule.getEnvironment().getApplicationContext().getServer().getURI() + pathInfo;
        WebTarget target = clientTestRule.getClient().target(restUrl);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityChildDTO facilityChildDTO = invocation.get(FacilityChildDTO.class);

        String fixture = fixture("fixtures/facility-child-response.json");
        assertThat(clientTestRule.getMapper().writeValueAsString(facilityChildDTO)).isEqualToIgnoringWhitespace(fixture);
    }
}

