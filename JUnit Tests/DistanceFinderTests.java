import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
public class DistanceFinderTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("DistanceFinderTests");
  }  
  
  @Test public void test_df1() {
    Location [] lots_to_buch = new Location[2]; 
    //lots_to_buch[0] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    //lots_to_buch[1] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_buch[0] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    //lots_to_engnr[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_buch[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    //lots_to_buch[2] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    //lots_to_engnr[6] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    
    DistanceFinder df_buch = new DistanceFinder(new Location("Buchanan Hall", new Coordinate(38.828826,-77.308485)),lots_to_buch);
    LotDistance [] ld_to_buch = df_buch.sortByDistance();
  } 
  
  @Test public void test_df2() {
    Location [] lots_to_taylor = new Location[3]; 
    lots_to_taylor[0] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    //lots_to_buch[1] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    //lots_to_engnr[2] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    //lots_to_engnr[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    //lots_to_engnr[4] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_taylor[1] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    lots_to_taylor[2] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    
    DistanceFinder df_taylor = new DistanceFinder(new Location("Taylor Hall", new Coordinate(38.827712,-77.301767)),lots_to_taylor);
    LotDistance [] ld_to_taylor = df_taylor.sortByDistance();
  }
  
  @Test public void test_df3() { 
    Location [] lots_to_carow = new Location[1]; 
    //lots_to_carow[1] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    //lots_to_carow[0] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    //lots_to_engnr[2] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    //lots_to_engnr[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    //lots_to_engnr[4] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_carow[0] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    //lots_to_taylor[2] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    
    DistanceFinder df_carow = new DistanceFinder(new Location("Carow Hall", new Coordinate(38.831475,-77.301275)),lots_to_carow);
    LotDistance [] ld_to_carow = df_carow.sortByDistance();
  } 
  
  @Test public void test_df4() {
    Location [] lots_to_JC = new Location[11]; 
    lots_to_JC[0] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    lots_to_JC[1] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_JC[2] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_JC[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_JC[4] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_JC[5] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    lots_to_JC[6] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    lots_to_JC[7] = new Location("West Campus Lot", new Coordinate(38.832945,-77.326507));
    lots_to_JC[8] = new Location("Global Center", new Coordinate(38.829699, -77.314943));
    lots_to_JC[9] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    lots_to_JC[10] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_JC = new DistanceFinder(new Location("JC", new Coordinate(38.827712,-77.301767)),lots_to_JC);
    LotDistance [] ld_to_JC = df_JC.sortByDistance();
  } 
  
  @Test public void test_df5() {
    Location [] lots_to_dk = new Location[8]; 
    lots_to_dk[0] = new Location("Lot D", new Coordinate(38.831244, -77.300739));
    lots_to_dk[1] = new Location("Lot R", new Coordinate(38.827147, -77.301938));
    lots_to_dk[2] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_dk[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_dk[4] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_dk[5] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    lots_to_dk[6] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    lots_to_dk[7] = new Location("West Campus Lot", new Coordinate(38.832945,-77.326507));
    //lots_to_JC[8] = new Location("Global Center", new Coordinate(38.829699, -77.314943));
    //lots_to_JC[9] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    //lots_to_JC[10] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_dk = new DistanceFinder(new Location("David King Hall", new Coordinate(38.830594,-77.306635)),lots_to_dk);
    LotDistance [] ld_to_dk = df_dk.sortByDistance(); 
  }
  
}
    
    
    