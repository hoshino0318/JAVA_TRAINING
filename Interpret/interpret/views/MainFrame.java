package interpret.views;

import java.lang.reflect.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import interpret.controllers.*;

public class MainFrame extends JFrame implements ActionListener {
  private static final long serialVersionUID = -4570424264389438080L;

  private JLabel title; //タイトル
  private JLabel searchLabel;   // クラス検索用
  private JTextField searchBox; // クラス検索用
  private JButton searchBtn; // クラス検索用
  private JLabel constLabel;    // コンストラクタ用
  private JButton constClearBtn;  // コンストラクタ用
  private JList constList;      // コンストラクタ用
  private DefaultListModel constructors; //コンストラクタ用
  private JScrollPane constScroll; // コンストラクタ用
  private JButton selectConstBtn; // コンストラクタ用

  private JLabel rightArrow;

  private JLabel objectLabel; // オブジェクト一覧用
  private JButton objectClearBtn; // オブジェクト一覧用
  private JList  objectList;  // オブジェクト一覧用
  private DefaultListModel objects; // オブジェクト一覧用
  private JScrollPane objectScroll; // オブジェクト一覧用

  private JButton objectBtn; // オブジェクト

  private JLabel paramLabel;          // パラメータ用
  private JLabel objectNameLabel;     // パラメータ用
  private JTextField objectNameText;  // パラメータ用
  private JLabel paramConstLabel; // パラメータ用
  private JTextField paramTextFiled;  // パラメータ用

  private JButton createObjectBtn; // オブジェクト生成ボタン

  private ObjectDialog methodDialog;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private static final Font commonFont = new Font("Arial", Font.BOLD, 20);

  private ClassController classController;

  public MainFrame(){
    super("MainFrame");
    methodDialog = new ObjectDialog(this, null);
    classController = new ClassController(this, methodDialog);
    setSize(new Dimension(1000, 700));
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* レイアウトの設定 */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* タイトル */
    title = new JLabel("Interpret");
    title.setFont(new Font("Arial", Font.BOLD, 30));

    /* クラス検索用 */
    searchLabel = new JLabel("class");
    searchLabel.setFont(commonFont);
    searchBox = new JTextField();
    searchBox.setPreferredSize(new Dimension(300, 40));
    searchBtn = new JButton("search");

    /* コンストラクタ一覧表示用 */
    constLabel = new JLabel("constructors");
    constLabel.setFont(commonFont);
    constClearBtn = new JButton("clear");
    constructors = new DefaultListModel();
    constList = new JList(constructors);
    constList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    constScroll = new JScrollPane();
    constScroll.getViewport().setView(constList);
    constScroll.setPreferredSize(new Dimension(400, 300));
    selectConstBtn = new JButton("select");

    /* オブジェクト一覧表示用 */
    objectLabel = new JLabel("objects");
    objectLabel.setFont(commonFont);
    objectClearBtn = new JButton("clear");
    objects = new DefaultListModel();
    objectList = new JList(objects);
    objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    objectScroll = new JScrollPane();
    objectScroll.getViewport().setView(objectList);
    objectScroll.setPreferredSize(new Dimension(400, 200));

    /* 右矢印 */
    rightArrow = new JLabel("→");
    rightArrow.setFont(new Font("Arial", Font.BOLD, 25));

    /* パラメータ表示 */
    paramLabel = new JLabel("parameter");
    paramLabel.setFont(commonFont);
    objectNameLabel = new JLabel("object name");
    objectNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    objectNameText = new JTextField();
    objectNameText.setPreferredSize(new Dimension(300, 40));
    paramConstLabel = new JLabel("constructor");
    paramConstLabel.setFont(new Font("Arial", Font.BOLD, 15));
    paramTextFiled = new JTextField();
    paramTextFiled.setPreferredSize(new Dimension(350, 40));

    /* オブジェクト用 */
    objectBtn = new JButton("object detail");

    /* オブジェクト生成用 */
    createObjectBtn = new JButton("create");

    /* 共通設定 */
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.gridwidth = GridBagConstraints.REMAINDER;

    /* タイトルの表示 */
    constraints.anchor = GridBagConstraints.NORTHWEST;
    addComp(title, 0, 0, 3, 1);

    /* 検索ボックス表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(searchLabel, 0, 1, 3, 1);
    constraints.anchor = GridBagConstraints.CENTER;
    addComp(searchBox, 0, 2, 4, 1);
    addComp(searchBtn, 4, 2, 1,1);

    /* コンストラクタ一覧表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(constLabel, 0, 3, 3, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(constClearBtn, 4, 3, 1, 1);
    constraints.anchor = GridBagConstraints.WEST;
    addComp(constScroll, 0, 4, 5, 8);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(selectConstBtn, 4, 12, 1, 1);

    /* オブジェクト一覧表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objectLabel, 7, 1, 1, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(objectBtn, 9, 1, 1, 1);
    addComp(objectClearBtn, 10, 1, 1, 1);
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objectScroll, 7, 2, 4, 3);

    /* 右矢印 */
    addComp(rightArrow, 6, 9, 1, 1);

    /* パラメータ表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(paramLabel, 7, 6, 2, 1);
    addComp(objectNameLabel, 7, 7, 2, 1);
    addComp(objectNameText, 7, 8, 4, 1);
    addComp(paramConstLabel, 7, 9, 4, 1);
    addComp(paramTextFiled, 7, 10, 4, 1);

    /* オブジェクト生成用 */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(createObjectBtn, 9, 12, 1, 1);

    /* メッセージウィンドウ */
    new MessageFrame();

    /* リスナーの登録 */
    searchBtn.addActionListener(this);
    constClearBtn.addActionListener(this);
    selectConstBtn.addActionListener(this);
    objectBtn.addActionListener(this);
    objectClearBtn.addActionListener(this);
    createObjectBtn.addActionListener(this);

    setVisible(true);
  }

