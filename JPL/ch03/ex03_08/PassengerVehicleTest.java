package ch03.ex03_08;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PassengerVehicleTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testConstructorSucceed() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", 5, 1);

    assertEquals(pV1.getSeatNum(), 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException1() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", 1, 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException2() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", -1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorThrowException3() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", 1, -1);
  }

  @Test
  public void testClone() {
    PassengerVehicle pV1 = new PassengerVehicle("Bob", 3, 1);
    PassengerVehicle pV2 = pV1.clone();

    assertEquals(pV1.getSeatNum(), pV2.getSeatNum());
  }
}
