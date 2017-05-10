package gov.ca.cwds.cals;

public final class Constants {

    private Constants() {}

    public static class API {

        /**
         * A {@code String} constant representing {@value #FACILITY} API..
         */
        public static final String FACILITY = "facility";

        public static final String FACILITY_CHILD = FACILITIES + "/{facility_id}/children";

        /**
         * A {@code String} constant representing {@value #RESOURCE_APPLICATION} API..
         */
        public static final String RESOURCE_APPLICATION = "application";

    }

}
