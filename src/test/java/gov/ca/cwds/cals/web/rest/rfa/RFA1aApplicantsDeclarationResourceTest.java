package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDeclarationDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsDeclarationResourceTest extends BaseInternalEntityApiTest<ApplicantsDeclarationDTO> {

  public static final String APPLICANTS_DECLARATION_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-applicants-declaration.json");

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsDeclarationDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ApplicantsDeclarationDTO> configuration =
        new TestInternalEntityConfiguration<ApplicantsDeclarationDTO>(
            clientTestRule, ApplicantsDeclarationDTO.class, API.RFA_1A_APPLICANTS_DECLARATION) {

          @Override
          protected String getFixture() {
            return APPLICANTS_DECLARATION_FIXTURE;
          }
        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }
}
