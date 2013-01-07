package ex02_04;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

class PropertyDialog extends JDialog {
  private static final long serialVersionUID = -8600365985749587342L;
  private static final Font labelCommonFont = new Font("Arial", Font.BOLD, 20);

  private DigitalClock owner;
  private ClockProperty property;

  private JLabel fontLabel;
  private DefaultComboBoxModel fontModel;
  private JComboBox fontBox;

  private JLabel fontSizeLabel;
  private DefaultComboBoxModel fontSizeModel;
  private JComboBox fontSizeBox;

  private JLabel fontColorLabel;
  private DefaultTableModel fontColorModel;
  private JTable fontColorTable;
  private JScrollPane fontColorScroll;

  private JLabel backGroundLabel;
  private DefaultTableModel backGroundModel;
  private JTable backGroundTable;
  private JScrollPane backGroundScroll;

  private JPanel buttonPanel;
  private JButton okButton;
  private JButton cancelButton;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  PropertyDialog(DigitalClock owner, ClockProperty property) {
    super(owner);
    this.owner = owner;
    this.property = property;
    setModal(true);
    setTitle("Property");
    setSize(500, 350);
    setResizable(false);
    setLocationRelativeTo(null);

    fontLabel = new JLabel("Font");
    fontLabel.setFont(labelCommonFont);
    fontModel = new DefaultComboBoxModel(ClockProperty.fontFamily);
    fontBox   = new JComboBox(fontModel);

    fontSizeLabel = new JLabel("Font Size");
    fontSizeLabel.setFont(labelCommonFont);
    fontSizeModel = new DefaultComboBoxModel(ClockProperty.fontSizes);
    fontSizeBox   = new JComboBox(fontSizeModel);

    /* 色情報 */
    int colorNum = ClockProperty.colorFamily.length;
    String[][] tableData = new String[colorNum][2];
    for (int i = 0; i < colorNum; i++) {
      tableData[i][0] = "";
      tableData[i][1] = ClockProperty.colorFamily[i];
    }

    fontColorLabel = new JLabel("Font Color");
    fontColorLabel.setFont(labelCommonFont);
    fontColorModel = new DefaultTableModel(tableData, new String[]{"", ""});
    fontColorTable = new JTable(fontColorModel);
    fontColorTable.setTableHeader(null);
    fontColorTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    fontColorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    fontColorTable.getSelectionModel().addListSelectionListener(new FontColorSelectionListener());
    fontColorTable.getColumnModel().getColumn(0).setCellRenderer(new ColorCellRenderer());
    fontColorTable.getColumnModel().getColumn(0).setPreferredWidth(50);
    fontColorTable.setDefaultEditor(Object.class, null);
    fontColorScroll = new JScrollPane(fontColorTable);
    fontColorScroll.setPreferredSize(new Dimension(200, 50));

    backGroundLabel = new JLabel("Background Color");
    backGroundLabel.setFont(labelCommonFont);
    backGroundModel = new DefaultTableModel(tableData, new String[]{"", ""});
    backGroundTable = new JTable(backGroundModel);
    backGroundTable.setTableHeader(null);
    backGroundTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    backGroundTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    backGroundTable.getSelectionModel().addListSelectionListener(new BackgroundSelectionListener());
    backGroundTable.getColumnModel().getColumn(0).setCellRenderer(new ColorCellRenderer());
    backGroundTable.getColumnModel().getColumn(0).setPreferredWidth(50);
    backGroundTable.setDefaultEditor(Object.class, null);
    backGroundScroll = new JScrollPane(backGroundTable);
    backGroundScroll.setPreferredSize(new Dimension(200, 50));

    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    okButton     = new JButton("OK");
    cancelButton = new JButton("Cancel");
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);
    
    /* 列ごとにコンポーネントを追加していく */
    /* 一列目 */
    constraints.gridwidth = GridBagConstraints.RELATIVE;
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontLabel, 0, 0);
    addComponent(fontSizeLabel, 0, 1);
    addComponent(fontColorLabel, 0, 2);
    addComponent(backGroundLabel, 0, 3);
    /* 二列目 */
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.anchor = GridBagConstraints.WEST;
    addComponent(fontBox, 1, 0);
    addComponent(fontSizeBox, 1, 1);
    addComponent(fontColorScroll, 1, 2);
    addComponent(backGroundScroll, 1, 3);
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(buttonPanel, 1, 4);

    PropertyActionListener listener = new PropertyActionListener();
    fontBox.addActionListener(listener);
    fontSizeBox.addActionListener(listener);
    okButton.addActionListener(listener);
    cancelButton.addActionListener(listener);
  }
  
  @Override
  public void setVisible(boolean b) {    
    /* ダイアログが表示された際には 
     * property 情報のスナップショットを取る */
    if (b)
      owner.snapshot();
    super.setVisible(b);
  }
  
  void setProperty(ClockProperty property) {
    this.property = property;
  }

  private void addComponent(Component com, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    layout.setConstraints(com, constraints);
    add(com);
  }

  public class ColorCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1601402918980924648L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
      JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
      Color color = property.getColorInstance(ClockProperty.colorFamily[row]);
      label.setBackground(color);

      return label;
    }
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
      } else if (source == okButton) {
        PropertyDialog.this.setVisible(false);
        System.out.println("[Info ]: Property changed");
      } else if (source == cancelButton) {
        owner.rollback();
        PropertyDialog.this.setVisible(false);
        System.out.println("[Info ]: Property change canceled");
      }
    }
  }

  private class FontColorSelectionListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
      int selectedRow = fontColorTable.convertRowIndexToModel(fontColorTable.getSelectedRow());
      String colorName = (String)fontColorModel.getValueAt(selectedRow, 1);
      property.setFontColor(colorName);
    }
  }

  private class BackgroundSelectionListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
      int selectedRow = backGroundTable.convertRowIndexToModel(backGroundTable.getSelectedRow());
      String colorName = (String)backGroundModel.getValueAt(selectedRow, 1);
      property.setBackGroundColor(colorName);
    }
  }
}
