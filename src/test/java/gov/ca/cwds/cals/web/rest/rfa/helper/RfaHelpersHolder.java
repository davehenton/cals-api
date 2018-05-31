package gov.ca.cwds.cals.web.rest.rfa.helper;

import gov.ca.cwds.cals.CalsApiConfiguration;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;

/**
 * Created by Alexander Serbin on 5/31/2018.
 */
public final class RfaHelpersHolder {

  private ApplicantHelper applicantHelper;
  private FormAHelper formAHelper;
  private FormBHelper formBHelper;
  private FormCHelper formCHelper;
  private MinorChildHelper minorChildHelper;
  private OtherAdultsHelper otherAdultHelper;
  private ResidenceHelper residenceHelper;
  private AdoptionHistoryHelper adoptionHistoryHelper;
  private ApplicantsHistoryHelper applicantsHistoryHelper;
  private StatusHelper statusHelper;

  public RfaHelpersHolder(RestClientTestRule<CalsApiConfiguration> clientTestRule) {
    applicantHelper = new ApplicantHelper(clientTestRule);
    formAHelper = new FormAHelper(clientTestRule);
    formBHelper = new FormBHelper(clientTestRule);
    formCHelper = new FormCHelper(clientTestRule);
    minorChildHelper = new MinorChildHelper(clientTestRule);
    otherAdultHelper = new OtherAdultsHelper(clientTestRule);
    residenceHelper = new ResidenceHelper(clientTestRule);
    adoptionHistoryHelper = new AdoptionHistoryHelper(clientTestRule);
    applicantsHistoryHelper = new ApplicantsHistoryHelper(
        clientTestRule);
    statusHelper = new StatusHelper(clientTestRule);
  }

  public ApplicantHelper getApplicantHelper() {
    return applicantHelper;
  }

  public FormAHelper getFormAHelper() {
    return formAHelper;
  }

  public FormBHelper getFormBHelper() {
    return formBHelper;
  }

  public FormCHelper getFormCHelper() {
    return formCHelper;
  }

  public MinorChildHelper getMinorChildHelper() {
    return minorChildHelper;
  }

  public OtherAdultsHelper getOtherAdultHelper() {
    return otherAdultHelper;
  }

  public ResidenceHelper getResidenceHelper() {
    return residenceHelper;
  }

  public AdoptionHistoryHelper getAdoptionHistoryHelper() {
    return adoptionHistoryHelper;
  }

  public ApplicantsHistoryHelper getApplicantsHistoryHelper() {
    return applicantsHistoryHelper;
  }

  public StatusHelper getStatusHelper() {
    return statusHelper;
  }
}
