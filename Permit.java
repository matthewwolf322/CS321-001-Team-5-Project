import java.util.*;

public class Permit
{
  private String title; // the name of the Permit
  private String [] avaliableLots; //array of all lots avaliable 
  
  public Permit(String title, String [] avaliableLots)
  {
    this.title = title;
    this.avaliableLots = avaliableLots;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public String [] getLots()
  {
    return avaliableLots;
  }
  
}
  
  
  
