package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.web.rest.rfa.helper.AdoptionHistoryHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ApplicantHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ApplicantsHistoryHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormAHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormBHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormCHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.MinorChildHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.OtherAdultsHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.ResidenceHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.RfaHelpersHolder;
import gov.ca.cwds.cals.web.rest.rfa.helper.StatusHelper;

/**
 * @author CWDS CALS API Team
 */
public abstract class BaseRFAIntegrationTest extends BaseCalsApiIntegrationTest {

  protected RfaHelpersHolder rfaHelpersHolder = new RfaHelpersHolder(clientTestRule);

  protected ApplicantHelper applicantHelper = rfaHelpersHolder.getApplicantHelper();
  protected FormAHelper formAHelper = rfaHelpersHolder.getFormAHelper();
  protected FormBHelper formBHelper = rfaHelpersHolder.getFormBHelper();
  protected FormCHelper formCHelper = rfaHelpersHolder.getFormCHelper();
  protected MinorChildHelper minorChildHelper = rfaHelpersHolder.getMinorChildHelper();
  protected OtherAdultsHelper otherAdultHelper = rfaHelpersHolder.getOtherAdultHelper();
  protected ResidenceHelper residenceHelper = rfaHelpersHolder.getResidenceHelper();
  protected AdoptionHistoryHelper adoptionHistoryHelper =
      rfaHelpersHolder.getAdoptionHistoryHelper();
  protected ApplicantsHistoryHelper applicantsHistoryHelper =
      rfaHelpersHolder.getApplicantsHistoryHelper();
  protected StatusHelper statusHelper = rfaHelpersHolder.getStatusHelper();


}
