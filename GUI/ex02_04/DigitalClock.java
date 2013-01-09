package ex02_04;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.prefs.*;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DigitalClock extends JFrame {
  private static final long serialVersionUID = -5780612053215771853L;
  private static final Font DEFAULT_CLOCK_FONT = new Font("Consolas", Font.BOLD, 60);

  private DateFormat sdf;
  private TimeZone timeZone;
  private MainPanel mainPanel;
  private ClockProperty property;
  private ClockProperty propertySnapshot = null;
  private PropertyDialog propertyDialog;
  private HelpDialog helpDialog;
  private ClockMenuBar menuBar;
  private boolean isFontChanged; // フォントが変更されたかどうか
                                 // フォントの変更に合わせてウィンドウのサイズを変更するために必要
  private Preferences prefs;

  static final String PREFS_PROPERTY_KEY = "Propery.DigitalClock.Test";
  static final String PREFS_LOCATION_KEY = "Location.DigitalClock.Test";

  DigitalClock() {
    super("DigitalClock");
    setSize(300, 200);

    /* ウィンドウが閉じられた時 */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exitClock();
      }
    });

    isFontChanged = true;

    /* setting for a clock */
    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    prefs = Preferences.userNodeForPackage(getClass());
    property = newProperty();
    setLocation();
    propertyDialog = new PropertyDialog(this, property);
    helpDialog = new HelpDialog();
    menuBar = new ClockMenuBar(this, propertyDialog, helpDialog);
    setJMenuBar(menuBar);

    mainPanel = new MainPanel(getSize());
    getContentPane().add(mainPanel);

    /* 200 ミリ秒間隔で再描画 */
    Timer timer = new Timer(true);
    timer.schedule(new PaintTimer(), 0, 200);

    /* ショートカットキー用のリスナーを登録 */
    addKeyListener(new ClockKeyListener());

    setVisible(true);
  }

  void changeFont() {
    isFontChanged = true;
  }

  /** 時計の状態をスナップショットを作成した時点まで戻す */
  void rollback() {
    System.out.println("[Debug]: DigitalClock.rollback()");
    property = propertySnapshot;
    propertyDialog.setProperty(propertySnapshot);
    isFontChanged = true; // フォントの変更がもとに戻った際にウィンドウのサイズを調整必要がある
  }

  /** 時計の設定をプリファレンスに書き出す */
  void saveProperty() {
    try {
      PrefObj.putObject(prefs, PREFS_PROPERTY_KEY, property);
      PrefObj.putObject(prefs, PREFS_LOCATION_KEY, getLocation());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /** 時計の状態を保存 (rollback() と対) */
  void snapshot() {
    System.out.println("[Debug]: DigitalClock.snapshot()");
    propertySnapshot = new ClockProperty(property);
  }

  private ClockProperty newProperty() {
    ClockProperty property = new ClockProperty(DEFAULT_CLOCK_FONT, Color.BLACK, Color.WHITE);
    try {
      Object obj = PrefObj.getObject(prefs, PREFS_PROPERTY_KEY);
      if (obj != null) {
        property = (ClockProperty)obj;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return property;
  }

  private void setLocation() {
    setLocationRelativeTo(null);
    try {
      Object obj = PrefObj.getObject(prefs, PREFS_LOCATION_KEY);
      if (obj != null) {
        setLocation((Point)obj);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /** 現在の状態を保存して時計を終了する */
  void exitClock() {
    saveProperty();
    System.exit(0);
  }

  private class MainPanel extends JPanel {
    private static final long serialVersionUID = -3865314627366195394L;

    MainPanel(Dimension d) {
      super();
      setPreferredSize(d);
      setOpaque(true);
      setForeground(Color.BLACK);
      setBackground(property.getBackGroundColor());

      addMouseListener(new DoubleClickListener());
    }

    @Override
    public void paintComponent(Graphics g) {
      setForeground(property.getFontColor());
      setBackground(property.getBackGroundColor());
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setFont(property.getFont());
      g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                              RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

      Calendar calendar = Calendar.getInstance();
      String time_str = sdf.format(calendar.getTime());

      FontMetrics metrics = g2.getFontMetrics();
      Insets insets = DigitalClock.this.getInsets();
      int strWidth = metrics.stringWidth(time_str);
      int strHeight = metrics.getDescent() + metrics.getAscent();
      int width = strWidth + insets.left + insets.right;
      int height = strHeight + insets.top + menuBar.getHeight();
      setSizeOneTime(width, height);

      int x = (getWidth() / 2) - (strWidth / 2);
      int y = metrics.getAscent() + (getHeight() - metrics.getAscent()) / 2;
      g2.drawString(time_str, x, y);
    }

    private void setSizeOneTime(int width, int height) {
      if (!isFontChanged)
        return;

      DigitalClock.this.setMinimumSize(new Dimension(width, height));
      DigitalClock.this.setSize(new Dimension(width, height));
      isFontChanged = false;
    }

    private class DoubleClickListener extends MouseAdapter {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) {
          if (e.getButton() == MouseEvent.BUTTON1) {
            Color fontColor = property.getFontColor();
            Color backGroundColor = property.getBackGroundColor();
            property.setFontColor(reverseColor(fontColor));
            property.setBackGroundColor(reverseColor(backGroundColor));
          } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (menuBar.isVisible()) {
              menuBar.setVisible(false);
            } else {
              menuBar.setVisible(true);
            }
          }
        }
      }

      private Color reverseColor(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
      }
    }
  }

  /**
   * Alt  + VK_ENTER : プロパティを開く
   * Ctrl + VK_W     : 時計を終了する
   */
  private class ClockKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      int keyCode   = e.getKeyCode();
      int modiriers = e.getModifiers();
      if (modiriers == InputEvent.ALT_MASK) {
        switch (keyCode) {
        case KeyEvent.VK_ENTER:
          propertyDialog.setVisible(true);
          break;
        default:
          // nothing to do
          break;
        }
      } else if (modiriers == InputEvent.CTRL_MASK) {
        switch (keyCode) {
        case KeyEvent.VK_H:
          helpDialog.setVisible(true);
          break;
        case KeyEvent.VK_W:
          exitClock();
          break;
        default:
          // nothing to do
          break;
        }
      }
    }
  }

  private class PaintTimer extends TimerTask {
    @Override
    public void run() {
      repaint();
    }
  }
}
