package ex01_04;

import java.awt.event.*;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.MenuItem;

class PropertyPopupMenu extends PopupMenu implements ActionListener {
  private Menu fontMenu;
  private Menu fontSizeMenu;
  private Menu fontColorMenu;
  private Menu backColorMenu;
  private MenuItem exitMenu;

  private MenuItem[] fontSubMenus;
  private MenuItem[] fontSizeSubMenus;
  private MenuItem[] fontColorSubMenus;
  private MenuItem[] backColorSubMenus;

  PropertyPopupMenu() {
    fontMenu = new Menu("Font");
    fontSizeMenu = new Menu("Font size");
    fontColorMenu = new Menu("Font color");
    backColorMenu = new Menu("Back color");
    exitMenu = new MenuItem("Exit");

    /* setting for font menu */
    fontSubMenus = new MenuItem[Property.fontSet.length];
    for (int i = 0; i < fontSubMenus.length; ++i) {
      fontSubMenus[i] = new MenuItem(Property.fontSet[i]);
    }

    /* setting for font size menu */
    fontSizeSubMenus = new MenuItem[Property.fontSizes.length];
    for (int i = 0; i < fontSizeSubMenus.length; ++i) {
      fontSizeSubMenus[i] = new MenuItem(Property.fontSizes[i]);
    }

    /* setting for font color menu */
    fontColorSubMenus = new MenuItem[Property.colorStrs.length];
    for (int i = 0; i < fontColorSubMenus.length; ++i) {
      fontColorSubMenus[i] = new MenuItem(Property.colorStrs[i]);
    }

    /* setting for back color menu */
    backColorSubMenus = new MenuItem[Property.colorStrs.length];
    for (int i = 0; i < backColorSubMenus.length; ++i) {
      backColorSubMenus[i] = new MenuItem(Property.colorStrs[i]);
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object eventObject = e.getSource();
    DigitalClock parent = (DigitalClock)getParent();

    if (eventObject == exitMenu) {
      parent.saveProperty();
      System.exit(0);
    }

    /* change font */
    for (MenuItem fontSubMenu : fontSubMenus) {
      if (eventObject == fontSubMenu) {
        String fontName = fontSubMenu.getLabel();
        parent.changeClockFont(fontName);
      }
    }
    /* change font size */
    for (MenuItem fontSizeSubMenu : fontSizeSubMenus) {
      if (eventObject == fontSizeSubMenu) {
        int fontSize = Integer.valueOf(fontSizeSubMenu.getLabel());
        parent.changeClockFontSize(fontSize);
      }
    }
    /* change font color */
    for (MenuItem fontColorSubMenu : fontColorSubMenus) {
      if (eventObject == fontColorSubMenu) {
        String fontColorName = fontColorSubMenu.getLabel();
        parent.changeFontColor(fontColorName);
      }
    }
    /* change back color */
    for (MenuItem backColorSubMenu : backColorSubMenus) {
      if (eventObject == backColorSubMenu) {
        String backColorName = backColorSubMenu.getLabel();
        parent.changeBackColor(backColorName);
      }
    }
  }

  void init() {
    add(fontMenu);
    for (MenuItem fontSubMenu : fontSubMenus) {
      fontMenu.add(fontSubMenu);
      fontSubMenu.addActionListener(this);
    }

    add(fontSizeMenu);
    for (MenuItem fontSizeSubMenu : fontSizeSubMenus) {
      fontSizeMenu.add(fontSizeSubMenu);
      fontSizeSubMenu.addActionListener(this);
    }

    add(fontColorMenu);
    for (MenuItem fontColorSubMenu : fontColorSubMenus) {
      fontColorMenu.add(fontColorSubMenu);
      fontColorSubMenu.addActionListener(this);
    }

    add(backColorMenu);
    for (MenuItem backColorSubMenu : backColorSubMenus) {
      backColorMenu.add(backColorSubMenu);
      backColorSubMenu.addActionListener(this);
    }

    add(exitMenu);
    exitMenu.addActionListener(this);
  }
}
