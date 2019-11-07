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
  
  //create panels for map and input
  private JPanel userPanel;
  private JPanel mapPanel;
  private JPanel key_infoPanel;
  private JPanel inputPanel;
  //create buttons
  private JButton btn_findLot;
  private JButton btn_permitInfo;
  private JButton btn_mapKey;
  
  //create labels
  private JLabel lbl_selectPermit;
  private JLabel lbl_selectBuilding;
  
  //create combo boxes
  private JComboBox permitCombo;
  private JComboBox buildingCombo;
  
  //create text areas
  private JTextArea helpText;
  
  public Display() throws IOException{
    gui();

  }
  public void gui() throws IOException{
    
    mainFrame = new JFrame("Smart Parking");
    mainFrame.setSize(3000,3000);
    //mainFrame.setVisible(true);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    mapPanel = new JPanel();
    BufferedImage image = ImageIO.read(new File("C:/Users/howar/Desktop/GMU_Fall 2019/CS 321/Group Work/CS321-001-Team-5-Project-master/CS321-001-Team-5-Project-master/assets/GMU_Campus.jpg"));
    JLabel label = new JLabel(new ImageIcon(image));
    mapPanel.add(label);
    
    JFrame.setDefaultLookAndFeelDecorated(true);
    userPanel = new JPanel();
    userPanel.setLayout(new GridLayout(2,1));
    userPanel.setBackground(Color.WHITE);
    //userPanel.setSize(400,100);
    
    inputPanel = new JPanel();
    BoxLayout boxLayout_Y = new BoxLayout(inputPanel,BoxLayout.Y_AXIS);
    inputPanel.setLayout(boxLayout_Y);
    inputPanel.setBorder(new EmptyBorder(new Insets(15, 20, 50, 20)));
    
    key_infoPanel = new JPanel();
    BoxLayout boxLayout_X = new BoxLayout(key_infoPanel,BoxLayout.X_AXIS);
    key_infoPanel.setLayout(boxLayout_X);
    key_infoPanel.setBorder(new EmptyBorder(new Insets(15, 20, 15, 20)));
    
    //create combo boxes
    String permitList[] = {"A","B","C","I","J","RRPD", "K"};
    String buildingList[] = {"Johnson Center", "Robinson B", "Innovation", "Center for the Arts", "Fennwick Library"};
    permitCombo = new JComboBox(permitList);
    buildingCombo = new JComboBox(buildingList);
    
    //create buttons
    helpText = new JTextArea();
    btn_findLot = new JButton("Find Lot");
    btn_findLot.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0){
        helpText.append("Finding Best Lot ...\n");
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
}
