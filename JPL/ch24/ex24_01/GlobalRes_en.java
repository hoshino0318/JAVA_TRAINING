package ch24.ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_en extends ListResourceBundle {
  public Object[][] getContents() {
    return contents;
  }
  private static final Object[][] contents = {
    { GlobalRes.HELLO, "Hello" },
    { GlobalRes.GOODBYE, "Goodbye" },
  };
}
