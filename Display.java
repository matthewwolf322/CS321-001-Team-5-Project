import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;


public class Display extends JFrame{
  
  //create main frame
  private JFrame mainFrame;
  private JFrame expandedMapFrame;//to dispay close up images of the map
  private JFrame findPermitFrame;//to get permit name
  private JFrame permitInfoFrame;//to display 
  
  //create frames for lot images
  private JFrame firstFrame;
  private JFrame secondFrame;
  private JFrame thirdFrame;
  

  
  //create panels for map and user input
  private JPanel userPanel;//holds input panel, goButtonPanel, buttonPanel, key_infoPanel 
  private JPanel mapPanel;//displays main map
  private JPanel inputPanel;//
  private JPanel goButtonPanel;
  private JPanel buttonPanel;//holds 1st/2nd/3rd lot buttons
  private JPanel key_infoPanel;//
  
  
  //private JPanel first_map_Panel;


  //create panels for displaying specific lots
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
  

  //Show ordered lots
  private JButton btn_firstLot;
  private JButton btn_secondLot;
  private JButton btn_thirdLot;
  
  //create labels
  private JLabel lbl_selectPermit;
  private JLabel lbl_selectBuilding;
  
  //create combo boxes
  private JComboBox permitCombo;
  private JComboBox buildingCombo;
  
  //create text areas
  private JTextArea helpText;
  
  
  private String [] permitList = new String [10];
  private String [] buildingList = new String [10];
  

  boolean selected_permit;
  boolean selected_building;
  
  String inputted_permit;
  String inputted_building;
  
  //BestLotFinder blf;
  

  public Display() throws IOException{

    //blf = new BestLotFinder(); //call best lot finder
    
    createMainFrame();
    createCombos();
    createButtons();

    createInputPanel();
    createGoButtonPanel();
    
    createButtonPanel();
    createKeyInfoPanel();
    
    createUserPanel();
    //createfirstFrame();

    

    
    
    mainFrame.add(userPanel,BorderLayout.EAST);
    mainFrame.add(helpText,BorderLayout.SOUTH);
    mainFrame.pack();//conform frame to size of components
    mainFrame.setVisible(true);//set visible after elements are added
  }
    
  public static void main (String[] args) throws IOException{
    //JOptionPane.ShowMessageDialog(mainFrame, "Welcome to Smart Parking!", "Smart Parking");
    new Display();
    
  }
  
  
  public void createMainFrame() throws IOException{
    mainFrame = new JFrame("Smart Parking");
    mainFrame.setSize(3000,2000);
    //mainFrame.setBackground(new Color (0,103,0));
    mainFrame.setLocationRelativeTo(null);
    JFrame.setDefaultLookAndFeelDecorated(true);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    mapPanel = new JPanel();
    BufferedImage image = ImageIO.read(new File("assets/MapOfFairfaxCampus.png"));
    //BufferedImage image = ImageIO.read(new File("C:/Users/howar/Desktop/GMU_Fall 2019/CS 321/Group Work/CS321-001-Team-5-Project-master/assets/mapOfFairfaxCampus.png"));
    JLabel label = new JLabel(new ImageIcon(image));
    //mapPanel.setLayout(new BoxLayout(mapPanel,BoxLayout.X_AXIS));
    mapPanel.add(label);
    mapPanel.setBorder(new EmptyBorder(new Insets(30, 20, 0, 20)));
    //mapPanel.setBackground(new Color (0,103,0));
    //mapPanel.setBorder(BorderFactory.createMatteBorder(
                                    //1, 5, 1, 1, Color.red));
    mainFrame.add(mapPanel);
    
  }
  
  public void createUserPanel(){
    userPanel = new JPanel();
    userPanel.setSize(3000,100);
    userPanel.setLayout(new GridLayout(4,1));//four sections
    userPanel.setBackground(Color.WHITE);
    userPanel.add(inputPanel);
    userPanel.add(goButtonPanel);
    userPanel.add(buttonPanel);
    userPanel.add(key_infoPanel);
    
  }
  public String [] getPermitList(){
    String [] permitList = {"A","B","C","I","J","RRPD", "K"};
    return permitList;
  }
  
  public void createInputPanel(){
    inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(80,80));
    inputPanel.setAlignmentX(LEFT_ALIGNMENT);
    BoxLayout boxLayout_Y = new BoxLayout(inputPanel,BoxLayout.PAGE_AXIS);
    inputPanel.setLayout(boxLayout_Y);
    inputPanel.setBorder(new EmptyBorder(new Insets(20, 20, 50, 10)));

    //create combo boxes
    //String [] permitList = {"A","B","C","I","J","RRPD", "K"};
    String [] buildingList = {"Johnson Center", "Robinson B", "Innovation", "Center for the Arts", "Fennwick Library"};
    
