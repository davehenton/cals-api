package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.EDUCATION_LEVEL_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.ETHNICITY_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.RACE_TYPE_PATH;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.io.IOException;
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
  private static final String FIXTURES_EDUCATION_LEVEL_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-education-level-type-response.json";
  private static final String FIXTURES_ETHNICITY_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-ethnicity-type-response.json";
  private static final String FIXTURES_RACE_TYPE_RESPONSE_JSON =
      "fixtures/dictionary-race-type-response.json";


  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  private <T extends BaseDictionary> void baseDictionaryTest(String path, String fixturePath, Class<T> clazz) throws IOException {
    WebTarget target = clientTestRule
        .target(Constants.API.DICTIONARIES + "/" + path);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<T> collectionDTO =
        invocation.get(new GenericType<CollectionDTO<T>>() {
        });
    assertNotNull(collectionDTO);
    String fixture = fixture(fixturePath);
    assertEqualsResponse(fixture, collectionDTO);
  }

  @Test
  public void getDictionaryRaceTypeTest() throws Exception {
    baseDictionaryTest(RACE_TYPE_PATH, FIXTURES_RACE_TYPE_RESPONSE_JSON, RaceType.class);
  }

  @Test
  public void getDictionaryAgeGroupTypeTest() throws Exception {
    baseDictionaryTest(AGE_GROUP_TYPE_PATH, FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON, AgeGroupType.class);
  }

  @Test
  public void getDictionaryLanguageTypeTest() throws Exception {
    baseDictionaryTest(LANGUAGE_TYPE_PATH, FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON, LanguageType.class);
  }

  @Test
  public void getDictionaryGenderTypeTest() throws Exception {
    baseDictionaryTest(GENDER_TYPE_PATH, FIXTURES_GENDER_TYPE_RESPONSE_JSON, GenderType.class);
  }

  @Test
  public void getDictionaryNameTypeTest() throws Exception {
    baseDictionaryTest(NAME_TYPE_PATH, FIXTURES_NAME_TYPE_RESPONSE_JSON, NameType.class);
  }

  @Test
  public void getDictionaryEducationLevelTypeTest() throws Exception {
    baseDictionaryTest(EDUCATION_LEVEL_TYPE_PATH, FIXTURES_EDUCATION_LEVEL_TYPE_RESPONSE_JSON, EducationLevelType.class);
  }

  @Test
  public void getDictionaryEthnicityTypeTest() throws Exception {
    baseDictionaryTest(ETHNICITY_TYPE_PATH, FIXTURES_ETHNICITY_TYPE_RESPONSE_JSON, EthnicityType.class);
  }

}


