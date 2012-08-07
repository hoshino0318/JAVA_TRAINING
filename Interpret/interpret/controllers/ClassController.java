package interpret.controllers;

import interpret.views.*;
import interpret.models.*;

import java.lang.reflect.*;

public class ClassController {
  private MainFrame mainFrame;
  private ConstructorModel constModel;

  public ClassController(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
    constModel = new ConstructorModel();
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

    System.out.println("コンストラクタを見つけました");
    mainFrame.printConstLabel(con.toString());
  }

  public void constClear() {
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
}
