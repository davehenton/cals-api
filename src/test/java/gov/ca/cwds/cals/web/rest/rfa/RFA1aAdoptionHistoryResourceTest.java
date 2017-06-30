package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.AdoptionHistory;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aAdoptionHistoryResourceTest extends BaseInternalEntityApiTest<AdoptionHistory> {

  @Override
  protected BaseInternalEntityApiHelper<AdoptionHistory> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<AdoptionHistory> configuration =
        new TestInternalEntityConfiguration<AdoptionHistory>(
            clientTestRule, AdoptionHistory.class, API.RFA_1A_ADOPTION_HISTORY) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-adoption-history.json";
          }
        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
