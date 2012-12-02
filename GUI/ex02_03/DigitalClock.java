package ex02_03;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JWindow;
import javax.swing.JPanel;

//class DigitalClock extends JFrame {
class DigitalClock extends JWindow {
  private static final long serialVersionUID = -5780612053215771853L;
  private static final Font DEFAULT_CLOCK_FONT = new Font("Consolas", Font.BOLD, 60);

  private DateFormat sdf;
  private TimeZone timeZone;
  private MainPanel mainPanel;
  private ClockProperty property;
  private PropertyDialog propertyDialog;

  private boolean isFontChanged;

  DigitalClock() {
    setSize(300, 200);
    setLocationRelativeTo(null);

    isFontChanged = true;

    /* setting for a clock */
    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    property = new ClockProperty(DEFAULT_CLOCK_FONT, Color.BLACK, Color.WHITE);
    //propertyDialog = new PropertyDialog(this, property);
    mainPanel = new MainPanel(getSize());
    getContentPane().add(mainPanel);

    /* 200 ミリ秒間隔で再描画 */
    Timer timer = new Timer(true);
    timer.schedule(new PaintTimer(), 0, 200);

    setVisible(true);
  }

  void changeFont() {
    isFontChanged = true;
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
      int height = strHeight + insets.top;
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
        if (e.getClickCount() == 1) {         // クリック 1 回
          if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("右クリック 1 回");
          }
        } else if (e.getClickCount() >= 2) {  // クリック 2 回
          if (e.getButton() == MouseEvent.BUTTON1) {
            Color fontColor = property.getFontColor();
            Color backGroundColor = property.getBackGroundColor();
            property.setFontColor(reverseColor(fontColor));
            property.setBackGroundColor(reverseColor(backGroundColor));
          }
        }
      }

      private Color reverseColor(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
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
