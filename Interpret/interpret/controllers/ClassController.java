package interpret.controllers;

public class ClassController {
  public void searchButton(String text) {
    Class<?> cls = null;
    try {
      cls = Class.forName(text);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    if (cls != null) {
      System.out.println(cls.getCanonicalName() + " is Found!!");
    } else {
      System.err.println("No such type: " + text);
    }
  }
}
