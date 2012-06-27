package ch13.ex13_04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;

class StringToWrapper {
  private List<Object> list;

  private static final String javaLangPkg = "java.lang.";

  StringToWrapper() {
    this.list = new ArrayList<Object>();
  }

  /**
   * 入力 line を受け取り list に value を持った
   * type(Wrapper) を追加する
   * @param line
   * @throws IllegalInputLine
   */
  void addWrapperValue(String line)
      throws IllegalInputLine
  {
   String[] strs = line.split(" ");

   /* "type value" の形式でないとき */
   if (strs.length != 2)
     throw new IllegalInputLine(line);

   String typeStr = javaLangPkg + strs[0];
   String valueStr = strs[1];

   try {
     /* typeStr からクラスオブジェクトを取得 */
     Class<?> wrapperClass = Class.forName(typeStr);
     /* String オブジェクトを取るコンストラクタを取得 */
     Constructor<?> wrapperConstructor = wrapperClass.getConstructor(String.class);
     /* インスタンスを生成し，list に追加 */
     list.add(wrapperConstructor.newInstance(valueStr));
   } catch (ClassNotFoundException e) {
     throw new IllegalInputLine("Class: " + typeStr + " was not found");
   } catch (NoSuchMethodException e) {
     throw new IllegalInputLine("Constructor: " + typeStr + "(String arg) was not found");
   } catch (IllegalAccessException e) {
     e.printStackTrace();
   } catch (InstantiationException e) {
     e.printStackTrace();
   } catch (InvocationTargetException e) {
     e.printStackTrace();
   }
  }

  /**
   * すべての値(value)を表示
   */
  void showAllValue() {
    for (Object obj : list) {
      System.out.println(obj.toString());
    }
  }

  /**
   * クラス名(type)と値(value)と表示
   */
  void showAllTypeAndValue() {
    for (Object obj : list) {
      System.out.println(obj.getClass().getName() + " " + obj.toString());
    }
  }

  public static void main(String[] fpaths) {
    StringToWrapper strToWrap = new StringToWrapper();

    for (String fpath : fpaths) {
      try {
        FileReader fread = new FileReader(fpath);
        BufferedReader buf = new BufferedReader(fread);
        String line;
        while ((line = buf.readLine()) != null) {
          strToWrap.addWrapperValue(line);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (IllegalInputLine e) {
        e.printStackTrace();
      }
    }

    strToWrap.showAllTypeAndValue();
  }

  /**
   * 入力行が不正であることを示す例外
   */
  class IllegalInputLine extends Exception {
    IllegalInputLine(String msg) {
      super(msg);
    }
  }
}
