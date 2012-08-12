package ch13.ex13_01;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyStringTest {

  @Test
  public void testCount1() {
    MyString myStr = new MyString("hogehoge");
    int expectedCount = 2;
    char targetChar = 'h';

    assertEquals(expectedCount, myStr.count(targetChar));
  }

  @Test
  public void testCount2() {
    MyString myStr = new MyString("hogehoge");
    int expectedCount = 0;
    char targetChar = 'y';

    assertEquals(expectedCount, myStr.count(targetChar));
  }

  @Test
  public void testCount3() {
    MyString myStr = new MyString("122333444455555");
    int expectedCount = 3;
    char targetChar = '3';

    assertEquals(expectedCount, myStr.count(targetChar));
  }

}
