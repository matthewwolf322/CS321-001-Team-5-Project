import java.util.*;

/* Class used to hold the different lots' and buildings' names and geographic coordinates
 * (A Location can be a building or parking lot on GMU FFX campus)*/
public class Location {
  
  /* holds the String format name of the building or parking lot*/
  private String name;
  
  /* holds the geographic coordinates of the location*/
  private Coordinate coord;
  
  /* Constructor initializes the name and coordinate of the Location object*/
  public Location(String name, Coordinate coord) {
    this.name = name;
    this.coord = coord;
  }
  
  /* Returns the coordinates of the Location*/
  public Coordinate getCoordinates() {
    return coord;
  }
  
  /* Returns the name of the location*/
  public String getName() {
    return name;
  } 
  
  /* Returns the string format of the location in the form "Location: name at (latitude, longitude)"*/
  public String toString() {
    return "Location: " + name + " at (" + coord.toString() + ")\n";
  }
  
   // used for testing
  //public static void main(String [] args) {
    //Location jc = new Location("Johnson Center", new Coordinate(38.829869, -77.307356));
    //Location lot_j = new Location("Lot J", new Coordinate(38.83005,-77.31476));
    //Coordinate jc_coords = jc.getCoordinates();
    //System.out.println("JC_x: " + jc_coords.getLat() + " JC_y: " + jc_coords.getLong()); 
    //System.out.println("Lot " + lot_j.getName());
    //System.out.println(lot_j.toString());
    
  //}
}
    
