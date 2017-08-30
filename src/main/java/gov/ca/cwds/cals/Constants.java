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
  public static final String Y = "Y";

  public static final String N = "N";

  public static final String SPACE = " ";

  public static final String SQL_TYPE = "sqlType";

  public static final String RETURNED_CLASS_NAME_PARAM = "returnedClassName";

  public static final String SYSTEM_USER_ID = "SYSTEM";

  public static final String RFA = "rfa-1a";

  public static class API {

    public static final String SYSTEM_INFORMATION = "system-information";

    public static final String FACILITIES = "facilities";

    public static final String CHILDREN = "children";

    public static final String COMPLAINTS = "complaints";

    public static final String DICTIONARIES = "dictionaries";

    public static final String INSPECTIONS = "inspections";

    public static final String RFA_1A_FORMS = "rfa-1a-forms";

    public static final String RFA_1B_FORMS = "rfa-1b-forms";

    public static final String RFA_1B_FORM = "rfa-1b-form";

    public static final String RFA_1C_FORMS = "rfa-1c-forms";

    public static final String RFA_1A_APPLICANTS = "applicants";

    public static final String APPLICANTS_RELATIONSHIP = "applicants-relationship";

    public static final String RFA_1A_MINOR_CHILDREN = "minor-children";

    public static final String RFA_1A_OTHER_ADULTS = "other-adults";

    public static final String RESIDENCE = "residence";

    public static final String RFA_1A_ADOPTION_HISTORY = "adoption-history";

    public static final String RFA_1A_REFERENCES = "references";

    public static final String APPLICANTS_HISTORY = "applicants-history";

    public static final String CHILD_DESIRED = "child-desired";

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

    public static final Long CALIFORNIA_STATE_ID = 6L;

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

  public static class Validation {

    public static final String DEFAULT_DROOLS_VALIDATION_SESSION = "inProgressValidationSession";
    public static final String FORM_SUBMISSION_VALIDATION_SESSION = "formSubmissionValidationSession";

    public static class Error {
      public static final String BASE_MESSAGE =
              "There was an error processing your request. It has been logged with unique incident id";

      private Error() {
      }
    }

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
          "Applicant with firstName - [%s], last name - [%s] and name suffix - [%s]";

      public static final String APPLICANT_NAMES_DUPLICATION_MESSAGE =
          "Applicant with first name - [%s],"
              + " last name - [%s] and name suffix - [%s] already exists in application";

      public static final String APPLICANT_PHONE_NUMBERS_DUPLICATION_MESSAGE =
          "Phone [%s] already exists for Applicant with first name - [%s], last name - [%s] and name suffix - [%s]";

      public static final String APPLICANT_PREFERRED_NUMBER_MESSAGE =
          "Applicant has more then one preferred number";

      public static final String APPLICANT_FIRST_NAME_IS_EMPTY =
          APPLICANT_WITH_NAME_MESSAGE + " has empty first name";

      public static final String APPLICANT_LAST_NAME_IS_EMPTY =
          APPLICANT_WITH_NAME_MESSAGE + " has empty last name";

      public static final String APPLICANT_DRIVER_LICENSE_IS_INVALID =
          "Driver's License field must have both State and Number";

      public static class Code {

        public static final String APPLICANT_FIRST_NAME = "BV000001";
        public static final String APPLICANT_LAST_NAME = "BV000002";
        public static final String APPLICANT_PREFERRED_PHONE = "BV000003";
        public static final String APPLICANT_PHONE_NUMBERS_DUPLICATION = "BV000004";
        public static final String APPLICANT_NAMES_DUPLICATION = "BV000005";
        public static final String APPLICANT_DRIVER_LICENSE = "BV000006";

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