  private void addComp(Component com, int x, int y, int width, int height) {
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridheight = height;
    constraints.gridwidth = width;
    layout.setConstraints(com, constraints);
    add(com);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == searchBtn) { // 検索ボタン
      constructors.clear();
      constList.ensureIndexIsVisible(constructors.getSize() - 1);
      classController.constClearButton();

      classController.searchButton(searchBox.getText());
      searchBox.setText(""); // 検索ボックスをクリアする
    } else if (source == constClearBtn) { // コンストラクタクリアボタン
      constructors.clear();
      constList.ensureIndexIsVisible(constructors.getSize() - 1);
      classController.constClearButton();
    } else if (source == selectConstBtn) { // コンストラクタ選択ボタン
      if (constList.isSelectionEmpty()) {
        System.out.println("コンストラクタを選択してください");
      } else {
        System.out.println(constList.getSelectedValue());
        classController.selectButton((String)constList.getSelectedValue());
      }
    } else if (source == objectClearBtn) { // オブジェクトクリアボタン
      objects.clear();
      objectList.ensureIndexIsVisible(objects.getSize() - 1);
      classController.objectClearButton();
    } else if (source == objectBtn) { // オブジェクト呼び出しボタン
      if (objectList.isSelectionEmpty()) {
        System.out.println("オブジェクトを選択してください");
      } else {
        classController.objectButton();
      }
    } else if (source == createObjectBtn) { // オブジェクト生成ボタン
      if (classController.createButton()) {
        String objName = objectNameText.getText();
        objects.addElement(objName);
        objectList.ensureIndexIsVisible(objects.getSize() - 1);
        System.out.println("オブジェクト \"" + objName + "\" を生成しました");
      }
    }
  }

  public void printConstructorList (Constructor<?>[] cons) {
    for (Constructor<?> con : cons) {
      constructors.addElement(con.toString());
      constList.ensureIndexIsVisible(constructors.getSize() - 1);
    }
  }

  public void printConstLabel(String constName) {
    paramConstLabel.setText(constName);
  }

  public String getSelectedObject() {
    return (String)objectList.getSelectedValue();
  }

  public String getObjectName() {
    return objectNameText.getText();
  }

  public String getConstName() {
    return paramConstLabel.getText();
  }

  public String getParamName() {
    return paramTextFiled.getText();
  }

  class MyMouseListener extends MouseAdapter implements MouseListener {

  }
}
