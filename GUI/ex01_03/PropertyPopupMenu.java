package ex01_03;

import java.awt.event.*;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.MenuItem;

class PropertyPopupMenu extends PopupMenu implements ActionListener {
  private static final long serialVersionUID = -4702595175694349518L;
  private Menu fontMenu;
  private Menu fontSizeMenu;
  private Menu fontColorMenu;
  private Menu backColorMenu;
  private MenuItem exitMenu;

  private MenuItem[] fontSubMenus;
  private MenuItem[] fontSizeSubMenus;
  private MenuItem[] fontColorSubMenus;
  private MenuItem[] backColorSubMenus;

  private static final String fontSet[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  private static final String[] fontSizes = {"40", "60", "100", "150", "200", "300"};
  private static final String[] colorStrs = ColorUtil.getColorStrs();

  PropertyPopupMenu() {
    fontMenu = new Menu("Font");
    fontSizeMenu = new Menu("Font size");
    fontColorMenu = new Menu("Font color");
    backColorMenu = new Menu("Back color");
    exitMenu = new MenuItem("exit");

    /* setting for font menu */
    fontSubMenus = new MenuItem[fontSet.length];
    for (int i = 0; i < fontSubMenus.length; ++i) {
      fontSubMenus[i] = new MenuItem(fontSet[i]);
    }

    /* setting for font size menu */
    fontSizeSubMenus = new MenuItem[fontSizes.length];
    for (int i = 0; i < fontSizeSubMenus.length; ++i) {
      fontSizeSubMenus[i] = new MenuItem(fontSizes[i]);
    }

    /* setting for font color menu */
    fontColorSubMenus = new MenuItem[colorStrs.length];
    for (int i = 0; i < fontColorSubMenus.length; ++i) {
      fontColorSubMenus[i] = new MenuItem(colorStrs[i]);
    }

    /* setting for back color menu */
    backColorSubMenus = new MenuItem[colorStrs.length];
    for (int i = 0; i < backColorSubMenus.length; ++i) {
      backColorSubMenus[i] = new MenuItem(colorStrs[i]);
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object eventObject = e.getSource();

    if (eventObject == exitMenu)
      System.exit(0);

    DigitalClock parent = (DigitalClock)getParent();

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
