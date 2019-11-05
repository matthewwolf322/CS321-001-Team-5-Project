
import java.util.*;
import java.io.*;

public class PermitReader {
	File infile;
	
	public PermitReader(String fileName) {
		infile = new File(fileName);
	}
	
	// Main part of the program, reads in the file and creates permit objects
	public Permit readFile(String permitName) {
		Scanner sc = null;
		String temp = null;
		try {
			sc = new Scanner(infile);
		} catch (FileNotFoundException e) {
			//Exit if file is not found
			System.out.println("Error loading file.");
			System.exit(0);
		}
		
		//using an arraylist to easily add each string of an arbitrary-length line
		while(!sc.next().equals(permitName)) {
			temp = sc.nextLine();
		}
		Permit permit = new Permit(permitName,temp.split(","));
		
		return permit;
		
		
		
	}

}
