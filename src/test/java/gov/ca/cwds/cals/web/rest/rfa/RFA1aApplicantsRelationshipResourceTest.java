package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationshipDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsRelationshipResourceTest extends BaseInternalEntityApiTest<ApplicantsRelationshipDTO> {

  public static final String APPLICANTS_RELATIONSHIP_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-applicants-relationship.json");

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsRelationshipDTO> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ApplicantsRelationshipDTO> configuration =

        new TestInternalEntityConfiguration<ApplicantsRelationshipDTO>(
            clientTestRule, ApplicantsRelationshipDTO.class, API.RFA_1A_APPLICANTS_RELATIONSHIP) {

          @Override
          protected String getFixture() {
            return APPLICANTS_RELATIONSHIP_FIXTURE;
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

}
