package interpret.controllers;

import interpret.views.*;
import interpret.models.*;

import java.lang.reflect.*;

public class ClassController {
  private MainFrame mainFrame;
  private ConstructorModel constModel;
  private ObjectModel objectModel;

  public ClassController(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    constModel = new ConstructorModel();
    objectModel = new ObjectModel();
  }

  public void searchButton(String text) {
    if (isEmptyString(text)) return;

    Class<?> cls = null;
    try {
      cls = Class.forName(text);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    if (cls != null) {
      System.out.println(cls.getCanonicalName() + " is Found!!");
      System.out.println(cls.getSimpleName());
      System.out.println(cls.getName());
      Constructor<?>[] cons = cls.getConstructors();
      constModel.saveConstructors(cons);
      mainFrame.printConstructorList(cons);
    } else {
      System.err.println("No such type: \"" + text + "\"");
    }
  }

  public void selectButton(String constName) {
    Constructor<?> con = constModel.getConstructor(constName);
    if (con == null) return;

    mainFrame.printConstLabel(con.toString());
  }

  public boolean createButton() {
    String objName = mainFrame.getObjectName();
    String constName = mainFrame.getConstName();
    String params = mainFrame.getParamName();
    if (isEmptyString(objName)) {
      System.out.println("オブジェクト名を入力してください");
      return false;
    } else if (objectModel.isEntried(objName)) {
      System.out.println("同名のオブジェクトが既に存在します");
      return false;
    }

    Constructor<?> con = constModel.getConstructor(constName);
    if (con != null) {
      System.out.println("コンストラクタを見つけました");
    }

    if (!createObject(objName, con, params)) {
      return false; // オブジェクトの生成失敗
    }

    return true;
  }

  public void objectClearButton() {
    objectModel.clearObjects();
  }

  public void constClearButton() {
    constModel.clearConstructors();
  }

  private boolean isEmptyString(String str) {
    if (str.length() == 0) return true;
    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) == ' ')
        return true;
    }
    return false;
  }

  private boolean createObject(String objName, Constructor<?> con, String paramStr) {
   Type[] types = con.getGenericParameterTypes();

   /* デフォルトコンストラクタの場合 */
   if (types.length == 0) {
     try {
       Object obj = con.newInstance();
       objectModel.saveObject(objName, obj);
       return true;
     } catch (IllegalAccessException e) {
       e.printStackTrace();
       return false;
     } catch (InstantiationException e) {
       e.printStackTrace();
       return false;
     } catch (InvocationTargetException e) {
       e.printStackTrace();
       return false;
     }
   }

   String[] params = csvParse(paramStr);
   if (types.length != params.length) {
     System.out.println("引数の数が一致しません");
     return false;
   }

    return true;
  }

  private String[] csvParse(String csv) {
    return csv.split(",", 0);
  }

}
