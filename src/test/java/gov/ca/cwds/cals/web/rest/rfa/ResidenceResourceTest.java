package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import static gov.ca.cwds.cals.Constants.API.RESIDENCE;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author CWDS CALS API Team
 */
public class ResidenceResourceTest extends BaseCalsApiIntegrationTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpCalsns();
    }

    @Test
    public void testGetResidenceApplicationNotFound() throws Exception {
        WebTarget target = clientTestRule.target(RFA_1A_FORMS + "/-1/" + RESIDENCE);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        try {
            invocation.get(Residence.class);
            fail();
        } catch (NotFoundException e) {
            assertEquals(404, e.getResponse().getStatus());
            assertEquals("HTTP 404 Not Found", e.getMessage());
        }
    }

    @Test
    public void testPutResidenceApplicationNotFound() throws Exception {
        WebTarget target = clientTestRule.target(RFA_1A_FORMS + "/-1/" + RESIDENCE);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        String fixture = fixture("fixtures/rfa/rfa-1a-residence-request.json");
        Residence residence = clientTestRule.getMapper().readValue(fixture, Residence.class);
        try {
            invocation.put(Entity.entity(residence, MediaType.APPLICATION_JSON), Residence.class);
            fail();
        } catch (NotFoundException e) {
            assertEquals(404, e.getResponse().getStatus());
            assertEquals("HTTP 404 Not Found", e.getMessage());
        }
    }

    @Test
    public void testPutAndGetResidence() throws Exception {
        RFA1aFormDTO form = RFAHelper.createForm(clientTestRule);

        WebTarget residenceTarget = clientTestRule.target(RFA_1A_FORMS + "/" + form.getId() + "/" + RESIDENCE);
        Invocation.Builder residenceInvocation = residenceTarget.request(MediaType.APPLICATION_JSON);
        String fixture = fixture("fixtures/rfa/rfa-1a-residence-request.json");
        Residence residenceRequest = clientTestRule.getMapper().readValue(fixture, Residence.class);
        Residence residenceResponse = residenceInvocation.put(Entity.entity(residenceRequest, MediaType.APPLICATION_JSON), Residence.class);

        String expectedFixture = fixture("fixtures/rfa/rfa-1a-residence-response.json");
        Residence expectedResidenceResponse = clientTestRule.getMapper().readValue(expectedFixture, Residence.class);
        assertEquals(expectedResidenceResponse, residenceResponse);
    }
}