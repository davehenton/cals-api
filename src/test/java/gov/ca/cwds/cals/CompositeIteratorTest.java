package gov.ca.cwds.cals;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author CWDS TPT-2
 */
public class CompositeIteratorTest {

  private Iterator<String> iterator1;
  private Iterator<String> iterator2;
  private Iterator<String> iterator3;
  private Iterator<String> iterator4;

  @Before
  public void setUp() {
    iterator1 = new ArrayList<String>() {{
      add("a");
      add("b");
      add("c");
    }}.iterator();
    iterator2 = new ArrayList<String>().iterator(); // intentionally empty
    iterator3 = new ArrayList<String>() {{
      add("d");
      add("e");
    }}.iterator();
    iterator4 = new ArrayList<String>() {{
      add("f");
    }}.iterator();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty() {
    new CompositeIterator<>();
  }

  @Test
  public void testIteration() {
    int iterationCount = 0;
    CompositeIterator<String> compositeIterator = new CompositeIterator<>(iterator1, iterator2,
        iterator3, iterator4);
    while (compositeIterator.hasNext()) {
      ++iterationCount;
      compositeIterator.next();
    }
    assertEquals(6, iterationCount);
  }
}
