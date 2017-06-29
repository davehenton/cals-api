package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.ExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantResourceTest extends BaseExternalEntityApiTest<Applicant> {

  @Override
  protected BaseExternalEntityApiHelper<Applicant> getExternalEntityApiHelper() {
    ExternalEntityConfiguration<Applicant> configuration =

        new ExternalEntityConfiguration<Applicant>(
            clientTestRule,
            Applicant.class,
            new GenericType<CollectionDTO<Applicant>>() {
            },
            API.RFA_1A_APPLICANTS) {

          @Override
          protected String getCreateFixture() {
            //return "fixtures/rfa/rfa-1a-applicant.json";
            return "fixtures/rfa/temp-rfa-1a-applicant.json";
          }

          @Override
          public void updateEntity(Applicant entity) {
            entity.setFirstName("testFirstName");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
