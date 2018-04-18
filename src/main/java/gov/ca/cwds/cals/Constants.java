package gov.ca.cwds.cals;

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
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RelationshipToApplicantType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SchoolGradeType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SiblingGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;

/**
 * @author CWDS CALS API Team
 */
public final class Constants {

  public static final String NULL_STRING = "null";

  public static final String Y = "Y";

  public static final String N = "N";

  public static final String SPACE = " ";

  public static final String SQL_TYPE = "sqlType";

  public static final String RETURNED_CLASS_NAME_PARAM = "returnedClassName";

  public static final String RFA = "rfa-1abc";

  public static class API {

    public static final String SYSTEM_INFORMATION = "system-information";

    public static final String RFA_PACKET = "rfa-packet";

    public static final String SUMMARY = "summary";

    public static final String FACILITIES = "facilities";

    public static final String CHILDREN = "children";

    public static final String COMPLAINTS = "complaints";

    public static final String DICTIONARIES = "dictionaries";

    public static final String INSPECTIONS = "inspections";

    public static final String RFA_1A_FORMS = "rfa-1a-forms";

    public static final String RFA_1B_FORMS = "rfa-1b-forms";

    public static final String RFA_1B_FORM = "rfa-1b-form";

    public static final String LIC_198B_FORMS = "lic-198b-forms";
    
    public static final String LIC_198B_FORM = "lic-198b-form";

    public static final String RFA_1C_FORMS = "rfa-1c-forms";

    public static final String RFA_1A_APPLICANTS = "applicants";

    public static final String RFA_1A_APPLICANTS_RELATIONSHIP = "applicants-relationship";

    public static final String RFA_1A_MINOR_CHILDREN = "minor-children";

    public static final String RFA_1A_OTHER_ADULTS = "other-adults";

    public static final String RFA_1A_RESIDENCE = "residence";

    public static final String RFA_1A_ADOPTION_HISTORY = "adoption-history";

    public static final String RFA_1A_REFERENCES = "references";

    public static final String RFA_1A_APPLICANTS_HISTORY = "applicants-history";

    public static final String RFA_1A_CHILD_DESIRED = "child-desired";

    public static final String RFA_1A_APPLICANTS_DECLARATION = "applicants-declaration";

    public static final String STATUS = "status";

    public static class PathParams {

      public static final String FACILITY_ID = "facilityId";

      public static final String CHILD_ID = "childId";

      public static final String COMPLAINT_ID = "complaintId";

      public static final String INSPECTION_ID = "inspectionId";

      public static final String RFA_1A_APPLICATION_ID = "applicationId";

      public static final String RFA_1A_APPLICANT_ID = "applicantId";

      public static final String RFA_1A_MINOR_CHILD_ID = "minorChildId";

      public static final String RFA_1A_OTHER_ADULT_ID = "otherAdultId";

      public static final String RFA_1A_MINOR_CHILD = "minorChild";

      public static final String RFA_1A_ADOPTION_HISTORY_API_PARAM = "adoptionHistory";

      public static final String RFA_1A_OTHER_ADULT = "otherAdult";

      public static final String RFA_1B_FORM = "rfa1bForm";

      public static final String RFA_1C_FORM = "rfa1cForm";

      public static final String RFA_1B_FORM_ID = "rfa1bFormId";

      public static final String LIC_198B_FORM_ID = "lic198bFormId";

      public static final String RFA_1C_FORM_ID = "rfa1cFormId";

      public static final String RFA_1A_APPLICANT = "applicant";

      private PathParams() {
      }
    }

    public static class QueryParams {

      public static final String EXPANDED = "expanded";

      private QueryParams() {
      }
    }

    private API() {
    }
  }

  public static class Authorize {
    public static final String PLACEMENT_HOME_CREATE = "placementHome:create";

    public static final String SUBSTITUTE_CARE_PROVIDER_CREATE = "substituteCareProvider:create";

    private Authorize() {
    }
  }

