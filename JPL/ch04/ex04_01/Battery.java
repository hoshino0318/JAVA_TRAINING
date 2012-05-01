package ch04.ex04_01;

class Battery implements EnergySource {
  private int battery = 0; 

  @Override
  public void charge(int source) {
    System.out.println("バッテリーをチャージします");
    this.battery = source; 
  }

  @Override
  public boolean empty() {
    if (battery > 0)
      return false;
    
    System.out.println("バッテリは空です");
    return true;
  }
}
