package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aChildDesiredResourceTest extends BaseInternalEntityApiTest<ChildDesiredDTO> {

  public static final String CHILD_DESIRED_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-child-desired.json");

  @Override
  protected BaseInternalEntityApiHelper<ChildDesiredDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ChildDesiredDTO> configuration =

        new TestInternalEntityConfiguration<ChildDesiredDTO>(
            clientTestRule, ChildDesiredDTO.class, Constants.API.RFA_1A_CHILD_DESIRED) {

          @Override
          protected String getFixture() {
            return CHILD_DESIRED_FIXTURE;
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

}
