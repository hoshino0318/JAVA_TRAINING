package interpret.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import interpret.controllers.*;

public class ObjectDialog extends JDialog implements ActionListener {

  private static final long serialVersionUID = -6197444475660229365L;
  private JLabel title;

  private JLabel methodLabel;       // メソッド一覧用
  private DefaultListModel methods; // メソッド一覧用
  private JList methodList;         // メソッド一覧用
  private JScrollPane methodScroll; // メソッド一覧用

  private JLabel methodParamLabel;     // メソッドパラメータ用
  private JLabel methodNameLabel;      // メソッドパラメータ用
  private JTextField methodParamField; // メソッドパラメータ用

  private JLabel fieldLabel;       // フィールド一覧用
  private DefaultListModel fields; // フィールド一覧用
  private JList fieldList;         // フィールド一覧用
  private JScrollPane fieldScroll; // フィールド一覧用

  private JLabel fieldValueLabel;     // フィールドの値用
  private JLabel fieldNameLabel;      // フィールドの値用
  private JTextField fieldValueField; // フィールドの値用

  private JButton methodSelectBtn; // メソッド選択ボタン
  private JButton methodCallBtn;   // メソッド呼び出しボタン
  private JButton fieldSelectBtn;  // フィールド選択ボタン
  private JButton fieldSetBtn;     // フィールド設定ボタン
  private JButton closeBtn; // 閉じるボタン

  private ClassController classController;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  public ObjectDialog(MainFrame frame) {
    super(frame, false);
    setTitle("Object Dialog");
    setSize(1000, 800);
    setResizable(false);
    setLocationRelativeTo(null);

    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* タイトル */
    title = new JLabel("Title");
    title.setFont(new Font("Arial", Font.BOLD, 30));

    /* メソッド一覧 */
    methodLabel = new JLabel("methods");
    methodLabel.setFont(new Font("Arial", Font.BOLD, 20));
    methods = new DefaultListModel();
    methodList = new JList(methods);
    methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    methodScroll = new JScrollPane();
    methodScroll.getViewport().setView(methodList);
    methodScroll.setPreferredSize(new Dimension(400, 300));

    /* メソッドパラメータ */
    methodParamLabel = new JLabel("pamameter");
    methodParamLabel.setFont(new Font("Arial", Font.BOLD, 20));
    methodNameLabel = new JLabel("method name");
    methodNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    methodParamField = new JTextField();
    methodParamField.setPreferredSize(new Dimension(300, 30));

    /* フィールド一覧 */
    fieldLabel = new JLabel("fields");
    fieldLabel.setFont(new Font("Arial", Font.BOLD, 20));
    fields = new DefaultListModel();
    fieldList = new JList(fields);
    fieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    fieldScroll = new JScrollPane();
    fieldScroll.getViewport().setView(fieldList);
    fieldScroll.setPreferredSize(new Dimension(400, 300));

    /* フィールドの値 */
    fieldValueLabel = new JLabel("value");
    fieldValueLabel.setFont(new Font("Arial", Font.BOLD, 20));
    fieldNameLabel = new JLabel("field name");
    fieldNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    fieldValueField = new JTextField();
    fieldValueField.setPreferredSize(new Dimension(300, 30));

    /* メソッド選択・呼び出しボタン */
    methodSelectBtn = new JButton("select");
    methodCallBtn = new JButton("call");

    /* フィールド選択・設定ボタン */
    fieldSelectBtn = new JButton("select");
    fieldSetBtn = new JButton("set");

    /* 閉じるボタン*/
    closeBtn = new JButton("close");

    /* 共通設定 */
    constraints.insets = new Insets(5, 5, 5, 5);

    /* タイトル */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(title, 0, 0, 1, 1);

    /* メソッド一覧 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(methodLabel, 0, 1, 1, 1);
    addComp(methodScroll, 0, 2, 3, 3);

    /* メソッドパラメータ一覧 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(methodParamLabel, 4, 1, 1, 1);
    addComp(methodNameLabel, 4, 2, 1, 1);
    addComp(methodParamField, 4, 3, 3, 1);

    /* フィールド一覧 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(fieldLabel, 0, 5, 1, 1);
    addComp(fieldScroll, 0, 6, 3, 3);

    constraints.anchor = GridBagConstraints.WEST;
    addComp(fieldValueLabel, 4, 5, 1, 1);
    addComp(fieldNameLabel, 4, 6, 1, 1);
    addComp(fieldValueField, 4, 7, 1, 1);

    /* メソッド選択ボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(methodSelectBtn, 2, 1, 1, 1);

    /* メソッド呼び出しボタン */
    constraints.anchor = GridBagConstraints.NORTHEAST;
    addComp(methodCallBtn, 7, 3, 1, 1);

    /* フィールド選択ボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(fieldSelectBtn, 2, 5, 1, 1);

    /* フィールド設定ボタン */
    constraints.anchor = GridBagConstraints.NORTHEAST;
    addComp(fieldSetBtn, 7, 7, 1, 1);

    /* 閉じるボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(closeBtn, 7, 0, 1, 1);

    /* リスナーの登録 */
    methodSelectBtn.addActionListener(this);
    methodCallBtn.addActionListener(this);
    fieldSelectBtn.addActionListener(this);
    fieldSetBtn.addActionListener(this);
    closeBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == methodSelectBtn) {
      if (methodList.isSelectionEmpty()) {
        System.out.println("メソッドを選択してください");
        return;
      } else {
        String methodName = (String)methodList.getSelectedValue();
        methodNameLabel.setText(methodName);
      }
    } else if (source == methodCallBtn) {
      classController.methodCallButton();
    } else if (source == fieldSelectBtn) {
      if (fieldList.isSelectionEmpty()) {
        System.out.println("フィールドを選択してください");
        return;
      } else {
        String fieldName = (String)fieldList.getSelectedValue();
        fieldNameLabel.setText(fieldName);
        classController.fieldSelectButton();
      }
    } else if (source == fieldSetBtn) {
      classController.fieldSetButton();
    } else if (source == closeBtn) {
      methods.clear();
      classController.methodClear();
      fields.clear();
      classController.fieldClear();
      methodList.ensureIndexIsVisible(methods.getSize() - 1);
      fieldList.ensureIndexIsVisible(fields.getSize() - 1);
      setVisible(false);
    }
  }

  public void setTitleLabel(String titleName) {
    title.setText(titleName);
  }

  public String getTitleLabel() {
    return title.getText();
  }

  public String getMethodName() {
    return methodNameLabel.getText();
  }

  public String getFieldName() {
    return fieldNameLabel.getText();
  }

  public void setFieldArea(String value) {
    fieldValueField.setText(value);
  }

  public String getFieArea() {
    return fieldValueField.getText();
  }

  public String getParams() {
    return methodParamField.getText();
  }

  public void printMethod(String method) {
    methods.addElement(method);
    methodList.ensureIndexIsVisible(methods.getSize() - 1);
  }

  public void printField(String field) {
    fields.addElement(field);
    fieldList.ensureIndexIsVisible(fields.getSize() - 1);
  }

  public void setClassController(ClassController classController) {
    this.classController = classController;
  }

  public void allClear() {
    methods.clear();
    methodList.ensureIndexIsVisible(methods.getSize() - 1);
    fields.clear();
    fieldList.ensureIndexIsVisible(fields.getSize() - 1);
    methodNameLabel.setText("method name");
    fieldNameLabel.setText("field name");
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
