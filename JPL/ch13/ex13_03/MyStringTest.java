package ch13.ex13_03;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MyStringTest {
  private String[] expected;
  private String from;
  private char start;
  private char end;

  public MyStringTest(String[] expected, String from, char start, char end) {
    this.expected = expected;
    this.from = from;
    this.start = start;
    this.end = end;
  }

  @Parameters
  public static List<Object[]> data(){
    Object[][] data = new Object[][] {
        {new String[]{"<Hello>", "<Bonjour>"}, "<Hello><Bonjour>", '<', '>'},
        {new String[]{"<Hello>", "<Bonjour>"}, "11<Hello>22<Bonjour>", '<', '>'},
        {new String[]{"<Hello>", "<Bonjour>"}, "11<Hello>22<Bonjour>33", '<', '>'},
        {new String[]{"<Bonjour>"}, "11Hello>22<Bonjour>33", '<', '>'},
        {new String[]{"<Hello>"}, "11<Hello>22<Bonjour33", '<', '>'},
        {new String[]{}, "11>Hello>22<Bonjour33", '<', '>'},
        {new String[]{"<He<llo>"}, "11<He<llo>22>33", '<', '>'},
    };

    return Arrays.asList(data);
  }

  @Test
  public void testDelimited() {
    assertArrayEquals(expected, MyString.delimitedString(from, start, end));
  }
}
