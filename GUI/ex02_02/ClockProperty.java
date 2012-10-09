package ex02_02;

import java.awt.*;

class ClockProperty {
  public static final String[] fontFamily;
  public static final String[] fontSizes = {"10", "20", "30", "40", "50", "60"};

  private Font font;

  static {
    fontFamily =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  }

  ClockProperty(Font font) {
    this.font = font;
  }

  Font getFont() {
    return font;
  }

  void setFont(String fontName) {
    int fontStyle = font.getStyle();
    int fontSize  = font.getSize();
    font = new Font(fontName, fontStyle, fontSize);
  }
}
