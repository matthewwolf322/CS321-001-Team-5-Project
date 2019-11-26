import org.junit.*;
import static org.junit.Assert.*;

public class BuildingReaderTest {
 public static void main(String args[]){
  org.junit.runner.JUnitCore.main("BuildingReaderTest");
 }  
 @Test public void test_br1(){
   //Test to see if BuildingReader can open a file
   BuildingReader reader = new BuildingReader("testBuilding.csv");
 }
 @Test public void test_br2(){
   //Ensure no crashing when the file is read in.
   BuildingReader reader = new BuildingReader("testBuilding.csv");
   reader.readFile();
 }
 @Test public void test_br3(){
   //ensure the proper number of buildings were read in from the text file.
   BuildingReader reader = new BuildingReader("testbuilding.csv");
   Location[] testarray = reader.readFile();
   assertTrue(testarray.length == 4);
 }
 @Test public void test_br4(){
   /* Majority of testing done here, testing regarding the accuracy of the 
    * data being read in is what is being tested in this section, so
    * many assertTrue()'s are being used on various data points different 
    * coordinates and strings.
    */
   BuildingReader reader = new BuildingReader("testbuilding.csv");
   Location[] testarray = reader.readFile();
   assertTrue(testarray[0].getCoordinates().getLat() == 15);
   assertTrue(testarray[3].getName().equals("building with a dash\\hyphen-"));
   assertTrue(testarray[1].getCoordinates().getLong() == 43.23999);
   assertTrue(testarray[1].getCoordinates().getLat() == -24.01234);
 }
}