package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsDeclaration;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsDeclarationResourceTest
    extends BaseInternalEntityApiTest<ApplicantsDeclaration> {

  @Override
  protected BaseInternalEntityApiHelper<ApplicantsDeclaration> getInternalEntityApiHelper() {

    TestInternalEntityConfiguration<ApplicantsDeclaration> configuration =
        new TestInternalEntityConfiguration<ApplicantsDeclaration>(
            clientTestRule, ApplicantsDeclaration.class, API.RFA_1A_APPLICANTS_DECLARATION) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-applicants-declaration.json";
          }
        };

    return new BaseInternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
