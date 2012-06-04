package ex02_02;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

class DigitalClock extends Frame implements ActionListener, Runnable {
  private int width;
  private int height;
  private DateFormat sdf;
  private TimeZone timeZone;
  private Thread thread;
  private Font clockFont;
  private Color fontColor;
  private Color clockBackColor;

  DigitalClock(int width, int height) {
    super("DigitalClock");
    this.width = width; this.height = height;
    this.clockFont = new Font("Arial", Font.BOLD, 40);
    this.fontColor = Color.BLACK;
    this.clockBackColor = Color.WHITE;
    setSize(width, height);
    setLocationRelativeTo(null);
    setBackground(clockBackColor);

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

    setVisible(true);
    thread = new Thread(this);
    thread.start();
  }

  public void paint(Graphics g) {
    Image img = createImage(getWidth(), getHeight());
    Graphics2D buffer = (Graphics2D)img.getGraphics();

    Calendar calendar = Calendar.getInstance();
    String time_str = sdf.format(calendar.getTime());
    int x = (getWidth() / 2) - 80;
    int y = getHeight() / 2;
    
    buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    buffer.setFont(clockFont);
    buffer.setColor(fontColor);
    buffer.drawString(time_str, x, y);
        
    setBackground(clockBackColor);
    
    g.drawImage(img, 0, 0, this);
  }

  public void run() {
    while (true){
      repaint();
      try {
        thread.sleep(500);
      } catch (InterruptedException e) {
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();

    if (actionCommand.equals("Properties")) {
      System.out.println(actionCommand);
      new PropertyDialog(this, actionCommand, 500, 400);
    } else {
      // nothing to do
    }
  }
  
  private void setClockFont(String name, int style, int size) {
    clockFont = new Font(name, style, size);
  }
  
  private void setFontColor(String fontColorName) {
    if (fontColorName.equals("White")) {
      fontColor = Color.WHITE;
    } else if (fontColorName.equals("Black")) {
      fontColor = Color.BLACK;          
    } else if (fontColorName.equals("Blue")){
      fontColor = Color.BLUE;
    } else if (fontColorName.equals("Green")) {
      fontColor = Color.GREEN;
    } else {
      fontColor = Color.RED;
    }
  }
  
  private void setBackColor(String colorName) {
    if (colorName.equals("White")) {
      clockBackColor = Color.WHITE;
    } else if (colorName.equals("Black")) {
      clockBackColor = Color.BLACK;          
    } else if (colorName.equals("Blue")){
      clockBackColor = Color.BLUE;
    } else if (colorName.equals("Green")) {
      clockBackColor = Color.GREEN;
    } else {
      clockBackColor = Color.RED;
    }
  }

  class PropertyDialog extends Dialog implements ActionListener, ItemListener {
    private Choice fontChoice;
    private Choice fontSizeChoice;
    private Choice fontColorChoice;
    private Choice clockBackColorChoice;
    
    private String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private String[] fontSizes = {"10", "14", "18", "24", "30", "36"};
    private String[] fontColors = {"Black", "Blue", "Green", "Red"};
    private String[] clockBackColors = {"White", "Black", "Blue", "Green", "Red"};
    
    private Font propertyFont = new Font("Arial", Font.PLAIN, 20);
    
    PropertyDialog(Frame owner, String title, int width, int height) {
      super(owner, title, true);
      setSize(width, height);
      setResizable(false);
      setLocationRelativeTo(null);

      /* close window */
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
         setVisible(false);
        }
      });

      setLayout(new GridLayout(6, 1));
      Panel pTitle = new Panel();
      Panel pFont = new Panel();      
      Panel pFontSize = new Panel();
      Panel pFontColor = new Panel();
      Panel pClockBackColor = new Panel();
      Panel pOKButton = new Panel();
      add(pTitle);
      add(pFont);
      add(pFontSize);
      add(pFontColor);
      add(pClockBackColor);
      add(pOKButton);
      
      pTitle.setLayout(null);
      Label titleLabel = new Label(title);
      titleLabel.setBounds(170, 20, 150, 100);
      titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
      pTitle.add(titleLabel);
      
      pFont.setLayout(new FlowLayout());
      Label fontLabel = new Label("Font");
      fontLabel.setFont(propertyFont);
      pFont.add(fontLabel);
      fontChoice = new Choice();
      for (String font : fonts) {
        fontChoice.add(font);
      }
      fontChoice.addItemListener(this);
      pFont.add(fontChoice);
      
      pFontSize.setLayout(new FlowLayout());
      Label fontSizeLabel = new Label("Font Size");
      fontSizeLabel.setFont(propertyFont);
      pFontSize.add(fontSizeLabel);
      fontSizeChoice = new Choice();
      for (String fontSize : fontSizes) {
        fontSizeChoice.add(fontSize);
      }
      fontSizeChoice.addItemListener(this);
      pFontSize.add(fontSizeChoice);      
      
      pFontColor.setLayout(new FlowLayout());
      Label fontColorLabel = new Label("Font Color");
      fontColorLabel.setFont(propertyFont);
      pFontColor.add(fontColorLabel);
      fontColorChoice = new Choice();
      for (String fontColor : fontColors) {
        fontColorChoice.add(fontColor);
      }
      fontColorChoice.addItemListener(this);      
      pFontColor.add(fontColorChoice);
      
      pClockBackColor.setLayout(new FlowLayout());
      Label clockBackColorLabel = new Label("Clock Back Color");
      clockBackColorLabel.setFont(propertyFont);
      pClockBackColor.add(clockBackColorLabel);
      clockBackColorChoice = new Choice();
      for (String clockBackColor : clockBackColors) {
        clockBackColorChoice.add(clockBackColor);
      }
      clockBackColorChoice.addItemListener(this);
      pClockBackColor.add(clockBackColorChoice);

      pOKButton.setLayout(new FlowLayout());
      Button bOK = new Button("OK");
      bOK.addActionListener(this);
      pOKButton.add(bOK);

      setVisible(true);
    } // end PropertyDialog()

    public void actionPerformed(ActionEvent e) {
      String actionCommand =  e.getActionCommand();
      System.out.println(actionCommand);

      if (actionCommand.equals("OK")) {       
        setVisible(false);
      }
    }
    
    public void itemStateChanged(ItemEvent e) {
      Object source = e.getSource();
      
      if (source == fontChoice) {        
        System.out.println("font choice");
        String fontName = fontChoice.getSelectedItem();
        setClockFont(fontName, clockFont.getStyle(), clockFont.getSize());
      } else if (source == fontSizeChoice) {
        System.out.println("font size choice");
        int fontSize = Integer.parseInt(fontSizeChoice.getSelectedItem());
        setClockFont(clockFont.getFontName(), clockFont.getStyle(), fontSize);
      } else if (source == fontColorChoice) {
        System.out.println("font color choice");
        String fontColorName = fontColorChoice.getSelectedItem();
        setFontColor(fontColorName);
      } else if (source == clockBackColorChoice) {
        System.out.println("clock back color choice");
        String backColorName = clockBackColorChoice.getSelectedItem();
        setBackColor(backColorName);
      } else {
        // this never occurs
        throw new InternalError();
      }
    }
  }

  public static void main(String[] args) {
    new DigitalClock(400, 300);
  }
}
