package ch06.ex06_04;

import java.awt.Color;

public enum Signaler {
  GRN, YLW, RED;

  Color color;

  Signaler() {
    String name = this.name();
    if (name.equals("GRN")) {
      color = Color.GREEN;
    } else if (name.equals("YLW")) {
      color = Color.YELLOW;
    } else {
      color = Color.RED;
    }
  }

  Color getColor() {
    return color;
  }
}