    permitCombo = new JComboBox(getPermitList());
    permitCombo.setFont(new Font("Arial", Font.PLAIN, 20));
    buildingCombo = new JComboBox(buildingList);
    buildingCombo.setFont(new Font("Arial", Font.PLAIN, 20));
    lbl_selectPermit = new JLabel("Select Your Permit");
    lbl_selectPermit.setFont(new Font("Arial", Font.BOLD, 20));
    lbl_selectBuilding = new JLabel("Select Your Destination");
    lbl_selectBuilding.setFont(new Font("Arial", Font.BOLD, 20));
    lbl_selectPermit.setHorizontalTextPosition(JLabel.LEFT);
    inputPanel.add(lbl_selectPermit);
    //inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
    inputPanel.add(permitCombo);
    inputPanel.add(Box.createRigidArea(new Dimension(0,30)));
    inputPanel.add(lbl_selectBuilding);
    inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
    inputPanel.add(buildingCombo);
    
    
  }
  
  
  public void createGoButtonPanel(){
    goButtonPanel = new JPanel();
    goButtonPanel.setSize(5,5);
    goButtonPanel.setLayout(new GridLayout(1,2));
    goButtonPanel.setBorder(new EmptyBorder(new Insets(20, 20, 200, 5)));
    goButtonPanel.add(Box.createRigidArea(new Dimension(20,0)));
    goButtonPanel.add(btn_findLot);
  }
  
  
  public void createButtonPanel(){
    buttonPanel = new JPanel();
    //BoxLayout boxLayout_Y = new BoxLayout(buttonPanel,BoxLayout.Y_AXIS);
    buttonPanel.setLayout(new GridLayout(6,1));
    buttonPanel.setBorder(new EmptyBorder(new Insets(0, 20, 20, 20)));
   // buttonPanel.add(btn_findLot);
  }
  
  public void createKeyInfoPanel(){
    key_infoPanel = new JPanel();
    BoxLayout boxLayout_X = new BoxLayout(key_infoPanel,BoxLayout.X_AXIS);
    key_infoPanel.setLayout(boxLayout_X);
    //
    
    key_infoPanel.add(Box.createRigidArea(new Dimension(5,0)));
    key_infoPanel.add(btn_permitInfo);
    
  }
  
  public void createfirstFrame(LotDistance [] ld) throws IOException{
    firstFrame = new JFrame("firstframe");
    firstFrame.setSize(3000,3000);
    firstFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String first_lot_name = ld[0].getName();
    
    first_map_Panel = new JPanel();
    BufferedImage image2 = ImageIO.read(new File(getImage(first_lot_name)));
    JLabel label2 = new JLabel(new ImageIcon(image2));
    first_map_Panel.add(label2);
    firstFrame.add(first_map_Panel);
    firstFrame.pack();
    
  } 

  public void createsecondFrame(LotDistance [] ld) throws IOException{
    secondFrame = new JFrame("secondframe");
    secondFrame.setSize(3000,3000);
    secondFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String sec_lot_name = ld[1].getName();
    
    second_map_Panel = new JPanel();
    BufferedImage image3 = ImageIO.read(new File(getImage(sec_lot_name)));
    JLabel label3 = new JLabel(new ImageIcon(image3));
    second_map_Panel.add(label3);
    secondFrame.add(second_map_Panel);
    secondFrame.pack();
    
  }
  
  public void createthirdFrame(LotDistance [] ld) throws IOException{
    thirdFrame = new JFrame("thirdframe");
    thirdFrame.setSize(3000,3000);
    thirdFrame.setLocationRelativeTo(null);
   
    // will all permit types have at least 3 associated lots?
    String third_lot_name = ld[2].getName();
    
    third_map_Panel = new JPanel();
    BufferedImage image4 = ImageIO.read(new File(getImage(third_lot_name)));
    JLabel label4 = new JLabel(new ImageIcon(image4));
    third_map_Panel.add(label4);
    thirdFrame.add(third_map_Panel);
    thirdFrame.pack();
    
  }

  // Helper Method: returns the path name of image containing the lot lot_dist_name
  public String getImage(String lot_dist_name) {
    if (lot_dist_name.equals("A") || lot_dist_name.equals("C") ||
        lot_dist_name.equals("L") || lot_dist_name.equals("J") ||
        lot_dist_name.equals("K") || lot_dist_name.equals("Global")) {
      return "/assets/mapOfFairfaxCampus-ACLJKGlobal-l.png";
    }
    
    else if(lot_dist_name.equals("M") || lot_dist_name.equals("O") ||
            lot_dist_name.equals("P") || lot_dist_name.equals("PV") ||
            lot_dist_name.equals("I")) {
      return "/assets/mapOfFairfaxCampus-POMPVI-l.png";
    } 
    
    else if(lot_dist_name.equals("D") || lot_dist_name.equals("Rappahannock")) {
      return "/assets/mapOfFairfaxCampus-DRRPD-l.png";
    }
    
    else if(lot_dist_name.equals("R") || lot_dist_name.equals("Mason Pond") || lot_dist_name.equals("Shenandoah")) {
      return "/assets/mapOfFairfaxCampus-RMasonPondShenndoah-l.png";
    }
    else if(lot_dist_name.equals("West Campus")) {
      return "/assets/mapOfFairfaxCampus-WestCampus-l.png";
    }
     return "/assets/mapOfFairfaxCampus.png";  
  } 


  public void createPermitInfoFrames(){
     JFrame findPermitFrame = new JFrame("Select Permit");//to get permit name
     JFrame permitInfoFrame = new JFrame("Display Permit Information");//to display 
     findPermitFrame.setSize(500,200);
     findPermitFrame.setLocationRelativeTo(null);
     findPermitFrame.setResizable(false);
     JPanel findPermitPanel = new JPanel();
     
     JLabel lbl_permitSelect = new JLabel("Select Permit Type");
     JComboBox permitSelectCombo = new JComboBox(getPermitList());
     permitSelectCombo.setFont(new Font("Arial", Font.BOLD, 30));
     permitSelectCombo.addActionListener(permitSelectCombo);
     
     findPermitPanel.add(lbl_permitSelect);
     findPermitPanel.add(permitSelectCombo);
     findPermitFrame.setVisible(true);
     findPermitFrame.add(findPermitPanel);
     
  }
  
  public void findPermitInfo(){
    
    
    
  }
  
  public void createCombos(){
    //create combo boxes
    //String permitList[] = {"A","B","C","I","J","RRPD", "K"};
    String buildingList[] = {"Johnson Center", "Robinson B", "Innovation", "Center for the Arts", "Fennwick Library"};
    
    // commented lines: stores selected permit and building values 
    // selected_permit and selected_building used to check if the user selected both the permit and the building 
    permitCombo = new JComboBox(getPermitList()); //change blf.getPermitList()
    permitCombo.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        selected_permit = true;
        inputted_permit = (String) permitCombo.getSelectedItem();
        helpText.append(inputted_permit);
      }
      
    });
    
    buildingCombo = new JComboBox(buildingList); //change to blf.getBuildingList()
    buildingCombo.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        selected_building = true;
        inputted_building = (String) buildingCombo.getSelectedItem();
        helpText.append(inputted_building);
      }
      
    });
    
  }
  
  public void createButtons(){
    //create buttons
    helpText = new JTextArea();
    btn_findLot = new JButton("Find Lot");
    btn_findLot.setFont(new Font("Arial", Font.BOLD, 30));
    btn_findLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Finding Best Lot ...\n");
        
        // way to call backend?

        // if (selected_building == true && selected_permit == true) {
        //     blf.setPermit(inputted_permit);
        //     blf.setBuilding(inputted_building);
        //     blf.findBestLot();
        //     Permit chosen_permit = new Permit(inputted_permit,  );
        //     Location [] lots = chosen_permit.getLots();
        //     Location building = new Location(inputted_building,   ); 
        //     LotDistance [] ld = findBestLot(building, Lots);
        //     if statements in place to check how many parking lots are available (some permit types restrict to one or two parking lots)
        //     if (ld.length >= 3) { 
        //       createfirstFrame();
        //       createsecondFrame();
        //       createthirdFrame();
        //     }
        //     if (ld.length >= 2) {
        //        createfirstFrame();
        //        createsecondFrame();
        //     }
        //     if (ld.length >= 1) {
        //        createfirstFrame();
        //      }

        buttonPanel.add(btn_firstLot);
        buttonPanel.add(btn_secondLot);
        buttonPanel.add(btn_thirdLot);
        mainFrame.pack();
      }
      
    });
    
    btn_permitInfo = new JButton("Permit Info");
    btn_permitInfo.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Displaying Permit Info ...\n");
        createPermitInfoFrames();
        mainFrame.pack();
      }
      
    });
    
    //btn_mapKey = new JButton("Map Key");
    //btn_mapKey.addActionListener(new ActionListener(){
     // @Override
      //public void actionPerformed(ActionEvent arg0){
       // helpText.append("Displaying Map Key...\n");
       // mainFrame.pack();
     // }
    // 
    //});
    
    btn_firstLot = new JButton("#1 Lot");
    btn_firstLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_firstLot.setFont(new Font("Arial", Font.BOLD, 30));
    btn_firstLot.setBackground(Color.GREEN);
    btn_firstLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        firstFrame.setVisible(true);
      }
      
    });
    
    btn_secondLot = new JButton("#2 Lot");
    btn_secondLot.setBackground(Color.YELLOW);
    btn_secondLot.setFont(new Font("Arial", Font.BOLD, 30));
    btn_secondLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_secondLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        secondFrame.setVisible(true);
      }
      
    });
    
    btn_thirdLot = new JButton("#3 Lot");
    btn_thirdLot.setBackground(Color.RED);
    btn_thirdLot.setFont(new Font("Arial", Font.BOLD, 30));
    btn_thirdLot.setBorder(BorderFactory.createBevelBorder(0));
    btn_thirdLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        thirdFrame.setVisible(true);
      }
      
    });
    
  }
}
