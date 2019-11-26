import org.junit.*;
import static org.junit.Assert.*;

public class PermitReaderTest {
 public static void main(String args[]){
  org.junit.runner.JUnitCore.main("PermitReaderTest");
 }  
 @Test public void test_pr1(){
   //Test that the reader can open the file without crashing
   PermitReader reader = new PermitReader("permitTest.csv");
 }
  @Test public void test_pr2(){
    //Test that the reader can read the file with no issue
   PermitReader reader = new PermitReader("permitTest.csv");
   reader.readFile();
 }
   @Test public void test_pr3(){
     //testing to ensure proper number of permits are read in
   PermitReader reader = new PermitReader("permitTest.csv");
   Permit[] permits = reader.readFile();
   assertTrue(permits.length == 4);
 }
   @Test public void test_pr4(){
     // test that proper number of lots associated with a permit
     PermitReader reader = new PermitReader("permitTest.csv");
     Permit[] permits = reader.readFile();
     assertTrue(permits[1].getLots().length == 26);
   }
    @Test public void test_pr5(){
      /* majority of testing is done here, several data
       * points from each permit are testing, mainly the 
       * accuracy of the strings read in
       */
   PermitReader reader = new PermitReader("permitTest.csv");
   Permit[] permits = reader.readFile();
   assertTrue(permits[0].getTitle().equals("TestPermit"));
   assertTrue(permits[3].getTitle().equals("Test permit with spaces and lots with spaces"));
   String[] lots1 = permits[1].getLots();
   String[] lots2 = permits[3].getLots();
   assertTrue(lots1[24].equals("Y"));
   assertTrue(lots1[13].equals("N"));
   assertTrue(lots2[3].equals("fourth lot"));
   assertTrue(lots2[4].equals("and the final lot"));
 }
}
