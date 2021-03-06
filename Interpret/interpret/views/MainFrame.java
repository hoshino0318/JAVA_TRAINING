package interpret.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import interpret.controllers.*;

public class MainFrame extends JFrame implements ActionListener, ItemListener {
  private static final long serialVersionUID = -4570424264389438080L;

  private JLabel title; //タイトル

  private JTextField searchBox; // クラス検索用
  private JButton searchBtn;    // クラス検索用
  private JPanel searchPanel;   // クラス検索用

  private JLabel classLabel;                     // コンストラクタ用
  private JButton constClearBtn;                 // コンストラクタ用
  private JList constList;               // コンストラクタ用
  private DefaultListModel constructors; //コンストラクタ用
  private JScrollPane constScroll;               // コンストラクタ用
  private JButton selectConstBtn;                // コンストラクタ用
  private JPanel constPanel;                     // コンストラクタ用

  private JCheckBox checkAryBox;   // 配列生成用
  private JLabel aryNameLabel;     // 配列生成用
  private JTextField aryNameField; // 配列生成用
  private JLabel aryNumLabel;      // 配列生成用
  private JTextField aryNumField;  // 配列生成用
  private JButton aryCreateBtn;    // 配列生成用

  private JLabel rightArrow;

  private JButton objectBtn;                // オブジェクト一覧用
  private JButton arrayBtn;                 // オブジェクト一覧用
  private JButton objectClearBtn;           // オブジェクト一覧用
  private JList  objectList;        // オブジェクト一覧用
  private DefaultListModel objects; // オブジェクト一覧用
  private JScrollPane objectScroll;         // オブジェクト一覧用
  private JPanel objectPanel;               // オブジェクト一覧用

  private JLabel objectNameLabel;    // パラメータ用
  private JTextField objectNameText; // パラメータ用
  private JLabel paramConstLabel;    // パラメータ用
  private JTextField paramTextFiled; // パラメータ用
  private JPanel paramPanel;         // パラメータ用

  private JButton createObjectBtn;   // オブジェクト生成ボタン

  private ObjectDialog objectDialog;
  private ArrayDialog arrayDialog;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private static final Font commonFont = new Font("Arial", Font.BOLD, 20);

  private ClassController classController;

