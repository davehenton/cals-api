package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.Validation.Pattern.ALFANUMERICAL_PATTERN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantFirstNamePatternTest {

  @Test
  public void firstNamePatternTest() {
    Pattern pattern = Pattern.compile(ALFANUMERICAL_PATTERN);
    assertTrue(pattern.matcher("").matches());
    assertTrue(pattern.matcher("abc").matches());
    assertTrue(pattern.matcher("abc s1e").matches());
    assertFalse(pattern.matcher("a}c s1e").matches());
  }

}
