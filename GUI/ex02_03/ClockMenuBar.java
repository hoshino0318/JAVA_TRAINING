package ex02_03;

import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class ClockMenuBar extends JMenuBar {
  private static final long serialVersionUID = -7749741919979618811L;

  private JMenu menu;
  private JMenuItem propertyItem;
  private JMenuItem exitItem;
  private PropertyDialog dialog;

  ClockMenuBar(PropertyDialog dialog) {
    this.dialog = dialog;
    menu = new JMenu("Menu");
    propertyItem = new JMenuItem("Property");
    exitItem = new JMenuItem("Exit");

    add(menu);
    menu.add(propertyItem);
    menu.add(exitItem);

    MenuItemListener menuItemListener = new MenuItemListener();
    propertyItem.addActionListener(menuItemListener);
    exitItem.addActionListener(menuItemListener);
  }

  private class MenuItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == propertyItem) {
        dialog.setVisible(true);
      } else if (source == exitItem) {
        System.exit(0);
      }
    }
  }
}
