package ch13.ex13_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStringTest {

  @Test
  public void testCountStr1() {
    MyString myStr = new MyString("hogehoge");
    int expectedCount = 2;
    String targetStr = "hoge";

    assertEquals(expectedCount, myStr.countStr(targetStr));
  }

  @Test
  public void testCountStr2() {
    MyString myStr = new MyString("122333444455555");
    int expectedCount = 5;
    String targetStr = "5";

    assertEquals(expectedCount, myStr.countStr(targetStr));
  }

  @Test
  public void testCountStr3() {
    MyString myStr = new MyString("yyyyy");
    int expectedCount = 4;
    String targetStr = "yy";

    assertEquals(expectedCount, myStr.countStr(targetStr));
  }

}
