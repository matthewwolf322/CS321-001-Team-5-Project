import java.util.*;

public class Coordinate {
  private double latitude;
  private double longitude;
  
  public Location(double latitude, double longitude) {//create new Coordinate object
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  public double getLat(){ //return latitude
    return this.latitude;
  }
  
  public double getLong(){ //return longitude
    return this.longitude
  }
  
