package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.ExternalEntityConfiguration;
import javax.ws.rs.core.GenericType;
import org.junit.Ignore;

/**
 * @author CWDS CALS API Team
 */

@Ignore
public class RFA1aOtherAdultsResourceTest extends BaseExternalEntityApiTest<OtherAdult> {

  @Override
  protected BaseExternalEntityApiHelper<OtherAdult> getExternalEntityApiHelper() {

    ExternalEntityConfiguration<OtherAdult> configuration =

        new ExternalEntityConfiguration<OtherAdult>(
            clientTestRule,
            OtherAdult.class,
            new GenericType<CollectionDTO<OtherAdult>>() {
            },
            API.RFA_1A_OTHER_ADULTS) {

          @Override
          protected String getCreateFixture() {
            return "fixtures/rfa/rfa-1a-other-adults.json";
          }

          @Override
          public void modifyEntity(OtherAdult entity) {
            entity.setFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }

}
