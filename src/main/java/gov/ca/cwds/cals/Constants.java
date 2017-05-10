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
        public static final String FACILITY_CHILD = FACILITIES + "/{facility_id}/children";

        public static final String COMPLAINS = "complains";
    }

    public static class ADDRESS_TYPES {

        public static final String RESIDENTIAL = "residential";

        public static final String MAIL = "mail";

    }

}
