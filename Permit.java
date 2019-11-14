import java.util.*;

public class Permit{
  
  private String title; // the name of the Permit
  private String [] avaliableLots; //array of all lots avaliable 
  
  public Permit(String title, String [] avaliableLots){
    this.title = title;
    this.avaliableLots = avaliableLots;
  }
  
  public String getTitle(){
    return title;
  }
  
  public String [] getLots(){
    return avaliableLots;
  }
  
  public String toString(){ 
    String retr = "";
    for (int i = 0; i < this.avaliableLots.length; i++){
      retr = retr + this.avaliableLots[i] +", ";
    }
    return this.getTitle() + ": " + retr;  
  }
  
 // public static void main(String [] args){ //for testing purposes
    //test code here
    //String [] list = new String [3];
    //list[0] = "A";
    //list[1] = "B";
    //list[2] = "C";
    //Permit A = new Permit("Permit 1",list);
    //System.out.print(A.toString());
  //}
}
  
  
  
