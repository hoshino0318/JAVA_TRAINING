package ch10.ex10_03;

import static org.junit.Assert.*;
import org.junit.Test;

public class WorkIfElseTest {

  @Test
  public void testIsWorkingOnFRI() {
    Week week = Week.FRI;

    assertFalse(WorkIfElse.isWorkingOn(week));
  }

  @Test
  public void testIsWorkingOnWED() {
    Week week = Week.WED;

    assertTrue(WorkIfElse.isWorkingOn(week));
  }
}
