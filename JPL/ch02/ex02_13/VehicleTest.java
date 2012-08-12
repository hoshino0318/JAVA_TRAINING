package ch02.ex02_13;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {
  private static Vehicle vehicle;

  @BeforeClass
  public static void setUp() throws Exception {
    vehicle = new Vehicle();
  }

  @Test
  public void testAjustDirection1() {
    vehicle.changeDirection(300);

    assertEquals(vehicle.getDirection(), 300);
  }

  @Test
  public void testAjustDirection2() {
    vehicle.changeDirection(1000);

    assertEquals(vehicle.getDirection(), 280);
  }

  @Test
  public void testAjustDirection3() {
    vehicle.changeDirection(-1);

    assertEquals(vehicle.getDirection(), 359);
  }

  @Test
  public void testAjustDirection4() {
    vehicle.changeDirection(-1000);

    assertEquals(vehicle.getDirection(), 80);
  }

  @Test
  public void testId1() {
    assertEquals(vehicle.getID(), 0);
  }

  @Test
  public void testMaxID() {
    assertEquals(Vehicle.maxID(), 2);
  }

  @Test
  public void testToString() {
    vehicle.changeDirection(100);
    String expected = "Owner: no one, Speed: 0, Direction: 100, ID: 0";
    assertEquals(vehicle.toString(), expected);
  }


}
