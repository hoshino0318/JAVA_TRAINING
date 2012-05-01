package ch04.ex04_01;

class GasTank implements EnergySource {
  private int gasTank = 0; 

  @Override
  public void charge(int source) {
    System.out.println("ガスタンクをチャージします");
    this.gasTank += source;
  }

  @Override
  public boolean empty() {
    if (gasTank > 0)
      return false;
    
    System.out.println("ガスタンクは空です");
    return true;
  }
}
