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

  private JLabel paramLabel;         // パラメータ用
  private JLabel methodNameLabel;    // パラメータ用
  private JTextField methodParamField; // パラメータ用

  private JButton methodSelectBtn; // メソッド選択ボタン
  private JButton methodCallBtn;  // メソッド呼び出しボタン
  private JButton closeBtn; // 閉じるボタン

  private ClassController classController;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  public ObjectDialog(MainFrame frame, ClassController classController) {
    super(frame, true);
    setTitle("Method Dialog");
    setSize(800, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* タイトル */
    title = new JLabel("Title");
    title.setFont(new Font("Arial", Font.BOLD, 30));

    /* メソッド一覧表示 */
    methodLabel = new JLabel("methods");
    methodLabel.setFont(new Font("Arial", Font.BOLD, 20));
    methods = new DefaultListModel();
    methods.addElement("method");
    methodList = new JList(methods);
    methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    methodScroll = new JScrollPane();
    methodScroll.getViewport().setView(methodList);
    methodScroll.setPreferredSize(new Dimension(400, 300));

    /* パラメータ表示 */
    paramLabel = new JLabel("pamameter");
    paramLabel.setFont(new Font("Arial", Font.BOLD, 20));
    methodNameLabel = new JLabel("method name");
    methodNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    methodParamField = new JTextField();
    methodParamField.setPreferredSize(new Dimension(300, 30));

    /* メソッド選択ボタン */
    methodSelectBtn = new JButton("select");

    /* メソッド呼び出しボタン */
    methodCallBtn = new JButton("call");

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

    /* パラメータ一覧 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(paramLabel, 4, 1, 1, 1);
    addComp(methodNameLabel, 4, 2, 1, 1);
    addComp(methodParamField, 4, 3, 3, 1);

    /* メソッド選択ボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(methodSelectBtn, 2, 1, 1, 1);

    /* メソッド呼び出しボタン */
    constraints.anchor = GridBagConstraints.NORTHEAST;
    addComp(methodCallBtn, 6, 4, 1, 1);

    /* 閉じるボタン */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(closeBtn, 6, 7, 1, 1);

    /* リスナーの登録 */
    methodSelectBtn.addActionListener(this);
    methodCallBtn.addActionListener(this);
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
    } else if (source == closeBtn) {
      methods.clear();
      methodList.ensureIndexIsVisible(methods.getSize() - 1);
      setVisible(false);
    }
  }

  private void addComp(Component com, int x, int y, int width, int height) {
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridwidth = width;
    constraints.gridheight = height;
    layout.setConstraints(com, constraints);
    add(com);
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

  public String getParams() {
    return methodParamField.getText();
  }

  public void printMethod(String method) {
      methods.addElement(method);
      methodList.ensureIndexIsVisible(methods.getSize() - 1);
  }

  public void setClassController(ClassController classController) {
    this.classController = classController;
  }
}
