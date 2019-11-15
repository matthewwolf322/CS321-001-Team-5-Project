import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import java.io.IOException;


public class Display extends JFrame{

  
  //create main frame
  private JFrame mainFrame;//master frame for program interface
  private JFrame findPermitFrame;//to get permit name
  private JFrame permitInfoFrame;//to display 
  
  //create frames for lot images
  private JFrame firstFrame;
  private JFrame secondFrame;
  private JFrame thirdFrame;
  
  //create panels for map and user input
  private JPanel userPanel;//holds upperUserPanel, centerUserPanel and key_infoPanel 
  private JPanel mapPanel;//displays main map
  
  //smaller panels to hold objects
  private JPanel inputPanel;//holds combo boxes
  private JPanel goButtonPanel;//holds "Find Lot" Button
  private JPanel buttonPanel;//holds 1st/2nd/3rd lot buttons
  private JPanel key_infoPanel;// hold about button and permit info button
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
  
  private JLabel lbl_permit_title;
  private JLabel lbl_permit_selectPermit;
  
  
  //create combo boxes
  private JComboBox<String> permitCombo;
  private JComboBox<String> buildingCombo;
  
  //create text areas
  private JTextArea helpText;
  
  
  private String [] permitList;
  private String [] buildingList;
  

  boolean selected_permit = false;
  boolean selected_building = false;
  
  String inputted_permit;
  String inputted_building;
  
  BestLotFinder blf;
  
  //for displaying the intro message
  Timer timer; 
  int delay = 0;
  
  public Display() throws IOException{

    blf = new BestLotFinder(); //call best lot finder
    permitList = blf.getPermitList();
    buildingList = blf.getBuildingList();
    
    initTimer(); //set timer to show intro message and then display mainframe
    
    createMainFrame();
    createCombos();
    createButtons();
    createUpperUserPanel();//create panel to hold the top set of userPanel components
    createCenterUserPanel();
    createKeyInfoPanel();
    createUserPanel();
    
    mainFrame.add(userPanel,BorderLayout.EAST);
    mainFrame.add(helpText,BorderLayout.SOUTH);
    mainFrame.pack();//conform frame to size of components
    mainFrame.setVisible(true);//set visible after elements are added
  }
  
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
    //BufferedImage image = ImageIO.read(new File("assets/MapOfFairfaxCampus.png"));
    JLabel label = new JLabel(new ImageIcon(image));
    label.setBorder(BorderFactory.createRaisedBevelBorder());
    
