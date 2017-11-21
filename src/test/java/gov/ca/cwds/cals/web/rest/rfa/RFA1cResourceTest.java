package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;
import java.io.IOException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.GenericType;
import org.junit.Assert;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1cResourceTest extends BaseExternalEntityApiTest<RFA1cFormDTO> {

  public final String FRA1C_FORM_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1c-form.json");

  @Override
  protected BaseExternalEntityApiHelper<RFA1cFormDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<RFA1cFormDTO> configuration =

        new TestExternalEntityConfiguration<RFA1cFormDTO>(
            clientTestRule,
            RFA1cFormDTO.class,
            API.RFA_1C_FORMS) {

          @Override
          protected String getFixture() {
            return FRA1C_FORM_FIXTURE;
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

        };

    return new BaseExternalEntityApiHelper<RFA1cFormDTO>(clientTestRule, configuration, formAHelper) {
      @Override
      protected RFA1cFormDTO createEntity(RFA1aFormDTO form) throws Exception {
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
    };

  }
}
