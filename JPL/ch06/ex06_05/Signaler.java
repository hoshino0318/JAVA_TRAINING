package ch06.ex06_05;

import java.awt.Color;

public enum Signaler {
  GRN {
    Color getColor() {
      return Color.GREEN;
    }
  },    
    
  YLW {
    Color getColor() {
      return Color.YELLOW;
    }
  },
  
  RED {
    Color getColor() {
      return Color.RED;
    }
  };
  
  abstract Color getColor();
  
}
