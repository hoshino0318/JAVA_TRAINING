package ex01_04;

import java.awt.*;
import java.awt.event.*;

class PropertyDialog extends Dialog implements ActionListener, ItemListener {
  private Label titleLabel;
  private Label fontLabel;
  private Label fontSizeLabel;
  private Label fontColorLabel;
  private Label backColorLabel;

  private Button okButton;
  private Button cancelButton;

  private List fontList;
  private List fontSizeList;
  private List fontColorList;
  private List backColorList;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private Property savedPropery;

  private Font dialogFont = new Font("Arial", Font.PLAIN, 20);

  PropertyDialog(Frame owner, String title) {
    super(owner, title, true);
    setSize(400, 400);
    setLocationRelativeTo(null);
    setResizable(false);

    /* close window */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       setVisible(false);
      }
    });

    /* setting layout */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    titleLabel = new Label("Properties");
    fontLabel = new Label("Font");
    fontSizeLabel = new Label("Font size");
    fontColorLabel = new Label("Font color");
    backColorLabel = new Label("Back color");

    fontList = new List(4, false);
    for (String font : Property.fonts) {
      fontList.add(font);
    }

    fontSizeList = new List(3, false);
    for (String fontSize : Property.fontSizes) {
      fontSizeList.add(fontSize);
    }

    fontColorList = new List(3, false);
    for (String fontColor : Property.colorStrs) {
      fontColorList.add(fontColor);
    }

    backColorList = new List(3, false);
    for (String backColor : Property.colorStrs) {
      backColorList.add(backColor);
    }

    okButton = new Button("OK");
    cancelButton = new Button("Cancel");
  } // end PropertyDialog()

  void init() {
    /* label setting */
    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    fontLabel.setFont(dialogFont);
    fontSizeLabel.setFont(dialogFont);
    fontColorLabel.setFont(dialogFont);
    backColorLabel.setFont(dialogFont);

    /* constraints common setting */
    constraints.insets = new Insets(5, 10, 5, 10);

    /* title setting */
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.anchor = GridBagConstraints.CENTER;
    addComponent(titleLabel, 0, 0);

    /* label setting */
    constraints.weightx = 0.4;
    constraints.gridwidth = GridBagConstraints.RELATIVE;
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontLabel, 0, 1);
    addComponent(fontSizeLabel, 0, 2);
    addComponent(fontColorLabel, 0, 3);
    addComponent(backColorLabel, 0, 4);

    /* list setting */
    constraints.weightx = 0.6;
    constraints.gridwidth = GridBagConstraints.RELATIVE;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 10, 5, 50);
    addComponent(fontList, 1, 1);
    addComponent(fontSizeList, 1, 2);
    addComponent(fontColorList, 1, 3);
    addComponent(backColorList, 1, 4);

    /* button setting */
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.EAST;
    constraints.insets = new Insets(5, 0, 0, 0);
    Panel buttonPanel = new Panel();
    addComponent(buttonPanel, 0, 5);

    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    /* set up listeners */
    fontList.addItemListener(this);
    fontSizeList.addItemListener(this);
    fontColorList.addItemListener(this);
    backColorList.addItemListener(this);
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
  }

  @Override
  public void setVisible(boolean b) {
    if (b) {
      ajustLocation();
      DigitalClock d = (DigitalClock)getParent();
      savedPropery = d.getCurrentProperty();
      fontList.select(savedPropery.fontIndex());
      fontList.makeVisible(savedPropery.fontIndex());
      fontSizeList.select(savedPropery.fontSizeIndex());
      fontSizeList.makeVisible(savedPropery.fontSizeIndex());
      fontColorList.select(savedPropery.fontColorIndex());
      fontColorList.makeVisible(savedPropery.fontColorIndex());
      backColorList.select(savedPropery.backColorIndex());
      backColorList.makeVisible(savedPropery.backColorIndex());
    }

    super.setVisible(b);
  }

  private void ajustLocation() {
    DigitalClock clock = (DigitalClock)getParent();
    Point clockLocation = clock.getLocation();
    Dimension clockSize = clock.getSize();
    clockLocation.translate(clockSize.width + 10, 0);

    setLocation(clockLocation);
  }

  private void addComponent(Component com, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    layout.setConstraints(com, constraints);
    add(com);
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == okButton) {
      setVisible(false);
    } else if (source == cancelButton) {
      DigitalClock d = (DigitalClock)getParent();
      d.restoreProperty(savedPropery);
      setVisible(false);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object source = e.getSource();

    DigitalClock parent = (DigitalClock)getParent();

    if (source == fontList) {
      String fontName = fontList.getSelectedItem();
      parent.changeClockFont(fontName);
    } else if (source == fontSizeList) {
      int fontSize = Integer.parseInt(fontSizeList.getSelectedItem());
      parent.changeClockFontSize(fontSize);
    } else if (source == fontColorList) {
      String fontColorName = fontColorList.getSelectedItem();
      parent.changeFontColor(fontColorName);
    } else if (source == backColorList) {
      String backColorName = backColorList.getSelectedItem();
      parent.changeBackColor(backColorName);
    } else {
      // this never occurs
      throw new InternalError();
    }
  }
}
