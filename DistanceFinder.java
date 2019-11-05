import java.util.*;
import java.lang.*;

/* DistanceFinder is used to find the distances between the target building Location and an array of parking lot 
   Locations and return the distances of each lot to to target building in a sorted LotDistance array */ 
public class DistanceFinder {
  
  /* target is the Location object indicating the user inputted target building*/
  private Location target;
  
  /* lots is the array of legal parking lot locations to calculate target building's distance from (lots is associated 
  with a Permit type, i.e. each permit has an array of legal parking lots that the user can park at and this array
  is passed into DistanceFinder as lots array*/
  private Location[] lots;
  
  /* Constructor which initializes target and lots*/
  public DistanceFinder(Location target, Location[] lots) {
    this.target = target;
    this.lots = lots;
  }
  
  /* sortByDistance() calculates the distance from target to each parking lot in lots and sorts them into an array
      of LotDistance objects. The array is sorted so that the parking lot closest to the target building is at the 
      beginning of the array, second closest parking lot is at the following index, and so on. This array of LotDistance
      objects is returned*/
  public LotDistance[] sortByDistance() {
    ArrayList <LotDistance> sorted_ld = new ArrayList<LotDistance>();
    double dist = 0.0;
    double lowest_dist = 0.0; 
    
    // for each lot, loop calculates the distance from a lot to target by calling findDistance()
    // and each lot's name and distance to target is used to create a LotDistance object
    // if a lot's distance is the lowest distance to the target building it's LotDistance object is added to the beginning of the list
    // otherwise its LotDistance object is added to the end of the list
    for (int i = 0; i < lots.length; i++) {
      dist = findDistance (target, lots[i]);
      if (i == 0) 
        lowest_dist = dist;
      if (dist <= lowest_dist) {
        lowest_dist = dist;
        sorted_ld.add(0, new LotDistance(lots[i].getName(), dist));
      }
      else {
        sorted_ld.add( new LotDistance(lots[i].getName(), dist));
      }
    }
    
    // the ArrayList of LotDistance objects is converted to an array of LotDistance objects and returned 
    LotDistance [] sorted_ld_arr = new LotDistance [sorted_ld.size()];
    for (int j = 0; j < sorted_ld_arr.length; j++) {
      sorted_ld_arr[j] = sorted_ld.get(j);
    }
    
    return sorted_ld_arr;
  }
  
  /* findDistance returns the distance between 2 Location objects. It uses the distance formula
     to calculate and return the distance between the target building (t) and a parking lot (l) in the method */
  public double findDistance(Location t, Location l) {
    
    Coordinate t_coords = t.getCoordinates();
    Coordinate l_coords = l.getCoordinates();
    double t_x = t_coords.getLat();
    double t_y = t_coords.getLong();
    double l_x = l_coords.getLat();
    double l_y = l_coords.getLong();
    
    double dist = Math.sqrt((Math.pow((t_x - l_x), 2)) + (Math.pow((t_y - l_y), 2))); 
    return dist;
    
  } 
}
    
      
    
    