  public enum DictionaryType {
    AGE_GROUP_TYPE(AgeGroupType.class),
    LANGUAGE_TYPE(LanguageType.class),
    GENDER_TYPE(GenderType.class),
    NAME_TYPE(NameType.class),
    EDUCATION_LEVEL_TYPE(EducationLevelType.class),
    ETHNICITY_TYPE(EthnicityType.class),
    FACILITY_TYPE(FacilityType.class),
    RACE_TYPE(RaceType.class),
    RELATIONSHIP_TO_APPLICANT_TYPE(RelationshipToApplicantType.class),
    INCOME_TYPE(IncomeType.class),
    PHONE_NUMBER_TYPE(PhoneNumberType.class),
    ADDRESS_TYPE(AddressType.class),
    SIBLING_GROUP_TYPE(SiblingGroupType.class),
    STATE_TYPE(StateType.class),
    RESIDENCE_OWNERSHIP_TYPE(ResidenceOwnershipType.class),
    APPLICANT_RELATIONSHIP_TYPE(ApplicantRelationshipType.class),
    LICENSE_TYPE(LicenseType.class),
    MARRIAGE_TERMINATION_REASON(MarriageTerminationReasonType.class),
    SCHOOL_GRADE_TYPE(SchoolGradeType.class),
    COUNTY_TYPE(CountyType.class),
    SUFFIX_TYPE(NameSuffixType.class),
    NAME_PREFIX_TYPE(NamePrefixType.class);

    public static final String AGE_GROUP_TYPE_PATH = "age-groups";
    public static final String LANGUAGE_TYPE_PATH = "languages";
    public static final String GENDER_TYPE_PATH = "genders";
    public static final String NAME_TYPE_PATH = "name-types";
    public static final String EDUCATION_LEVEL_TYPE_PATH = "education-level-types";
    public static final String ETHNICITY_TYPE_PATH = "ethnicity-types";
    public static final String FACILITY_TYPE_PATH = "facility-types";
    public static final String RACE_TYPE_PATH = "race-types";
    public static final String RELATIONSHIP_TO_APPLICANT_TYPE_PATH = "relationship-to-applicant-types";
    public static final String INCOME_TYPE_PATH = "income-types";
    public static final String PHONE_NUMBER_TYPE_PATH = "phone-number-types";
    public static final String ADDRESS_TYPE_PATH = "address-types";
    public static final String SIBLING_GROUP_TYPE_PATH = "sibling-groups";
    public static final String STATE_TYPE_PATH = "states";
    public static final String APPLICATION_STATUS_TYPE_PATH = "application-statuses";
    public static final String RESIDENCE_OWNERSHIP_TYPE_PATH = "residence-ownership-types";
    public static final String APPLICANT_RELATIONSHIP_TYPE_PATH = "applicant-relationship-types";
    public static final String RELATIONSHIP_EVENT_TYPE_PATH = "relationship-event-types";
    public static final String LICENSE_TYPE_PATH = "license-types";
    public static final String MARRIAGE_TERMINATION_REASON_PATH = "marriage-termination-reasons";
    public static final String SCHOOL_GRADE_TYPE_PATH = "school-grades";
    public static final String COUNTY_TYPE_PATH = "counties";
    public static final String SUFFIX_TYPE_PATH = "name-suffix-types";
    public static final String NAME_PREFIX_TYPE_PATH = "name-prefix-types";

    private Class<? extends BaseDictionary> dictionaryClass;

    DictionaryType(Class<? extends BaseDictionary> dictionaryClass) {
      this.dictionaryClass = dictionaryClass;
    }

    public Class<? extends BaseDictionary> getDictionaryClass() {
      return dictionaryClass;
    }
  }

  public static class AddressTypes {

    public static final String RESIDENTIAL = "Residential";

    public static final String MAIL = "Mailing";

    private AddressTypes() {
    }
  }

  public static class StateTypes {

    public static final String CALIFORNIA_STATE_ID = "CA";

    private StateTypes() {
    }
  }

  public static class PhoneTypes {

    public static final String PRIMARY = "primary";

    public static final String ALTERNATE = "alternate";

    private PhoneTypes() {
    }
  }

  public static class VisitTypes {

    public static final String ANNUAL_10_MONTH = "Annual 10 month";

    public static final String ANNUAL_22_MONTH = "Annual 22 month";

    public static final String POST_LICENSING = "Post Licensing";

    public static final String RENEWAL = "Renewal";

    private VisitTypes() {
    }
  }

  public static class UnitOfWork {

    public static final String FAS = "fas";

    public static final String LIS = "lis";

    public static final String CMS = "cwscms";

    public static final String CMS_RS = "cwscmsrs";

    public static final String CALSNS = "calsns";

    public static final String XA_CMS = "xa_cwscms";

