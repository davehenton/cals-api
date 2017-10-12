package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aApplicantsHistoryResourceTest extends BaseInternalEntityApiTest<ApplicantsHistoryDTO> {

  public static final String APPLICANTS_HISTORY_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-applicants-history.json");

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsHistoryDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ApplicantsHistoryDTO> configuration =

        new TestInternalEntityConfiguration<ApplicantsHistoryDTO>(
            clientTestRule, ApplicantsHistoryDTO.class, API.RFA_1A_APPLICANTS_HISTORY) {

          @Override
          protected String getFixture() {
            return APPLICANTS_HISTORY_FIXTURE;
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

}