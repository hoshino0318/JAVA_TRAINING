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

class DigitalClock extends JWindow {
  private static final long serialVersionUID = -5780612053215771853L;
  private static final Font DEFAULT_CLOCK_FONT = new Font("Consolas", Font.BOLD, 60);

  private DateFormat sdf;
  private TimeZone timeZone;
  private ClockPanel mainPanel;
  private ClockProperty property;

  private boolean isFontChanged;
  private PropertyPopupMenu propertyPopupMenu;

  DigitalClock() {
    setSize(300, 200);
    setLocationRelativeTo(null);

    isFontChanged = true;

    /* setting for a clock */
    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    property = new ClockProperty(DEFAULT_CLOCK_FONT, Color.BLACK, Color.WHITE);

    mainPanel = new ClockPanel(getSize());
    getContentPane().add(mainPanel);

    /* 200 ミリ秒間隔で再描画 */
    Timer timer = new Timer(true);
    timer.schedule(new PaintTimer(), 0, 200);

    propertyPopupMenu = new PropertyPopupMenu();
    addMouseListener(new MouseClickListener());
    addMouseMotionListener(new WindowMotionLinstener());
    setVisible(true);
  }

  void changeFont() {
    isFontChanged = true;
  }

  private class MouseClickListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3) {
        propertyPopupMenu.show(e.getComponent(), e.getX(), e.getY());
      } else if (e.getClickCount() >= 2) {  // クリック 2 回
        System.out.println("mosue double Clicked");
        if (e.getButton() == MouseEvent.BUTTON1) {
          Color fontColor = property.getFontColor();
          Color backGroundColor = property.getBackGroundColor();
          property.setFontColor(reverseColor(fontColor));
          property.setBackGroundColor(reverseColor(backGroundColor));
        }
      }
    }
  }

  private class WindowMotionLinstener extends MouseMotionAdapter {
    @Override
    public void mouseMoved(MouseEvent e) {
      // TODO
      //System.out.println("MouseMoved");
    }
    public void mouseDragged(MouseEvent e) {
      // TODO
//      /System.out.println("MouseDragged");
    }
  }

  private class ClockPanel extends JPanel {
    private static final long serialVersionUID = -3865314627366195394L;

    ClockPanel(Dimension d) {
      super();
      setPreferredSize(d);
      setOpaque(true);
      setForeground(Color.BLACK);
      setBackground(property.getBackGroundColor());

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

  }

  private Color reverseColor(Color color) {
    return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
  }

  private class PaintTimer extends TimerTask {
    @Override
    public void run() {
      repaint();
    }
  }
}
