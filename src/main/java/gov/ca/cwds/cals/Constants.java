package gov.ca.cwds.cals;

/**
 * @author CWDS CALS API Team
 */

public final class Constants {

    private Constants() {}

    public static class API {

        public static final String FACILITIES = "facilities";

        public static final String RESOURCE_APPLICATION = "application";

        //todo: check and remove
        public static final String CHILDREN = "children";

        public static final String COMPLAINTS = "complaints";

        public static final String DICTIOMARY = "dictionary";

        public static final String COUNTIES = "counties";

        public static class PATH_PARAMS {

            public static final String FACILITY_ID = "facilityId";

            public static final String COMPLAINT_ID = "complaintId";

        }
    }

    public static class ADDRESS_TYPES {

        public static final String RESIDENTIAL = "residential";

        public static final String MAIL = "mail";

    }

}
