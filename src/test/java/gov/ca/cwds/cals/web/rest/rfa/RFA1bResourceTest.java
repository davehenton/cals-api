package gov.ca.cwds.cals.web.rest.rfa;

import static org.assertj.core.api.Assertions.assertThat;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.service.mapper.RFA1bFormMapper;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.rfa.helper.ApplicantHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormAHelper;
import gov.ca.cwds.cals.web.rest.rfa.helper.OtherAdultsHelper;
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

  public static final String RFA1B_FORM_FIXTURE = FixtureHelpers
      .fixture("fixtures/rfa/rfa-1b-form-meta-data.json");
  public static final String RFA1B_FORM_BLANK_FIXTURE = FixtureHelpers
      .fixture("fixtures/rfa/rfa-1b-form-blank-data.json");

  private static class ApplicantAwareRFA1bEntityApiHelper extends
      BaseExternalEntityApiHelper<RFA1bFormDTO> {

    protected ApplicantHelper applicantHelper;

    public ApplicantAwareRFA1bEntityApiHelper(RestClientTestRule clientTestRule,
        TestExternalEntityConfiguration<RFA1bFormDTO> configuration,
        FormAHelper helper) {
      super(clientTestRule, configuration, helper);
      applicantHelper = new ApplicantHelper(clientTestRule);
    }

    @Override
    public RFA1bFormDTO createEntity(RFA1aFormDTO form) throws Exception {
      ApplicantDTO applicantDTO = applicantHelper
          .getFirstExistedOrPostNewApplicant(form.getId(), applicantHelper.getValidApplicant());

      TestExternalEntityConfiguration<RFA1bFormDTO> configuration = getConfiguration();
      WebTarget target =
          getClientTestRule().target(
              API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath() + "/"
                  + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());
      RFA1bFormDTO entity = configuration.createEntity();
      return target.request(MediaType.APPLICATION_JSON).post(
          Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
    }

    public void getEntitiesByFormId() throws Exception {
    }
  }

  private static class OtherAdultAwareRFA1bEntityApiHelper extends
      BaseExternalEntityApiHelper<RFA1bFormDTO> {

    protected ApplicantHelper applicantHelper;
    protected OtherAdultsHelper otherAdultsHelper;

    public OtherAdultAwareRFA1bEntityApiHelper(RestClientTestRule clientTestRule,
        TestExternalEntityConfiguration<RFA1bFormDTO> configuration,
        FormAHelper helper) {
      super(clientTestRule, configuration, helper);
      applicantHelper = new ApplicantHelper(clientTestRule);
      otherAdultsHelper = new OtherAdultsHelper(clientTestRule);
    }

    @Override
    public RFA1bFormDTO createEntity(RFA1aFormDTO form) throws Exception {
      ApplicantDTO applicantDTO = applicantHelper
          .getFirstExistedOrPostNewApplicant(form.getId(), applicantHelper.getValidApplicant());
      OtherAdultDTO otherAdultDTO = otherAdultsHelper.createOtherAdult(form.getId(), applicantDTO);
      TestExternalEntityConfiguration<RFA1bFormDTO> configuration = getConfiguration();
      WebTarget target =
          getClientTestRule().target(
              API.RFA_1A_FORMS + "/" + form.getId() + "/" + configuration.getApiPath() + "/"
                  + API.RFA_1A_OTHER_ADULTS + "/" + otherAdultDTO.getId());
      RFA1bFormDTO entity = configuration.createEntity();
      return target.request(MediaType.APPLICATION_JSON).post(
          Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), configuration.getEntityClass());
    }

    public void getEntitiesByFormId() throws Exception {
    }
  }

  private static class RFA1bBaseEntityConfiguration extends
      TestExternalEntityConfiguration<RFA1bFormDTO> {

    public RFA1bBaseEntityConfiguration(RestClientTestRule clientTestRule,
        Class<RFA1bFormDTO> entityClass, String apiPath) {
      super(clientTestRule, entityClass, apiPath);
    }

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
  }

  @Override
  protected BaseExternalEntityApiHelper<RFA1bFormDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<RFA1bFormDTO> configuration =
        new RFA1bBaseEntityConfiguration(clientTestRule, RFA1bFormDTO.class, API.RFA_1B_FORMS);
    return new ApplicantAwareRFA1bEntityApiHelper(clientTestRule, configuration, formAHelper);
  }

  @Test
  public void getRFA1bFormApplicantTest() throws Exception {
    RFA1aFormDTO form1a = formAHelper.createRFA1aForm();
    RFA1bFormDTO created = getExternalEntityApiHelper().createEntity(form1a);
    ApplicantDTO applicantDTO = applicantHelper
        .getFirstExistedOrPostNewApplicant(form1a.getId(), applicantHelper.getValidApplicant());
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form1a.getId() + "/" + getExternalEntityApiHelper()
                .getConfiguration().getApiPath() + "/"
                + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());
    RFA1bFormDTO found = target.request().get(RFA1bFormDTO.class);
    assertThat(found).isEqualTo(created);
  }

  @Test
  public void populateApplicantAwareDataToRFA1bFormTest() throws Exception {
    RFA1aFormDTO form1a = formAHelper.createRFA1aForm();
    RFA1bBaseEntityConfiguration configuration = new RFA1bBaseEntityConfiguration(
        clientTestRule, RFA1bFormDTO.class, API.RFA_1B_FORMS) {
      @Override
      protected String getFixture() {
        return RFA1B_FORM_BLANK_FIXTURE;
      }
    };

    ResidenceDTO residenceDTO = residenceHelper
        .putResidence(form1a.getId(), residenceHelper.buildResidenceDTO());

    ApplicantAwareRFA1bEntityApiHelper helper = new ApplicantAwareRFA1bEntityApiHelper(
        clientTestRule,
        configuration, formAHelper);
    RFA1bFormDTO created = helper.createEntity(form1a);
    ApplicantDTO applicantDTO = applicantHelper
        .getFirstExistedOrPostNewApplicant(form1a.getId(), applicantHelper.getValidApplicant());

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form1a.getId() + "/" + configuration.getApiPath() + "/"
                + API.RFA_1A_APPLICANTS + "/" + applicantDTO.getId());

    RFA1bFormDTO form1b = target.request().get(RFA1bFormDTO.class);

    assertThat(form1b.getApplicationCounty()).isEqualTo(form1a.getApplicationCounty());
    assertThat(form1b.getApplicantNamePrefix()).isEqualTo(applicantDTO.getNamePrefix());
    assertThat(form1b.getApplicantFirstName()).isEqualTo(applicantDTO.getFirstName());
    assertThat(form1b.getApplicantMiddleName()).isEqualTo(applicantDTO.getMiddleName());
    assertThat(form1b.getApplicantLastName()).isEqualTo(applicantDTO.getLastName());
    assertThat(form1b.getApplicantNameSuffix()).isEqualTo(applicantDTO.getNameSuffix());
    assertThat(form1b.getDateOfBirth()).isEqualTo(applicantDTO.getDateOfBirth());
    assertThat(form1b.getDriverLicense()).isEqualTo(applicantDTO.getDriverLicenseNumber());
    assertThat(form1b.getDriverLicenseState()).isEqualTo(applicantDTO.getDriverLicenseState());

    form1a = formAHelper.getRFA1aForm(form1a.getId());

    RFAAddressDTO addressDTOFromForm1a = form1a.getResidence().getAddresses().stream().filter(
        dto -> dto.getType() != null && dto.getType().getId()
            .equals(RFA1bFormMapper.RESIDENTIAL_ADDRESS_TYPE_ID)).findFirst().get();

    assertThat(form1b.getResidenceAddress()).isEqualTo(addressDTOFromForm1a);
  }

  @Test
  public void populateOtherAdultAwareDataToRFA1bFormTest() throws Exception {
    RFA1aFormDTO form1a = formAHelper.createRFA1aForm();
    RFA1bBaseEntityConfiguration configuration = new RFA1bBaseEntityConfiguration(
        clientTestRule, RFA1bFormDTO.class, API.RFA_1B_FORMS) {
      @Override
      protected String getFixture() {
        return RFA1B_FORM_BLANK_FIXTURE;
      }
    };

    OtherAdultAwareRFA1bEntityApiHelper helper = new OtherAdultAwareRFA1bEntityApiHelper(
        clientTestRule,
        configuration, formAHelper);

    RFA1bFormDTO createdRfa1bFormDTO = helper.createEntity(form1a);

    // Find first Other adult should be only one in the RFA1aForm at this moment
    form1a = formAHelper.getRFA1aForm(form1a.getId());
    OtherAdultDTO otherAdultDTO = form1a.getOtherAdults().get(0);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form1a.getId() + "/" + configuration.getApiPath() + "/"
                + API.RFA_1A_OTHER_ADULTS + "/" + otherAdultDTO.getId());

    RFA1bFormDTO form1b = target.request().get(RFA1bFormDTO.class);

    assertThat(form1b.getApplicantNamePrefix()).isEqualTo(otherAdultDTO.getNamePrefix());
    assertThat(form1b.getApplicantFirstName()).isEqualTo(otherAdultDTO.getFirstName());
    assertThat(form1b.getApplicantMiddleName()).isEqualTo(otherAdultDTO.getMiddleName());
    assertThat(form1b.getApplicantLastName()).isEqualTo(otherAdultDTO.getLastName());
    assertThat(form1b.getApplicantNameSuffix()).isEqualTo(otherAdultDTO.getNameSuffix());
    assertThat(form1b.getDateOfBirth()).isEqualTo(otherAdultDTO.getDateOfBirth());
  }

}
