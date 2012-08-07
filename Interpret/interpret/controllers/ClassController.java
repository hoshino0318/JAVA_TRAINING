package interpret.controllers;

import interpret.views.*;

import java.lang.reflect.*;

public class ClassController {
  private MainFrame mainFrame;

  public ClassController(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }

  public void searchButton(String text) {
    if (text.equals("")) return;

    Class<?> cls = null;
    try {
      cls = Class.forName(text);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    if (cls != null) {
      System.out.println(cls.getCanonicalName() + " is Found!!");
      Constructor<?>[] cons = cls.getConstructors();
      mainFrame.printConstructorList(cons);
    } else {
      System.err.println("No such type: \"" + text + "\"");
    }
  }
}
