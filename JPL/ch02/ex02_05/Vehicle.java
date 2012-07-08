package ch02.ex02_05;

class Vehicle {
  private int speed; // 現在のスピード
  private int direction; // 現在の方向 (0 - 359 で表現)
  private String owner; // 所有者
  private long idNum; // 識別子

  private static final int FULL_DEGREE = 360;
  private static long nextID = 0; // 全体の識別子

  {
    idNum = nextID++;
  }

  /** direction を 0 - 359 の間の角度に調整する */
  private static int ajustDirection(int direction) {
    int ret = 0;

    if (direction >= 0) {
      ret = direction % FULL_DEGREE;
    } else {
      ret = FULL_DEGREE + (direction % FULL_DEGREE);
    }

    return ret;
  }

  Vehicle() {
    this(0, 0, null);
  }

  Vehicle(int speed, int direction, String owner) {
    if (owner == null) {
      speed = 0;
      direction = 0;
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

  int getDirection() {
    return direction;
  }

  void changeDirection(int direction) {
    this.direction = ajustDirection(direction);
  }

  String getOwner() {
    return owner;
  }

  void changeOwner(String newOwner) {
    this.owner = newOwner;
  }

  long getID() {
    return idNum;
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

  public static void main(String[] args) {
    /* 3 台の乗り物を作成 */
    Vehicle v1 = new Vehicle(10, 30, "Alice");
    Vehicle v2 = new Vehicle(-1, 370, "Bob");
    Vehicle v3 = new Vehicle(100, -1, null);

    /* フィールドを表示 */
    v1.printFields();
    v2.printFields();
    v3.printFields();
  }
}
