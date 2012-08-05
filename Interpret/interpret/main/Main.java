/* main.java */

package interpret.main;

import interpret.controllers.*;
import interpret.views.*;

class Main {
  public static void main(String[] args) {
    ClassController classController = new ClassController();
    new MainFrame(classController);
  }
}
