import java.util.*;

public class Location {
  private String name;
  private Coordinate coord;
  
  public Location(String name, Coordinate coord) {
    this.name = name;
    this.coord = coord;
  }
  
  public Coordinate getCoordinates() {
    return coord;
  }
  
  public String getName() {
    return name;
  } 
}
    
