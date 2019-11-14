import java.util.*;

public class Coordinate {
  private double latitude;
  private double longitude;
  
  public Coordinate(double latitude, double longitude) {//create new Coordinate object
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  public double getLat(){ //return latitude
    return this.latitude;
  }
  
  public double getLong(){ //return longitude
    return this.longitude;
  }
  
  public String toString(){
    return this.getLat() + "," + this.getLong();
  }
  
  //public static void main(String args []){ //for testing purposes
    //Coordinate x = new Coordinate (312,159);
    //System.out.print("Latitude: "+ x.getLat() + " Longitude: " + x.getLong() );
  //}
}