    public static final String XA_CALSNS = "xa_calsns";

    private UnitOfWork() {
    }
  }

  public static class BusinessRulesAgendaGroups {

    public static final String APPLICANT_NAMES_DUPLICATION_VALIDATION = "applicant-names-duplication-validation";

    public static final String APPLICANT_VALIDATION = "applicant-validation";

    public static final String FORM_SUBMISSION_VALIDATION = "form-submission-validation";

    private BusinessRulesAgendaGroups() {
    }
  }

  public static class RulesConfigPaths {

    public static final String FORM_SUBMISSION = "form-submission-rules";

    public static final String FORM_INPROGRESS = "form-inprogress-rules";

    private RulesConfigPaths() {
    }
  }

  public static class ExpectedExceptionMessages {

    public static final String COMPLAINT_NOT_FOUND_BY_ID = "Facility Complaint is not found by Facility Number and Complaint Id";

    public static final String DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN = "Disctrict office (lis_fac_file.fac_do_nbr) is unexpectedly empty";

    public static final String RFA_1A_APPLICANT_NOT_FOUND_BY_ID = "Applicant is not found by Form Id and Applicant Id";

    public static final String RFA_1A_APPLICATION_NOT_FOUND_BY_ID = "Application is not found by Form Id";

    public static final String TRANSITION_IS_NOT_ALLOWED = "Requested status transition is not allowed";

    public static final String CANNOT_PARSE_STREET_ADDRESS = "Cannot parse street address";

    public static final String RFA_1C_FORM_ALREADY_EXISTS = "RFA1c form already exists";

    public static final String RFA_1B_FORM_ALREADY_EXISTS = "RFA1b form already exists";

    private ExpectedExceptionMessages() {
    }

  }

  public static class Validation {

    public static final String DEFAULT_DROOLS_VALIDATION_SESSION = "inProgressValidationSession";
    public static final String FORM_SUBMISSION_VALIDATION_SESSION = "formSubmissionValidationSession";

    public static class Constraint {

      public static final String BETWEEN_LENGTH_MESSAGE =
              "size must be between %d and %d";
      public static final String MAX_LENGTH_MESSAGE =
              "${validatedValue} exceeds maximum length of {max}";
      public static final String ALPHANUMERIC_MESSAGE =
              "${validatedValue} is invalid. Only alphanumerical characters and spaces are allowed";
      public static final String NUMERIC_MESSAGE =
              "${validatedValue} is invalid. Only numerical characters are allowed";
      public static final String NOT_NULL_MESSAGE =
              "may not be null";

      private Constraint() {
      }
    }

    public static class Field {

      public static final String REFERENTIAL_INTEGRITY_MESSAGE =
          " Object %s is not found in database. Referential integrity was not confirmed.";
      public static final String REFERENTIAL_INTEGRITY_LIST_MESSAGE =
          " [%s] object %s is not found in database ";
      public static final String CANNOT_OPEN_DATABASE_SESSION_MESSAGE =
          " Cannot open database session.";

      private Field() {
      }
    }

    public static class Business {

      private static final String APPLICANT_WITH_NAME_MESSAGE =
          "Applicant with first name - [%s], last name - [%s] and name suffix - [%s]";

      private static final String CHILD_WITH_NAME_MESSAGE =
          "Child with first name - [%s], last name - [%s] and name suffix - [%s]";

      private static final String OTHER_ADULT_WITH_NAME =
          "Other adult with first name - [%s], last name - [%s] and name suffix - [%s]";

      public static final String APPLICATION_HAS_NO_APPLICANT_MESSAGE =
          "Application has no applicant";

      public static final String APPLICANT_HAS_NO_RFA1B_FORM_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has no RFA1b form";

      public static final String APPLICATION_HAS_NO_RESIDENCE_MESSAGE =
          "Application has no residence";

      public static final String APPLICATION_HAS_NO_RESIDENTIAL_ADDRESS_MESSAGE =
          "Application has no residential address";

      public static final String RESIDENTIAL_ADDRESS_HAS_NO_STREET_NAME_MESSAGE =
          "Residential address has no street name";

      public static final String RESIDENTIAL_ADDRESS_HAS_NO_CITY_MESSAGE =
          "Residential address has no city";

      public static final String RESIDENTIAL_ADDRESS_HAS_NO_STATE_MESSAGE =
          "Residential address has no state";

