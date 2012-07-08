package ch03.ex03_07;

/** ダミークラス */
public class ScreenColor {
  public ScreenColor(Object object) {
  }

  public boolean equals(ScreenColor screenColor) {
    System.out.println("debug: ScreenColor");
    return true;
  }

  public int hashCode() {
    return 0;
  }
}
