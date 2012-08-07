package interpret.views;

import java.lang.reflect.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

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
  private DefaultListModel dlModel; //コンストラクタ用
  private JScrollPane constScroll; // コンストラクタ用
  private JButton selectConstBtn; // コンストラクタ用

  private JLabel rightArrow;

  private JLabel objectLabel; // オブジェクト一覧用
  private JList  objectList;  // オブジェクト一覧用

  private JLabel paramLabel;          // パラメータ用
  private JLabel objectNameLabel;     // パラメータ用
  private JTextField objectNameText;  // パラメータ用
  private JLabel paramConstLabel; // パラメータ用
  private JTextField paramTextFiled;  // パラメータ用

  private JButton createObjectBtn; // オブジェクト生成ボタン

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private static final Font commonFont = new Font("Arial", Font.BOLD, 20);

  private ClassController classController;

  public MainFrame(){
    super("MainFrame");
    classController = new ClassController(this);
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
    searchBox.setPreferredSize(new Dimension(200, 40));
    searchBtn = new JButton("search");

    /* コンストラクタ一覧表示用 */
    constLabel = new JLabel("constructors");
    constLabel.setFont(commonFont);
    constClearBtn = new JButton("clear");
    dlModel = new DefaultListModel();
    dlModel.addElement("list test");
    constList = new JList(dlModel);
    constList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    constScroll = new JScrollPane();
    constScroll.getViewport().setView(constList);
    constScroll.setPreferredSize(new Dimension(300, 300));
    selectConstBtn = new JButton("select");

    /* 右矢印 */
    rightArrow = new JLabel("→");
    rightArrow.setFont(new Font("Arial", Font.BOLD, 25));

    /* パラメータ表示用 */
    paramLabel = new JLabel("parameter");
    paramLabel.setFont(commonFont);
    objectNameLabel = new JLabel("object name");
    objectNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    objectNameText = new JTextField();
    objectNameText.setPreferredSize(new Dimension(200, 30));
    paramConstLabel = new JLabel("constructor");
    paramConstLabel.setFont(new Font("Arial", Font.BOLD, 15));
    paramTextFiled = new JTextField();
    paramTextFiled.setPreferredSize(new Dimension(250, 30));

    /* オブジェクト一覧表示用 */
    objectLabel = new JLabel("objects");
    objectLabel.setFont(commonFont);
    objectList = new JList();
    objectList.setPreferredSize(new Dimension(300, 200));
    objectList.setBorder(new EtchedBorder(EtchedBorder.RAISED));

    /* オブジェクト生成用 */
    createObjectBtn = new JButton("create");

    /* 共通設定 */
    constraints.insets = new Insets(5, 5, 5, 5);
    //constraints.gridwidth = GridBagConstraints.REMAINDER;

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

    /* 右矢印 */
    addComp(rightArrow, 6, 9, 1, 1);

    /* パラメータ表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(paramLabel, 7, 6, 2, 1);
    addComp(objectNameLabel, 7, 7, 2, 1);
    addComp(objectNameText, 7, 8, 2, 1);
    addComp(paramConstLabel, 7, 9, 2, 1);
    addComp(paramTextFiled, 7, 10, 2, 1);

    /* オブジェクト一覧表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objectLabel, 7, 1, 3, 1);
    addComp(objectList, 7, 2, 3, 3);

    /* オブジェクト生成用 */
    constraints.anchor = GridBagConstraints.EAST;
    addComp(createObjectBtn, 9, 12, 1, 1);

    new MessageFrame();

    searchBtn.addActionListener(this);
    constClearBtn.addActionListener(this);
    selectConstBtn.addActionListener(this);

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
      classController.searchButton(searchBox.getText());

      searchBox.setText(""); // 検索ボックスをクリアする
    } else if (source == constClearBtn) { // コンストラクタクリアボタン
      dlModel.clear();
      constList.ensureIndexIsVisible(dlModel.getSize() - 1);
      classController.constClear();
    } else if (source == selectConstBtn) { // コンストラクタ選択ボタン
      if (constList.isSelectionEmpty()) {
        System.out.println("コンストラクタを選択してください");
      } else {
        System.out.println(constList.getSelectedValue());
        classController.selectButton((String)constList.getSelectedValue());
      }
    } else if (source == createObjectBtn) { // オブジェクト生成ボタン

    }
  }

  public void printConstructorList (Constructor<?>[] cons) {
    for (Constructor<?> con : cons) {
      dlModel.addElement(con.toString());
      constList.ensureIndexIsVisible(dlModel.getSize() - 1);
    }
  }

  public void printConstLabel(String constName) {
    paramConstLabel.setText(constName);
  }

  class MyMouseListener extends MouseAdapter implements MouseListener {

  }
}
