package interpret.views;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 標準出力を表示するためのクラス */
class MessageFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = -3941456441208116678L;

  private JLabel title;
  private JTextArea msgArea;
  private JButton clearButton;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  MessageFrame() {
    super("Message");
    setSize(500, 600);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* レイアウトの設定 */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* コンポーネントの生成 */
    title = new JLabel("メッセージ");
    title.setFont(new Font("Arial", Font.BOLD, 20));
    clearButton = new JButton("clear");
    msgArea = new JTextArea();
    msgArea.setLineWrap(true);
    /* 枠線の設定 (中に空の枠線を設定する) */
    Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    msgArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    msgArea.setEditable(false);  // Read only
    msgArea.setPreferredSize(new Dimension(400, 500));

    JTextAreaStream stream = new JTextAreaStream(msgArea);
    PrintStream pStream = new PrintStream(stream, true); // true は AutoFlush の設定
    System.setOut(pStream);
    System.setErr(pStream);

    /* コンポーネントの追加 */
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.anchor = GridBagConstraints.WEST;
    addComp(title, 0, 0, 1, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(clearButton, 1, 0, 1, 1);
    addComp(msgArea, 0, 1, 2, 9);

    System.out.println("message test");

    clearButton.addActionListener(this);

    setVisible(true);
  }

  public class JTextAreaStream extends OutputStream {
    private JTextArea _area;
    private ByteArrayOutputStream _buf;

    public JTextAreaStream(JTextArea area) {
      _area = area;
      _buf = new ByteArrayOutputStream();
    }

    @Override
    public void write(int b) throws IOException {
      _buf.write(b);
    }

    @Override
    public void flush() throws IOException {
      // Swing のイベントスレッドにのせる
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          _area.append(_buf.toString());
          _buf.reset();
        }
      });
    }
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

    if (source == clearButton) {
      msgArea.setText("");
    }
  }

}
