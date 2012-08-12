package ch03.ex03_08;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {
  private static Vehicle vehicle;
  private static final double prec = 1.0e-8;

  @BeforeClass
  public static void setUp() throws Exception {
    vehicle = new Vehicle();
  }

  @Test
  public void testAjustDirection1() {
    vehicle.changeDirection(300.0);

    assertEquals(vehicle.getDirection(), 300.0, prec);
  }

  @Test
  public void testAjustDirection2() {
    vehicle.changeDirection(1000.0);

    assertEquals(vehicle.getDirection(), 280.0, prec);
  }

  @Test
  public void testAjustDirection3() {
    vehicle.changeDirection(-1.0);

    assertEquals(vehicle.getDirection(), 359.0, prec);
  }

  @Test
  public void testAjustDirection4() {
    vehicle.changeDirection(-1000.0);

    assertEquals(vehicle.getDirection(), 80.0, prec);
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
    vehicle.changeDirection(100.0);
    String expected = "Owner: no one, Speed: 0, Direction: 100.0, ID: 0";
    assertEquals(vehicle.toString(), expected);
  }

  @Test
  public void testChangeSpeed() {
    vehicle.changeSpeed(100);
    int expectedSpeed = 100;
    assertEquals(vehicle.getSpeed(), expectedSpeed);
  }

  @Test
  public void testTurn1() {
    vehicle.changeDirection(0.0);
    vehicle.turn(90.0);

    double expectedDirection = 90.0;
    assertEquals(vehicle.getDirection(), expectedDirection, prec);
  }

  @Test
  public void testTurn2() {
    vehicle.changeDirection(0.0);
    vehicle.turn(Vehicle.TURN_LEFT);

    double expectedDirection = 270.0;
    assertEquals(vehicle.getDirection(), expectedDirection, prec);
  }

  @Test
  public void testClone1() {
    Vehicle v1 = new Vehicle("Bob");
    Vehicle v2 = v1.clone();

    assertEquals(v1.getOwner(), v2.getOwner());
  }

  @Test
  public void testClone2() {
    Vehicle v1 = new Vehicle("Bob");
    Vehicle v2 = v1.clone();

    v2.changeSpeed(10);

    assertFalse(v1.getSpeed() == v2.getSpeed());
  }

  @Test
  public void testClone3() {
    Vehicle v1 = new Vehicle("Bob");
    Vehicle v2 = v1.clone();

    v2.changeOwner("Yamada");

    assertFalse(v1.getOwner().equals(v2.getOwner()));
  }
}
