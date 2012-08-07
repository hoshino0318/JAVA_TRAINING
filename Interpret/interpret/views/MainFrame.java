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
  private JButton searchButton; // クラス検索用
  private JLabel constLabel;    // コンストラクタ用
  private JButton constClearButton;  // コンストラクタ用
  private JList constList;      // コンストラクタ用
  private DefaultListModel dlModel; //コンストラクタ用
  private JScrollPane constScroll; // コンストラクタ用

  private JLabel objectLabel;   // オブジェクト一覧用
  private JList  objectList;   // オブジェクト一覧用

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

    /* 各コンポーネントの生成 */
    title = new JLabel("Interpret");
    title.setFont(new Font("Arial", Font.BOLD, 30));

    /* クラス検索用 */
    searchLabel = new JLabel("class");
    searchLabel.setFont(commonFont);
    searchBox = new JTextField();
    searchBox.setPreferredSize(new Dimension(200, 40));
    searchButton = new JButton("search");

    /* コンストラクタ一覧表示用 */
    constLabel = new JLabel("constructors");
    constLabel.setFont(commonFont);
    constClearButton = new JButton("clear");
    dlModel = new DefaultListModel();
    dlModel.addElement("list test");
    constList = new JList(dlModel);
    constScroll = new JScrollPane();
    constScroll.getViewport().setView(constList);
    constScroll.setPreferredSize(new Dimension(300, 400));

    /* オブジェクト一覧表示用 */
    objectLabel = new JLabel("objects");
    objectLabel.setFont(commonFont);
    objectList = new JList();
    objectList.setPreferredSize(new Dimension(300, 300));
    objectList.setBorder(new EtchedBorder(EtchedBorder.RAISED));

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
    addComp(searchButton, 4, 2, 1,1);
    /* コンストラクタ一覧表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(constLabel, 0, 3, 3, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(constClearButton, 4, 3, 1, 1);
    addComp(constScroll, 0, 4, 5, 6);
    /* オブジェクト一覧表示 */
    constraints.anchor = GridBagConstraints.WEST;
    addComp(objectLabel, 5, 1, 3, 1);
    addComp(objectList, 5, 2, 3, 3);

    new MessageFrame();

    searchButton.addActionListener(this);
    constClearButton.addActionListener(this);

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

    if (source == searchButton) {
      classController.searchButton(searchBox.getText());

      searchBox.setText(""); // 検索ボックスをクリアする
    } else if (source == constClearButton) {
      dlModel.clear();
      constList.ensureIndexIsVisible(dlModel.getSize() - 1);
    }
  }

  public void printConstructorList (Constructor<?>[] cons) {
    for (Constructor<?> con : cons) {
      dlModel.addElement(con.toString());
      constList.ensureIndexIsVisible(dlModel.getSize() - 1);
    }
  }
}
