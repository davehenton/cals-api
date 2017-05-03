package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.rest.JerseyGuiceRule;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 *  UnitTest for Facility resource
 *
 *
 *  @author CALS API Team
 */
public class FacilityResourceTest {

    private static final String ROOT_RESOURCE = "/" + FACILITIES;
    private static final String FOUND_RESOURCE = ROOT_RESOURCE + "/1";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @ClassRule
    public static JerseyGuiceRule rule = new JerseyGuiceRule();

    private static ResourceDelegate resourceDelegate = mock(ResourceDelegate.class);

    @ClassRule
    public static final ResourceTestRule inMemoryResource =
            ResourceTestRule.builder().addResource(new FacilityResource(resourceDelegate)).build();

    @SuppressWarnings("javadoc")
    @Before
    public void setup() throws Exception {
        Mockito.reset(resourceDelegate);
    }

    @Test
    @Ignore
    public void get() throws Exception {
/*

        FacilityDTO facility = new FacilityDTO();
        facility.setId(1l);
        facility.setAssignedWorker("Assigned Worker");
        facility.setCapacity(10);
        facility.setDistrictOffice("District office");
//        facility.setLiceneeType("License Type");
//        facility.setLicenseEffectiveDate("2000-01-01");
        facility.setLicenseeName("Licensee Name");
//        facility.setLicenseNumber("#123456");
        facility.setLicenseStatus("License Status");
        facility.setName("Name");
//        facility.setOriginalApplicationRecievedDate("2000-01-01");
        facility.setType("Type");

        Response response = inMemoryResource.client().target(FOUND_RESOURCE).request()
                .accept(MediaType.APPLICATION_JSON).get();
        
        verify(resourceDelegate).get("1");
*/
    }

}