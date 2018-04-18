package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.web.rest.rfa.helper.*;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseRFAIntegrationTest extends BaseCalsApiIntegrationTest {

  protected ApplicantHelper applicantHelper = new ApplicantHelper(clientTestRule);
  protected FormAHelper formAHelper = new FormAHelper(clientTestRule);
  protected FormBHelper formBHelper = new FormBHelper(clientTestRule);
  protected FormCHelper formCHelper = new FormCHelper(clientTestRule);
  protected MinorChildHelper minorChildHelper = new MinorChildHelper(clientTestRule);
  protected OtherAdultsHelper otherAdultHelper = new OtherAdultsHelper(clientTestRule);
  protected ResidenceHelper residenceHelper = new ResidenceHelper(clientTestRule);
  protected AdoptionHistoryHelper adoptionHistoryHelper = new AdoptionHistoryHelper(clientTestRule);
  protected ApplicantsHistoryHelper applicantsHistoryHelper = new ApplicantsHistoryHelper(
      clientTestRule);
  protected StatusHelper statusHelper = new StatusHelper(clientTestRule);

}
