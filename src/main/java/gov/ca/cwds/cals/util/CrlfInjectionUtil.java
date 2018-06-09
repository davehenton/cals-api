package gov.ca.cwds.cals.util;

/**
 * Created by Alexander Serbin on 6/7/2018.
 */
public final class CrlfInjectionUtil {

  private CrlfInjectionUtil() {
  }

  public static String sanitizeParameter(String parameter) {
    return parameter == null ? null : parameter.replaceAll("[\r\n]", "");
  }
}
