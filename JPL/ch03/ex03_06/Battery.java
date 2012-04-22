package ch03.ex03_06;

public class Battery extends EnergySource {

  @Override
  void charge(int source) {
    System.out.println("バッテリーをチャージします");
    this.source += source;
  }

  @Override
  boolean empty() {
    if (source > 0)
      return false;
    
    System.out.println("バッテリーは空です");
    return true;
  }

}
