package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildrenDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends
    BaseExternalEntityApiTest<MinorChild, MinorChildrenDTO> {

  @Override
  protected BaseExternalEntityApiHelper<MinorChild, MinorChildrenDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<MinorChild, MinorChildrenDTO> configuration =

        new TestExternalEntityConfiguration<MinorChild, MinorChildrenDTO>(
            clientTestRule,
            MinorChild.class,
            MinorChildrenDTO.class,
            API.RFA_1A_MINOR_CHILDREN) {

          @Override
          protected String getCreateFixture() {
            //return "fixtures/rfa/rfa-1a-minor_children.json";
            return "fixtures/rfa/stub.json";
          }

          @Override
          public void modifyEntity(MinorChild entity) {
            entity.setOtherRelativeFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
