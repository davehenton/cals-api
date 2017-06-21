package gov.ca.cwds.cals;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EducationLevelType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.EthnicityType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenderType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.RaceType;

/** @author CWDS CALS API Team */
public final class Constants {

  public static class API {

    public static final String FACILITIES = "facilities";

    public static final String RESOURCE_APPLICATION = "application";

    public static final String CHILDREN = "children";

    public static final String COMPLAINTS = "complaints";

    public static final String DICTIONARIES = "dictionaries";

    public static final String COUNTIES = "counties";

    public static final String INSPECTIONS = "inspections";

    public static final String FACILITY_TYPES = "facility-types";

    public static final String RFA = "rfa";

    public static final String RFA_1A_FORMS = "rfa-1a-forms";

    public static final String RFA_1A_APPLICANT = "applicant";

    public static final String RFA_1A_APPLICANTS = "applicants";

    public static final String AGE_GROUP_TYPES = "age-groups";

    public static final String SYSTEM_USER_ID = "SYSTEM";

    public static class PathParams {

      public static final String FACILITY_ID = "facilityId";

      public static final String CHILD_ID = "childId";

      public static final String COMPLAINT_ID = "complaintId";

      public static final String INSPECTION_ID = "inspectionId";

      public static final String DICTIONARY_TYPE = "dictionaryType";

      public static final String RFA_1A_FORM_ID = "formId";

      public static final String RFA_1A_APPLICANT_ID = "applicantId";

      private PathParams() {}
    }

    private API() {}
  }

  public enum DictionaryType {
    AGE_GROUP_TYPE(AgeGroupType.class),
    LANGUAGE_TYPE(LanguageType.class),
    GENDER_TYPE(GenderType.class),
    NAME_TYPE(NameType.class),
    EDUCATION_LEVEL_TYPE(EducationLevelType.class),
    ETHNICITY_TYPE(EthnicityType.class),
    RACE_TYPE(RaceType.class)

    /*
    COUNTY_TYPE("county-type", null),
    STATE_TYPE("state-type", null),
    APPLICATION_STATUS_TYPE("application-status-type"),
    OTHER_NAME_TYPE("other-name-type"),

    ETHNICITY_TYPE("ethnicity-type"),
    PHONE_NUMBER_TYPE("phone-number-type"),
    ADDRESS_TYPE("address-type"),
    RESIDENCE_OWNERSHIP_TYPE("residence-ownership-type"),
    RELATIONSHIP_TYPE("relationship-type"),
    RELATIONSHIP_EVENT_TYPE("relationship-event-type"),
    SIBLING_GROUP_TYPE("sibling-group-type")
    */

    ;
    public static final String AGE_GROUP_TYPE_PATH = "age-group-type";
    public static final String LANGUAGE_TYPE_PATH = "language-type";
    public static final String GENDER_TYPE_PATH = "gender-type";
    public static final String NAME_TYPE_PATH = "name-type";
    public static final String EDUCATION_LEVEL_TYPE_PATH = "education-level-type";
    public static final String ETHNICITY_TYPE_PATH = "ethnicity-type";
    public static final String COUNTY_TYPE_PATH = "county-type";
    public static final String STATE_TYPE_PATH = "state-type";
    public static final String APPLICATION_STATUS_TYPE_PATH = "application-status-type";
    public static final String OTHER_NAME_TYPE_PATH = "other-name-type";
    public static final String RACE_TYPE_PATH = "race-type";
    public static final String PHONE_NUMBER_TYPE_PATH = "phone-number-type";
    public static final String ADDRESS_TYPE_PATH = "address-type";
    public static final String RESIDENCE_OWNERSHIP_TYPE_PATH = "residence-ownership-type";
    public static final String RELATIONSHIP_TYPE_PATH = "relationship-type";
    public static final String RELATIONSHIP_EVENT_TYPE_PATH = "relationship-event-type";
    public static final String SIBLING_GROUP_TYPE_PATH = "sibling-group-type";

    private Class<? extends BaseDictionary> dictionaryClass;

    DictionaryType(Class<? extends BaseDictionary> dictionaryClass) {
      this.dictionaryClass = dictionaryClass;
    }

    public Class<? extends BaseDictionary> getDictionaryClass() {
      return dictionaryClass;
    }

  }

  public static class AddressTypes {

    public static final String RESIDENTIAL = "residential";

    public static final String MAIL = "mail";

    private AddressTypes() {}
  }

  public static class PhoneTypes {

    public static final String PRIMARY = "primary";

    public static final String ALTERNATE = "alternate";

    private PhoneTypes() {}
  }

  public static class VisitTypes {

    public static final String ANNUAL_10_MONTH = "Annual 10 month";

    public static final String ANNUAL_22_MONTH = "Annual 22 month";

    public static final String POST_LICENSING = "Post Licensing";

    public static final String RENEWAL = "Renewal";

    public static final String MIDDLE_YEAR = "Middle Year";

    private VisitTypes() {}
  }

  public static class UnitOfWork {

    public static final String FAS = "fas";

    public static final String LIS = "lis";

    public static final String CMS = "cwscms";

    public static final String CALSNS = "calsns";

    private UnitOfWork() {}
  }

  private Constants() {}
}
