package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.ExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends BaseExternalEntityApiTest<MinorChild> {

  @Override
  protected BaseExternalEntityApiHelper<MinorChild> getExternalEntityApiHelper() {
    ExternalEntityConfiguration<MinorChild> configuration =

        new ExternalEntityConfiguration<MinorChild>(
            clientTestRule,
            MinorChild.class,
            new GenericType<CollectionDTO<MinorChild>>() {
            },
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
