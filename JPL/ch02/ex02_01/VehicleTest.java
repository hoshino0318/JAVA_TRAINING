package ch02.ex02_01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {
  private Vehicle vehicle;


  @Before
  public void setUp() throws Exception {
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
}
