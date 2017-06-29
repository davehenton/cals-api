package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationship;
import gov.ca.cwds.cals.web.rest.rfa.configuration.InternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aRelationshipResourceTest extends
    BaseInternalEntityApiTest<ApplicantsRelationship> {

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsRelationship> getInternalEntityApiHelper() {

    InternalEntityConfiguration<ApplicantsRelationship> configuration =

        new InternalEntityConfiguration<ApplicantsRelationship>(
            clientTestRule, ApplicantsRelationship.class, API.APPLICANTS_RELATIONSHIP) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-applicants-relationship.json";
          }

        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
