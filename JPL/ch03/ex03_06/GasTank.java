package ch03.ex03_06;

public class GasTank extends EnergySource {

  @Override
  void charge(int source) {
    System.out.println("ガスタンクをチャージします");
    this.source += source;
  }

  @Override
  boolean empty() {
    if (source > 0)
      return false;

    System.out.println("ガスタンクは空です");
    return true;
  }
}
