package ch03.ex03_06;

public abstract class EnergySource {
  protected int source = 0;

  abstract void charge(int source);
  abstract boolean empty();
}
