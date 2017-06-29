package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.cals.web.rest.rfa.configuration.InternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */

public class ResidenceResourceTest extends BaseInternalEntityApiTest<Residence> {

  @Override
  protected BaseInternalEntityApiHelper<Residence> getInternalEntityApiHelper() {

    InternalEntityConfiguration<Residence> configuration =

        new InternalEntityConfiguration<Residence>(
            clientTestRule, Residence.class, API.RESIDENCE) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-residence-request.json";
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
    }

}