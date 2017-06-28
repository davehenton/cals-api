package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends BaseExternalEntityApiTest<MinorChild> {

  @Override
  protected BaseExternalEntityApiHelper<MinorChild> getExternalEntityApiHelper() {
    BaseExternalEntityConfiguration<MinorChild> configuration =

        new BaseExternalEntityConfiguration<MinorChild>(
            MinorChild.class,
            new GenericType<CollectionDTO<MinorChild>>() {
            },
            API.RFA_1A_MINOR_CHILDREN) {

          @Override
          protected void updateEntity(MinorChild entity) {
            entity.setOtherRelativeFirstName("FIRST_NAME");
          }

          @Override
          protected MinorChild createEntity() {
            return new MinorChild();
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
