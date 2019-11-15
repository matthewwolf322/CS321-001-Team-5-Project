import java.util.*;
import java.io.*;

public class PermitReader {
	File infile;
	
	public PermitReader(String fileName) {
		infile = new File(fileName);
	}
	
	// Main part of the program, reads in the file and creates permit objects
	public Permit[] readFile() {
		Scanner sc = null;
		String temp = null;
		String listTemp[], lotList[];
		try {
			sc = new Scanner(infile);
		} catch (FileNotFoundException e) {
			//Exit if file is not found
			System.out.println("Error loading file " + infile);
			System.exit(0);
		}
		
		//using an arraylist to easily add each string of an arbitrary-length line
		Permit permit;
		ArrayList<Permit> permitList = new ArrayList<Permit>();
		while(sc.hasNextLine()) {
			temp = sc.nextLine();
			listTemp = temp.split(",");
			temp = listTemp[0];
			lotList = new String[listTemp.length-1];
			for(int i = 1; i < listTemp.length;i++) {
				lotList[i-1] = listTemp[i];
			}
			permit = new Permit(temp,lotList);
			permitList.add(permit);
		}
		
		Permit[] permitArray = new Permit[permitList.size()];
		for(int i = 0; i < permitArray.length;i++) {
			permitArray[i] = permitList.get(i);
		}
		permitList = null;
		sc.close();
		return permitArray;
		
		
	}

}