  public MainFrame(){
    super("MainFrame");
    objectDialog = new ObjectDialog(this);
    arrayDialog = new ArrayDialog(this);
    classController = new ClassController(this, objectDialog, arrayDialog);
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
    searchBox = new JTextField();
    searchBox.setPreferredSize(new Dimension(400, 40));
    searchBtn = new JButton("search");
    searchPanel = new JPanel();
    searchPanel.setPreferredSize(new Dimension(500, 100));
    GridBagLayout searchLayout = new GridBagLayout();
    GridBagConstraints searchConstraints = new GridBagConstraints();
    searchPanel.setLayout(searchLayout);
    searchPanel.setBorder(new TitledBorder(new EtchedBorder(), "Class",
                                           TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, commonFont));
    searchConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(searchBox, searchLayout, searchConstraints, 0, 0, 3, 1);
    searchPanel.add(searchBox);
    searchConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(searchBtn, searchLayout, searchConstraints, 3, 0, 1, 1);
    searchPanel.add(searchBtn);

    /* コンストラクタ一覧表示用 */
    classLabel = new JLabel();
    constClearBtn = new JButton("clear");
    checkAryBox = new JCheckBox("Array");
    checkAryBox.setPreferredSize(new Dimension(150, 30));
    aryNameLabel = new JLabel("name");
    aryNameField = new JTextField();
    aryNameField.setPreferredSize(new Dimension(150, 30));
    aryNumLabel = new JLabel("size");
    aryNumField = new JTextField();
    aryNumField.setPreferredSize(new Dimension(100, 30));
    aryCreateBtn = new JButton("create");
    constructors = new DefaultListModel();
    constList = new JList(constructors);
    constList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    constScroll = new JScrollPane();
    constScroll.getViewport().setView(constList);
    constScroll.setPreferredSize(new Dimension(480, 320));
    selectConstBtn = new JButton("select");
    constPanel = new JPanel();
    constPanel.setPreferredSize(new Dimension(500, 470));
    GridBagLayout constLayout = new GridBagLayout();
    GridBagConstraints constConstraints = new GridBagConstraints();
    constPanel.setLayout(constLayout);
    constPanel.setBorder(new TitledBorder(new EtchedBorder(), "Constructor",
                                           TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, commonFont));
    constConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(classLabel, constLayout, constConstraints, 0, 0, 1, 1);
    constPanel.add(classLabel);
    constConstraints.anchor = GridBagConstraints.CENTER;
    setConstraints(aryNameLabel, constLayout, constConstraints, 1, 0, 1, 1);
    constPanel.add(aryNameLabel);
    aryNameLabel.setVisible(false);
    constConstraints.anchor = GridBagConstraints.CENTER;
    setConstraints(aryNumLabel, constLayout, constConstraints, 2, 0, 1, 1);
    constPanel.add(aryNumLabel);
    aryNumLabel.setVisible(false);
    constConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(constClearBtn, constLayout, constConstraints, 3, 0, 1, 1);
    constPanel.add(constClearBtn);
    constConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(checkAryBox, constLayout, constConstraints, 0, 1, 1, 1);
    constPanel.add(checkAryBox);
    constConstraints.anchor = GridBagConstraints.CENTER;
    setConstraints(aryNameField, constLayout, constConstraints, 1, 1, 1, 1);
    constPanel.add(aryNameField);
    aryNameField.setVisible(false);
    constConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(aryNumField, constLayout, constConstraints, 2, 1, 1, 1);
    constPanel.add(aryNumField);
    aryNumField.setVisible(false);
    constConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(aryCreateBtn, constLayout, constConstraints, 3, 1, 1, 1);
    constPanel.add(aryCreateBtn);
    aryCreateBtn.setVisible(false);
    constConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(constScroll, constLayout, constConstraints, 0, 2, 4, 7);
    constPanel.add(constScroll);
    constConstraints.anchor = GridBagConstraints.SOUTHEAST;
    setConstraints(selectConstBtn, constLayout, constConstraints, 3, 9, 1, 1);
    constPanel.add(selectConstBtn);

    /* 右矢印 */
    rightArrow = new JLabel("→");
    rightArrow.setFont(new Font("Arial", Font.BOLD, 25));

    /* オブジェクト一覧表示用 */
    objectBtn = new JButton("object detail");
    arrayBtn = new JButton("array detail");
    objectClearBtn = new JButton("clear");
    objects = new DefaultListModel();
    objectList = new JList(objects);
    objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    objectScroll = new JScrollPane();
    objectScroll.getViewport().setView(objectList);
    objectScroll.setPreferredSize(new Dimension(350, 200));
    objectPanel = new JPanel();
    objectPanel.setPreferredSize(new Dimension(400, 300));
    GridBagLayout objectLayout = new GridBagLayout();
    GridBagConstraints objectConstraints = new GridBagConstraints();
    objectPanel.setLayout(objectLayout);
    objectPanel.setBorder(new TitledBorder(new EtchedBorder(), "Object",
                                           TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, commonFont));
    objectConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(objectBtn, objectLayout, objectConstraints, 0, 0, 1, 1);
    objectPanel.add(objectBtn);
    objectConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(arrayBtn, objectLayout, objectConstraints, 1, 0, 1, 1);
    objectPanel.add(arrayBtn);
    objectConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(objectClearBtn, objectLayout, objectConstraints, 2, 0, GridBagConstraints.REMAINDER, 1);
    objectPanel.add(objectClearBtn);
    setConstraints(objectScroll, objectLayout, objectConstraints, 0, 1, 5, 5);
    objectPanel.add(objectScroll);

    /* パラメータ表示 */
    objectNameLabel = new JLabel("Object name");
    objectNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    objectNameText = new JTextField();
    objectNameText.setPreferredSize(new Dimension(350, 40));
    paramConstLabel = new JLabel("Constructor");
    paramConstLabel.setFont(new Font("Arial", Font.BOLD, 15));
    paramTextFiled = new JTextField();
    paramTextFiled.setPreferredSize(new Dimension(350, 40));
    paramPanel = new JPanel();
    paramPanel.setPreferredSize(new Dimension(400, 200));
    GridBagLayout paramLayout = new GridBagLayout();
    GridBagConstraints paramConstraints = new GridBagConstraints();
    paramPanel.setLayout(paramLayout);
    paramPanel.setBorder(new TitledBorder(new EtchedBorder(), "Parameter",
                                           TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, commonFont));
    paramConstraints.anchor = GridBagConstraints.WEST;
    setConstraints(objectNameLabel, paramLayout, paramConstraints, 0, 0, 1, 1);
    paramPanel.add(objectNameLabel);
    setConstraints(objectNameText, paramLayout, paramConstraints, 0, 1, 4, 1);
    paramPanel.add(objectNameText);
    setConstraints(paramConstLabel, paramLayout, paramConstraints, 0, 2, 1, 1);
    paramPanel.add(paramConstLabel);
    setConstraints(paramTextFiled, paramLayout, paramConstraints, 0, 3, 4, 1);
    paramPanel.add(paramTextFiled);

    /* オブジェクト生成用 */
    createObjectBtn = new JButton("create");
    paramConstraints.anchor = GridBagConstraints.EAST;
    setConstraints(createObjectBtn, paramLayout, paramConstraints, 3, 4, GridBagConstraints.REMAINDER, 1);
    paramPanel.add(createObjectBtn);

    /* 共通設定 */
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.gridwidth = GridBagConstraints.REMAINDER;

    /* タイトルの表示 */
    constraints.anchor = GridBagConstraints.NORTHWEST;
    addComp(title, 0, 0, 1, 1);

    /* 検索ボックス表示 */
    addComp(searchPanel, 0, 1, 4, 2);

    /* コンストラクタ一覧表示 */
    addComp(constPanel, 0, 3, 4, 10);

    /* 右矢印 */
    addComp(rightArrow, 4, 9, 1, 1);

    /* オブジェクト一覧表示 */
    addComp(objectPanel, 5, 1, 5, 6);

    /* パラメータ表示 */
    addComp(paramPanel, 5, 7, 5, 4);

    /* メッセージウィンドウ */
    new MessageFrame();

    /* アクションリスナーの登録 */
    searchBtn.addActionListener(this);
    checkAryBox.addItemListener(this);
    aryCreateBtn.addActionListener(this);
    constClearBtn.addActionListener(this);
    selectConstBtn.addActionListener(this);
    objectBtn.addActionListener(this);
    arrayBtn.addActionListener(this);
    objectClearBtn.addActionListener(this);
    createObjectBtn.addActionListener(this);

    /* キーイベントリスナーの登録 */
    MyKeyAdapter keyAdapter = new MyKeyAdapter();
    searchBox.addKeyListener(keyAdapter);
    searchBtn.addKeyListener(keyAdapter);
    aryNameField.addKeyListener(keyAdapter);
    aryNumField.addKeyListener(keyAdapter);
    aryCreateBtn.addKeyListener(keyAdapter);
    constList.addKeyListener(keyAdapter);
    selectConstBtn.addKeyListener(keyAdapter);
    objectNameText.addKeyListener(keyAdapter);
    paramTextFiled.addKeyListener(keyAdapter);

    setVisible(true);
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
      classLabel.setText("");
      constructors.clear();
      constList.ensureIndexIsVisible(constructors.getSize() - 1);
      classController.constClearButton();
    } else if (source == selectConstBtn) { // コンストラクタ選択ボタン
      selectConst();
    } else if (source == objectClearBtn) { // オブジェクトクリアボタン
      if (classController.objectClearButton()) {
        objects.clear();
        objectList.ensureIndexIsVisible(objects.getSize() - 1);
      }
    } else if (source == objectBtn) { // オブジェクト呼び出しボタン
      if (objectList.isSelectionEmpty()) {
        System.out.println("オブジェクトを選択してください");
      } else {
        classController.objectButton(getSelectedObject());
      }
    } else if (source == arrayBtn) { // 配列呼び出しボタン
      if (objectList.isSelectionEmpty()) {
        System.out.println("配列を選択してください");
      } else {
        classController.arrayButton();
      }
    } else if (source == aryCreateBtn) { // 配列生成ボタン
      arrayCreate();
    } else if (source == createObjectBtn) { // オブジェクト生成ボタン
      createObject();
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    Object source = e.getSource();
    if (source == checkAryBox) {
      if (checkAryBox.isSelected()) {
        aryNameLabel.setVisible(true);
        aryNameField.setVisible(true);
        aryNumLabel.setVisible(true);
        aryNumField.setVisible(true);
        aryCreateBtn.setVisible(true);
        constPanel.validate();
        constList.setEnabled(false);
        selectConstBtn.setEnabled(false);
      } else {
        aryNameLabel.setVisible(false);
        aryNameField.setVisible(false);
        aryNumLabel.setVisible(false);
        aryNumField.setVisible(false);
        aryCreateBtn.setVisible(false);
        constList.setEnabled(true);
        selectConstBtn.setEnabled(true);
      }
    }
  }

