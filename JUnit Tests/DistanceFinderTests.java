import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
public class DistanceFinderTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("DistanceFinderTests");
  }  
  
  // Finds and sorts distances between Buchanan Hall and Lots M and P (2 lots)
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
  
  // Finds and sorts distances between Taylor Hall and Lot K, Shenandoah Parking Deck, and Mason Pond Parking Deck (3 Lots)
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
  
  // Finds and sorts distances between Carow Hall and Shenandoah Parking Deck (1 lot)
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
  
  // Finds and sorts distances between Johnson Center and all the valid lots for the General permit type
  @Test public void test_df4() {
    Location [] lots_to_JC = new Location[13]; 
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
    lots_to_JC[11] = new Location("Lot C", new Coordinate(38.825355, -77.30594));
    lots_to_JC[12] = new Location("Lot L", new Coordinate(38.825882, -77.31063));
    
    DistanceFinder df_JC = new DistanceFinder(new Location("JC", new Coordinate(38.827712,-77.301767)),lots_to_JC);
    LotDistance [] ld_to_JC = df_JC.sortByDistance();
  } 
  
  // Finds and sorts distances between David King Hall  and 8 Lots
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
  
  // Finds and sorts distances between Lecture Hall and all the valid lots for the General Faculty/Staff Annual permit type
  @Test public void test_df6() {
    Location [] lots_to_lecture = new Location[13]; 
    lots_to_lecture[0] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_lecture[1] = new Location("Lot C", new Coordinate(38.825355, -77.30594));
    lots_to_lecture[2] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    lots_to_lecture[3] = new Location("Lot L", new Coordinate(38.825882, -77.31063));
    lots_to_lecture[4] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    lots_to_lecture[5] = new Location("Lot D", new Coordinate(38.831244, -77.300739));
    lots_to_lecture[6] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_lecture[7] = new Location("Lot P", new Coordinate(38.835085,-77.316257));
    lots_to_lecture[8] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_lecture[9] = new Location("West Campus Lot", new Coordinate(38.832945, -77.326507));
    lots_to_lecture[10] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_lecture[11] = new Location("Lot J", new Coordinate(38.83005, -77.31476));
    lots_to_lecture[12] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_lecture = new DistanceFinder(new Location("Lecture Hall", new Coordinate(38.833121,-77.307579)),lots_to_lecture);
    LotDistance [] ld_to_lecture = df_lecture.sortByDistance(); 
  }
  
  // Finds and sorts distances between Aquia Building and all the valid lots for the Mason Pond F/S Annual permit type
  @Test public void test_df7() {
    Location [] lots_to_aqui = new Location[13]; 
    lots_to_aqui[0] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    lots_to_aqui[1] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    lots_to_aqui[2] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_aqui[3] = new Location("Lot J", new Coordinate(38.83005, -77.31476));
    lots_to_aqui[4] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_aqui[5] = new Location("Lot C", new Coordinate(38.825355, -77.30594));
    lots_to_aqui[6] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    lots_to_aqui[7] = new Location("Lot L", new Coordinate(38.825882, -77.31063));
    lots_to_aqui[8] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_aqui[9] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_aqui[10] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    lots_to_aqui[11] = new Location("Lot P", new Coordinate(38.835085,-77.316257));
    lots_to_aqui[12] = new Location("West Campus Lot", new Coordinate(38.832945, -77.326507));
    
    DistanceFinder df_aqui = new DistanceFinder(new Location("Aquia Building", new Coordinate(38.832053, -77.309502)),lots_to_aqui);
    LotDistance [] ld_to_aqui = df_aqui.sortByDistance(); 
  } 
  
  // Finds and sorts distances between East Building and all the valid lots for the Shenandoah F/S Annual permit type
  @Test public void test_df8() {
    Location [] lots_to_east = new Location[13]; 
    lots_to_east[0] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    lots_to_east[1] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    lots_to_east[2] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_east[3] = new Location("Lot J", new Coordinate(38.83005, -77.31476));
    lots_to_east[4] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_east[5] = new Location("Lot C", new Coordinate(38.825355, -77.30594));
    lots_to_east[6] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    lots_to_east[7] = new Location("Lot L", new Coordinate(38.825882, -77.31063));
    lots_to_east[8] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_east[9] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_east[10] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    lots_to_east[11] = new Location("Lot P", new Coordinate(38.835085,-77.316257));
    lots_to_east[12] = new Location("West Campus Lot", new Coordinate(38.832945, -77.326507));
    
    DistanceFinder df_east = new DistanceFinder(new Location("East Building", new Coordinate(38.832998,-77.308297)),lots_to_east);
    LotDistance [] ld_to_east = df_east.sortByDistance(); 
  } 
  
  // Finds and sorts distances between Fenwick Library and all the valid lots for the Rapphanock River Parking Deck (RRPD) F/S Annual permit type
  @Test public void test_df9() {
    Location [] lots_to_fenwick = new Location[12]; 
    lots_to_fenwick[0] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    lots_to_fenwick[1] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_fenwick[2] = new Location("Lot J", new Coordinate(38.83005, -77.31476));
    lots_to_fenwick[3] = new Location("Lot A", new Coordinate(38.826389, -77.307105));
    lots_to_fenwick[4] = new Location("Lot C", new Coordinate(38.825355, -77.30594));
    lots_to_fenwick[5] = new Location("Lot K", new Coordinate(38.827987, -77.312649));
    lots_to_fenwick[6] = new Location("Lot L", new Coordinate(38.825882, -77.31063));
    lots_to_fenwick[7] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_fenwick[8] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_fenwick[9] = new Location("Lot P", new Coordinate(38.835085,-77.316257));
    lots_to_fenwick[10] = new Location("PV Lot", new Coordinate(38.831003, -77.314271));
    lots_to_fenwick[11] = new Location("West Campus Lot", new Coordinate(38.832945, -77.326507));
    
    
    DistanceFinder df_fenwick = new DistanceFinder(new Location("Fenwick Library", new Coordinate(38.830594,-77.306635)),lots_to_fenwick);
    LotDistance [] ld_to_fenwick = df_fenwick.sortByDistance(); 
  }
  
  // Finds and sorts distances between Center for the Arts and all the valid lots for the RRPD Roof Only F/S Annual permit type
  @Test public void test_df10() {
    Location [] lots_to_ca = new Location[1]; 
    lots_to_ca[0] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_ca = new DistanceFinder(new Location("Center for the Arts", new Coordinate(38.829516, -77.309348)),lots_to_ca);
    LotDistance [] ld_to_ca = df_ca.sortByDistance(); 
  } 
  
  // Finds and sorts distances between Cross Cottage  and all the valid lots for the West Campus permit type
  @Test public void test_df11() {
    Location [] lots_to_cc = new Location[1]; 
    lots_to_cc[0] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_cc = new DistanceFinder(new Location("Cross Cottage", new Coordinate(38.828752, -77.310916)),lots_to_cc);
    LotDistance [] ld_to_cc = df_cc.sortByDistance(); 
  }  
  
  // Finds and sorts distances between Greenhouse and all the valid lots for the Lot M & P Permit type
  @Test public void test_df12() {
    Location [] lots_to_greenhouse = new Location[3]; 
    lots_to_greenhouse[0] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_greenhouse[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_greenhouse[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    
    DistanceFinder df_greenhouse = new DistanceFinder(new Location("Greenhouse", new Coordinate(38.828044,-77.301397)),lots_to_greenhouse);
    LotDistance [] ld_to_greenhouse = df_greenhouse.sortByDistance();
  } 
  // Finds and sorts distances between Exploratory Hall and all the valid lots for the Resident Student Annual Lite type
  @Test public void test_df13() {
    Location [] lots_to_ex= new Location[5]; 
    lots_to_ex[0] = new Location("Lot M", new Coordinate(38.834085, -77.313695));
    lots_to_ex[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_ex[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_ex[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_ex[4] = new Location("Lot K", new Coordinate(38.827987, -77.312649)); 
    
    DistanceFinder df_ex = new DistanceFinder(new Location("Exploratory", new Coordinate(38.829729,-77.305454)),lots_to_ex);
    LotDistance [] ld_to_ex = df_ex.sortByDistance();
  } 
  
  // Finds and sorts distances between Innovation Hall and all the valid lots for the Mason Pond (Student Annual)permit type
  @Test public void test_df14() {
    Location [] lots_to_in = new Location[5]; 
    lots_to_in[0] = new Location("Mason Pond", new Coordinate(38.830631, -77.309557));
    lots_to_in[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_in[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_in[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_in[4] = new Location("Lot M", new Coordinate(38.834085, -77.313695)); 
    
    DistanceFinder df_in = new DistanceFinder(new Location("Innovation", new Coordinate(38.828502,-77.307427)),lots_to_in);
    LotDistance [] ld_to_in = df_in.sortByDistance();
  } 
  
  // Finds and sorts distances between Finley Building  and all the valid lots for the Shenandoah Deck (Reserved Student) permit type
  @Test public void test_df15() {
    Location [] lots_to_fin = new Location[4]; 
    lots_to_fin[0] = new Location("Shenandoah", new Coordinate(38.82913, -77.30452));
    lots_to_fin[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_fin[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_fin[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    
    DistanceFinder df_fin = new DistanceFinder(new Location("Finley", new Coordinate(38.833025,-77.308967)),lots_to_fin);
    LotDistance [] ld_to_fin = df_fin.sortByDistance();
  } 
  
  // Finds and sorts distances between Krasnow Institute and all the valid lots for the Rappahannock River Parking Deck permit type
  @Test public void test_df16() {
    Location [] lots_to_kr = new Location[5]; 
    lots_to_kr[0] = new Location("Rapphanock Deck", new Coordinate(38.834922, -77.305778));
    lots_to_kr[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_kr[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_kr[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_kr[4] = new Location("Lot M", new Coordinate(38.834085, -77.313695)); 
    
    DistanceFinder df_kr = new DistanceFinder(new Location("Krasnow", new Coordinate(38.831141,-77.300377)),lots_to_kr);
    LotDistance [] ld_to_kr = df_kr.sortByDistance();
  } 
  
  // Finds and sorts distances between Merten Hall  and all the valid lots for the Lot J Permit type
  @Test public void test_df17() {
    Location [] lots_to_mr = new Location[5]; 
    lots_to_mr[0] = new Location("Lot J", new Coordinate(38.83005, -77.31476));
    lots_to_mr[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_mr[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_mr[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_mr[4] = new Location("Lot M", new Coordinate(38.834085, -77.313695)); 
    
    DistanceFinder df_mr = new DistanceFinder(new Location("Merten", new Coordinate(38.835009, -77.307841)),lots_to_mr);
    LotDistance [] ld_to_mr = df_mr.sortByDistance();
  } 
  
  // Finds and sorts distances between Peterson Hall and all the valid lots for the Lot I Permit type
  @Test public void test_df18() {
    Location [] lots_to_pt = new Location[5]; 
    lots_to_pt[0] = new Location("Lot I", new Coordinate(38.83358, -77.311956));
    lots_to_pt[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_pt[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_pt[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_pt[4] = new Location("Lot M", new Coordinate(38.834085, -77.313695)); 
    
    DistanceFinder df_pt = new DistanceFinder(new Location("Peterson", new Coordinate(38.834435, -77.309244 )),lots_to_pt);
    LotDistance [] ld_to_pt = df_pt.sortByDistance();
  } 
  
  // Finds and sorts distances between Robinson Hall B and all the valid lots for the Lot R Permit type
  @Test public void test_df19() {
    Location [] lots_to_rb = new Location[5]; 
    lots_to_rb[0] = new Location("Lot R", new Coordinate(38.827147, -77.301938));
    lots_to_rb[1] = new Location("Lot P", new Coordinate(38.835085, -77.316257));
    lots_to_rb[2] = new Location("West Campus Lot", new Coordinate(38.834922, -77.305778));
    lots_to_rb[3] = new Location("Lot O", new Coordinate(38.834475, -77.313499));
    lots_to_rb[4] = new Location("Lot M", new Coordinate(38.834085, -77.313695)); 
    
    DistanceFinder df_rb = new DistanceFinder(new Location("Robinson B", new Coordinate(38.830897,-77.308043)),lots_to_rb);
    LotDistance [] ld_to_rb = df_rb.sortByDistance();
  }  
}
    
    
    