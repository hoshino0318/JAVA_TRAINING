package ch03.ex03_07;

import static org.junit.Assert.*;
import org.junit.Test;

public class ColorAttrTest {

  @Test
  public void testEqualsReturnTrue() {
    ColorAttr ca1 = new ColorAttr("red", new ScreenColor("red"));
    ColorAttr ca2 = new ColorAttr("red", new ScreenColor("red"));

    assertTrue(ca1.equals(ca2));
  }

  @Test
  public void testEqualsReturnFalse() {
    ColorAttr ca1 = new ColorAttr("red", new ScreenColor("red"));
    ColorAttr ca2 = new ColorAttr("blue", new ScreenColor("blue"));

    assertFalse(ca1.equals(ca2));
  }

  @Test
  public void testHashCodeReturnTrue() {
    ColorAttr ca1 = new ColorAttr("red", new ScreenColor("red"));
    ColorAttr ca2 = new ColorAttr("red", new ScreenColor("red"));

    assertEquals(ca1.hashCode(), ca2.hashCode());
  }

  @Test
  public void testHashCodeReturnFalse() {
    ColorAttr ca1 = new ColorAttr("red", new ScreenColor("red"));
    ColorAttr ca2 = new ColorAttr("blue", new ScreenColor("blue"));

    assertFalse(ca1.hashCode() == ca2.hashCode());
  }
}
