package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1aApplicantResourceTest extends
    BaseExternalEntityApiTest<ApplicantDTO> {

  @Override
  protected BaseExternalEntityApiHelper<ApplicantDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<ApplicantDTO> configuration =
        new TestExternalEntityConfiguration<ApplicantDTO>(
            clientTestRule, ApplicantDTO.class, API.RFA_1A_APPLICANTS) {

          @Override
          protected String getCreateFixture() {
            //return "fixtures/rfa/rfa-1a-applicant.json";
            return "fixtures/rfa/stub.json";
          }

          @Override
          public GenericType<CollectionDTO<ApplicantDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<ApplicantDTO>>() {
            };
          }

          @Override
          public void modifyEntity(ApplicantDTO entity) {
            entity.setFirstName("testFirstName");
          }
        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
