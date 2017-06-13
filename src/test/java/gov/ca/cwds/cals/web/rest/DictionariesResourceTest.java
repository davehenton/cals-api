package gov.ca.cwds.cals.web.rest;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.Dictionary;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS CALS API Team */
public class DictionariesResourceTest extends BaseCalsApiIntegrationTest {

  private static final String FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-age-group-type-response.json";
  private static final String FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-language-type-response.json";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getDictionaryAgeGroupTypeTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/age-group-type");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

    invocation.get(new GenericType<CollectionDTO<Dictionary>>() {});

    CollectionDTO<Dictionary> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<Dictionary>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }

  @Test
  public void getDictionaryLanguageTypeTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/language-type");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<Dictionary> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<Dictionary>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }
}
