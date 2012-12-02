package ex02_03;

import java.awt.event.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

class PropertyPopupMenu extends JPopupMenu implements ActionListener {
  private static final long serialVersionUID = -5886033453588110137L;
  private DigitalClock parent;
  private JMenu fontMenu;
  private JMenu fontSizeMenu;
  private JMenu fontColorMenu;
  private JMenu backColorMenu;
  private JMenuItem quitMenu;

  private JMenuItem[] fontSubMenus;
  private JMenuItem[] fontSizeSubMenus;
  private JMenuItem[] fontColorSubMenus;
  private JMenuItem[] backColorSubMenus;

  PropertyPopupMenu(DigitalClock parent) {
    this.parent = parent;
    fontMenu = new JMenu("Font");
    fontSizeMenu = new JMenu("Font Size");
    fontColorMenu = new JMenu("Font Color");
    backColorMenu = new JMenu("Background Color");
    quitMenu = new JMenuItem("Quit");

    add(fontMenu);
    fontSubMenus = new JMenuItem[ClockProperty.fontFamily.length];
    for (int i = 0; i < fontSubMenus.length; ++i) {
      fontSubMenus[i] = new JMenuItem(ClockProperty.fontFamily[i]);
      fontMenu.add(fontSubMenus[i]);
      fontSubMenus[i].addActionListener(this);
    }

    add(fontSizeMenu);
    fontSizeSubMenus = new JMenuItem[ClockProperty.fontSizes.length];
    for (int i = 0; i < fontSizeSubMenus.length; ++i) {
      fontSizeSubMenus[i] = new JMenuItem(ClockProperty.fontSizes[i]);
      fontSizeMenu.add(fontSizeSubMenus[i]);
      fontSizeSubMenus[i].addActionListener(this);
    }

    add(fontColorMenu);
    fontColorSubMenus = new JMenuItem[ClockProperty.colorFamily.length];
    for (int i = 0; i < fontColorSubMenus.length; ++i) {
      fontColorSubMenus[i] = new JMenuItem(ClockProperty.colorFamily[i]);
      fontColorMenu.add(fontColorSubMenus[i]);
      fontColorSubMenus[i].addActionListener(this);
    }

    add(backColorMenu);
    backColorSubMenus = new JMenuItem[ClockProperty.colorFamily.length];
    for (int i = 0; i < backColorSubMenus.length; ++i) {
      backColorSubMenus[i] = new JMenuItem(ClockProperty.colorFamily[i]);
      backColorMenu.add(backColorSubMenus[i]);
      backColorSubMenus[i].addActionListener(this);
    }

    add(quitMenu);
    fontSizeMenu.addActionListener(this);
    quitMenu.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == quitMenu)
      System.exit(0);

    /* Change font */
    for (JMenuItem fontItem : fontSubMenus) {
      if (source == fontItem) {
        parent.changeFont(fontItem.getText());
      }
    }
    /* Change font size */
    for (JMenuItem fontSizeItem : fontSizeSubMenus) {
      if (source == fontSizeItem) {
        int fontSize = Integer.valueOf(fontSizeItem.getText());
        parent.changeFontSize(fontSize);
      }
    }
    /* Change font color */
    for (JMenuItem fontColorItem : fontColorSubMenus) {
      if (source == fontColorItem) {
        parent.changeFontColor(fontColorItem.getText());
      }
    }
    /* Change background color */
    for (JMenuItem backColorItem : backColorSubMenus) {
      if (source == backColorItem) {
        parent.changeBackColor(backColorItem.getText());
      }
    }
  }
}
