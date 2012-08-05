package interpret.views;

import java.awt.*;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
  private static final long serialVersionUID = -4570424264389438080L;

  public MainFrame(){
    super("MainFrame");
    setSize(new Dimension(1000, 600));
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    setVisible(true);
  }
}
