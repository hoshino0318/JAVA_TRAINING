package ex02_04;

import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class ClockMenuBar extends JMenuBar {
  private static final long serialVersionUID = -7749741919979618811L;

  private DigitalClock parent;
  private JMenu menu;
  private JMenuItem propertyItem;
  private JMenuItem exitItem;
  private PropertyDialog dialog;

  ClockMenuBar(DigitalClock parent, PropertyDialog dialog) {
    this.parent = parent;
    this.dialog = dialog;
    menu = new JMenu("Menu");
    menu.setMnemonic('m');
    propertyItem = new JMenuItem("Property");
    propertyItem.setMnemonic('p');
    exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic('e');
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
        parent.exitClock();
      }
    }
  }
}
