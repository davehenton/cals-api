package gov.ca.cwds.cals;

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

    public static final String AGE_GROUP_TYPES = "age-groups";

    public static class PathParams {

      public static final String FACILITY_ID = "facilityId";

      public static final String CHILD_ID = "childId";

      public static final String COMPLAINT_ID = "complaintId";

      public static final String INSPECTION_ID = "inspectionId";

      public static final String DICTIONARY_TYPE = "dictionaryType";

      private PathParams() {}
    }

    private API() {}
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
