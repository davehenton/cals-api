package gov.ca.cwds.cals.web.rest.rfa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */

public class ApplicantFirstNamePatternTest {

  @Test
  public void firstNamePatternTest() {
    Pattern pattern = Pattern.compile(ApplicantDTO.ALFANUMERICAL_PATTERN);
    assertTrue(pattern.matcher("").matches());
    assertTrue(pattern.matcher("abc").matches());
    assertTrue(pattern.matcher("abc s1e").matches());
    assertFalse(pattern.matcher("a}c s1e").matches());
  }

}
