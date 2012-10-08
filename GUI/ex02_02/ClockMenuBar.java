package ex02_02;

import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class ClockMenuBar extends JMenuBar {
  private static final long serialVersionUID = -7749741919979618811L;

  private JMenu menu;
  private JMenuItem propertyItem;
  private JMenuItem exitItem;

  ClockMenuBar() {
    menu = new JMenu("Menu");
    propertyItem = new JMenuItem("Property");
    exitItem = new JMenuItem("Exit");

    add(menu);
    menu.add(propertyItem);
    menu.add(exitItem);

    exitItem.addActionListener(new ExitItemListener());
  }

  private class ExitItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == exitItem) {
        System.exit(0);
      }
    }
  }
}
