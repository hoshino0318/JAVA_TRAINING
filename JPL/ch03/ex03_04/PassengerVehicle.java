package ch03.ex03_04;

public class PassengerVehicle extends Vehicle {
  private int seatNum; // シートの数 (0 以上)
  private int passengerNum;  // 乗客の数 (0 以上，シートの数以下)


  public PassengerVehicle(int seatNum) {
    this(0, 0.0, null, seatNum, 0);
  }

  public PassengerVehicle(int seatNum, int passengerNum) {
    this(0, 0.0, null, seatNum, passengerNum);
  }

  public PassengerVehicle(String owner, int seatNum, int passengerNum) {
    this(0, 0.0, owner, seatNum, passengerNum);
  }

  public PassengerVehicle(int speed, double direction, String owner,
      int seatNum, int passengerNum) {
    super(speed, direction, owner);
    if (seatNum < passengerNum) {
      throw new IllegalArgumentException("'seatNum' must be larger than 'passengerNum'");
    } else if (seatNum < 0) {
      throw new IllegalArgumentException("'seatNum' must be positive integer");
    } else if (passengerNum < 0) {
      throw new IllegalArgumentException("'passengerNum' must be positive integer");
    }

    this.seatNum = seatNum;
    this.passengerNum = passengerNum;
  }

  final public int getSeatNum() {
    return seatNum;
  }

  final public int getPanssengersNum() {
    return passengerNum;
  }

  final public boolean getOutPassengers(int passengerNum) {
    if (this.passengerNum < passengerNum) return false;

    this.passengerNum -= passengerNum;
    return true;
  }

  public String toString() {
    String ret = super.toString();
    ret += ", SeatNum: " +  seatNum;
    ret += ", PassengerNum: " + passengerNum;

    return ret;
  }
}
