package interpret.views;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
  private static final long serialVersionUID = -4570424264389438080L;

  private JLabel title; //タイトル
  private JTextField searchBox; // クラス検索用
  private JButton searchButton; // クラス検索用

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  public MainFrame(){
    super("MainFrame");
    setSize(new Dimension(1000, 600));
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* レイアウトの設定 */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* 各コンポーネントの生成 */
    title = new JLabel("Interpret");
    searchBox = new JTextField("hogehoge");
    searchButton = new JButton("検索");

    /* タイトルの表示 */
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridx = 0;
    constraints.gridy = 0;
    layout.setConstraints(title, constraints);
    add(title);

    new MessageFrame();

    setVisible(true);
  }
}
