package ch04.ex04_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortHarnessTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testSortArray1() {
    String[] testData = new String[3];
    testData[0] = "Yamada Taro";
    testData[1] = "Yamada";
    testData[2] = "Yamada Hanako";

    SortHarness<String> sHarness = new SortString(testData);
    sHarness.sort();
    String[] expected = new String[3];
    expected[0] = "Yamada";
    expected[1] = "Yamada Taro";
    expected[2] = "Yamada Hanako";

    assertArrayEquals(expected, testData);
  }
}
