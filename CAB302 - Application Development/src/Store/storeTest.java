package Store;
/**
 * <h1>Unit Tests For The Store Class</h1>
 * <p>The itemTest JUnitTests Test the
 *  expected inputs and outputs of the Store class</p>
 *  
 * @author Derren Healy
 * @Version 1.0
 * @since 2018-05-27
 */
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class storeTest {
	
	Store bob = Store.Singleton();
	/**
	 * This Test Is used to test if the isInteger function 
	 * is working as intended
	 * @Result  
	 * 
	 */
	@Test
	public void isIntegerPass(){
		assertEquals(Store.isInteger("1"),true);
	}
		 
	@Test
	public void isIntegerlengthZero(){
		assertEquals(Store.isInteger(""),false);
	}
	
	public void isIntegerNull(){
		assertEquals(Store.isInteger(null),false);
	}
		 
	@Test
	public void isIntegerChar(){
		assertEquals(Store.isInteger("C"),false);
	}
		 
		 @Test
	public void isIntegerChars(){
		//line 90?
		assertEquals(Store.isInteger("-"),false);
	}
	
	@Test
	public void getCapitalTest() {
		bob.init("Bob's Beans", 100000.00);
		assertEquals(bob.Getcapital(), 100000,0.001);
	}
	
	@Test
	public void GetnameTest() {
		bob.init("Bob's Beans", 100000.00);
		assertEquals(bob.Getname(),"Bob's Beans");
	}
	
	@Test
	public void changecapitalTest() {
		bob.init("Bob's Beans", 100000.00);
		bob.changecapital(1000);
		assertEquals(101000,bob.Getcapital(),0.001);
	}
	
	//InitialiseItemProperties(Scanner scan);

}
