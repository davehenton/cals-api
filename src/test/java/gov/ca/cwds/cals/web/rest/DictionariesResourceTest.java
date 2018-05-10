package gov.ca.cwds.cals.web.rest;

import static gov.ca.cwds.cals.Constants.DictionaryType.ADDRESS_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.AGE_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.APPLICANT_RELATIONSHIP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.COUNTY_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.EDUCATION_LEVEL_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.ETHNICITY_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.FACILITY_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.GENDER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.INCOME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LANGUAGE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.LICENSE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.MARRIAGE_TERMINATION_REASON_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_PREFIX_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.NAME_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.PHONE_NUMBER_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.RELATIONSHIP_TO_APPLICANT_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.RESIDENCE_OWNERSHIP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.SCHOOL_GRADE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.SIBLING_GROUP_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.STATE_TYPE_PATH;
import static gov.ca.cwds.cals.Constants.DictionaryType.SUFFIX_TYPE_PATH;
import static gov.ca.cwds.cals.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertNotNull;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AddressType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ApplicantRelationshipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.IncomeType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LicenseType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.MarriageTerminationReasonType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SchoolGradeType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SiblingGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.utils.TestModeUtils;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class DictionariesResourceTest extends BaseCalsApiIntegrationTest {

  private static final String BASE_DICTIONARY_PATH = "fixtures/dictionary/";

  private static final String FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "age-group-type-response.json";
  private static final String FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "language-type-response.json";
  private static final String FIXTURES_GENDER_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "gender-type-response.json";
  private static final String FIXTURES_NAME_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "name-type-response.json";
  private static final String FIXTURES_EDUCATION_LEVEL_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "education-level-type-response.json";
  private static final String FIXTURES_ETHNICITY_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "ethnicity-type-response.json";
  private static final String FIXTURES_FACILITY_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "facility-type-response.json";
  private static final String FIXTURES_FACILITY_TYPE_RESPONSE_JSON_PG =
      "facility-type-response-pg.json";
  private static final String FIXTURES_RACE_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "race-type-response.json";
  private static final String FIXTURES_RELATIONSHIP_TO_APPLICANT_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "relationship-to-applicant-type-response.json";
  private static final String FIXTURES_INCOME_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "income-type-response.json";
  private static final String FIXTURES_PHONE_NUMBER_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "phone-number-type-response.json";
  private static final String FIXTURES_ADDRESS_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "address-type-response.json";
  private static final String FIXTURES_SIBLING_GROUP_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "sibling-group-type-response.json";
  private static final String FIXTURES_STATE_TYPE_RESPONSE_JSON_H2 =
      BASE_DICTIONARY_PATH + "state-type-response-h2.json";
  private static final String FIXTURES_STATE_TYPE_RESPONSE_JSON_PG =
      BASE_DICTIONARY_PATH + "state-type-response-pg.json";
  private static final String FIXTURES_RESIDENCE_OWNERSHIP_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "residence-ownership-type-response.json";
  private static final String FIXTURES_APPLICANT_RELATIONSHIP_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "applicant-relationship-type-response.json";
  private static final String FIXTURES_LICENSE_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "license-type-response.json";
  private static final String FIXTURES_MARRIAGE_TERMINATION_REASON_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "marriage-termination-reason-response.json";
  private static final String FIXTURES_SCHOOL_GRADE_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "school-grade-type-response.json";
  private static final String FIXTURES_COUNTY_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "county-type-response.json";
  private static final String FIXTURES_SUFFIX_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "suffix-type-response.json";
  private static final String FIXTURES_NAME_PREFIX_TYPE_RESPONSE_JSON =
      BASE_DICTIONARY_PATH + "name-prefix-type-response.json";


  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCalsns();
  }

  private <T extends BaseDictionary> void baseDictionaryTest(
      String path, String fixturePath, GenericType<CollectionDTO<T>> genericType)
      throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + path);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<T> collectionDTO = invocation.get(genericType);
    assertNotNull(collectionDTO);
    String fixture = fixture(fixturePath);
    assertEqualsResponse(fixture, transformDTOtoJSON(collectionDTO));
  }

  private <T extends BaseDictionary> void baseDictionaryTest(
      String path, String fixturePathH2, String fixturePathPG, GenericType<CollectionDTO<T>> genericType)
      throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.DICTIONARIES + "/" + path);
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    CollectionDTO<T> collectionDTO = invocation.get(genericType);
    assertNotNull(collectionDTO);
    String fixture;
    if (TestModeUtils.isIntegrationTestsMode()) {
      fixture = fixture(fixturePathPG);
    } else {
      fixture = fixture(fixturePathH2);
    }
    assertEqualsResponse(fixture, transformDTOtoJSON(collectionDTO));
  }

  @Test
  public void getDictionaryRelationshipToApplicantTypeTest() throws Exception {
    baseDictionaryTest(
        RELATIONSHIP_TO_APPLICANT_TYPE_PATH,
        FIXTURES_RELATIONSHIP_TO_APPLICANT_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<RelationshipToApplicantType>>() {
        });
  }

  @Test
  public void getDictionaryAgeGroupTypeTest() throws Exception {
    baseDictionaryTest(
        AGE_GROUP_TYPE_PATH,
        FIXTURES_AGE_GROUP_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<AgeGroupType>>() {
        });
  }

  @Test
  public void getDictionaryLanguageTypeTest() throws Exception {
    baseDictionaryTest(
        LANGUAGE_TYPE_PATH,
        FIXTURES_LANGUAGE_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<LanguageType>>() {
        });
  }

  @Test
  public void getDictionaryGenderTypeTest() throws Exception {
    baseDictionaryTest(
        GENDER_TYPE_PATH,
        FIXTURES_GENDER_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<GenderType>>() {
        });
  }

  @Test
  public void getDictionaryNameTypeTest() throws Exception {
    baseDictionaryTest(
        NAME_TYPE_PATH,
        FIXTURES_NAME_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<NameType>>() {
        });
  }

  @Test
  public void getDictionaryEducationLevelTypeTest() throws Exception {
    baseDictionaryTest(
        EDUCATION_LEVEL_TYPE_PATH,
        FIXTURES_EDUCATION_LEVEL_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<EducationLevelType>>() {
        });
  }

  @Test
  public void getDictionaryEthnicityTypeTest() throws Exception {
    baseDictionaryTest(
        ETHNICITY_TYPE_PATH,
        FIXTURES_ETHNICITY_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<EthnicityType>>() {
        });
  }

  @Test
  public void getDictionaryFacilityTypeTest() throws Exception {
    baseDictionaryTest(
        FACILITY_TYPE_PATH,
        FIXTURES_FACILITY_TYPE_RESPONSE_JSON, FIXTURES_FACILITY_TYPE_RESPONSE_JSON_PG,
        new GenericType<CollectionDTO<FacilityType>>() {
        });
  }

  @Test
  public void getIncomeTypeTest() throws Exception {
    baseDictionaryTest(
        INCOME_TYPE_PATH,
        FIXTURES_INCOME_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<IncomeType>>() {
        });
  }

  @Test
  public void getPhoneNumberTypeTest() throws Exception {
    baseDictionaryTest(
        PHONE_NUMBER_TYPE_PATH,
        FIXTURES_PHONE_NUMBER_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<PhoneNumberType>>() {
        });
  }

  @Test
  public void getAddressTypeTest() throws Exception {
    baseDictionaryTest(
        ADDRESS_TYPE_PATH,
        FIXTURES_ADDRESS_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<AddressType>>() {
        });
  }

  @Test
  public void getSiblingGroupTypeTest() throws Exception {
    baseDictionaryTest(
        SIBLING_GROUP_TYPE_PATH,
        FIXTURES_SIBLING_GROUP_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<SiblingGroupType>>() {
        });
  }

  @Test
  public void getStateTypeTest() throws Exception {
    baseDictionaryTest(
        STATE_TYPE_PATH,
        FIXTURES_STATE_TYPE_RESPONSE_JSON_H2, FIXTURES_STATE_TYPE_RESPONSE_JSON_PG,
        new GenericType<CollectionDTO<StateType>>() {
        });
  }

  @Test
  public void getResidenceOwnershipTypeTest() throws Exception {
    baseDictionaryTest(
        RESIDENCE_OWNERSHIP_TYPE_PATH,
        FIXTURES_RESIDENCE_OWNERSHIP_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<ResidenceOwnershipType>>() {
        });
  }

  @Test
  public void getApplicantRelationshipTypeTest() throws Exception {
    baseDictionaryTest(
        APPLICANT_RELATIONSHIP_TYPE_PATH,
        FIXTURES_APPLICANT_RELATIONSHIP_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<ApplicantRelationshipType>>() {
        });
  }

  @Test
  public void getLicenseTypeTest() throws Exception {
    baseDictionaryTest(
        LICENSE_TYPE_PATH,
        FIXTURES_LICENSE_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<LicenseType>>() {
        });
  }

  @Test
  public void getMarriageTerminationReasonTypeTest() throws Exception {
    baseDictionaryTest(
        MARRIAGE_TERMINATION_REASON_PATH,
        FIXTURES_MARRIAGE_TERMINATION_REASON_RESPONSE_JSON,
        new GenericType<CollectionDTO<MarriageTerminationReasonType>>() {
        });
  }

  @Test
  public void getSchoolGradeTypeTest() throws Exception {
    baseDictionaryTest(
        SCHOOL_GRADE_TYPE_PATH,
        FIXTURES_SCHOOL_GRADE_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<SchoolGradeType>>() {
        });
  }

  @Test
  public void getCountyTypeTest() throws Exception {
    baseDictionaryTest(
        COUNTY_TYPE_PATH,
        FIXTURES_COUNTY_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<CountyType>>() {
        });
  }

  @Test
  public void getSuffixTypesTest() throws Exception {
    baseDictionaryTest(
        SUFFIX_TYPE_PATH,
        FIXTURES_SUFFIX_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<NameSuffixType>>() {
        });
  }

  @Test
  public void getNamePrefixTypesTest() throws Exception {
    baseDictionaryTest(
        NAME_PREFIX_TYPE_PATH,
        FIXTURES_NAME_PREFIX_TYPE_RESPONSE_JSON,
        new GenericType<CollectionDTO<NamePrefixType>>() {
        });
  }


}
