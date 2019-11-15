
import java.util.*;
import java.io.*;

public class BuildingReader {
	File infile;
	
	public BuildingReader(String fileName) {
		infile = new File(fileName);
	}
	
	// Main part of the program, reads in file and creates location objects
	public Location[] readFile() {
		Scanner sc = null;
		String temp;
		try {
			sc = new Scanner(infile);
		} catch (FileNotFoundException e) {
			//Exit if file is not found
			System.out.println("Error loading file " + infile);
			System.exit(0);
		}
		
		//using an arraylist to easily add each line of an arbitrary-length file
		ArrayList<Location> array = new ArrayList<Location>();
		String dataHolder[] = new String[3];
		Location tempLocation;
		while(sc.hasNextLine()) {
			temp = sc.nextLine();
			dataHolder = temp.split(",");
			tempLocation = new Location(dataHolder[0],new Coordinate(Double.parseDouble(dataHolder[1]),Double.parseDouble(dataHolder[2])));
			array.add(tempLocation);
			
		}
		
		Location[] returnList = new Location[array.size()];
		for(int i = 0; i < returnList.length;i++) {
			returnList[i] = array.get(i);
		}
		
		return returnList;
		
	}

}
