package gov.ca.cwds.cals.web.rest.rfa;

import static org.assertj.core.api.Assertions.assertThat;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1bResourceTest extends BaseExternalEntityApiTest<RFA1bFormDTO> {

  public static final String RFA1B_FORM_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1b-form.json");

  @Override
  protected BaseExternalEntityApiHelper<RFA1bFormDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<RFA1bFormDTO> configuration =
        new TestExternalEntityConfiguration<RFA1bFormDTO>(clientTestRule, RFA1bFormDTO.class,
            API.RFA_1B_FORMS) {

          @Override
          protected String getFixture() {
            return RFA1B_FORM_FIXTURE;
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

    return new BaseExternalEntityApiHelper<RFA1bFormDTO>(clientTestRule, configuration,
        formAHelper) {
      @Override
      public RFA1bFormDTO createEntity(RFA1aFormDTO form) throws Exception {
        ApplicantDTO applicantDTO = applicantHelper
            .getFirstExistedOrPostNewApplicant(form.getId(), applicantHelper.getValidApplicant());
        WebTarget target =
            clientTestRule.target(
                API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath() + "/"
                    + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());
        RFA1bFormDTO entity = configuration.createEntity();
        return target.request(MediaType.APPLICATION_JSON).post(
            Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
      }

      public void getEntitiesByFormId() throws Exception {

      }

    };

  }

  @Test
  public void getRFA1bFormApplicantTest() throws Exception {
    RFA1aFormDTO form1a = formAHelper.createRFA1aForm();
    RFA1bFormDTO created = getExternalEntityApiHelper().createEntity(form1a);
    ApplicantDTO applicantDTO = applicantHelper
        .getFirstExistedOrPostNewApplicant(form1a.getId(), applicantHelper.getValidApplicant());
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form1a.getId() + "/" + getExternalEntityApiHelper().getConfiguration().getApiPath() + "/"
                + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());
    RFA1bFormDTO found = target.request().get(RFA1bFormDTO.class);
    assertThat(found).isEqualTo(created);
  }

}
