package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantResourceTest extends BaseExternalEntityApiTest<Applicant> {

  @Override
  protected BaseExternalEntityApiHelper<Applicant> getExternalEntityApiHelper() {
    BaseExternalEntityConfiguration<Applicant> configuration =

        new BaseExternalEntityConfiguration<Applicant>(
            Applicant.class,
            new GenericType<CollectionDTO<Applicant>>() {
            },
            API.RFA_1A_APPLICANTS) {

          @Override
          protected void updateEntity(Applicant entity) {
            entity.setFirstName("testFirstName");
          }

          @Override
          protected Applicant createEntity() {
            return new Applicant();
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