      public static final String RESIDENTIAL_ADDRESS_HAS_NO_ZIP_CODE_MESSAGE =
          "Residential address has no zip code";

      public static final String APPLICATION_HAS_NO_COUNTY_MESSAGE =
          "Application has no county";

      public static final String OTHER_ADULT_HAS_NO_REFERENCE_TO_APPLICANT_MESSAGE =
          OTHER_ADULT_WITH_NAME + " has no reference to any applicant";

      public static final String OTHER_ADULT_HAS_NO_FIRST_NAME_MESSAGE =
          OTHER_ADULT_WITH_NAME + " has no first name";

      public static final String OTHER_ADULT_HAS_NO_LAST_NAME_MESSAGE =
          OTHER_ADULT_WITH_NAME + " has no last name";

      public static final String OTHER_ADULT_HAS_NO_DATE_OF_BIRTH_MESSAGE =
          OTHER_ADULT_WITH_NAME + " has no date of birth";

      public static final String MINOR_CHILD_HAS_NO_REFERENCE_TO_APPLICANT_MESSAGE =
          "Some minor child has no reference to any applicant";

      public static final String APPLICANT_NAMES_DUPLICATION_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " already exists in application";

      public static final String APPLICANT_PHONE_NUMBERS_DUPLICATION_MESSAGE =
          "Phone [%s] already exists for Applicant with first name - [%s], last name - [%s] and name suffix - [%s]";

      public static final String APPLICANT_PREFERRED_NUMBER_MESSAGE =
          "Applicant has more then one preferred number";

      public static final String APPLICANT_FIRST_NAME_IS_EMPTY_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has empty first name";

      public static final String APPLICANT_LAST_NAME_IS_EMPTY_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has empty last name";

      public static final String APPLICANT_DRIVER_LICENSE_IS_INVALID_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " driver's license field must have both State and Number";

      public static final String OTHER_ADULT_IS_NOT_ADDED_AS_A_CHILD =
          "System has discovered Other Adult with first name - [%s], last name - [%s] that is a child of applicant(s)"
              + " but is not added to Adult Children of Applicant(s) section";

      public static final String ADULT_CHILD_LIVING_IN_HOME_IS_NOT_ADDED_AS_OTHER_ADULT =
          "System has discovered Adult Child of applicant(s) with first name - [%s], last name - [%s] that lives in home"
              + " but is not added to Other Adults in the home section";

      public static final String ADULT_CHILD_WRONG_LIVING_IN_HOME_FLAG =
          "System has discovered Adult Child of applicant(s) with first name - [%s], last name - [%s] that does not live in home"
              + " but is added to Other Adults residing in the home section";

      public static final String REQUIRED_FIELD_MESSAGE= "Required field [%s] is not provided";

      public static final String RFA1B_FORM_HAS_NO_COUNTY_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b form without county";
      public static final String RFA1B_FORM_HAS_NO_FIRST_NAME_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b form without first name";
      public static final String RFA1B_FORM_HAS_NO_LAST_NAME_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b form without last name";
      public static final String RFA1B_FORM_CONVICTED_CA_OFFENSE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with CA conviction but without 'offense title'";
      public static final String RFA1B_FORM_CONVICTED_CA_WHERE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with CA conviction but without 'where'";
      public static final String RFA1B_FORM_CONVICTED_CA_WHEN_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with CA conviction but without 'when'";
      public static final String RFA1B_FORM_CONVICTED_CA_DETAILS_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with CA conviction but without 'offense details'";
      public static final String RFA1B_FORM_CONVICTED_CA_DISCLOSURE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with CA conviction but without any disclosure";

      public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_OFFENSE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with outside of CA conviction but without 'offense title'";
      public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_WHERE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with outside of CA conviction but without 'where'";
      public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_WHEN_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with outside of CA conviction but without 'when'";
      public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_DETAILS_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with outside of CA conviction but without 'offense details'";
      public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_DISCLOSURE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with outside of CA conviction but without any disclosure";

      public static final String RFA1B_FORM_CRIME_OFFENSE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with 'arrested for crime' but without 'offense title'";
      public static final String RFA1B_FORM_CRIME_WHERE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with 'arrested for crime' but without 'where'";
      public static final String RFA1B_FORM_CRIME_WHEN_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with 'arrested for crime' but without 'when'";
      public static final String RFA1B_FORM_CRIME_DETAILS_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with 'arrested for crime' but without 'offense details'";
      public static final String RFA1B_FORM_CRIME_DISCLOSURE_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b with 'arrested for crime' but without any disclosure";

