package ex02_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PropertyDialog extends JDialog {
  private static final long serialVersionUID = -8600365985749587342L;
  private static final Font lableCommonFont = new Font("Arial", Font.BOLD, 20);

  private DigitalClock owner;
  private ClockProperty property;

  private JLabel fontLabel;
  private DefaultComboBoxModel<String> fontModel;
  private JComboBox<String> fontBox;

  private JLabel fontSizeLabel;
  private DefaultComboBoxModel<String> fontSizeModel;
  private JComboBox<String> fontSizeBox;

  private JLabel fontColorLabel;

  private JLabel backGroundLabel;

  private JButton okButton;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  PropertyDialog(DigitalClock owner, ClockProperty property) {
    super(owner);
    this.owner = owner;
    this.property = property;
    setModal(true);
    setTitle("Property");
    setSize(450,300);
    setResizable(false);
    setLocationRelativeTo(null);

    fontLabel = new JLabel("フォント");
    fontLabel.setFont(lableCommonFont);
    fontModel = new DefaultComboBoxModel<String>(ClockProperty.fontFamily);
    fontBox   = new JComboBox<String>(fontModel);

    fontSizeLabel = new JLabel("フォントサイズ");
    fontSizeLabel.setFont(lableCommonFont);
    fontSizeModel = new DefaultComboBoxModel<String>(ClockProperty.fontSizes);
    fontSizeBox   = new JComboBox<String>(fontSizeModel);

    fontColorLabel = new JLabel("文字色");
    fontColorLabel.setFont(lableCommonFont);

    backGroundLabel = new JLabel("背景色");
    backGroundLabel.setFont(lableCommonFont);

    okButton = new JButton("OK");

    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontLabel, 0, 0);
    constraints.anchor = GridBagConstraints.WEST;
    addComponent(fontBox, 1, 0);
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontSizeLabel, 0, 1);
    constraints.anchor = GridBagConstraints.WEST;
    addComponent(fontSizeBox, 1, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontColorLabel, 0, 2);
    addComponent(backGroundLabel, 0, 3);
    addComponent(okButton, 1, 4);

    PropertyActionListener listener = new PropertyActionListener();
    fontBox.addActionListener(listener);
    fontSizeBox.addActionListener(listener);
  }

  private void addComponent(Component com, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    layout.setConstraints(com, constraints);
    add(com);
  }

  private class PropertyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == fontBox) {
        String fontName = (String)fontBox.getSelectedItem();
        property.setFont(fontName);
        owner.changeFont();
      } else if (source == fontSizeBox) {
        int fontSize = Integer.valueOf((String)fontSizeBox.getSelectedItem());
        property.setFont(fontSize);
        owner.changeFont();
      }
    }
  }
}
