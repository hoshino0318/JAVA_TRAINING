package ch13.ex13_06;

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
  private char delimiter;
  private int digit;

  public MyStringTest(String expected, String inputStr, char delimiter, int digit) {
    this.expected = expected;
    this.inputStr = inputStr;
    this.delimiter = delimiter;
    this.digit = digit;
  }

  @Parameters
  public static List<Object[]> data(){
    Object[][] data = new Object[][] {
        {"123", "123", ',', 3},
        {"1,234", "1234", ',', 3},
        {"12,345", "12345", ',', 3},
        {"1,23,45", "12345", ',', 2},
        {"123:456", "123456", ':', 3},
        {"1*2*3*4*5*6*7", "1234567", '*', 1},
        {"1*2*3*4*5*6*7", "1234567", '*', 0},
    };

    return Arrays.asList(data);
  }

  @Test
  public void testSeparateStr() {
    assertEquals(expected, MyString.separateStr(inputStr, delimiter, digit));
  }
}
