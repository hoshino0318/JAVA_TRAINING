package ch03.ex03_04;

import static org.junit.Assert.*;
import org.junit.Test;

public class PassengerVehicleTest {

  @Test
  public void testConstructorSucceed() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", 5, 1);

    assertEquals(pV1.getSeatNum(), 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException1() {
    new PassengerVehicle("Bob", 1, 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException2() {
    new PassengerVehicle("Bob", -1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException3() {
    new PassengerVehicle("Bob", 1, -1);
  }

}
