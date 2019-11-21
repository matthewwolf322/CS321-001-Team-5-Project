import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

public class Display extends JFrame{
  
  //create global variables
  
  //Create common colors
  private static Color LIGHT_GREY = new Color(224,224,224);
  

  
  //create main frame
  private JFrame mainFrame;//master frame for program interface
  
  //create frames for lot images
  private JFrame firstFrame;
  private JFrame secondFrame;
  private JFrame thirdFrame;
  
  //create panels for map and user input
  private JPanel userPanel;//holds upperUserPanel, centerUserPanel and aboutPanel 
  private JPanel mapPanel;//displays main map
  
  //smaller panels to hold objects
  private JPanel inputPanel;//holds combo boxes
  private JPanel goButtonPanel;//holds "Find Lot" Button
  private JPanel buttonPanel;//holds 1st/2nd/3rd lot buttons
  private JPanel aboutPanel;// hold about button and permit info button
  private JPanel upperUserPanel; //Holds the inputPanel and title
  private JPanel centerUserPanel; //holds buttonPanel and title

  //each panel holds a label and button for displaying specific lots
  private JPanel firstButtonPanel;
  private JPanel secondButtonPanel;
  private JPanel thirdButtonPanel;
  

  // create panels for displaying specific lots
  // 3 panels hold the three images (zoomed in map of lots) 
  // first_map_Panel holds map image of 1st closest lot
  // second_map_Panel holds map image of 2nd closest lot 
  // third_map_Panel holds map image of 3rd closest lot
  private JPanel first_map_Panel;
  private JPanel second_map_Panel;
  private JPanel third_map_Panel;
  

  //create user navigation buttons
  private JButton btn_findLot;
  private JButton btn_permitInfo;
  private JButton btn_about;
  

  //Show ordered lots
  private JButton btn_firstLot;
  private JButton btn_secondLot;
  private JButton btn_thirdLot;
  
  //general labels
  private JLabel lbl_title;//title for userPanel
  private JLabel lbl_selectPermit;//title for inputPanel
  private JLabel lbl_selectLot; //title for buttonPanel
  
  //lables for lots
  private JLabel lbl_firstLot;
  private JLabel lbl_secondLot;
  private JLabel lbl_thridLot;
  
  //create combo boxes for main user interface
  private JComboBox<String> permitCombo;//for user panel
  private JComboBox<String> buildingCombo;
  
  //create text areas
  private JTextArea helpText;
  
  //combo box values
  private String [] permitList;
  private String [] buildingList;
  
  //check if permtit and building have been selected
  private boolean selected_permit = false;
  private boolean selected_building = false;
  
  //values selected in combo boxes
  private String inputted_permit;
  private String inputted_building;
  
  BestLotFinder blf;
  
  //for displaying the intro message
  Timer timer; 
  int delay = 0;
  
  
  //for displaying permit information
  //all permit frames/panels/buttons/comboboxes
  
  private JFrame permitFrame;//to display permit information
  private JPanel permitTitlePanel;//to display title for permit frame
  private JPanel findPermitPanel;//to get permit name
  private JPanel displayPermitPanel; //= new JPanel();//displays
  private boolean permitFrameIsOpen = false;
  
  private JLabel lbl_permit_title;
  private JComboBox<String> selectPermitCombo; //for permit info panel
  private JButton btn_goToPermit;
  private boolean canDisplay = false; //determines if user has selected a permit
  private String permitName;
  private BufferedImage permitImage;

  
  
  private JFrame aboutFrame;
  private JPanel aboutInfoPanel;
  private JLabel lbl_aboutText;
  private boolean aboutFrameIsOpen = false;
  
  /*
   * Display creates a new BestLotFinder Object and creates GUI
   */ 
  public Display() throws IOException{

    blf = new BestLotFinder(); //call best lot finder
    
    //get lists for combo boxes
    permitList = blf.getPermitList();
    buildingList = blf.getBuildingList();
    
    initTimer(); //set timer to show intro message and then display mainframe
    
    createMainFrame();
    createCombos();
    createButtons();
    createUpperUserPanel();//create panel to hold the top set of userPanel components
    createCenterUserPanel();
    createAboutPanel();
    createUserPanel();
    
    mainFrame.add(userPanel,BorderLayout.EAST);
    mainFrame.add(helpText,BorderLayout.SOUTH);
    mainFrame.pack();//conform frame to size of components
    mainFrame.setVisible(true);//set visible after elements are added
  }
  
