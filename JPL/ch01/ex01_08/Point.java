package ch01.ex01_08;

class Point {
  public double x, y;
  
  public void clear() {
    x = 0.0;
    y = 0.0;
  }
  
  public double distance(Point that) {
    double xdiff = x - that.x;
    double ydiff = y - that.y;
    
    return Math.sqrt(xdiff * xdiff + ydiff * ydiff); 
  }
  
  public void move(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public void setPoint(Point p) {
    this.x = p.x;
    this.y = p.y;
  }
}
