package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertNull;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.AdoptionHistoryDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aAdoptionHistoryResourceTest extends
    BaseInternalEntityApiTest<AdoptionHistoryDTO> {

  @Override
  protected BaseInternalEntityApiHelper<AdoptionHistoryDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<AdoptionHistoryDTO> configuration =
        new TestInternalEntityConfiguration<AdoptionHistoryDTO>(
            clientTestRule, AdoptionHistoryDTO.class, API.RFA_1A_ADOPTION_HISTORY) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-adoption-history.json";
          }
        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

  @Test
  public void checkSetNullIsPossible() throws Exception {

    RFA1aFormDTO form = formAHelper.createRFA1aForm();
    AdoptionHistoryDTO adoptionHistoryDTO =
        adoptionHistoryHelper.getAdoptionHistory("fixtures/rfa/rfa-1a-adoption-history-small.json");
    adoptionHistoryHelper.putAdoptionHistory(form.getId(), adoptionHistoryDTO);
    adoptionHistoryDTO = adoptionHistoryHelper.getAdoptionHistory(form.getId());
    assertNull(adoptionHistoryDTO.getFosterCareLicensesQ1().isWasPreviouslyLicensed());
    assertNull(adoptionHistoryDTO.getApplicationsForAdoptionQ2().isHaveAppliedForAdoption());
    assertNull(adoptionHistoryDTO.getFacilityOperationLicensesQ3().isWasPreviouslyLicensed());
    assertNull(adoptionHistoryDTO.getEmploymentInFacilitiesQ4().isWasEmployedOrVolunteered());
    assertNull(adoptionHistoryDTO.getDenialHistoryQ5().isHadDenials());
    assertNull(adoptionHistoryDTO.getSuspensionRevocationHistoryQ6().isHadSuspensionsRevocations());
    assertNull(adoptionHistoryDTO.isWasSubjectForExclusionOrderQ7());
  }

}
