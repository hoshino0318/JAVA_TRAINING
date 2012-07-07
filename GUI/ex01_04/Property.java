package ex01_04;

import java.awt.*;
import java.io.*;

class Property implements Serializable {
  private Font font;
  private Color fontColor;
  private Color backColor;

  static final String[] fontSizes = {"40", "60", "100", "150", "200", "300"};
  static final String[] colorStrs = ColorUtil.getColorStrs();
  static final String[] fontSet = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

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
    return getIndex(font.getName(), fontSet);
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
  
  static byte[] property2Bytes(Property p) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);    
      oos.writeObject(p);    
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return baos.toByteArray();
  }
  
  static Property bytes2Property(byte[] raw) {
    ByteArrayInputStream bais = new ByteArrayInputStream(raw);
    Property p = null;
    
    try {
      ObjectInputStream ois = new ObjectInputStream(bais);
      p = (Property)ois.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    return p;
  }  
}
