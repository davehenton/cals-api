package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import javax.ws.rs.core.GenericType;
import org.junit.Ignore;

/**
 * @author CWDS CALS API Team
 */

@Ignore
public class RFA1aOtherAdultsResourceTest extends BaseExternalEntityApiTest<OtherAdult> {

  @Override
  protected BaseExternalEntityApiHelper<OtherAdult> getExternalEntityApiHelper() {

    BaseExternalEntityConfiguration<OtherAdult> configuration =

        new BaseExternalEntityConfiguration<OtherAdult>(
            OtherAdult.class,
            new GenericType<CollectionDTO<OtherAdult>>() {
            },
            API.RFA_1A_OTHER_ADULTS) {

          @Override
          protected void updateEntity(OtherAdult entity) {
            entity.setFirstName("FIRST_NAME");
          }

          @Override
          protected OtherAdult createEntity() {
            return new OtherAdult();
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
