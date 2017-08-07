package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseRFAIntegrationTest extends BaseCalsApiIntegrationTest {

  protected RFAHelper rfaHelper = new RFAHelper(clientTestRule);

}
