package ex01_04;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.prefs.*;

class DigitalClock extends Frame implements ActionListener, MouseMotionListener, Runnable {
  private DateFormat sdf;
  private TimeZone timeZone;
  private Thread thread;
  private Property property;
  private MenuItem propertyMenu;
  
  private PropertyDialog propertyDialog;
  private PropertyPopupMenu popupMenu;
  
  private Point clockPosition;
  private Preferences prefs;
  
  static final String PREFS_PROPERTY_KEY = "hoshinoPropery";
  static final String PREFS_LOCATION_KEY = "hoshinoLocation";

  DigitalClock() {
    super("DigitalClock");
    setResizable(false);
    setLocationRelativeTo(null);

    /* close window */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        saveProperty();
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
    propertyMenu = new MenuItem("Properties");
    propertyMenu.addActionListener(this);
    menu.add(propertyMenu);

    /* get a preference */
    prefs = Preferences.userNodeForPackage(getClass());
    try {
      Object obj = PrefObj.getObject(prefs, PREFS_PROPERTY_KEY);
      if (obj != null) {
        property = (Property)obj;
      } else {
        property = new Property();
      }      
      obj = PrefObj.getObject(prefs, PREFS_LOCATION_KEY);
      if (obj != null) {
        setLocation((Point)obj);
      } else {
        setLocationRelativeTo(null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    propertyDialog = new PropertyDialog(this, "Properties");
    propertyDialog.init();
    
    /* set a pop up menu */
    popupMenu = new PropertyPopupMenu();
    popupMenu.init();
    add(popupMenu);
    
    /* add a mouse click event */
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        /* right click event */
        if (e.getButton() == MouseEvent.BUTTON3) {
          popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          System.out.println("mouse pressed");
          clockPosition = e.getLocationOnScreen();
        }
      }
      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          System.out.println("mouse released");
          clockPosition = null;
        }
      }      
    });
    
    addMouseMotionListener(this);
        
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
    Object source = e.getSource();

    if (source == propertyMenu) {
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
  
  /** set a window location in a screen */
  private void ajustWindowLocation(Point newLocation) {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    if (newLocation.x < 0)
      newLocation.x = 0;
    if (newLocation.y < 0)
      newLocation.y = 0;
    if (newLocation.x + getSize().width > screen.width)
       newLocation.x = screen.width - getSize().width;
    if (newLocation.y + getSize().height > screen.height)
      newLocation.y = screen.height - getSize().height;

    setLocation(newLocation);
  }
  
  @Override
  public void mouseMoved(MouseEvent e) {}
  @Override
  public void mouseDragged(MouseEvent e) {
    if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
      if (clockPosition == null)
        clockPosition = e.getLocationOnScreen();

      int dx = e.getXOnScreen() - clockPosition.x;
      int dy = e.getYOnScreen() - clockPosition.y;

      clockPosition = e.getLocationOnScreen();

      Point newLocation = getLocation();
      newLocation.translate(dx, dy);

      //ajustWindowLocation(newLocation);
      setLocation(newLocation);
    }
  }

  private void setClockFont(String name, int style, int size) {
    property.setFont(new Font(name, style, size));
  }
}
