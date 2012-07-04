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
  private Property property;

  private PropertyDialog propertyDialog;

  DigitalClock() {
    super("DigitalClock");
    setResizable(false);
    setLocationRelativeTo(null);    

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
    Menu menu = new Menu("Menu");
    setMenuBar(menuBar);
    menu.addActionListener(this);
    menuBar.add(menu);
    MenuItem propMenu = new MenuItem("Properties");
    menu.add(propMenu);
    
    /* create a properties and a properties dialog */
    property = new Property();
    propertyDialog = new PropertyDialog(this, "Properties");
    propertyDialog.init();

    setBackground(property.getBackColor());
    setVisible(true);    
    
    thread = new Thread(this);
    thread.start();
  }

  public void paint(Graphics g) {
    Image img = createImage(getWidth(), getHeight());
    Graphics2D buffer = (Graphics2D)img.getGraphics();

    Calendar calendar = Calendar.getInstance();
    String time_str = sdf.format(calendar.getTime());

    buffer.setFont(property.getFont());
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

    buffer.setColor(property.getFontColor());
    buffer.drawString(time_str, x, y);
    setBackground(property.getBackColor());

    g.drawImage(img, insets.left, insets.top, this);
  }

  public void run() {
    while (true){
      repaint();
      try {
        /* 500 msec 間隔で再描画 */
        Thread.sleep(500);
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
    setClockFont(fontName, property.getFont().getStyle(), property.getFont().getSize());
  }

  void changeClockFontSize(int fontSize) {
    setClockFont(property.getFont().getFontName(), property.getFont().getStyle(), fontSize);
  }

  void changeFontColor(String fontColorName) {
    property.setFontColor(ColorUtil.getColorInstance(fontColorName));
  }

  void changeBackColor(String backColorName) {
    property.setBackColor(ColorUtil.getColorInstance(backColorName));
  }
  
  void restoreProperty(Property property) {
    this.property = property;
  }
  
  Property getCurrentProperty() {
    return new Property(property);
  }

  private void setClockFont(String name, int style, int size) {
    property.setFont(new Font(name, style, size));
  }
}
