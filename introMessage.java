import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;

public class introMessage extends JPanel{
  
  private JFrame frame;
  public final static int ONE_SECOND = 300;
  Image [] letters = new Image [12];
  JLabel [] labels = new JLabel [12];
  JPanel panel;
  JPanel panel2;
  private Timer timer;
  private int count = 0;
  
  Display mainFrame;
  
  public void initTimer() throws IOException{
    timer = new Timer(ONE_SECOND, new ActionListener(){
    public void actionPerformed(ActionEvent evt){
      if (count < labels.length){
        addLetter();
      }
      else if (count < 15){
        count++;  
      }
      else{
        timer.stop();
        frame.setVisible(false);
        try{
          mainFrame = new Display();
        }
        catch(IOException e){
            e.printStackTrace();
            
         }
      }
    }
 });
    
  timer.start();
  }
  
  public void addLetter(){
    if(count < 5){
      panel.add(labels[count]);
      panel.add(Box.createRigidArea(new Dimension(5,0)));
      frame.pack();
    }
    else{
      panel2.add(labels[count]);
      panel2.add(Box.createRigidArea(new Dimension(5,0)));
      frame.pack();
    }
    count++;
  }
  
  
  public void load (){

    letters[0] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_S.jpg");
    letters[1] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_M.jpg");
    letters[2] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_A.jpg");
    letters[3] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_R.jpg");
    letters[4] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_T.jpg");
    letters[5] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_P.jpg");
    letters[6] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_A.jpg");
    letters[7] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_R.jpg");
    letters[8] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_K.jpg");
    letters[9] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_I.jpg");
    letters[10] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_N.jpg");
    letters[11] = Toolkit.getDefaultToolkit().getImage("Graphics/LETTER_G.jpg");
    
    panel = new JPanel();
    panel2 = new JPanel();
    for (int i = 0; i< labels.length; i ++){
      labels[i] = new JLabel(new ImageIcon(letters[i]));
      labels[i].setBorder(BorderFactory.createRaisedBevelBorder());
    }
    
    
    frame = new JFrame("Welcome");
    BoxLayout boxlayoutY = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
    frame.setLayout(boxlayoutY);
    
    frame.setSize(1550,800);
    
    frame.setUndecorated(true);
    frame.setBackground(new Color(0,0,0,0));
    frame.getContentPane().setBackground(new Color (0,0,0,50));
    //panel.setBackground(new Color (0,0,0,30));
    //panel2.setBackground(new Color (0,0,0,30));
    frame.add(panel);
    panel.setPreferredSize(new Dimension(1550,300));
    panel2.setPreferredSize(new Dimension(1550,300));
    frame.add(panel2);
    frame.setLocationRelativeTo(null);
    
    frame.setOpacity(0.55f);
    frame.setVisible(true);
    frame.requestFocus();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  
  public void run(){
   introMessage x = new introMessage(); 
   x.load();
   try{
     x.initTimer();
   }
   catch(IOException e){
     e.printStackTrace();
   }
   
  }
  
  public static void main( String [] args){

  }

}

