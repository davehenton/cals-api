package gov.ca.cwds.cals;

/**
 * @author CWDS CALS API Team
 */

public final class Constants {

    private Constants() {}

    public static class API {

        public static final String FACILITIES = "facilities";

        public static final String RESOURCE_APPLICATION = "application";

        public static final String CHILDREN = "children";

        public static final String COMPLAINTS = "complaints";

        public static final String DICTIONARY = "dictionary";

        public static final String COUNTIES = "counties";

        public static final String INSPECTIONS ="inspections";

        public static final String FACILITY_TYPES = "facility-types";

        public static class PATH_PARAMS {

            public static final String FACILITY_ID = "facilityId";

            public static final String CHILD_ID = "childId";

            public static final String COMPLAINT_ID = "complaintId";

            public static final String INSPECTION_ID = "inspectionId";

        }
    }

    public static class ADDRESS_TYPES {

        public static final String RESIDENTIAL = "residential";

        public static final String MAIL = "mail";

    }

    public static class PHONE_TYPES {

        public static final String PRIMARY = "primary";

        public static final String ALTERNATE = "alternate";

    }

    public static class UNIT_OF_WORK {

        public static final String FAS = "fas";

        public static final String LIS = "lis";

        public static final String CMS = "cms";

    }

}
