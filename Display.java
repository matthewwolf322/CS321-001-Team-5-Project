import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame{
  
  //create main frame
  private JFrame mainFrame;
  private JFrame firstFrame;
  private JFrame secondFrame;
  private JFrame thirdFrame;
  
  
  //create panels for map and input
  private JPanel userPanel;
  private JPanel mapPanel;
  private JPanel key_infoPanel;
  private JPanel inputPanel;
  
  
  // 3 panels hold the three images (zoomed in map of lots) 
  // first_map_Panel holds map image of 1st closest lot
  // second_map_Panel holds map image of 2nd closest lot 
  // third_map_Panel holds map image of 3rd closest lot
  private JPanel first_map_Panel;
  private JPanel second_map_Panel;
  private JPanel third_map_Panel;
  
  //create buttons
  private JButton btn_findLot;
  private JButton btn_permitInfo;
  private JButton btn_mapKey;
  
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
  
  //boolean selected_permit;
    //boolean selected_building;
  // String inputted_permit;
  // String inputted_building;
  
  public Display() throws IOException{
    gui();
  }
  
  public void gui() throws IOException{
    
    createMainFrame();
    
    JFrame.setDefaultLookAndFeelDecorated(true);
    userPanel = new JPanel();
    userPanel.setLayout(new GridLayout(2,1));
    userPanel.setBackground(Color.WHITE);
    
    inputPanel = new JPanel();
    BoxLayout boxLayout_Y = new BoxLayout(inputPanel,BoxLayout.Y_AXIS);
    inputPanel.setLayout(boxLayout_Y);
    //inputPanel.setBorder(new EmptyBorder(new Insets(15, 20, 50, 10)));
    
    key_infoPanel = new JPanel();
    BoxLayout boxLayout_X = new BoxLayout(key_infoPanel,BoxLayout.X_AXIS);
    key_infoPanel.setLayout(boxLayout_X);
    //key_infoPanel.setBorder(new EmptyBorder(new Insets(15, 20, 15, 5)));
    
    //create combo boxes
    String permitList[] = {"A","B","C","I","J","RRPD", "K"};
    String buildingList[] = {"Johnson Center", "Robinson B", "Innovation", "Center for the Arts", "Fennwick Library"};
    
    // commented lines: stores selected permit and building values 
    // selected_permit and selected_building used to check if the user selected both the permit and the building 
    permitCombo = new JComboBox(permitList);
    //permitCombo.addActionListener(new ActionListener(){
      //@Override
      //public void actionPerformed(ActionEvent arg0){
        // selected_permit = true;
        // inputted_permit = (String) permitCombo.getSelectedItem();
        // 
      //}
      
    //});
    
    buildingCombo = new JComboBox(buildingList);
    //buildingCombo.addActionListener(new ActionListener(){
      //@Override
      //public void actionPerformed(ActionEvent arg0){
        // selected_building = true;
        // inputted_building = (String) buildingCombo.getSelectedItem();
      //}
      
    //});
    
    //BestLotFinder blf = new BestLotFinder(); call best lot finder 
    // blf.load();
    
    //createFirstFrame();
    createButtons();
    
    
    
    
    lbl_selectPermit = new JLabel("Select Your Permit");
    lbl_selectBuilding = new JLabel("Select Your Destination");
    
    inputPanel.add(lbl_selectPermit);
    inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
    inputPanel.add(permitCombo);
    inputPanel.add(Box.createRigidArea(new Dimension(0,30)));
    inputPanel.add(lbl_selectBuilding);
    inputPanel.add(Box.createRigidArea(new Dimension(0,5)));
    inputPanel.add(buildingCombo);
    inputPanel.add(Box.createRigidArea(new Dimension(0,20)));
    inputPanel.add(btn_findLot);
    inputPanel.add(Box.createRigidArea(new Dimension(0,15)));
    
    
    key_infoPanel.add(btn_mapKey);
    key_infoPanel.add(Box.createRigidArea(new Dimension(5,0)));
    key_infoPanel.add(btn_permitInfo);
     
    userPanel.add(inputPanel);
    userPanel.add(key_infoPanel,BorderLayout.SOUTH);
    
    mainFrame.add(mapPanel);
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
    mainFrame.setSize(3000,3000);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    mapPanel = new JPanel();
    BufferedImage image = ImageIO.read(new File("assets/mapOfFairfaxCampus-DRRPD-l.png"));
    JLabel label = new JLabel(new ImageIcon(image));
    mapPanel.add(label);
    
  }
  
  public void createfirstFrame(LotDistance [] ld) throws IOException{
    firstFrame = new JFrame("firstframe");
    firstFrame.setSize(3000,3000);
    firstFrame.setLocationRelativeTo(null);
    //firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
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
    //firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    // will all permit types have at least 3 associated lots?
    String second_lot_name = ld[1].getName();
    
    second_map_Panel = new JPanel();
    BufferedImage image3 = ImageIO.read(new File(getImage(second_lot_name)));
    JLabel label3 = new JLabel(new ImageIcon(image3));
    second_map_Panel.add(label3);
    secondFrame.add(second_map_Panel);
    secondFrame.pack();
    
  }
  
  public void createthirdFrame(LotDistance [] ld) throws IOException{
    thirdFrame = new JFrame("thirdframe");
    thirdFrame.setSize(3000,3000);
    thirdFrame.setLocationRelativeTo(null);
    //firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
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
      return "assets/mapOfFairfaxCampus-ACLJKGlobal-l.png";
    }
    
    else if(lot_dist_name.equals("P") || lot_dist_name.equals("O") ||
            lot_dist_name.equals("M") || lot_dist_name.equals("PV") ||
            lot_dist_name.equals("I")) {
      return "/assets/mapOfFairfaxCampus-POMPVI-l.png";
    } 
    
    else if(lot_dist_name.equals("D") || lot_dist_name.equals("RRPD")) {
      return "/assets/mapOfFairfaxCampus-DRRPD-l.png";
    }
    
    else if(lot_dist_name.equals("R") || lot_dist_name.equals("Mason Pond") || lot_dist_name.equals("Shenandoah")) {
      return "/assets/mapOfFairfaxCampus-RMasonPondShenndoah-l.png";
    }
    else if(lot_dist_name.equals("West Campus")) {
      return "/assets/mapOfFairfaxCampus-WestCampus-l.png";
    }
    return "String"; 
  } 
  
  public String getPermitImage(String permit_name) {return permit_name;}
    
      
        
  public void createButtons(){
    //create buttons
    helpText = new JTextArea();
    btn_findLot = new JButton("Find Lot");
    btn_findLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Finding Best Lot ...\n"); 
        
        // way to call backend?
        //BestLotFinder blf = new BestLotFinder(); call best lot finder 
        // blf.load();
        // if (selected_building == true && selected_permit == true) {
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
        inputPanel.add(btn_firstLot);
        inputPanel.add(btn_secondLot);
        inputPanel.add(btn_thirdLot);
        mainFrame.pack();
      }
      
    });
    
    btn_permitInfo = new JButton("Permit Info");
    btn_permitInfo.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Displaying Permit Info ...\n"); 
        
        mainFrame.pack();
      }
      
    });
    
    btn_mapKey = new JButton("Map Key");
    btn_mapKey.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Displaying Map Key...\n");
        mainFrame.pack();
      }
      
    });
    
    btn_firstLot = new JButton("#1 Lot");
    btn_firstLot.setBackground(Color.GREEN);
    btn_firstLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        firstFrame.setVisible(true);
      }
      
    });
    
    btn_secondLot = new JButton("#2 Lot");
    btn_secondLot.setBackground(Color.YELLOW);
    btn_secondLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        secondFrame.setVisible(true);
      }
      
    });
    
    btn_thirdLot = new JButton("#3 Lot");
    btn_thirdLot.setBackground(Color.RED);
    btn_thirdLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        thirdFrame.setVisible(true);
      }
      
    });
    
  }
}
