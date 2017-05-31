package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.CountiesDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.COUNTIES;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author CWDS CALS API Team
 */
public class CountiesResourceTest extends BaseCalsApiIntegrationTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpCms();
        getCmsDatabaseHelper().runScript("liquibase/cms/cms-dml-master.xml", "cms");
    }

    @Test
    public void testGetCounties() throws Exception {
        WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + COUNTIES);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        CountiesDTO countiesDTO = invocation.get(CountiesDTO.class);

        assertNotNull(countiesDTO);
        assertTrue(countiesDTO.getCounties().size() > 0);
    }

}
