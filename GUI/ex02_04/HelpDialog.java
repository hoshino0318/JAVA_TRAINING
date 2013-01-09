package ex02_04;

import java.awt.*;
import javax.swing.*;

class HelpDialog extends JDialog {
  private static final long serialVersionUID = 2968338579604084965L;
  
  private JLabel propertyLabel;
  private JLabel exitLabel;
  private JLabel helpLabel;
  private JLabel rightDoubleClickLabel;
  private JLabel leftDoubleClickLabel;
  
  private GridBagLayout layout;
  private GridBagConstraints constraints;
 
  private static final Font LABEL_FONT = new Font("Consolas", Font.PLAIN, 16);
  
  HelpDialog() {    
    setTitle("Help");
    setSize(400, 200);
    setModal(true);
    setLocationRelativeTo(null);
    setResizable(false);
    
    propertyLabel = new JLabel("Alt + Enter : Show property");
    exitLabel = new JLabel("Ctrl + W    : Exit clock");
    helpLabel = new JLabel("Ctrl + H    : Show help");
    rightDoubleClickLabel = new JLabel("Right Double Click: Reverse color");
    leftDoubleClickLabel  = new JLabel("Left  Double Click: Toggle menu bar visible");
    propertyLabel.setFont(LABEL_FONT);
    exitLabel.setFont(LABEL_FONT);
    helpLabel.setFont(LABEL_FONT);
    rightDoubleClickLabel.setFont(LABEL_FONT);
    leftDoubleClickLabel.setFont(LABEL_FONT);
    
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);
    
    constraints.anchor = GridBagConstraints.WEST;
    addComponent(propertyLabel, 0, 0);
    addComponent(exitLabel, 0, 1);
    addComponent(helpLabel, 0, 2);
    addComponent(rightDoubleClickLabel, 0, 3);
    addComponent(leftDoubleClickLabel, 0, 4);
  }
  
  private void addComponent(Component com, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    layout.setConstraints(com, constraints);
    add(com);
  }
}
