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
      tmpJMenuItem.addMouseListener(new MouseClickListener());
    }


    add(quitMenu);
    quitMenu.addActionListener(this);
    quitMenu.addActionListener(this);
    quitMenu.addMouseListener(new MouseClickListener());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    System.out.println("PropertyPopupMenu");
    if (source == quitMenu)
      System.exit(0);
    for (JMenuItem fontSizeSubMenu : fontSizeSubMenus) {
      if (source == fontSizeSubMenu) {
        System.out.println("hogheoge");
      }
    }
  }

  private class MouseClickListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      System.out.println("mouseClicked");
    }
    public void mousePressed(MouseEvent e) {
      System.out.println("mousePressed");
    }
    public void mouseEntered(MouseEvent e){
      System.out.println("mouseEntered");
    }
    public void mouseExited(MouseEvent e){
      System.out.println("mouseExited");
    }

  }
}
