package CSV;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CSVtest{
	 //@Rule 
	 //public final ExpectedException exception = ExpectedException.none();
	 
	 @Test
	 public void pathCheckThrowException() throws CSVFormatException {
		 try {
	        CSVreader.readFile("test");
	        fail("expected exception has not occurred");
		 } catch(CSVFormatException e) {
			 //if execution reaches here then the exception has occurred
			 //so we do not need to handle it
		 }
	 }
	 
	 @Test
	 public void pathCheckPass() throws CSVFormatException {
	       CSVreader.readFile("C:/Users/Caitin/Desktop/item_properties.csv");
	 }
	 
	 @Test
	 public void pathCheckIsClosed() {
		  //Not sure how to do	    
	 }
	 

}
