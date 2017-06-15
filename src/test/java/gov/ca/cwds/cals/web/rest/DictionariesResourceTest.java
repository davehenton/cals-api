package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE_PATH;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.Dictionary;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
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
  private static final String FIXTURES_GENDER_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-gender-type-response.json";
  private static final String FIXTURES_NAME_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-name-type-response.json";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  @Test
  public void getDictionaryAgeGroupTypeTest() throws Exception {
    WebTarget target =
        clientTestRule.target(Constants.API.DICTIONARIES + "/" + AGE_GROUP_TYPE_PATH);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    invocation.get(new GenericType<CollectionDTO<Dictionary>>() {});
    CollectionDTO<AgeGroupType> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<AgeGroupType>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }

  @Test
  public void getDictionaryLanguageTypeTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + LANGUAGE_TYPE_PATH);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<LanguageType> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<LanguageType>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }

  @Test
  public void getDictionaryGenderTypeTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + GENDER_TYPE_PATH);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<GenderType> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<GenderType>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_GENDER_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }

  @Test
  public void getDictionaryNameTypeTest() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + NAME_TYPE_PATH);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<NameType> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<NameType>>() {});
    assertNotNull(collectionDTO);
    String fixture = fixture(FIXTURES_NAME_TYPE_RESPONSE_JSON);
    assertEqualsResponse(fixture, collectionDTO);
  }
}
