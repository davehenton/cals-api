package gov.ca.cwds.cals.web.rest.utils;

/**
 * @author CWDS CALS API Team
 */
public class TestModeUtils {

  public static final String CALS_API_URL = "cals.api.url";

  public static boolean isIntegrationTestsMode() {
    return System.getProperty(CALS_API_URL) != null;
  }

  private TestModeUtils() {
  }
}