  /*
   * Create a new introMessage object, run the intro screen and then open
   * the main display
   */
  public static void main (String[] args) throws IOException{
    introMessage titleScreen = new introMessage();
    titleScreen.run();
  }
  
  /*
   * initTimer runs a timer to allow the intro screen to play before opening the main gui
   * 
   */ 
  public void initTimer(){
    timer = new Timer(12000, new ActionListener(){
    public void actionPerformed(ActionEvent evt){
      if (delay < 11){
        delay++;
      }
      else{
        timer.stop();
      }
    } 
 });
    timer.start();
  }

  public void createMainFrame() throws IOException{
    mainFrame = new JFrame("Smart Parking");
    mainFrame.setSize(3000,2000);
    mainFrame.setLocationRelativeTo(null);
    JFrame.setDefaultLookAndFeelDecorated(true);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    mapPanel = new JPanel();
    
    //get map image
    BufferedImage image = ImageIO.read(new File("assets/mapOfFairfaxCampus.png"));
    JLabel label = new JLabel(new ImageIcon(image));
    label.setBorder(BorderFactory.createRaisedBevelBorder());
    
    //add map to mapPanel
    mapPanel.add(label);
    mapPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    
    //add mapPanel to mainFrame
    mainFrame.add(mapPanel);   
  }
  /*
   * The mainframe is broken up into two main panels, the user panel and the map panel
   * the user panel holds all interactive/input features of the main frame
   * The user panel contains the upperUserPanel (user input), centerUserPanel(lot buttons),
   * and aboutPanel(about and permit buttons)
   */ 
  public void createUserPanel(){
    //initialize userPanel
    userPanel = new JPanel();
    userPanel.setPreferredSize(new Dimension(500,700));
    userPanel.setLayout(new GridLayout(3,1));//three sections divided vertically
    userPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    
    //add components
    userPanel.add(upperUserPanel);
    userPanel.add(centerUserPanel);
    userPanel.add(aboutPanel); 
  }
  /*
   * upperUserPanel holds the inputPanel and goButtonPanel
   * 
   */ 
  public void createUpperUserPanel(){
    //build sub panels
    createInputPanel();
    createGoButtonPanel();
    
    //initialize upperUserPanel
    upperUserPanel = new JPanel();
    upperUserPanel.setPreferredSize(new Dimension(500,100));
    upperUserPanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 0))); //15
    upperUserPanel.setBackground(LIGHT_GREY);
    
    //set up title
    lbl_title = new JLabel("WELCOME TO SMART PARKING", SwingConstants.CENTER);
    lbl_title.setForeground(Color.BLACK);
    lbl_title.setFont(new Font("Bookman Old Style", Font.BOLD, 27));
    lbl_title.setBorder(new EmptyBorder(new Insets(5, 0, 10, 5)));
    
    //set up label for comboboxes
    lbl_selectPermit = new JLabel("Please Enter Permit and Building", SwingConstants.CENTER);
    lbl_selectPermit.setForeground(new Color (150,20,3));
    lbl_selectPermit.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
    lbl_selectPermit.setBorder(new EmptyBorder(new Insets(5, 0, 10, 5)));
    
    //add title, combobox label, comboboxes (inputPanel) and goButton (goButtonPanel)
    upperUserPanel.add(lbl_title);
    upperUserPanel.add(Box.createRigidArea(new Dimension(0,30)));
    upperUserPanel.add(lbl_selectPermit);
    upperUserPanel.add(Box.createRigidArea(new Dimension(0,30)));
    upperUserPanel.add(inputPanel);
    upperUserPanel.add(goButtonPanel);
    upperUserPanel.add(Box.createRigidArea(new Dimension(10,30)));
    
    //add warning
    JLabel warning = new JLabel(("Warning: Smart Parking does not gaurantee a parking spot"), SwingConstants.CENTER);
    warning.setFont(new Font ("Seif", Font.ITALIC, 15));
    warning.setPreferredSize(new Dimension(500,20));
    upperUserPanel.add(Box.createRigidArea(new Dimension(500,10)));
    upperUserPanel.add(warning);
    upperUserPanel.setBorder(BorderFactory.createRaisedBevelBorder());   
  }
  
  /*
   * inputPanel holds permitCombo and buildingCombo
   * 
   */ 
  public void createInputPanel(){
    //initialize inputPanel
    inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension (450,90));
    inputPanel.setBackground(LIGHT_GREY);
    
    //add components
    inputPanel.add(permitCombo);
    inputPanel.add(buildingCombo);
  }
  /*
   * goButtonPanel holds the findLot button
   */ 
  public void createGoButtonPanel(){
    //initialize goButtonPanel
    goButtonPanel = new JPanel();
    goButtonPanel.setPreferredSize(new Dimension(445,50));
    goButtonPanel.setAlignmentX(LEFT_ALIGNMENT);
    BoxLayout boxLayout_X = new BoxLayout(goButtonPanel,BoxLayout.LINE_AXIS);
    
    //add components
    goButtonPanel.add(Box.createRigidArea(new Dimension(295,30)));
    goButtonPanel.add(btn_findLot);
    goButtonPanel.setBackground(LIGHT_GREY);
  }
  /*
   * ButtonPanel holds firstButtonPanel, secondButtonPanel, and thirdButtonPanel
   * 
   */ 
  public void createButtonPanel(){
    //initialize buttonPanel
    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(500,350));
    buttonPanel.setBackground(LIGHT_GREY);
    
    //build subpanels
    createFirstLotButtonPanel();
    createSecondLotButtonPanel();
    createThirdLotButtonPanel();
    
    //add components
    buttonPanel.add(firstButtonPanel);
    buttonPanel.add(Box.createRigidArea(new Dimension(500,10)));
    buttonPanel.add(secondButtonPanel);
    buttonPanel.add(Box.createRigidArea(new Dimension(500,10)));
    buttonPanel.add(thirdButtonPanel);
  }
  
  /*
   * CenterUserPanel holds the lot buttons (part of the ButtonPanel)
   */ 
  public void createCenterUserPanel(){
    //initialize centerUserPanel
    centerUserPanel = new JPanel();
    centerUserPanel.setPreferredSize(new Dimension(500,200));
    centerUserPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    centerUserPanel.setBackground(LIGHT_GREY);
    centerUserPanel.setBorder(new EmptyBorder(new Insets(29, 0, 0, 15)));
    
    //set up title label
    lbl_selectLot = new JLabel(" Click Buttons To View The Best Lots",SwingConstants.CENTER );
    lbl_selectLot.setPreferredSize(new Dimension(495,20));
    lbl_selectLot.setForeground(new Color (0,102,0));
    lbl_selectLot.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
    
    //build subpanel
    createButtonPanel();
    
    //add components
    centerUserPanel.add(lbl_selectLot);
    centerUserPanel.add(Box.createRigidArea(new Dimension(495,20)));
    centerUserPanel.add(buttonPanel);
  }
  /*
   * create(First/Second/Third)Lot Button Panels
   * these methods, create a panel that holds a button and a label
   * the panels created by these methods are added to the buttonPanel
   * 
   */ 
  public void createFirstLotButtonPanel(){
    //initialize firstButtonPanel
    firstButtonPanel = new JPanel();
    firstButtonPanel.setPreferredSize(new Dimension (450,60));
    firstButtonPanel.setBackground(LIGHT_GREY);
    
    //create button label
    JLabel lbl_firstLot = new JLabel("Closest Lot");
    lbl_firstLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));

    //fix label and button to fit panel size
    lbl_firstLot.setPreferredSize(new Dimension(150,20));
    btn_firstLot.setPreferredSize(new Dimension(250,50));
        
    //add components
    firstButtonPanel.add(lbl_firstLot);
    firstButtonPanel.add(btn_firstLot);
    
  }
  
  public void createSecondLotButtonPanel(){
    //initialize secondButtonPanel
    secondButtonPanel = new JPanel();
    secondButtonPanel.setPreferredSize(new Dimension (450,60));
    secondButtonPanel.setBackground(LIGHT_GREY);
    
    //create button label
    JLabel lbl_secondLot = new JLabel("2nd Closest");
    lbl_secondLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
    
    //fix label and button to fit panel size
    lbl_secondLot.setPreferredSize(new Dimension(150,20));
    btn_secondLot.setPreferredSize(new Dimension(250,50));
    
    //add components
    secondButtonPanel.add(lbl_secondLot);
    secondButtonPanel.add(btn_secondLot);

  }
  
  public void createThirdLotButtonPanel(){
    //initialize thirdButtonPanel
    thirdButtonPanel = new JPanel();
    thirdButtonPanel.setPreferredSize(new Dimension (450,60));
    thirdButtonPanel.setBackground(LIGHT_GREY);
    
    //create button label
    JLabel lbl_thirdLot = new JLabel("3rd Closest");
    lbl_thirdLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
    
    //fix label and button to fit panel size
    lbl_thirdLot.setPreferredSize(new Dimension(150,20));
    btn_thirdLot.setPreferredSize(new Dimension(250,50));
    
    //add components
    thirdButtonPanel.add(lbl_thirdLot);
    thirdButtonPanel.add(btn_thirdLot);
  }
  
  public void createAboutPanel(){
    //initialize aboutPanel
    aboutPanel = new JPanel();
    aboutPanel.setPreferredSize(new Dimension(100,5));
    aboutPanel.setLayout(new GridLayout(4,3));
    aboutPanel.setBackground(LIGHT_GREY);
    aboutPanel.setBorder(new EmptyBorder(new Insets(56, 20, 20, 50)));
    
    //add components
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));//spacing
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(btn_permitInfo);//add permit info button
    btn_permitInfo.setBorder(new EmptyBorder(new Insets(10, 5, 5, 5)));
    aboutPanel.add(Box.createRigidArea(new Dimension(20,0)));
    aboutPanel.add(btn_about); //add about info button
    btn_about.setBorder(new EmptyBorder(new Insets(5, 5,5, 5)));
  }
  
  public void createFirstFrame(LotDistance [] ld) throws IOException{
    //initialize firstFrame
    firstFrame = new JFrame("Closest Lot");
    firstFrame.setSize(3000,3000);
    firstFrame.setLocationRelativeTo(null);
   
    String first_lot_name = ld[0].getName();//get first lot name
    
    //set up first map panel
    first_map_Panel = new JPanel();
    BufferedImage image2 = ImageIO.read(new File(getLotImage(first_lot_name)));
    JLabel label2 = new JLabel(new ImageIcon(image2));
    first_map_Panel.add(label2);
    
    //add components
    firstFrame.add(first_map_Panel);
    firstFrame.pack();
  } 

  public void createSecondFrame(LotDistance [] ld) throws IOException{
    //initialize secondFrame
    secondFrame = new JFrame("Second Closest Lot");
    secondFrame.setSize(3000,3000);
    secondFrame.setLocationRelativeTo(null);
   
    String sec_lot_name = ld[1].getName();//get second lot name
    
    //set up second map panel
    second_map_Panel = new JPanel();
    BufferedImage image3 = ImageIO.read(new File(getLotImage(sec_lot_name)));
    JLabel label3 = new JLabel(new ImageIcon(image3));
    second_map_Panel.add(label3);
    
    //add components
    secondFrame.add(second_map_Panel);
    secondFrame.pack();
    
  }
  
  public void createThirdFrame(LotDistance [] ld) throws IOException {
    //initialize thirdFrame
    thirdFrame = new JFrame("Third Closest Lot");
    thirdFrame.setSize(3000,3000);
    thirdFrame.setLocationRelativeTo(null);

    String third_lot_name = ld[2].getName(); //get third lot name
    
    //set up third map panel
    third_map_Panel = new JPanel();
    BufferedImage image4 = ImageIO.read(new File(getLotImage(third_lot_name)));
    JLabel label4 = new JLabel(new ImageIcon(image4));
    third_map_Panel.add(label4);
    
    //add components
    thirdFrame.add(third_map_Panel);
    thirdFrame.pack();
    
  }

  // Helper Method: returns the path name of image containing the lot lot_dist_name
  public String getLotImage(String lot_dist_name) {
    if (lot_dist_name.equals("Lot A") || lot_dist_name.equals("Lot C") ||
        lot_dist_name.equals("Lot L") || lot_dist_name.equals("Lot J") ||
        lot_dist_name.equals("Lot K") || lot_dist_name.equals("Global Center Lot")) {
      return "assets/mapOfFairfaxCampus-ACLJKGlobal-l.png";
    }
    
    else if(lot_dist_name.equals("Lot M") || lot_dist_name.equals("Lot O") ||
            lot_dist_name.equals("Lot P") || lot_dist_name.equals("PV Lot") ||
            lot_dist_name.equals("Lot I")) {
      return "assets/mapOfFairfaxCampus-POMPVI-l.png";
    } 
    
    else if(lot_dist_name.equals("Lot D") || lot_dist_name.equals("Rapphannock Deck")) {
      return "assets/mapOfFairfaxCampus-DRRPD-l.png";
    }
    
    else if(lot_dist_name.equals("Lot R") || lot_dist_name.equals("Mason Pond Deck") || lot_dist_name.equals("Shenandoah Deck")) {
      return "assets/mapOfFairfaxCampus-RMasonPondShenndoah-l.png";
    }
    else if(lot_dist_name.equals("West Campus Lot")) {
      return "assets/mapOfFairfaxCampus-WestCampus-l.png";
    }
     return "assets/mapOfFairfaxCampus.png";  
  } 

  public void createAboutFrame() throws IOException{
    //initialize aboutFrame
    aboutFrame = new JFrame("About Smart Parking");
    aboutFrame.setSize(1000,500);
    aboutFrame.setLocationRelativeTo(null);
    
    aboutFrame.addWindowListener(new WindowAdapter(){
      @Override
      public void windowClosing(WindowEvent windowEvent){
        aboutFrameIsOpen = false;
      }
    });

    //aboutFrame.setResizable(false);
    JPanel topColorPanel = new JPanel();
    topColorPanel.setPreferredSize(new Dimension(1000,70));
    //topColorPanel.setLayout(new GridLayout(1,5));
    //topColorPanel.setAlignmentX(CENTER_ALIGNMENT);
    //BoxLayout boxLayout_X = new BoxLayout(topColorPanel,BoxLayout.LINE_AXIS);
    //topColorPanel.setLayout(boxLayout_X);
    topColorPanel.setBackground(new Color(1,68,33));
    
    aboutInfoPanel = new JPanel();
    
    aboutInfoPanel.setBackground(new Color (255,255,255));
    aboutInfoPanel.setPreferredSize(new Dimension(1000,1000));
    String txt_title = ("About Smart Parking");
    //String txt_about = ("<html><h1 style = text-align: 'center';> Developers: Team 5 </h1>");

    BufferedImage aboutInfoImage = ImageIO.read(new File("assets/about_smart_parking.png"));
    JLabel label_i = new JLabel(new ImageIcon(aboutInfoImage));
    JLabel lbl_aboutPageTitle = new JLabel(txt_title);
    aboutInfoPanel.add(label_i);
    aboutInfoPanel.setBackground(Color.WHITE);
    
    lbl_aboutPageTitle.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
    lbl_aboutPageTitle.setForeground(new Color (255,165,0));
    lbl_aboutPageTitle.setBorder(new EmptyBorder(new Insets(0, 20, 0, 0)));
    topColorPanel.add(lbl_aboutPageTitle);

    aboutFrame.add(topColorPanel, BorderLayout.NORTH);
    aboutFrame.add(aboutInfoPanel);
    aboutFrame.repaint();
    aboutFrame.setVisible(true);
    aboutFrameIsOpen = true;
  }
  public void createPermitInfoFrame( boolean IsSelected, String permitName) throws IOException{
    permitFrame = new JFrame("Display Permit Information");//to display permit information
    permitFrame.setSize(1400,1400);
    permitFrame.setLocationRelativeTo(null);
    permitFrame.setResizable(false);
    
      permitFrame.addWindowListener( new WindowAdapter(){
      @Override
      public void windowClosing(WindowEvent windowEvent){
        permitFrameIsOpen = false;
      }
      });
    permitTitlePanel = new JPanel();
    permitTitlePanel.setPreferredSize(new Dimension(1400,200));
    permitTitlePanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 30)));//10
    permitTitlePanel.setBackground(new Color (1,68,33));
    
    lbl_permit_title = new JLabel("   GMU PERMIT INFORMATION", SwingConstants.CENTER);
    lbl_permit_title.setPreferredSize(new Dimension(1400,110));
    lbl_permit_title.setBorder(new EmptyBorder(new Insets(0, 10, 20, 0)));
    lbl_permit_title.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
    lbl_permit_title.setForeground( new Color (255,165,0));//gold
    
    permitTitlePanel.add(lbl_permit_title);
    
    findPermitPanel = new JPanel();//to get permit name
    findPermitPanel.setPreferredSize(new Dimension(1000,60));
    
    findPermitPanel.setBorder(new EmptyBorder(new Insets(0, 10, 5, 0))); //30
    findPermitPanel.setBackground(new Color (1,68,33));

    findPermitPanel.add(selectPermitCombo);
    findPermitPanel.add(Box.createRigidArea(new Dimension(20,0)));
    findPermitPanel.add(btn_goToPermit);
    
    permitTitlePanel.add(findPermitPanel);
    
    //displays
    displayPermitPanel = new JPanel();
    displayPermitPanel.setPreferredSize(new Dimension(200,100));
    displayPermitPanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 15)));
    displayPermitPanel.setBackground(new Color (1,68,33));
    if( IsSelected == true){
      try{
        permitImage = ImageIO.read(new File(getPermitImage(permitName)));
      }
      catch(IOException e){
        permitImage = ImageIO.read(new File("assets/George_Mason_Patriots_logo.png"));
      }
    }
    else
    {
      permitImage = ImageIO.read(new File("assets/George_Mason_Patriots_logo.png"));
    }
    JLabel label = new JLabel(new ImageIcon(permitImage));
    //label.setBorder(BorderFactory.createRaisedBevelBorder());
    
    //add map to mapPanel
    displayPermitPanel.add(label);
    permitFrame.add(permitTitlePanel, BorderLayout.NORTH);
    permitFrame.add(displayPermitPanel);
    permitFrame.setVisible(true);
    selectPermitCombo.setSelectedIndex(0);
    permitFrameIsOpen = true;
  }
  
  public void displayPermitInfo(String givenName){
    
    try{
      //get new image
      permitImage = ImageIO.read(new File(getPermitImage(givenName)));
      JLabel label = new JLabel(new ImageIcon(permitImage));
      label.setBorder(BorderFactory.createRaisedBevelBorder());
      
      //replace previous image on panel with new image
      displayPermitPanel.removeAll();
      displayPermitPanel.revalidate();
      displayPermitPanel.repaint();
      displayPermitPanel.add(label); 
      permitFrame.add(displayPermitPanel);
      permitFrame.setVisible(true);//reset the image
    }
    catch(IOException e){
      System.out.print("Cannot display image");
    }
  }
  
  //Helper method: gets image name based on given permit name
  public String getPermitImage(String permitName){
    if (permitName.equals("West Campus") || permitName.equals("Lot M & P Permit") ||
        permitName.equals("General") || permitName.equals("Resident Student Annual Lite")){
      return "assets/student_surface.png";
    }
    
    else if (permitName.equals("West Campus F/S") || permitName.equals("Lot M & P F/S") ||
        permitName.equals("General Faculty/Staff Annual")){
      return "assets/FFX_Faculty_Staff_Surface_Lot.png";
    }
    
    else if(permitName.equals("Mason Pond (Student Annual)") || permitName.equals("Shenandoah Deck (Reserved Student)") ||
            permitName.equals("Rappahannock River Parking Deck")){
      return "assets/FFX_Student_Reserved_Deck.png";
    }
    
    else if(permitName.equals("Lot J Permit") || permitName.equals("Lot I Permit")|| permitName.equals("Lot R Permit")) { //change to Permit
      return "assets/FFX_Reserved_Surface_Lot-l2.png";
    }
    else if (permitName.equals("Mason Pond F/S Annual") ||
        permitName.equals("Shenandoah F/S Annual") || permitName.equals("RRPD F/S Annual")|| permitName.equals("RRPD Roof Only F/S Annual")){
      return "assets/FFX_Faculty_Staff_Reserved_Deck-l.png";
    } 
    
    return null; 
  }
  
  /*
   * createCombos creates all combos for the mainFrame and permitFrame
   * combos: 
   * For Input Panel - permitCombo, building Combo
   * For Permit Frame - selectPermitCombo
   * 
  */ 
  public void createCombos(){
    
    // selected_permit and selected_building used to check if the user selected both the permit and the building 
    
    //initialize permitCombo--------------------------------------------------
    permitCombo = new JComboBox<String>(permitList);
    permitCombo.setFont(new Font("Arial", Font.BOLD, 20));
    permitCombo.setSelectedIndex(0);//set value to "Select Permit Type"
    
    //combo action - set inputted_permit to selected value, set selected_permit to true if user has selected a value
    permitCombo.addActionListener(
      new ActionListener(){
      @Override
          public void actionPerformed(ActionEvent e){
            JComboBox<String> this_combo = (JComboBox<String>)e.getSource();

            String command = e.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                    inputted_permit = (String) permitCombo.getSelectedItem();//get selection
                    if(inputted_permit.equals("Select Permit Type")){//check that selection is valid
                      selected_permit = false;
                    }
                    else{
                      selected_permit = true;
                    }
            }
      }
    });
    
    //initialize builidngCombo-------------------------------------------------
    buildingCombo = new JComboBox<String>(buildingList);
    buildingCombo.setFont(new Font("Arial", Font.BOLD, 20));
    buildingCombo.setPreferredSize(permitCombo.getPreferredSize());
    buildingCombo.setSelectedIndex(0); //set value to "Select Building"
    
     //combo action - set inputted_building to selected value, set selected_ building to true if user has selected a value
    buildingCombo.addActionListener(
      new ActionListener(){
      @Override
          public void actionPerformed(ActionEvent e){
            JComboBox<String> this_combo = (JComboBox<String>)e.getSource();

            String command = e.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                    inputted_building = (String) buildingCombo.getSelectedItem();//get selection
                    if(inputted_building.equals("Select Building")){//check that selection is valid
                      selected_building = false;
                    }
                    else{
                      selected_building = true;
                    }
            }
      }
      
    });
    
    //initialize selectPermitCombo-------------------------------------------
    selectPermitCombo = new JComboBox<String>(permitList);
    selectPermitCombo.setFont(new Font("Arial", Font.BOLD, 25));
    selectPermitCombo.setSelectedIndex(0);
    
     //combo action - set permitName to selected value, set canDisplay to true if user has selected a value
    selectPermitCombo.addActionListener(
      new ActionListener(){
      @Override
          public void actionPerformed(ActionEvent e){
            JComboBox<String> this_combo = (JComboBox<String>)e.getSource();

            String command = e.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                    permitName = (String) selectPermitCombo.getSelectedItem();//get user selection
                    if(permitName.equals("Select Permit Type")){//check that selection is valid
                      canDisplay = false;
                      System.out.print(canDisplay);
                    }
                    else{
                      canDisplay = true;
                      System.out.print(canDisplay);
                    }
            }
      }  
    });
    

  }
  /*
   * createButtons creates all buttons for the mainFrame and permitFrame
   * buttons: 
   * For goButton Panel - btn_findPermit
   * For Button Panel - btn_firstLot, btn_secondLot, btn_thirdLot
   * For About Panel - btn_permitInfo, btn_about
   * For Permit Frame - btn_goToPermit
   * 
   */ 
  public void createButtons() throws IOException{
    //initialize btn_firstLot------------------------------------------------
    btn_firstLot = new JButton("#1 Lot");
    btn_firstLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_firstLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_firstLot.setBorder(BorderFactory.createRaisedBevelBorder());
    btn_firstLot.setEnabled(false);//not clickable until btn_findLot is clicked
    
    //button action - display first lot frame
    btn_firstLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        firstFrame.setVisible(true);
      }
      
    }); 
    
    //initialize btn_secondLot------------------------------------------------
    btn_secondLot = new JButton("#2 Lot");
    btn_secondLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_secondLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_secondLot.setEnabled(false);
    
    //button action - display second lot frame
    btn_secondLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        secondFrame.setVisible(true);
      }
    });
    
    //initialize btn_thirdLot--------------------------------------------------
    btn_thirdLot = new JButton("#3 Lot");
    btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_thirdLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_thirdLot.setEnabled(false);
    
    //button action - display third lot frame
    btn_thirdLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        thirdFrame.setVisible(true);
      }
    });
    
    helpText = new JTextArea();//help text appears at bottom of screen to display errors
    
    //initialize btn_findLot----------------------------------------------------
    btn_findLot = new JButton("Find Lot");
    btn_findLot.setFont(new Font("Arial", Font.BOLD, 25));
    
    //button action - calculate best lot, set lot buttons active, create lot frames
    btn_findLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {
        //helpText.removeAll();
        //helpText.setForeground(Color.RED);
        //helpText.append("Smart Parking determines best lots used on we");
        
        //check that a building and permit have been selected
        if (selected_building == true && selected_permit == true) {
           
           //get best lot calculation
           blf.setPermit(inputted_permit);
           blf.setBuilding(inputted_building);
           LotDistance [] ld = blf.findBestLot();
           
           //if statements in place to check how many parking lots are available 
           //(some permit types restrict to one or two parking lots)
           
           //create frames and set lot buttons active
           if (ld.length >= 3) { 
             
             try {
               createFirstFrame(ld); 
               btn_firstLot.setText(ld[0].getName());
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               
               createSecondFrame(ld);
               btn_secondLot.setText(ld[1].getName());
               btn_secondLot.setBackground(Color.YELLOW);
               btn_secondLot.setEnabled(true);
               
               createThirdFrame(ld);
               btn_thirdLot.setText(ld[2].getName());
               btn_thirdLot.setBackground(Color.RED);
               btn_thirdLot.setEnabled(true);
             }
             catch(IOException e) {
               System.out.println("Can't open lot image files\n");
             }
           }
           else if (ld.length == 2) { 
             try {
               createFirstFrame(ld); 
               btn_firstLot.setText(ld[0].getName());
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               
               createSecondFrame(ld);
               btn_secondLot.setText(ld[1].getName());
               btn_secondLot.setBackground(Color.YELLOW);
               btn_secondLot.setEnabled(true); 
               
               btn_thirdLot.setText("None");
             }
             catch(IOException e) {
               System.out.println("Can't open lot image files\n");
             }
           }
           else if (ld.length >= 1) {
             try {
               createFirstFrame(ld); 
               btn_firstLot.setText(ld[0].getName());
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               
               btn_secondLot.setText("None");
               btn_secondLot.setEnabled(false);
               
               btn_thirdLot.setText("None");
               btn_thirdLot.setEnabled(false);
             }
             catch(IOException e) {
               System.out.println("Can't open lot image files\n");
             }
           }
          
          mainFrame.pack();
        }
        else if (selected_building == false && selected_permit == true){
          int answer = JOptionPane.showConfirmDialog(mainFrame, "No Building Selected. Continue with 'Johnson Center' as Building?", "Caution" ,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (answer == JOptionPane.YES_OPTION){
            buildingCombo.setSelectedIndex(26);//set buildingCombo to Johnson Center
            btn_findLot.doClick(); //click btn_findLot to perform calculations
          }
        }
        else{
         JOptionPane.showMessageDialog(mainFrame, "Please Select a Permit and Building to Continue");
        }
      }  
    });
    //initialize btn_permitInfo---------------------------------------------------
    btn_permitInfo = new JButton("Permits");
    btn_permitInfo.setFont(new Font("Arial", Font.BOLD, 22));
    
    //button action - create new permit info frame
    btn_permitInfo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0){
        try{
          if(permitFrameIsOpen == false){
            if (selected_permit == true){
              createPermitInfoFrame(true,inputted_permit);
            }
            else{
              createPermitInfoFrame(false,null);
            }
          }
          else{
            JOptionPane.showMessageDialog(mainFrame, "Permit Informaiton Page Already Open");
          }
        }
        catch(IOException e){
          e.printStackTrace();
        }
        
      }
      
    });
    
    //initialize btn_about-------------------------------------------------------
    btn_about = new JButton("About");
    btn_about.setFont(new Font("Arial", Font.BOLD, 22));
    
    //button action - create about frame
    btn_about.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        if(aboutFrameIsOpen == false){
          try{
            createAboutFrame();  
          }
          catch(IOException e){
            e.printStackTrace();
          }  
          

          }
          else{
            JOptionPane.showMessageDialog(mainFrame, "About Page Already Open");
          }
      }
    });
    
    //initialize btn_goToPermit, button located on Permit Frame-------------------
    btn_goToPermit = new JButton(" Go ");
    btn_goToPermit.setBorder(BorderFactory.createBevelBorder(0));
    btn_goToPermit.setFont(new Font("Arial", Font.BOLD, 35));
    btn_goToPermit.setForeground(Color.BLACK);
    
    //button action - call displayPermitInfo() (change the image based on the permit name)
    btn_goToPermit.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        //get permit combo info
        if (canDisplay == true){//check that a permit has been selected in the combo box
          displayPermitInfo(permitName);
        }
        else{
          JOptionPane.showMessageDialog(mainFrame, "Please Select a Permit to Continue");
        }
      }
      
    });
  }
  
}
