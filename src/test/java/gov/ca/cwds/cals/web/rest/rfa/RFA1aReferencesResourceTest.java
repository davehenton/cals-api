package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ReferencesDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aReferencesResourceTest extends BaseInternalEntityApiTest<ReferencesDTO> {

  public static final String REFERENCES_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-references.json");

  @Override
  protected BaseInternalEntityApiHelper<ReferencesDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ReferencesDTO> configuration =
        new TestInternalEntityConfiguration<ReferencesDTO>(
            clientTestRule, ReferencesDTO.class, API.RFA_1A_REFERENCES) {

          @Override
          protected String getFixture() {
            return REFERENCES_FIXTURE;
          }
        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }
}
