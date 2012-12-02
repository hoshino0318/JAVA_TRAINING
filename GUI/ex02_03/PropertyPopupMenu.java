package ex02_03;

import java.awt.event.*;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

class PropertyPopupMenu extends JPopupMenu implements ActionListener {
  private static final long serialVersionUID = -5886033453588110137L;
  private JMenu fontSizeMenu;
  private JMenuItem quitMenu;

  private JMenuItem[] fontSizeSubMenus;

  private static final String[] fontSizes = {"40", "60", "100"};

  PropertyPopupMenu() {
    fontSizeMenu = new JMenu("Font size");
    quitMenu = new JMenuItem("Quit");
    
    add(fontSizeMenu);
    fontSizeSubMenus = new JMenuItem[fontSizes.length];
    for (int i = 0; i < fontSizeSubMenus.length; ++i) {
      JMenuItem tmpJMenuItem = new JMenuItem(fontSizes[i]);
      fontSizeMenu.add(tmpJMenuItem);
      tmpJMenuItem.addActionListener(this);
    }
    
    add(quitMenu);
    fontSizeMenu.addActionListener(this);
    quitMenu.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == quitMenu) {
      System.exit(0);
    }
    
    System.out.println("Action!!");
    
    /*for (JMenuItem fontSizeSubMenu : fontSizeSubMenus) {
      if (source == fontSizeSubMenu) {
        System.out.println("hogheoge");
      }
    }*/
  }
}
