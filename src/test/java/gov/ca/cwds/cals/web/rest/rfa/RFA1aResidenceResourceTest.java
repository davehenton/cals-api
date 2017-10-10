package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aResidenceResourceTest extends BaseInternalEntityApiTest<ResidenceDTO> {

  public static final String RESIDENCE_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-residence-request.json");

  @Override
  protected BaseInternalEntityApiHelper<ResidenceDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ResidenceDTO> configuration =

        new TestInternalEntityConfiguration<ResidenceDTO>(
            clientTestRule, ResidenceDTO.class, API.RFA_1A_RESIDENCE) {

          @Override
          protected String getFixture() {
            return RESIDENCE_FIXTURE;
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
    }

}