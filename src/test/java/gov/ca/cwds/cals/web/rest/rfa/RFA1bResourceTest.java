package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1bResourceTest extends BaseExternalEntityApiTest<RFA1bFormDTO> {

  @Override
  protected BaseExternalEntityApiHelper<RFA1bFormDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<RFA1bFormDTO> configuration =
        new TestExternalEntityConfiguration<RFA1bFormDTO>(
            clientTestRule, RFA1bFormDTO.class, API.RFA_1B_FORMS) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1b-form.json";
          }

          @Override
          public GenericType<CollectionDTO<RFA1bFormDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<RFA1bFormDTO>>() {
            };
          }

          @Override
          public void modifyEntity(RFA1bFormDTO rfa1bFormDTO) {
            rfa1bFormDTO.setApplicantFirstName("Petya");
          }
        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
