package ex02_02;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DigitalClock extends JFrame {
  private static final long serialVersionUID = -5780612053215771853L;
  private static final Font CLOCK_FONT = new Font("Consolas", Font.BOLD, 60);

  private DateFormat sdf;
  private TimeZone timeZone;
  private MainPanel mainPanel;

  DigitalClock() {
    super("DigitalClock");
    setSize(300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    /* setting for a clock */
    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    mainPanel = new MainPanel(getSize());
    getContentPane().add(mainPanel);

    ClockMenuBar clockMenuBar = new ClockMenuBar();
    setJMenuBar(clockMenuBar);

    /* 1000 ミリ秒間隔で再描画 */
    Timer timer = new Timer(true);
    timer.schedule(new PaintTimer(), 0, 1000);

    setVisible(true);
  }

  private class MainPanel extends JPanel {
    private static final long serialVersionUID = -3865314627366195394L;

    MainPanel(Dimension d) {
      super();
      setPreferredSize(d);
      setOpaque(true);
      setForeground(Color.BLACK);
      setBackground(Color.WHITE);

      addMouseListener(new DoubleClickListener());
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      Calendar calendar = Calendar.getInstance();
      String time_str = sdf.format(calendar.getTime());

      g2.setFont(CLOCK_FONT);
      g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                              RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      FontMetrics metrics = g2.getFontMetrics();
      Insets insets = DigitalClock.this.getInsets();
      int strWidth = metrics.stringWidth(time_str);
      int strHeight = metrics.getDescent() + metrics.getAscent();
      int width = strWidth + insets.left + insets.right;
      int height = strHeight + insets.top;
      DigitalClock.this.setMinimumSize(new Dimension(width, height));

      int x = (getWidth() / 2) - (strWidth / 2);
      int y = metrics.getAscent() + (getHeight() - metrics.getAscent()) / 2;
      g2.drawString(time_str, x, y);
    }

    private class DoubleClickListener extends MouseAdapter {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) {
          if (getBackground().equals(Color.WHITE)) {
            setForeground(Color.WHITE);
            setBackground(Color.BLACK);
          } else {
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
          }
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
