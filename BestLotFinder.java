/* TODO: add a load() method to load all the data
 * add a helper method that will take a permit and all of the lots, and return only lots associated with that permit
 * this method will be used in the main findBestLot method.
 */

public class BestLotFinder {
	// Some variables need to be established, not entirely sure how many
	
	//main workhorse of this class, returns sorted list of lot distances for use by display
	public LotDistance[] findBestLot() {
		
	}
	//this should be called by the display class before use
	//we may want an assocaited boolean that doesn't allow use of the class until load has been called
	public void load() {
		
	}
	
	// may want to have a string parameter that is the permit title
	//alternatively we have a static varaible we reference to do it
	//all depends on how one wants to implement it.
	public Location[] lotsFromPermit() {
		
	}
	
}