  public void setClassLabel(String className) {
    classLabel.setText(className);
  }

  public String getClassLabel() {
    return classLabel.getText();
  }

  public String getAryObjName() {
    return aryNameField.getText();
  }

  public String getAryNumStr() {
    return aryNumField.getText();
  }

  public void printConstructor (String constName) {
    constructors.addElement(constName);
    constList.ensureIndexIsVisible(constructors.getSize() - 1);
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

  public void addObjectName(String objName) {
    objects.addElement(objName);
    objectList.ensureIndexIsVisible(objects.getSize() - 1);
    System.out.println("オブジェクト \"" + objName + "\" を生成しました");
  }

  private void addComp(Component com, int x, int y, int width, int height) {
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridwidth  = width;
    constraints.gridheight = height;
    layout.setConstraints(com, constraints);
    add(com);
  }

  private void setConstraints(Component com, GridBagLayout layout,
                              GridBagConstraints constraints,
                              int x, int y, int width, int height) {
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridwidth  = width;
    constraints.gridheight = height;
    layout.setConstraints(com, constraints);
  }

  private void arrayCreate() {
    if (classController.createAryButton()) {
      String aryName = aryNameField.getText();
      objects.addElement(aryName);
      objectList.ensureIndexIsVisible(objects.getSize() - 1);
      System.out.println("配列 \"" + aryName + "\" を生成しました");
    }
  }

  private void selectConst() {
    if (constList.isSelectionEmpty()) {
      System.out.println("コンストラクタを選択してください");
    } else {
      classController.selectButton((String)constList.getSelectedValue());
    }
  }

  private void createObject() {
    if (classController.createButton()) {
      String objName = objectNameText.getText();
      addObjectName(objName);
    }
  }

  class MyKeyAdapter extends KeyAdapter implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
      Object source = e.getSource();
      int keyCode = e.getKeyCode();
      if (source == searchBox || source == searchBtn) {
        if (keyCode == KeyEvent.VK_ENTER) {
          classController.searchButton(searchBox.getText());
        }
      } else if (source == aryNameField || source == aryNumField || source == aryCreateBtn) {
        if (keyCode == KeyEvent.VK_ENTER) {
          arrayCreate();
        }
      } else if (source == constList || source == selectConstBtn) {
        if (keyCode == KeyEvent.VK_ENTER) {
          selectConst();
        }
      } else if (source == objectNameText || source == paramTextFiled) {
        if (keyCode == KeyEvent.VK_ENTER) {
          createObject();
        }
      }
    }
  }
}
