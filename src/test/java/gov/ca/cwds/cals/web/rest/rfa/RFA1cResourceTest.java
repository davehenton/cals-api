package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.core.GenericType;

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

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

}
