package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenResourceTest extends
    BaseExternalEntityApiTest<MinorChildDTO> {

  public static final String FIXTURES_RFA_RFA_1A_MINOR_CHILDREN_JSON = "fixtures/rfa/rfa-1a-minor-children.json";

  @Override
  protected BaseExternalEntityApiHelper<MinorChildDTO> getExternalEntityApiHelper() {
    TestExternalEntityConfiguration<MinorChildDTO> configuration =

        new TestExternalEntityConfiguration<MinorChildDTO>(
            clientTestRule,
            MinorChildDTO.class,
            API.RFA_1A_MINOR_CHILDREN) {

          @Override
          protected String getCreateFixture() {
            return FIXTURES_RFA_RFA_1A_MINOR_CHILDREN_JSON;
          }

          @Override
          public GenericType<CollectionDTO<MinorChildDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<MinorChildDTO>>() {
            };
          }

          @Override
          public void modifyEntity(MinorChildDTO entity) {
            entity.setOtherRelativeFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration, rfaHelper);
  }

  @Test
  public void checkEmptyChildRelatedToTest() throws Exception {
    String outputFixture = "{\n"
        + "  \"id\" : %s\n"
        + "}";
    checkInputOutput("{}", outputFixture);
  }

  @Test
  public void checkNullChildRelatedToTest() throws Exception {
    String inputFixture = "{\"relationship_to_applicants\": null}";
    String outputFixture = "{\n"
        + "  \"id\" : %s\n"
        + "}";
    checkInputOutput(inputFixture, outputFixture);
  }

  @Test
  public void restoreUnselectedValueBackTest() throws Exception {
    String inputFixture = "{\"relationship_to_applicants\": []}";
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();
    WebTarget target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN);

    MinorChildDTO minorChildDTO = clientTestRule.getMapper()
        .readValue(inputFixture, MinorChildDTO.class);

    MinorChildDTO postMinorChildDTO = target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(minorChildDTO, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);

    String removeArrayFixture = "{\n"
        + "  \"id\" : %s\n"
        + ", \"relationship_to_applicants\":null"
        + "}";
    minorChildDTO = clientTestRule.getMapper().readValue(
        String.format(removeArrayFixture, postMinorChildDTO.getId()), MinorChildDTO.class);
    target =
        clientTestRule.target(
            API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN + "/"
                + postMinorChildDTO.getId());
    MinorChildDTO putMinorChildDTO =
        target.request(MediaType.APPLICATION_JSON).put(
            Entity.entity(minorChildDTO, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);

    String finalFixture = "{\n"
        + "  \"id\" : %s\n"
        + "}";

    JSONAssert.assertEquals(
        String.format(finalFixture, putMinorChildDTO.getId()),
        clientTestRule.getMapper().writeValueAsString(putMinorChildDTO), JSONCompareMode.STRICT);
  }


  private void checkInputOutput(String inputFixture, String expectedOutputFixture) throws Exception {
    RFA1aFormDTO form = rfaHelper.createRFA1aForm();
    WebTarget target = clientTestRule.target(
        API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN);

    MinorChildDTO minorChildDTO = clientTestRule.getMapper()
        .readValue(inputFixture, MinorChildDTO.class);

    MinorChildDTO postMinorChildDTO = target.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(minorChildDTO, MediaType.APPLICATION_JSON_TYPE), MinorChildDTO.class);

    target = clientTestRule.target(
        API.RFA_1A_FORMS + "/" + form.getId() + "/" + API.RFA_1A_MINOR_CHILDREN + "/"
            + postMinorChildDTO.getId());

    MinorChildDTO getMinorChildDTO = target.request(MediaType.APPLICATION_JSON)
        .get(MinorChildDTO.class);
    JSONAssert.assertEquals(
        String.format(expectedOutputFixture, getMinorChildDTO.getId()),
        clientTestRule.getMapper().writeValueAsString(getMinorChildDTO), JSONCompareMode.STRICT);
  }

}
