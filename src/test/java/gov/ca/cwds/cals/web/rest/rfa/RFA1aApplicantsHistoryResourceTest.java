package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsHistory;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aApplicantsHistoryResourceTest extends
    BaseInternalEntityApiTest<ApplicantsHistory> {

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsHistory> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ApplicantsHistory> configuration =

        new TestInternalEntityConfiguration<ApplicantsHistory>(
            clientTestRule, ApplicantsHistory.class, API.APPLICANTS_HISTORY) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-applicants-history.json";
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
  }

}