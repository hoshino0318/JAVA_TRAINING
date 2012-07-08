package ch03.ex03_09;

class Vehicle implements Cloneable {
  private int speed; // 現在のスピード
  private double direction; // 現在の方向 (0.0 - 359.99... で表現)
  private String owner; // 所有者
  private long idNum; // 識別子

  private static final double FULL_DEGREE = 360.0;
  private static long nextID = 0; // 全体の識別子
  static final int TURN_LEFT = 0;
  static final int TURN_RIGHT = 1;

  {
    idNum = nextID++;
  }

  /** direction を 0.0 - 359.99.. の間の角度に調整する */
  private static double ajustDirection(double direction) {
    double ret = 0;

    if (direction >= 0) {
      ret = direction % FULL_DEGREE;
    } else {
      ret = FULL_DEGREE + (direction % FULL_DEGREE);
    }

    return ret;
  }

  /** 今まで使われた識別番号の最大値を返す */
  public static long maxID() {
    if (nextID == 0) {
      return 0;
    } else {
      return nextID - 1;
    }
  }

  public Vehicle() {
    this(0, 0.0, null);
  }

  public Vehicle(String owner) {
    this(0, 0.0, owner);
  }

  public Vehicle(int speed, double direction, String owner) {
    if (owner == null) {
      speed = 0;
      direction = 0.0;
    }

    this.speed = speed;
    this.owner = owner;
    this.direction = ajustDirection(direction);
  }

  int getSpeed() {
    return speed;
  }

  void changeSpeed(int speed) {
    this.speed = speed;
  }

  void stop() {
    changeSpeed(0);
  }

  double getDirection() {
    return direction;
  }

  void changeDirection(double direction) {
    this.direction = ajustDirection(direction);
  }

  String getOwner() {
    return owner;
  }

  long getID() {
    return idNum;
  }

  void changeOwner(String newOwner) {
    this.owner = newOwner;
  }

  public void printFields() {
    String owner = this.owner;
    if (owner == null) {
      owner = "no one";
    }

    System.out.println("Owner: " + owner);
    System.out.println("Speed: " + speed);
    System.out.println("Direction: " + direction);
    System.out.println("ID: " + idNum);
    System.out.println();
  }

  void turn(double degree) {
    this.direction = ajustDirection(this.direction + degree);
  }

  void turn(int degree) {
    switch(degree) {
    case TURN_LEFT:
      turn(-90.0);
      break;
    case TURN_RIGHT:
      turn(90.0);
      break;
    default :
      break;
    }
  }

  public String toString() {
    String ret = "";

    String owner = this.owner;
    if (owner == null) {
      owner = "no one";
    }

    ret += "Owner: " + owner + ", ";
    ret += "Speed: " + speed + ", ";
    ret += "Direction: " + direction + ", ";
    ret += "ID: " + idNum;

    return ret;
  }

  public Vehicle clone() {
    try {
      Vehicle nObj = (Vehicle) super.clone();
      //nObj.owner = new String(owner);
      return nObj;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.toString());
    }
  }
}
