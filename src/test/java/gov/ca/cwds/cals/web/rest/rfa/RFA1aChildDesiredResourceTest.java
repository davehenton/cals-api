package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ChildDesired;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aChildDesiredResourceTest extends
    BaseInternalEntityApiTest<ChildDesired> {

  @Override
  protected BaseInternalEntityApiHelper<ChildDesired> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ChildDesired> configuration =

        new TestInternalEntityConfiguration<ChildDesired>(
            clientTestRule, ChildDesired.class, Constants.API.CHILD_DESIRED) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-child-desired.json";
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
