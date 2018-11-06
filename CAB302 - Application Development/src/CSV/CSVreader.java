/**
 * CSV parser and reader
 * 
 * This class has a single method for parsing and reading CSVs
 * and outputs the CSV as a Scanner which can be used ato iterate through the CSV
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */

package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVreader {

	
	/**
	 * a methode for turning raw CSV files into scanners
	 * @param pathname the file location
	 * @return a scanner of the input CSV
	 * @throws CSVFormatException
	 */
	public static Scanner readFile(String pathname) throws CSVFormatException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner;
	    
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new CSVFormatException();
		}
		
	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + "\n");
	        }
	        String my_new_str = fileContents.toString().replace("\n", ",").replace(">","");
	        //System.out.println(my_new_str);
	        Scanner scan = new Scanner(  my_new_str  );
	        return scan;
	    } finally {
	        scanner.close();
	    }
	}

}
