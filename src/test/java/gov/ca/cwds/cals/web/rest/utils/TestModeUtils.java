package gov.ca.cwds.cals.web.rest.utils;

/**
 * @author CWDS CALS API Team
 */
public class TestModeUtils {

  public static final String CALS_API_URL = "cals.api.url";
  public static final String PERRY_URL_PROP_NAME = "perry.url";
  public static final String LOGIN_FORM_TARGET_URL_PROP_NAME = "login.form.target.url";


  public static boolean isIntegrationTestsMode() {
    return System.getProperty(CALS_API_URL) != null;
  }

  public static String perryUrl() {
    return System.getProperty(PERRY_URL_PROP_NAME);
  }

  public static String loginFormTargetUrl() {
    return System.getProperty(LOGIN_FORM_TARGET_URL_PROP_NAME);
  }

  private TestModeUtils() {
  }
}
