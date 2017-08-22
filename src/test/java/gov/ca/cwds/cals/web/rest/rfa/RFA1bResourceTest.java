package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1bResourceTest extends BaseExternalEntityApiTest<RFA1bFormDTO> {

  public static final String RFA1B_FORM_FIXTURE_PATH = "fixtures/rfa/rfa-1b-form.json";

  @Override
  protected BaseExternalEntityApiHelper<RFA1bFormDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<RFA1bFormDTO> configuration =
        new TestExternalEntityConfiguration<RFA1bFormDTO>(clientTestRule, RFA1bFormDTO.class,
            API.RFA_1B_FORMS) {

          @Override
          protected String getCreateFixture() {
            return RFA1B_FORM_FIXTURE_PATH;
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

    return new BaseExternalEntityApiHelper<RFA1bFormDTO>(clientTestRule, configuration, rfaHelper) {
      @Override
      protected RFA1bFormDTO createEntity(RFA1aFormDTO form) throws IOException {
        WebTarget target =
            clientTestRule.target(
                API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath() + "/"
                    + API.RFA_1A_APPLICANTS + "/" + 1);
        RFA1bFormDTO entity = configuration.createEntity();
        return target.request(MediaType.APPLICATION_JSON).post(
            Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
      }
    };
  }

  @Override
  public void createEntity() throws Exception {
    super.createEntity();
  }
}
