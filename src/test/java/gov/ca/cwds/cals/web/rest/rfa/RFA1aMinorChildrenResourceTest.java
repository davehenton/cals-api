package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends
    BaseExternalEntityApiTest<MinorChildDTO> {

  @Override
  protected BaseExternalEntityApiHelper<MinorChildDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<MinorChildDTO> configuration =

        new TestExternalEntityConfiguration<MinorChildDTO>(
            clientTestRule,
            MinorChildDTO.class,
            API.RFA_1A_MINOR_CHILDREN) {

          @Override
          protected String getCreateFixture() {
            //return "fixtures/rfa/rfa-1a-minor_children.json";
            return "fixtures/rfa/stub.json";
          }

          @Override
          public GenericType<CollectionDTO<MinorChildDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<MinorChildDTO>>() {
            };
          }

          @Override
          public void modifyEntity(MinorChildDTO entity) {
            entity.setOtherRelativeFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