      public static final String RFA1B_FORM_OTHER_STATES_IS_REQUIRED_MESSAGE =
          APPLICANT_WITH_NAME_MESSAGE + " has RFA1b without other states";

      public static final String RFA1C_FORM_IN_HOME_IS_REQUIRED_MESSAGE =
           "'Is the child currently in your home?' is required for " + CHILD_WITH_NAME_MESSAGE;
      public static final String RFA1C_FORM_FIRST_NAME_IS_REQUIRED_MESSAGE =
          CHILD_WITH_NAME_MESSAGE + " has no first name";
      public static final String RFA1C_FORM_LAST_NAME_IS_REQUIRED_MESSAGE =
          CHILD_WITH_NAME_MESSAGE + " has no last name";
      public static final String RFA1C_FORM_DOB_IS_REQUIRED_MESSAGE =
          CHILD_WITH_NAME_MESSAGE + " has no date of birth";

      public static class Code {

        public static final String APPLICANT_FIRST_NAME = "BV000001";
        public static final String APPLICANT_LAST_NAME = "BV000002";
        public static final String APPLICANT_PREFERRED_PHONE = "BV000003";
        public static final String APPLICANT_PHONE_NUMBERS_DUPLICATION = "BV000004";
        public static final String APPLICANT_NAMES_DUPLICATION = "BV000005";
        public static final String APPLICANT_DRIVER_LICENSE = "BV000006";
        public static final String APPLICATION_HAS_NO_APPLICANT = "BV000007";
        public static final String APPLICANT_HAS_NO_RFA1B_FORM = "BV000008";
        public static final String OTHER_ADULT_HAS_NO_REFERENCE_TO_APPLICANT = "BV000009";
        public static final String MINOR_CHILD_HAS_NO_REFERENCE_TO_APPLICANT = "BV000010";
        public static final String APPLICATION_HAS_NO_RESIDENCE = "BV000011";
        public static final String APPLICATION_HAS_NO_RESIDENTIAL_ADDRESS = "BV000012";
        public static final String APPLICATION_HAS_NO_COUNTY = "BV000017";
        public static final String OTHER_ADULT_HAS_NO_FIRST_NAME = "BV000018";
        public static final String OTHER_ADULT_HAS_NO_LAST_NAME = "BV000019";
        public static final String OTHER_ADULT_IS_NOT_ADDED_AS_ADULT_CHILD = "BV000020";
        public static final String ADULT_CHILD_LIVING_IN_HOME_IS_NOT_ADDED_AS_OTHER_ADULT = "BV000021";
        public static final String ADULT_CHILD_LIVING_IN_HOME_WRONG_FLAG = "BV000022";
        public static final String OTHER_ADULT_HAS_NO_DATE_OF_BIRTH = "BV000023";




