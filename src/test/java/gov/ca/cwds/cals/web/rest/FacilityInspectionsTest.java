package gov.ca.cwds.cals.web.rest;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import org.junit.BeforeClass;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionsTest extends BaseCalsApiIntegrationTest {


    @BeforeClass
    public static void beforeClass() throws Exception {
        setUpFas();
        getFasDatabaseHelper().runScript("liquibase/fas/inspections_data.xml", "fas");
    }

}
