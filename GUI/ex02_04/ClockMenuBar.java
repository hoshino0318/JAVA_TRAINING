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
  private JMenu help;
  private JMenuItem keyShortCutsItem;
  private PropertyDialog propertyDialog;
  private HelpDialog helpDialog;

  ClockMenuBar(DigitalClock parent, PropertyDialog propertyDialog, HelpDialog helpDialog) {
    this.parent         = parent;
    this.propertyDialog = propertyDialog;
    this.helpDialog     = helpDialog;
    menu         = new JMenu("Menu");
    propertyItem = new JMenuItem("Property");
    exitItem     = new JMenuItem("Exit");
    help             = new JMenu("Help");
    keyShortCutsItem = new JMenuItem("Key Shortcuts");

    menu.setMnemonic('m');
    propertyItem.setMnemonic('p');
    exitItem.setMnemonic('e');
    help.setMnemonic('h');
    keyShortCutsItem.setMnemonic('k');

    menu.add(propertyItem);
    menu.add(exitItem);
    add(menu);
    help.add(keyShortCutsItem);
    add(help);

    MenuItemListener menuItemListener = new MenuItemListener();
    propertyItem.addActionListener(menuItemListener);
    exitItem.addActionListener(menuItemListener);
    keyShortCutsItem.addActionListener(menuItemListener);
  }

  private class MenuItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == propertyItem) {
        propertyDialog.setVisible(true);
      } else if (source == exitItem) {
        parent.exitClock();
      } else if (source == keyShortCutsItem) {
        helpDialog.setVisible(true);
      }
    }
  }
}
