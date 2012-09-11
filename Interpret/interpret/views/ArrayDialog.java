package interpret.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import interpret.controllers.*;

public class ArrayDialog extends JDialog implements ActionListener {

  private static final long serialVersionUID = 3923262360161817944L;
  private JLabel objTitle;
  private JLabel aryNumLabel;  // 配列サイズ表示用
  private JLabel clsNameLabel;     // クラス表示用

  private JButton closeBtn; // 閉じるボタン

  private DefaultTableModel tableModel;  // 配列一覧用
  private String[] columnNames = {"index", "value"}; // 配列一覧用
  private JTable objectTable;       // 配列一覧用
  private JScrollPane objectScroll; // 配列一覧用

  private JTextField objField; // オブジェクト設定用
  private JButton objSetBtn;   // オブジェクト設定用

  private ClassController classController;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  public ArrayDialog(MainFrame frame) {
    super(frame, false);
    setTitle("Array Dialog");
    setSize(800, 600);
    setResizable(false);
    setLocationRelativeTo(null);

    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* タイトル */
    objTitle = new JLabel("Title"); // ダミー (表示されることはない)
    objTitle.setFont(new Font("Arial", Font.BOLD, 30));
    aryNumLabel = new JLabel("[0]");  // ダミー (表示されることはない)
    aryNumLabel.setFont(new Font("Arial", Font.BOLD, 30));
    clsNameLabel = new JLabel("java.lang.Integer"); // ダミー (表示されることはない)
    clsNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

    /* 閉じるボタン */
    closeBtn = new JButton("close");

    /* オブジェクト一覧 */
    tableModel = new DefaultTableModel(new String[0][2], columnNames);
    objectTable = new JTable(tableModel);
    objectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    objectTable.setDefaultEditor(Object.class, null);
    objectScroll = new JScrollPane(objectTable);
    objectTable.setPreferredSize(new Dimension(350, 400));
    DefaultTableColumnModel columnModel
        = (DefaultTableColumnModel)objectTable.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(300);

    /* オブジェクト設定 */
    objField = new JTextField();
    objField.setPreferredSize(new Dimension(200, 30));
    objSetBtn = new JButton("set");

    /* 共通設定 */
    constraints.insets = new Insets(5, 5, 5, 5);

    /* タイトル */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objTitle, 0, 0, 1, 1);
    constraints.anchor = GridBagConstraints.WEST;
    addComp(aryNumLabel, 1, 0, 1, 1);
    constraints.anchor = GridBagConstraints.WEST;
    addComp(clsNameLabel, 2, 0, 1, 1);

    /* 閉じるボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(closeBtn, 4, 1, GridBagConstraints.REMAINDER, 1);

    /* オブジェクト一覧 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objectScroll, 0, 2, 3, 5);

    /* オブジェクト設定 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objField, 3, 2, 1, 1);
    addComp(objSetBtn, 4, 2, 1, 1);

    /* リスナーの登録 */
    closeBtn.addActionListener(this);
    objSetBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == closeBtn) {
      setVisible(false);
    } else if (source == objSetBtn) {
      int selectedRow = objectTable.getSelectedRow();
      if (selectedRow == -1) {
        System.out.println("配列を選択してください");
        return;
      } else {
        System.out.println(selectedRow + " が選択されました");
        classController.setArrayButton();
      }
    }
  }

  public void setObjName(String objTitle) {
    this.objTitle.setText(objTitle);
  }

  public String getObjName() {
    return objTitle.getText();
  }

  public void setAryNum(String aryNum) {
    aryNumLabel.setText(aryNum);
  }

  public void setClassNameLabel(String className) {
    clsNameLabel.setText(className);
  }

  public String getClassName() {
    return clsNameLabel.getText();
  }

  public void setObjectTable(String objName, Object[] objects) {
    tableModel.setRowCount(0);

    String[][] tableData = new String[objects.length][2];
    for (int i = 0; i < objects.length; ++i) {
      tableData[i][0] = objName + "[" + String.valueOf(i) + "]";
      if (objects[i] == null) {
        tableData[i][1] = "null";
      } else {
        tableData[i][1] = objects[i].toString();
      }
    }

    for (int i = 0; i < tableData.length; ++i) {
      tableModel.insertRow(i, tableData[i]);
    }
  }

  public String getParam() {
    return objField.getText();
  }

  public int getSelectedRowIndex() {
    return objectTable.getSelectedRow();
  }

  public void setClassController(ClassController classController) {
    this.classController = classController;
  }

  private void addComp(Component com, int x, int y, int width, int height) {
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridwidth  = width;
    constraints.gridheight = height;
    layout.setConstraints(com, constraints);
    add(com);
  }
}
