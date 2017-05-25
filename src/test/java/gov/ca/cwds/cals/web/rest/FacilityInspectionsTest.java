package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.FAS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CWDS CALS API Team
 */
@Ignore
public class FacilityInspectionsTest extends BaseCalsApiIntegrationTest {

    public static final Integer FACILITY_NUMBER = 193600027;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpFas();
        getFasDatabaseHelper().runScript("liquibase/fas/inspections_test_data.xml", FAS);
    }

    @Test
    public void getAllFacilityInspectionsTest() throws JsonProcessingException {
        WebTarget target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionDTO inspectionDTO = invocation.get(FacilityInspectionDTO.class);
        assertThat(inspectionDTO != null);
        assertThat(inspectionDTO.getPocs() != null);
        assertThat(inspectionDTO.getPocs().size() == 2);
    }

}
