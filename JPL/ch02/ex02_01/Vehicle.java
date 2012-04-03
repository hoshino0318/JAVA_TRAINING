package ch02.ex02_01;

class Vehicle {  
  private int speed; // 現在のスピード
  private int direction; // 現在の方向 (0 - 359 で表現) 
  private String owner; // 所有者
  
  private static final int FULL_DEGREE = 360;
  
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
}
