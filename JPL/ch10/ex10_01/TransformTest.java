package ch10.ex10_01;

import static org.junit.Assert.*;
import org.junit.Test;

public class TransformTest {

  @Test
  public void test1Str2JavaString() {
    String source = "\\\"\"";
    String expected = "\\\\\\\"\\\"";

    assertEquals(expected, Transform.str2JavaString(source));
  }

  @Test
  public void test2Str2JavaString() {
    String source = "\\\\\\\\";
    String expected = "\\\\\\\\\\\\\\\\";

    assertEquals(expected, Transform.str2JavaString(source));
  }

}
