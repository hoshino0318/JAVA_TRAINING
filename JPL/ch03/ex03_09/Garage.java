package ch03.ex03_09;

public class Garage implements Cloneable {
  protected Vehicle[] garage;
  
  public Garage(Vehicle... vehicles) {
    //this.garage = new Vehicle[vehicles.length];
    this.garage = vehicles;
  }
  
  public Garage clone() {
    try {
      Garage nObj = (Garage) super.clone();
      nObj.garage = new Vehicle[garage.length];
      for (int i = 0; i < garage.length; ++i) {
        nObj.garage[i] = garage[i].clone();
      }
      
      return nObj; 
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.toString());
    }
  }
  
  public static void main(String[] args) {
    Garage g1 = new Garage(new Vehicle("Alice"), new Vehicle("Bob"));
    Garage g2 = g1.clone();
    
    System.out.println(g1.garage[0]);
    System.out.println(g2.garage[0]);
    
    g2.garage[0] = new Vehicle("Chris");
    
    System.out.println();
    System.out.println(g1.garage[0]);
    System.out.println(g2.garage[0]);
  }
}
