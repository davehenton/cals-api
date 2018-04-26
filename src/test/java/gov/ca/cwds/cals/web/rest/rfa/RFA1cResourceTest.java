package gov.ca.cwds.cals.web.rest.rfa;


import static org.assertj.core.api.Java6Assertions.assertThat;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.RestClientTestRule;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import gov.ca.cwds.cals.web.rest.rfa.helper.FormAHelper;
import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1cResourceTest extends BaseExternalEntityApiTest<RFA1cFormDTO> {

  public static final String RFA1C_FORM_FIXTURE = FixtureHelpers
      .fixture("fixtures/rfa/rfa-1c-form-meta-data.json");
  public static final String RFA1C_FORM_BLANK_FIXTURE = FixtureHelpers
      .fixture("fixtures/rfa/rfa-1c-form-blank-data.json");


  private static class RFA1cTestExternalEntityConfiguration extends
      TestExternalEntityConfiguration<RFA1cFormDTO> {

    RFA1cTestExternalEntityConfiguration(RestClientTestRule clientTestRule) {
      super(clientTestRule, RFA1cFormDTO.class, API.RFA_1C_FORMS);
    }

    @Override
    protected String getFixture() {
      return RFA1C_FORM_FIXTURE;
    }

    @Override
    public GenericType<CollectionDTO<RFA1cFormDTO>> getCollectionDTOGenericType() {
      return new GenericType<CollectionDTO<RFA1cFormDTO>>() {
      };
    }

    @Override
    public void modifyEntity(RFA1cFormDTO rfa1cFormDTO) {
      CountyType applicationCounty = new CountyType();
      applicationCounty.setId(35L);
      applicationCounty.setValue("San Benito");
      rfa1cFormDTO.setApplicationCounty(applicationCounty);
    }
  }

  private static class TestHelper extends BaseExternalEntityApiHelper<RFA1cFormDTO> {

    public TestHelper(RestClientTestRule clientTestRule,
        TestExternalEntityConfiguration<RFA1cFormDTO> configuration,
        FormAHelper helper) {
      super(clientTestRule, configuration, helper);
    }

    @Override
    public RFA1cFormDTO createEntity(RFA1aFormDTO form) throws Exception {
      RFA1cFormDTO rfa1cFormDTO = super.createEntity(form);
      try {
        super.createEntity(form);
        Assert.fail();
      } catch (BadRequestException e) {
        //ok
      }
      return rfa1cFormDTO;
    }

    @Override
    public void getEntitiesByFormId() throws Exception {

    }

  }

  @Override
  protected BaseExternalEntityApiHelper<RFA1cFormDTO> getExternalEntityApiHelper() {

    return new TestHelper(clientTestRule, new RFA1cTestExternalEntityConfiguration(clientTestRule),
        formAHelper);
  }

  @Test
  public void populateDataToRFA1cFormTest() throws Exception {
    RFA1aFormDTO form1a = formAHelper.createRFA1aForm();
    RFA1cTestExternalEntityConfiguration configuration = new RFA1cTestExternalEntityConfiguration(
        clientTestRule) {
      @Override
      protected String getFixture() {
        return RFA1C_FORM_BLANK_FIXTURE;
      }
    };

    TestHelper helper = new TestHelper(clientTestRule, configuration, formAHelper);

    RFA1cFormDTO created = helper.createEntity(form1a);

    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form1a.getId() + "/" + configuration.getApiPath() + "/"
                + created.getId());

    RFA1cFormDTO form1c = target.request().get(RFA1cFormDTO.class);

    assertThat(form1c.getApplicationCounty()).isEqualTo(form1a.getApplicationCounty());

  }
}
