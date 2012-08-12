package ex01_01;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

class DigitalClock extends Frame implements Runnable {
  private static final long serialVersionUID = -6187735690601361227L;
  private DateFormat sdf;
  private TimeZone timeZone;
  private Thread thread;

  DigitalClock(int width, int height) {
    super("DitalClock");
    setSize(width, height);
    setLocationRelativeTo(null);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       System.exit(0);
      }
    });

    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    setVisible(true);
    thread = new Thread(this);
    thread.start();
  }

  public void paint(Graphics g) {
    Calendar calendar = Calendar.getInstance();
    String time_str = sdf.format(calendar.getTime());
    int x = (getWidth() / 2) - 30;
    int y = getHeight() / 2;
    g.drawString(time_str, x, y);
  }

  public void run() {
    while (true){
      repaint();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
      }
    }
  }

  public static void main(String[] args) {
    new DigitalClock(400, 300);
  }
}
