package interpret.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import interpret.controllers.*;

public class ArrayDialog extends JDialog implements ActionListener {

  private static final long serialVersionUID = 3923262360161817944L;
  private JLabel objTitle;
  private JLabel aryNumLabel;  // 配列サイズ表示用
  private JLabel clsNameLabel;     // クラス表示用

  private JButton closeBtn; // 閉じるボタン

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
    addComp(closeBtn, 3, 1, 1, 1);

    /* リスナーの登録 */
    closeBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == closeBtn) {
      setVisible(false);
    }
  }

  public void setObjTitle(String objTitle) {
    this.objTitle.setText(objTitle);
  }

  public void setAryNum(String aryNum) {
    aryNumLabel.setText(aryNum);
  }

  public void setClassNameLabel(String className) {
    clsNameLabel.setText(className);
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
