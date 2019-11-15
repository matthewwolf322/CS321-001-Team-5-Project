/*
 * NOTE PLEASE READ
 * I didn't put any of the boolean checks to ensure you set the permit and the building first
 * because yall are checking to make sure thats being done in the display class. 
 * 
 * If there are any issues with it, please message me cause its more than likely that I
 * 
 */
public class BestLotFinder {
 // Some variables need to be established, not entirely sure how many
 Permit[] permitList;
 Location[] buildingList,masterLotList,currentPermitLotList;
 Location currentBuilding;
 
 public BestLotFinder() {
  this.load();
 }
 //main workhorse of this class, returns sorted list of lot distances for use by display
 public LotDistance[] findBestLot() {
   DistanceFinder df = new DistanceFinder(currentBuilding, currentPermitLotList);
   LotDistance[] ld = df.sortByDistance();
   return ld;
   //return null;
 }
 //this should be called by the display class before use
 //we may want an assocaited boolean that doesn't allow use of the class until load has been called
 private void load() {
  BuildingReader buildingReader = new BuildingReader("assets/buildings.csv");
  BuildingReader lotReader = new BuildingReader("assets/lots.csv");
  PermitReader permitReader = new PermitReader("assets/permits.csv");
  permitList = permitReader.readFile();
  buildingList = buildingReader.readFile();
  masterLotList = lotReader.readFile();
  
 }
 
 //sets the permit to the given permit and sets the current lot list to lots in that permit
 //
 public void setPermit(String permitName) {
  //need a boolean check
  Permit p = null;
  Location temp;
  //find the permit
  for(int i = 0; i < permitList.length;i++) {
   if(permitName.equals(permitList[i].getTitle())) {
    p = permitList[i];
   }
  }
  currentPermitLotList = new Location[p.getLots().length];
  String[] reference = p.getLots();
  //find the lots associated with the permit
  for(int i = 0; i < currentPermitLotList.length;i++) {
   for(int j = 0; j < masterLotList.length;j++) {
    if(reference[i].equals(masterLotList[j].getName())) {
     currentPermitLotList[i] = masterLotList[j];
     break;
    }
   }
  }
 }
 
 //sets current building
 public void setBuilding(String buildingName) {
  for(int i = 0; i < buildingList.length;i++) {
   if(buildingName.equals(buildingList[i].getName())) {
    currentBuilding = buildingList[i];
   }
  }
 }
  
 public String[] getLotList() {
  String[] lotStrings = new String[masterLotList.length];
  for(int i = 0; i < lotStrings.length;i++){
   lotStrings[i] = masterLotList[i].getName();
  }
  return lotStrings;
 }
 public String[] getBuildingList() {
  String[] buildingStrings = new String[buildingList.length];
  for(int i = 0; i <buildingStrings.length;i++) {
   buildingStrings[i]=buildingList[i].getName();
  }
  return buildingStrings;
 }
 public String[] getPermitList() {
  String[] permitStrings = new String[permitList.length];
  for(int i = 0; i < permitStrings.length;i++) {
   permitStrings[i] = permitList[i].getTitle();
  }
  return permitStrings;
 }
}
