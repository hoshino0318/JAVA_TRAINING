package ex02_02;

import javax.swing.JDialog;
import javax.swing.JFrame;

class PropertyDialog extends JDialog {
  private static final long serialVersionUID = -8600365985749587342L;

  PropertyDialog(JFrame owner) {
    super(owner);
    setModal(true);
    setSize(100,100);
    setLocationRelativeTo(null);
  }
}
