package ch10.ex10_03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorkSwitchTest {

  @Test
  public void testIsWorkingOnFRI() {
    Week week = Week.FRI;

    assertFalse(WorkSwitch.isWorkingOn(week));
  }

  @Test
  public void testIsWorkingOnWED() {
    Week week = Week.WED;

    assertTrue(WorkSwitch.isWorkingOn(week));
  }

}
