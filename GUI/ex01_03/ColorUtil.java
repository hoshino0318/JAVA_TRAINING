package ex01_03;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

class ColorUtil {
  private static final String[] colorStrs;

  static {
    Class<Color> c = Color.class;
    Field[] fields = c.getFields();
    Set<String> colorStrSet = new TreeSet<String>();
    for (Field field : fields) {
      if (field.getDeclaringClass() == Color.class) {
        if (Character.isUpperCase(field.getName().charAt(0))) {
          colorStrSet.add(field.getName());
        }
      }
    }

    colorStrs = new String[colorStrSet.size()];
    int index = 0;
    for (String colorStr : colorStrSet) {
      colorStrs[index++] = colorStr;
    }
  }

  /** 色情報を文字列の配列として返す */
  static String[] getColorStrs() {
    return colorStrs;
  }

  /** 文字列から Color インスタンスを返す */
  static Color getColorInstance(String colorName) {
    Color ret = null;

    for (String colorStr : colorStrs) {
      if (colorStr.equals(colorName)) {
        Class<Color> colorClass = Color.class;
        try {
          Field filed = colorClass.getField(colorName);
          ret = (Color)filed.get(Color.WHITE);
        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    return ret;
  }
}
