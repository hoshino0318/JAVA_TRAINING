package ex01_04;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

class DigitalClock extends Frame implements ActionListener, Runnable {
  private DateFormat sdf;
  private TimeZone timeZone;
  private Thread thread;
  Font clockFont;
  Color fontColor;
  Color clockBackColor;
  
  private PropertyDialog propertyDialog;

  DigitalClock() {
    super("DigitalClock");
    setResizable(false);
    setLocationRelativeTo(null);
    setBackground(clockBackColor);
    clockFont = new Font("Arial", Font.BOLD, 60);
    fontColor = Color.BLACK;
    clockBackColor = Color.WHITE;

    /* close window */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       System.exit(0);
      }
    });

    /* setting for a clock */
    sdf = new SimpleDateFormat("HH:mm:ss");
    timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    sdf.setTimeZone(timeZone);

    /* add a MenuBar & Menu */
    MenuBar menuBar = new MenuBar();
    setMenuBar(menuBar);

    Menu menu = new Menu("Menu");
    menu.addActionListener(this);
    menuBar.add(menu);

    MenuItem propMenu = new MenuItem("Properties");
    menu.add(propMenu);
    
    /* create property dialog */
    propertyDialog = new PropertyDialog(this, "Properties");
    propertyDialog.init();

    setVisible(true);    
    
    thread = new Thread(this);
    thread.start();
  }

  public void paint(Graphics g) {
    Image img = createImage(getWidth(), getHeight());
    Graphics2D buffer = (Graphics2D)img.getGraphics();

    Calendar calendar = Calendar.getInstance();
    String time_str = sdf.format(calendar.getTime());

    buffer.setFont(clockFont);
    FontMetrics metrics = buffer.getFontMetrics();
    Insets insets = getInsets();
    int strWidth = metrics.stringWidth(time_str);
    int strHeight = metrics.getDescent() + metrics.getAscent();
    int width = strWidth + insets.left + insets.right;
    int height = strHeight + insets.top;
    setSize(width, height);

    int x = 0;
    int y = metrics.getAscent();

    buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    buffer.setColor(fontColor);
    buffer.drawString(time_str, x, y);
    setBackground(clockBackColor);

    g.drawImage(img, insets.left, insets.top, this);
  }

  public void run() {
    while (true){
      repaint();
      try {
        /* 250 msec 間隔で再描画 */
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();

    if (actionCommand.equals("Properties")) {
      //new PropertyDialog(this, actionCommand, 500, 400);
      propertyDialog.setVisible(true);
    } else {
      // nothing to do
    }
  }
  
  void changeClockFont(String fontName) {
    setClockFont(fontName, clockFont.getStyle(), clockFont.getSize());
  }

  void changeClockFontSize(int fontSize) {
    setClockFont(clockFont.getFontName(), clockFont.getStyle(), fontSize);
  }

  void changeFontColor(String fontColorName) {
    fontColor = ColorUtil.getColorInstance(fontColorName);
  }

  void changeBackColor(String backColorName) {
    clockBackColor = ColorUtil.getColorInstance(backColorName);
  }

  private void setClockFont(String name, int style, int size) {
    clockFont = new Font(name, style, size);
  }

  /* create digital clock */
  public static void main(String[] args) {
    new DigitalClock();
  }
}