    //add map to mapPanel
    mapPanel.add(label);
    mapPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    
    //add mapPanel to mainFrame
    mainFrame.add(mapPanel);   
  }
  
  public void createUserPanel(){
    userPanel = new JPanel();
    userPanel.setPreferredSize(new Dimension(500,700));
    userPanel.setLayout(new GridLayout(3,1));//four sections
    userPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    userPanel.add(upperUserPanel);
    userPanel.add(centerUserPanel);
    userPanel.add(key_infoPanel); 
  }
  
  public void createUpperUserPanel(){
    createInputPanel();
    createGoButtonPanel();
    
    upperUserPanel = new JPanel();
    upperUserPanel.setPreferredSize(new Dimension(500,100));
    upperUserPanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 15)));
    upperUserPanel.setBackground(new Color (224,224,224));
    
    lbl_title = new JLabel("WELCOME TO SMART PARKING");
    lbl_title.setForeground(Color.BLACK);
    lbl_title.setFont(new Font("Bookman Old Style", Font.BOLD, 27));
    lbl_title.setBorder(new EmptyBorder(new Insets(5, 0, 20, 5)));
    
    lbl_selectPermit = new JLabel("Please Enter Permit and Building");
    lbl_selectPermit.setForeground(new Color (150,20,3));
    lbl_selectPermit.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
    lbl_selectPermit.setBorder(new EmptyBorder(new Insets(5, 0, 20, 5)));
    
    upperUserPanel.add(lbl_title);
    upperUserPanel.add(Box.createRigidArea(new Dimension(0,30)));
    upperUserPanel.add(lbl_selectPermit);
    upperUserPanel.add(Box.createRigidArea(new Dimension(0,10)));
    upperUserPanel.add(inputPanel);
    upperUserPanel.add(goButtonPanel);
    upperUserPanel.setBorder(BorderFactory.createRaisedBevelBorder());
     
  }
  
  /*public String [] getPermitList(){
    String [] permitList = {"Select Permit Type", "A","B","C","I","J","RRPD", "K"};
    return permitList;
  }*/
  
  public void createInputPanel(){
    inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension (450,60));
    inputPanel.setBackground(new Color (224,224,224));
    inputPanel.add(permitCombo);
    inputPanel.add(Box.createRigidArea(new Dimension(11,0)));
    inputPanel.add(buildingCombo);
  }
  
  
  public void createGoButtonPanel(){
    goButtonPanel = new JPanel();
    goButtonPanel.setPreferredSize(new Dimension(445,50));
    goButtonPanel.setAlignmentX(LEFT_ALIGNMENT);
    BoxLayout boxLayout_X = new BoxLayout(goButtonPanel,BoxLayout.LINE_AXIS);
    goButtonPanel.add(Box.createRigidArea(new Dimension(295,50)));
    goButtonPanel.add(btn_findLot);
    goButtonPanel.setBackground(new Color (224,224,224));
  }
  
  public void createButtonPanel(){
    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(500,350));
    buttonPanel.setBackground(new Color (224,224,224));
    
    createFirstLotButtonPanel();
    createSecondLotButtonPanel();
    createThirdLotButtonPanel();
    
    buttonPanel.add(firstButtonPanel);
    buttonPanel.add(secondButtonPanel);
    buttonPanel.add(thirdButtonPanel);
  }
  
  public void createCenterUserPanel(){
    centerUserPanel = new JPanel();
    centerUserPanel.setPreferredSize(new Dimension(500,200));
    centerUserPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    centerUserPanel.setBackground(new Color (224,224,224));
    
    lbl_selectLot = new JLabel("Click To See Recommended Lots");
    lbl_selectLot.setForeground(new Color (0,102,0));
    lbl_selectLot.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
    
    centerUserPanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 15)));
    centerUserPanel.add(lbl_selectLot);
    centerUserPanel.add(Box.createRigidArea(new Dimension(0,5)));
    
    createButtonPanel();
  
    centerUserPanel.add(buttonPanel);
  }
  
  public void createFirstLotButtonPanel(){
    firstButtonPanel = new JPanel();
    firstButtonPanel.setPreferredSize(new Dimension (450,60));
    firstButtonPanel.setBackground(new Color (224,224,224));
    
    JLabel lbl_firstLot = new JLabel("Closest Lot");
    lbl_firstLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
    
    
    firstButtonPanel.add(lbl_firstLot);
    lbl_firstLot.setPreferredSize(new Dimension(150,20));
    btn_firstLot.setPreferredSize(new Dimension(250,50));
    
    firstButtonPanel.add(btn_firstLot);
    
  }
  
  public void createSecondLotButtonPanel(){
    secondButtonPanel = new JPanel();
    secondButtonPanel.setPreferredSize(new Dimension (450,60));
    secondButtonPanel.setBackground(new Color (224,224,224));
    
    JLabel lbl_secondLot = new JLabel("2nd Closest");
    lbl_secondLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
    
    
    secondButtonPanel.add(lbl_secondLot);
    lbl_secondLot.setPreferredSize(new Dimension(150,20));
    btn_secondLot.setPreferredSize(new Dimension(250,50));
    
    secondButtonPanel.add(btn_secondLot);

  }
  
  public void createThirdLotButtonPanel(){
    thirdButtonPanel = new JPanel();
    thirdButtonPanel.setPreferredSize(new Dimension (450,60));
    thirdButtonPanel.setBackground(new Color (224,224,224));
    
    JLabel lbl_thirdLot = new JLabel("3rd Closest");
    lbl_thirdLot.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
    
    
    thirdButtonPanel.add(lbl_thirdLot);
    lbl_thirdLot.setPreferredSize(new Dimension(150,20));
    btn_thirdLot.setPreferredSize(new Dimension(250,50));
    
    thirdButtonPanel.add(btn_thirdLot);
  }
  
  public void createKeyInfoPanel(){
    key_infoPanel = new JPanel();
    key_infoPanel.setPreferredSize(new Dimension(100,5));
    key_infoPanel.setLayout(new GridLayout(4,3));
    key_infoPanel.setBackground(new Color (224,224,224));

    key_infoPanel.setBorder(new EmptyBorder(new Insets(50, 20, 20, 50)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(btn_permitInfo);
    btn_permitInfo.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
    key_infoPanel.add(Box.createRigidArea(new Dimension(20,0)));
    key_infoPanel.add(btn_about);
    btn_about.setBorder(new EmptyBorder(new Insets(5, 5,5, 5)));
    //key_infoPanel.setBorder(BorderFactory.createMatteBorder(
                                   //1, 5, 1, 1, Color.red));
    
  }
  
  public void createFirstFrame(LotDistance [] ld) throws IOException{
    firstFrame = new JFrame("firstframe");
    firstFrame.setSize(3000,3000);
    firstFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String first_lot_name = ld[0].getName();
    
    first_map_Panel = new JPanel();
    BufferedImage image2 = ImageIO.read(new File(getLotImage(first_lot_name)));
    JLabel label2 = new JLabel(new ImageIcon(image2));
    first_map_Panel.add(label2);
    firstFrame.add(first_map_Panel);
    firstFrame.pack();
    
  } 

  public void createSecondFrame(LotDistance [] ld) throws IOException{
    secondFrame = new JFrame("secondframe");
    secondFrame.setSize(3000,3000);
    secondFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String sec_lot_name = ld[1].getName();
    
    second_map_Panel = new JPanel();
    BufferedImage image3 = ImageIO.read(new File(getLotImage(sec_lot_name)));
    JLabel label3 = new JLabel(new ImageIcon(image3));
    second_map_Panel.add(label3);
    secondFrame.add(second_map_Panel);
    secondFrame.pack();
    
  }
  
  public void createThirdFrame(LotDistance [] ld) throws IOException {
    thirdFrame = new JFrame("thirdframe");
    thirdFrame.setSize(3000,3000);
    thirdFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String third_lot_name = ld[2].getName();
    
    third_map_Panel = new JPanel();
    BufferedImage image4 = ImageIO.read(new File(getLotImage(third_lot_name)));
    JLabel label4 = new JLabel(new ImageIcon(image4));
    third_map_Panel.add(label4);
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
            lot_dist_name.equals("Lot P") || lot_dist_name.equals("Lot PV") ||
            lot_dist_name.equals("Lot I")) {
      return "assets/mapOfFairfaxCampus-POMPVI-l.png";
    } 
    
    else if(lot_dist_name.equals("Lot D") || lot_dist_name.equals("Rapphannock Deck")) {
      return "assets/mapOfFairfaxCampus-DRRPD-l.png";
    }
    
    else if(lot_dist_name.equals("Lot R") || lot_dist_name.equals("Mason Pond Deck") || lot_dist_name.equals("Shenandoah Parking Deck")) {
      return "assets/mapOfFairfaxCampus-RMasonPondShenndoah-l.png";
    }
    else if(lot_dist_name.equals("West Campus Lot")) {
      return "assets/mapOfFairfaxCampus-WestCampus-l.png";
    }
     return "assets/mapOfFairfaxCampus.png";  
  } 


  public void createPermitInfoFrames(){
     JFrame findPermitFrame = new JFrame("Select Permit");//to get permit name
     JFrame permitInfoFrame = new JFrame("Display Permit Information");//to display 
     findPermitFrame.setLocationRelativeTo(null);
     findPermitFrame.setResizable(false);
     JPanel findPermitPanel = new JPanel();
     
     findPermitPanel.setPreferredSize(new Dimension(500,100));
     findPermitPanel.setBorder(new EmptyBorder(new Insets(30, 0, 0, 15)));
     findPermitPanel.setBackground(new Color (224,224,224));
    
     lbl_permit_title = new JLabel("Find Your Permit Information");
     lbl_permit_title.setForeground(Color.BLACK);
     lbl_permit_title.setFont(new Font("Bookman Old Style", Font.BOLD, 27));
     lbl_permit_title.setBorder(new EmptyBorder(new Insets(5, 0, 20, 5)));
    
     lbl_permit_selectPermit = new JLabel("Please Enter Permit");
     lbl_permit_selectPermit.setForeground(new Color (150,20,3));
     lbl_permit_selectPermit.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
     lbl_permit_selectPermit.setBorder(new EmptyBorder(new Insets(5, 0, 20, 5)));
    
     findPermitPanel.add(lbl_permit_title);
     findPermitPanel.add(Box.createRigidArea(new Dimension(0,30)));
     findPermitPanel.add(lbl_selectPermit);
     findPermitPanel.add(Box.createRigidArea(new Dimension(0,10)));
     findPermitPanel.add(inputPanel);
     findPermitPanel.add(goButtonPanel);
     findPermitPanel.setBorder(BorderFactory.createRaisedBevelBorder());
     
     
     
     
     JLabel lbl_permitSelect = new JLabel("Select Permit Type");
     JComboBox permitSelectCombo = new JComboBox<String>(blf.getPermitList());
     permitSelectCombo.setFont(new Font("Arial", Font.BOLD, 30));
     permitSelectCombo.addActionListener(permitSelectCombo);
     
     findPermitPanel.add(lbl_permitSelect);
     findPermitPanel.add(permitSelectCombo);
     findPermitFrame.setVisible(true);
     findPermitFrame.add(findPermitPanel);
     
  }
  
  public String getPermitImage(String permitName){
    if (permitName.equals("West Campus") || permitName.equals("Lot M & P") ||
        permitName.equals("General") || permitName.equals("Resident Student Annual Lite")){
      return "assets/student_surface.png";
    }
    
    else if (permitName.equals("West Campus F/S") || permitName.equals("Lot M & P F/S") ||
        permitName.equals("General Faculty/Staff Annual")){
      return "assets/FFX_Faculty_Staff_Surface_Lot.png";
    }
    
    /*else if(permitName.equals("Mason Pond (Student Annual)") || permitName.equals("Shenandoah Deck (Reserved Student)") ||
            permitName.equals("Rappahannock River Parking Deck")){
      return "assets/FFX_Student_Reserved_Deck.png";
    }*/
    
    else if(permitName.equals("Lot J") || permitName.equals("Lot I")|| permitName.equals("Lot R")) {
      return "assets/FFX_Reserved_Surface_Lot-l2.png";
    }
    else if (permitName.equals("Mason Pond F/S Annual") ||
        permitName.equals("Shenandoah F/S Annual") || permitName.equals("RRPD F/S Annual")|| permitName.equals("RRPD Roof Only F/S")){
      return "assets/FFX_Faculty_Staff_Reserved_Deck-l.png";
    } 
    
    return null; 
  }
  

  public void createCombos(){
    //create combo boxes
    //String buildingList[] = {"Select Building" ,"Johnson Center", "Robinson B", "Innovation", "Center for the Arts", "Fennwick Library"};
    
    // commented lines: stores selected permit and building values 
    // selected_permit and selected_building used to check if the user selected both the permit and the building 
    permitCombo = new JComboBox<String>(permitList);
    permitCombo.setFont(new Font("Arial", Font.BOLD, 20));
    permitCombo.setSelectedIndex(0);
    
    permitCombo.addActionListener(
      new ActionListener(){
      @Override
          public void actionPerformed(ActionEvent e){
            JComboBox<String> this_combo = (JComboBox<String>)e.getSource();

            String command = e.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                    inputted_permit = (String) permitCombo.getSelectedItem();
                    if(inputted_permit.equals("Select Permit Type")){
                      selected_permit = false;
                    }
                    else{
                      selected_permit = true;
                    }
            }
      }
      
    });
    buildingCombo = new JComboBox<String>(buildingList);
    buildingCombo.setFont(new Font("Arial", Font.BOLD, 20));
    buildingCombo.setSelectedIndex(0);
    buildingCombo.addActionListener(
      new ActionListener(){
      @Override
          public void actionPerformed(ActionEvent e){
            JComboBox<String> this_combo = (JComboBox<String>)e.getSource();

            String command = e.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                    inputted_building = (String) buildingCombo.getSelectedItem();
                    if(inputted_building.equals("Select Building")){
                      selected_building = false;
                    }
                    else{
                      selected_building = true;
                    }
            }
      }
      
    });
    
    
    
  }
  public void createButtons(){
    //create buttons 
    btn_firstLot = new JButton("#1 Lot");
    btn_firstLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_firstLot.setFont(new Font("Arial", Font.BOLD, 35));
    //btn_firstLot.setBackground(new Color (0,182,0,155));
    btn_firstLot.setForeground(Color.BLACK);
    btn_firstLot.setBorder(BorderFactory.createRaisedBevelBorder());
    //btn_firstLot.setBackground(Color.GREEN);
    btn_firstLot.setEnabled(false);
    btn_firstLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        firstFrame.setVisible(true);
      }
      
    }); 
    
    btn_secondLot = new JButton("#2 Lot");
    //btn_secondLot.setBackground(Color.YELLOW);
    btn_secondLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_secondLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_secondLot.setEnabled(false);
    btn_secondLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        secondFrame.setVisible(true);
      }
      
    });
    
    btn_thirdLot = new JButton("#3 Lot");
    //btn_thirdLot.setBackground(Color.RED);
    btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_thirdLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_thirdLot.setEnabled(false);
    btn_thirdLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        thirdFrame.setVisible(true);
      }
      
    });
    helpText = new JTextArea();
    btn_findLot = new JButton("Find Lot");
    //btn_findLot.setBackground( new Color(100,0,0));
    btn_findLot.setFont(new Font("Arial", Font.BOLD, 25));
    btn_findLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {

        helpText.append("Finding Best Lot ...\n");
        
        // way to call backend?

        if (selected_building == true && selected_permit == true) {
           blf.setPermit(inputted_permit);
           blf.setBuilding(inputted_building);
           LotDistance [] ld = blf.findBestLot();
           
        //if statements in place to check how many parking lots are available (some permit types restrict to one or two parking lots)
           if (ld.length >= 3) { 
             try {
               createFirstFrame(ld); 
               btn_firstLot.setText(ld[0].getName());
               btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               createSecondFrame(ld);
               btn_secondLot.setText(ld[1].getName());
               btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_secondLot.setBackground(Color.YELLOW);
               btn_secondLot.setEnabled(true);
               createThirdFrame(ld);
               btn_thirdLot.setText(ld[2].getName());
               btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
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
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               createSecondFrame(ld);
               btn_secondLot.setText(ld[1].getName());
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_secondLot.setBackground(Color.YELLOW);
               btn_secondLot.setEnabled(true); 
               btn_thirdLot.setText("None");
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
             }
             catch(IOException e) {
               System.out.println("Can't open lot image files\n");
             }
           }
           else if (ld.length >= 1) {
             try {
               createFirstFrame(ld); 
               btn_firstLot.setText(ld[0].getName());
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_firstLot.setBackground(Color.GREEN);
               btn_firstLot.setEnabled(true);
               btn_secondLot.setText("None");
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
               btn_thirdLot.setText("None");
                btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
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
            helpText.append("Using JC");
          }
          //else --> do nothing
          
        }
        else{
         JOptionPane.showMessageDialog(mainFrame, "Please Select a Permit and Building to Continue");
        }
      }
      
    });
    
    btn_permitInfo = new JButton("Permit Info");
    btn_permitInfo.setFont(new Font("Arial", Font.BOLD, 22));
    //btn_permitInfo.setBackground(new Color(225,204,51));
    btn_permitInfo.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Displaying Permit Info ...\n");
        createPermitInfoFrames();
        mainFrame.pack();
      }
      
    });
    
    btn_about = new JButton("About");
    btn_about.setFont(new Font("Arial", Font.BOLD, 22));
    //btn_about.setBackground(new Color(225,204,51));
    btn_about.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Displaying About Info ...\n");
        createPermitInfoFrames();
        mainFrame.pack();
      }
      
    });
    
    /*btn_firstLot = new JButton("#1 Lot");
    btn_firstLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_firstLot.setFont(new Font("Arial", Font.BOLD, 35));
    //btn_firstLot.setBackground(new Color (0,182,0,155));
    btn_firstLot.setForeground(Color.BLACK);
    btn_firstLot.setBorder(BorderFactory.createRaisedBevelBorder());
    //btn_firstLot.setBackground(Color.GREEN);
    btn_firstLot.setEnabled(true);
    btn_firstLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        firstFrame.setVisible(true);
      }
      
    });*/
    
    /*btn_secondLot = new JButton("#2 Lot");
    //btn_secondLot.setBackground(Color.YELLOW);
    btn_secondLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_secondLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_secondLot.setEnabled(true);
    btn_secondLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        secondFrame.setVisible(true);
      }
      
    });
    
    btn_thirdLot = new JButton("#3 Lot");
    //btn_thirdLot.setBackground(Color.RED);
    btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 35));
    btn_thirdLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_thirdLot.setEnabled(true);
    btn_thirdLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        thirdFrame.setVisible(true);
      }
      
    });*/
    
  }
}
