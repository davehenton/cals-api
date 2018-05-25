package gov.ca.cwds.cals.util;

import static org.apache.commons.lang3.StringUtils.join;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by Alexander Serbin on 5/22/2018.
 */
public class NullFriendlyTextBuilderTest {

  private static final String STREET = "STREET";
  private static final String CITY = "CITY";
  private static final String STATE = "STATE";
  private static final String ZIP = "ZIP";

  @Test
  public void testHappyPath() {
    assertEquals(join(new String[]{STREET, CITY, STATE, ZIP}, ' '),
        new NullFriendlyTextBuilder().buildText(STREET, CITY, STATE, ZIP));
  }

  @Test
  public void testSomeNulls() {
    assertEquals(join(new String[]{CITY, ZIP}, ' '),
        new NullFriendlyTextBuilder().buildText(null, CITY, null, ZIP));
    assertEquals(join(new String[]{STREET, STATE}, ' '),
        new NullFriendlyTextBuilder().buildText(STREET, null, STATE, null));
  }

  @Test
  public void testAllNulls() {
    assertEquals("",
        new NullFriendlyTextBuilder().buildText(null, null, null, null));
  }

}