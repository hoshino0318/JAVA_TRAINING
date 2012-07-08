package ch06.ex06_05;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class SignalerTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testGRN() {
    Signaler grn = Signaler.GRN;

    assertEquals(Color.GREEN, grn.getColor());
  }

  @Test
  public void testYLW() {
    Signaler ylw = Signaler.YLW;

    assertEquals(Color.YELLOW, ylw.getColor());
  }

  @Test
  public void testRED() {
    Signaler red = Signaler.RED;

    assertEquals(Color.RED, red.getColor());
  }

}
