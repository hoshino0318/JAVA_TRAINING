package ch03.ex03_09;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GarageTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testClone1() {
    Garage g1 = new Garage(new Vehicle("Yamada"), new Vehicle("Hanako"));
    Garage g2 = g1.clone();

    assertEquals(g1.garage[0].getOwner(), g2.garage[0].getOwner());
  }

  @Test
  public void testClone2() {
    Garage g1 = new Garage(new Vehicle("Yamada"), new Vehicle("Hanako"));
    Garage g2 = g1.clone();

    g2.garage[1].changeOwner("Chris");
    String owner1 = g1.garage[1].getOwner();
    String owner2 = g2.garage[1].getOwner();

    assertFalse(owner1.equals(owner2));
  }
}
