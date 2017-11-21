package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class LIC198bResourceTest extends BaseExternalEntityApiTest<LIC198bFormDTO> {

  public static final String LIC198B_FORM_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/lic-198b-form.json");

  @Override
  protected BaseExternalEntityApiHelper<LIC198bFormDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<LIC198bFormDTO> configuration =
        new TestExternalEntityConfiguration<LIC198bFormDTO>(clientTestRule, LIC198bFormDTO.class,
            API.LIC_198B_FORMS) {

          @Override
          protected String getFixture() {
            return LIC198B_FORM_FIXTURE;
          }

          @Override
          public GenericType<CollectionDTO<LIC198bFormDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<LIC198bFormDTO>>() {
            };
          }

          @Override
          public void modifyEntity(LIC198bFormDTO lic198bFormDTO) {
            lic198bFormDTO.getIdentifyingData().getPersonName().setFirstName("Petya");
          }
        };

    return new BaseExternalEntityApiHelper<LIC198bFormDTO>(clientTestRule, configuration,
        formAHelper) {
      @Override
      protected LIC198bFormDTO createEntity(RFA1aFormDTO form) throws IOException {
        ApplicantDTO applicantDTO = applicantHelper
            .getFirstExistedOrPostNewApplicant(form.getId(), applicantHelper.getValidApplicant());
        WebTarget target =
            clientTestRule.target(
                API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath() + "/"
                    + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());
        LIC198bFormDTO entity = configuration.createEntity();
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
