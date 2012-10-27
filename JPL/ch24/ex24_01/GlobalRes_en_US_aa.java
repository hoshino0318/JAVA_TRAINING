package ch24.ex24_01;

import java.util.ResourceBundle;
import java.util.Enumeration;
import java.util.Vector;

public class GlobalRes_en_US_aa extends ResourceBundle {
  @Override
  protected Object handleGetObject(String key) {
    if (key.equals(GlobalRes.HELLO)) {
      return "haha";
    } else {
      return null;
    }
  }

  @Override
  public Enumeration<String> getKeys() {
    Vector<String> v = new Vector<String>();
    v.add(GlobalRes.HELLO);
    v.add(GlobalRes.GOODBYE);
    return v.elements();
  }
}
