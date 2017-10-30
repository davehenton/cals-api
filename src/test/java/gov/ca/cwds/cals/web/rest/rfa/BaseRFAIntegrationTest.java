package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.web.rest.rfa.helper.AdoptionHistoryHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ApplicantHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ApplicantsHistoryHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormAHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormBHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.MinorChildHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.OtherAdultsHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ResidenceHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.StatusHelper;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseRFAIntegrationTest extends BaseCalsApiIntegrationTest {

  protected ApplicantHelper applicantHelper = new ApplicantHelper(clientTestRule);
  protected FormAHelper formAHelper = new FormAHelper(clientTestRule);
  protected FormBHelper formBHelper = new FormBHelper(clientTestRule);
  protected MinorChildHelper minorChildHelper = new MinorChildHelper(clientTestRule);
  protected OtherAdultsHelper otherAdultHelper = new OtherAdultsHelper(clientTestRule);
  protected ResidenceHelper residenceHelper = new ResidenceHelper(clientTestRule);
  protected AdoptionHistoryHelper adoptionHistoryHelper = new AdoptionHistoryHelper(clientTestRule);
  protected ApplicantsHistoryHelper applicantsHistoryHelper = new ApplicantsHistoryHelper(
      clientTestRule);
  protected StatusHelper statusHelper = new StatusHelper(clientTestRule);

}
