package gov.ca.cwds.cals.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionsDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static gov.ca.cwds.cals.Constants.API.FACILITIES;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author CWDS CALS API Team
 */

public class FacilityInspectionsTest extends BaseCalsApiIntegrationTest {

    public static final Integer FACILITY_NUMBER = 11400218;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpFas();
        getFasDatabaseHelper().runScript("liquibase/fas/dml/inspections-data.xml", FAS);
    }

    @Test
    public void getAllFacilityInspectionsTest() throws JsonProcessingException {
        WebTarget target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionsDTO inspectionsDTO = invocation.get(FacilityInspectionsDTO.class);
        assertTrue(inspectionsDTO != null);
        assertTrue(inspectionsDTO.getInspections() != null);
        assertTrue(inspectionsDTO.getInspections().size() == 3);


        FacilityInspectionDTO inspectionDTO0 = inspectionsDTO.getInspections().get(0);
        String inspectionId = inspectionDTO0.getId();
        target = clientTestRule.target(FACILITIES + "/" + FACILITY_NUMBER + "/" + Constants.API.INSPECTIONS + "/" + inspectionId);
        invocation = target.request(MediaType.APPLICATION_JSON);
        FacilityInspectionDTO inspectionDTO = invocation.get(FacilityInspectionDTO.class);

        assertEquals(inspectionDTO0, inspectionDTO);
    }

}
