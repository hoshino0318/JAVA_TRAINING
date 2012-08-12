package ch01.ex01_16;

import org.junit.Before;
import org.junit.Test;

public class MyUtilitiesTest {
  private MyUtilities utility;

  @Before
  public void setUp() throws Exception {
    utility = new MyUtilities();
  }

  @Test
  public void testThrowBadDataSetException() {
    try {
      utility.getDataSet("test");
    } catch (BadDataSetException expected) {
      // succeed
      System.err.println(expected.getMessage());
    }
  }
}
