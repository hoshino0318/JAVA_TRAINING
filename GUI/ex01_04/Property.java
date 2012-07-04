package ex01_04;

import java.awt.*;

class Property {
  private Font font;
  private Color fontColor;
  private Color backColor;

  static final String[] fontSizes = {"40", "60", "100", "150", "200", "300"};
  static final String[] colorStrs = ColorUtil.getColorStrs();
  static final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

  Property() {
    font = new Font("Arial", Font.BOLD, 60);
    fontColor = Color.BLACK;
    backColor = Color.WHITE;
  }

  Property(Property property) {
    font = property.font;
    fontColor = property.fontColor;
    backColor = property.backColor;
  }

  void setFont(Font font) {
    this.font = font;
  }

  Font getFont() {
    return font;
  }

  void setFontColor(Color fontColor) {
    this.fontColor = fontColor;
  }

  Color getFontColor() {
    return fontColor;
  }

  void setBackColor(Color backColor) {
    this.backColor = backColor;
  }

  Color getBackColor() {
    return backColor;
  }

  int fontIndex() {
    return getIndex(font.getName(), fonts);
  }

  int fontSizeIndex() {
    return getIndex(String.valueOf(font.getSize()), fontSizes);
  }

  int fontColorIndex() {
    return getIndex(ColorUtil.getStrFromColor(fontColor), colorStrs);
  }

  int backColorIndex() {
    return getIndex(ColorUtil.getStrFromColor(backColor), colorStrs);
  }

  private int getIndex(String key, String[] targets) {
    int index = -1;
    for (int i = 0; i < targets.length; ++i) {
      if (key.equals(targets[i])) {
        index = i;
        break;
      }
    }
    return index;
  }
}
