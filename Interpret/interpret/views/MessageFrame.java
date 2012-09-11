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
 * 標準出力を GUI 表示するためのクラス */
class MessageFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = -3941456441208116678L;

  private JLabel title;
  private JTextArea outArea; // standard output
  private JTextArea errArea; // error output
  private JScrollPane outScrollPane; // standard output
  private JScrollPane errScrollPane; // error output
  private JButton oClearBtn;
  private JButton eClearBtn;
  private JButton aClearBtn; // all clear button

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  MessageFrame() {
    super("Message");
    setSize(800, 800);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* レイアウトの設定 */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    /* コンポーネントの生成 */
    title = new JLabel("Message");
    title.setFont(new Font("Arial", Font.BOLD, 20));
    oClearBtn = new JButton("message clear");
    eClearBtn = new JButton("error clear");
    aClearBtn = new JButton("all clear");
    outArea = new JTextArea();
    outArea.setLineWrap(true);
    errArea = new JTextArea();
    outArea.setLineWrap(true);
    /* 枠線の設定 (中に空の枠線を設定する) */
    Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    outArea.setBorder(BorderFactory.createCompoundBorder(border,
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    errArea.setBorder(BorderFactory.createCompoundBorder(border,
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    outArea.setEditable(false);  // Read only
    errArea.setEditable(false);  // Read only
    outScrollPane = new JScrollPane(outArea);
    errScrollPane = new JScrollPane(errArea);
    outScrollPane.setPreferredSize(new Dimension(700, 400));
    errScrollPane.setPreferredSize(new Dimension(700, 200));

    JTextAreaStream outStream = new JTextAreaStream(outArea);
    JTextAreaStream errStream = new JTextAreaStream(errArea);
    PrintStream outPStream = new PrintStream(outStream, true); // AutoFlush
    PrintStream errPStream = new PrintStream(errStream, true); // AutoFlush
    System.setOut(outPStream);
    System.setErr(errPStream);

    /* コンポーネントの追加 */
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.anchor = GridBagConstraints.WEST;
    addComp(title, 0, 0, 1, 1);
    constraints.anchor = GridBagConstraints.EAST;
    addComp(oClearBtn, 1, 0, 1, 1);
    addComp(outScrollPane, 0, 1, 2, 6);
    addComp(eClearBtn, 1, 7, 1, 1);
    addComp(errScrollPane, 0, 8, 2, 1);
    addComp(aClearBtn, 1, 9, 1, 1);

    /* ボタンのリスナーを登録 */
    oClearBtn.addActionListener(this);
    eClearBtn.addActionListener(this);
    aClearBtn.addActionListener(this);

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

    if (source == oClearBtn) {
      outArea.setText("");
    } else if (source == eClearBtn) {
      errArea.setText("");
    } else if (source == aClearBtn) {
      outArea.setText("");
      errArea.setText("");
    }
  }
}
