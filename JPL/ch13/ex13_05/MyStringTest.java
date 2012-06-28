package ch13.ex13_05;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MyStringTest {
  private String expected;
  private String inputStr;

  public MyStringTest(String expected, String inputStr) {
    this.expected = expected;
    this.inputStr = inputStr;
  }

  @Parameters
  public static List<Object[]> data(){
    Object[][] data = new Object[][] {
        {"123", "123"},
        {"1,234", "1234"},
        {"12,345", "12345"},
        {"123,456", "123456"},
        {"1,234,567", "1234567"},
    };

    return Arrays.asList(data);
  }

  @Test
  public void testSeparateStr() {
    assertEquals(expected, MyString.separateStr(inputStr));
  }
}
