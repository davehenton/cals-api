package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

public class RFA1aOtherAdultsResourceTest extends
    BaseExternalEntityApiTest<OtherAdultDTO> {

  @Override
  protected BaseExternalEntityApiHelper<OtherAdultDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<OtherAdultDTO> configuration =

        new TestExternalEntityConfiguration<OtherAdultDTO>(
            clientTestRule,
            OtherAdultDTO.class,
            API.RFA_1A_OTHER_ADULTS) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-other-adults.json";
          }

          @Override
          public GenericType<CollectionDTO<OtherAdultDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<OtherAdultDTO>>() {
            };
          }

          @Override
          public void modifyEntity(OtherAdultDTO entity) {
            entity.setFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
