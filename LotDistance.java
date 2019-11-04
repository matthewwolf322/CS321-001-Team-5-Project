import java.util.*;
import java.lang.*;

/* LotDistance is used to store the names of the parking lots on GMU FFX campus and their corresponding distances to the user's 
  target building. Objects are used in DistanceFinder */
public class LotDistance {
 
  /* lot_name is the name of the parking lot which has a distance to the user's target building*/
  private String lot_name;
  
  /* distance is the  distance of a parking lot  named lot_name to the user's target building*/
  private double distance;
  
  /* Constructor initializes lot_name and distance fields*/
  public LotDistance(String lot_name, double distance) {
    this.lot_name = lot_name;
    this.distance = distance;
  }
  
  /* getName() returns the name of the parking lot (lot_name)*/
  public String getName() {
    return lot_name;
  }
  
  /* getDistance() returns the distance*/
  public double getDistance() {
    return distance;
  }
}