        // Constraint Violation Codes
        public static final String REQUIRED_FIELD = "CV000001";
        public static final String REQUIRED_APPLICANT_FIRST_NAME = "CV000002";
        public static final String REQUIRED_APPLICANT_LAST_NAME = "CV000003";
        public static final String REQUIRED_APPLICANT_OTHER_NAMES_FIRST_NAME = "CV000004";
        public static final String REQUIRED_APPLICANT_OTHER_NAMES_LAST_NAME = "CV000005";
        public static final String REQUIRED_APPLICANT_DATE_OF_BIRTH = "CV000006";
        public static final String REQUIRED_APPLICANT_DRIVER_LICENSE_STATE = "CV000007";
        public static final String REQUIRED_APPLICANT_DRIVER_LICENSE_NUMBER = "CV000008";
        public static final String REQUIRED_APPLICANT_PHONE_NUMBER = "CV000009";
        public static final String REQUIRED_RESIDENCE_STREET_ADDRESS = "CV000010";
        public static final String REQUIRED_RESIDENCE_ADDRESS_CITY = "CV000011";
        public static final String REQUIRED_RESIDENCE_ADDRESS_STATE = "CV000012";
        public static final String REQUIRED_RESIDENCE_ADDRESS_ZIP = "CV000013";
        public static final String REQUIRED_PHYSICAL_MAILING_THE_SAME = "CV000014";
        public static final String REQUIRED_RESIDENCE_OWNERSHIP = "CV000015";
        public static final String REQUIRED_WEAPON_IN_HOME_FIELD = "CV000016";
        public static final String REQUIRED_BODY_OF_WATER_EXIST_FIELD = "CV000017";
        public static final String REQUIRED_OTHER_PEOPLE_IN_RESIDENCE = "CV000018";
        public static final String REQUIRED_HOME_LANGUAGES = "CV000019";
        public static final String REQUIRED_MINOR_CHILD_DATE_OF_BIRTH = "CV000020";
        public static final String REQUIRED_MINOR_CHILD_GENDER = "CV000021";
        public static final String REQUIRED_MINOR_CHILD_FINANCIALLY_SUPPORTED = "CV000022";
        public static final String REQUIRED_MINOR_CHILD_ADOPTED = "CV000023";
        public static final String REQUIRED_MINOR_CHILD_RELATIONSHIP_APPLICANT_ID = "CV000024";
        public static final String RFA1B_FORM_HAS_NO_COUNTY = "CV000025";
        public static final String RFA1B_FORM_HAS_NO_FIRST_NAME = "CV000026";
        public static final String RFA1B_FORM_HAS_NO_LAST_NAME = "CV000027";
        public static final String RFA1B_FORM_HAS_NO_OTHER_STATES = "CV000028";
        public static final String RFA1B_FORM_CONVICTED_CA_OFFENSE_IS_REQUIRED = "CV000029";
        public static final String RFA1B_FORM_CONVICTED_CA_WHERE_IS_REQUIRED = "CV000030";
        public static final String RFA1B_FORM_CONVICTED_CA_WHEN_IS_REQUIRED = "CV000031";
        public static final String RFA1B_FORM_CONVICTED_CA_DETAILS_IS_REQUIRED = "CV000032";
        public static final String RFA1B_FORM_CONVICTED_CA_CITY_IS_REQUIRED = "CV000033";
        public static final String RFA1B_FORM_CONVICTED_CA_DATE_IS_REQUIRED = "CV000034";
        public static final String RFA1B_FORM_CONVICTED_CA_SIGNATURE_IS_REQUIRED = "CV000035";
        public static final String RFA1B_FORM_CONVICTED_CA_DISCLOSURE_IS_REQUIRED = "CV000036";

        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_OFFENSE_IS_REQUIRED = "CV000037";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_WHERE_IS_REQUIRED = "CV000038";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_WHEN_IS_REQUIRED = "CV000039";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_DETAILS_IS_REQUIRED = "CV000040";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_CITY_IS_REQUIRED = "CV000041";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_DATE_IS_REQUIRED = "CV000042";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_SIGNATURE_IS_REQUIRED = "CV000043";
        public static final String RFA1B_FORM_CONVICTED_OUTSIDE_CA_DISCLOSURE_IS_REQUIRED = "CV000044";

        public static final String RFA1B_FORM_CRIME_OFFENSE_IS_REQUIRED = "CV000045";
        public static final String RFA1B_FORM_CRIME_WHERE_IS_REQUIRED = "CV000046";
        public static final String RFA1B_FORM_CRIME_WHEN_IS_REQUIRED = "CV000047";
        public static final String RFA1B_FORM_CRIME_DETAILS_IS_REQUIRED = "CV000048";
        public static final String RFA1B_FORM_CRIME_CITY_IS_REQUIRED = "CV000049";
        public static final String RFA1B_FORM_CRIME_DATE_IS_REQUIRED = "CV000050";
        public static final String RFA1B_FORM_CRIME_SIGNATURE_IS_REQUIRED = "CV000051";
        public static final String RFA1B_FORM_CRIME_DISCLOSURE_IS_REQUIRED = "CV000052";

        public static final String RFA1B_FORM_OTHER_STATES_IS_REQUIRED = "CV000053";

        public static final String RFA1C_FORM_IN_HOME_IS_REQUIRED = "CV000054";
        public static final String RFA1C_FORM_FIRST_NAME_IS_REQUIRED = "CV000055";
        public static final String RFA1C_FORM_LAST_NAME_IS_REQUIRED = "CV000056";
        public static final String RFA1C_FORM_DOB_IS_REQUIRED = "CV000057";


        private Code() {
        }
      }

      private Business() {
      }
    }

    private Validation() {
    }
  }

  private Constants() {
  }
}
