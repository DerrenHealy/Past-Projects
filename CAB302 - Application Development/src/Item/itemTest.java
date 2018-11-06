package Item;
/**
 * <h1>Unit Tests For The Item Class</h1>
 * <p>The itemTest JUnitTests Test the
 *  expected inputs and outputs of the Item class</p>
 *  
 * @author Derren Healy
 * @Version 1.0
 * @since 2018-05-27
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class itemTest {
	
	String name = "name";
	double mancost = 100;
	double sellprice = 100;
	int reorderpoint = 100;
	int reorderamount = 100;
	double shippingTemp = 100;
	
	Item test = new Item(name, mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
	

	@Test
	public void constructorFailTest() {
		//Need to do this?
	}
	
	@Test
	public void testGetName() {
		//Test to see if the name of the item is correctly returning
		 assertEquals(test.Getname(),name);
		 
	}
	
	@Test
	public void testGetManCost() {
		//Test to see if the mancost of the item is correctly returning
		 assertEquals(test.Getmancost(),mancost,0.001);
		 
	}
	
	@Test
	public void testGetSalePrice() {
		//Test to see if the sellprice of the item is correctly returning
		assertEquals(test.Getsellprice(),sellprice,0.001);
		
	}
	
	@Test
	public void testReorderPoint() {
		//Test to see if the reorderpoint of the item is correctly returning
		assertEquals(test.Getreorderpoint(),reorderpoint);
		
	}
	
	@Test
	public void testReorderAmount() {
		//Test to see if the reorderamount of the item is correctly returning
		assertEquals(test.Getreorderamount(),reorderamount);
	
	}
	
	@Test
	public void testShippingTemp() {
		//Test to see if the shippingtemp of the item is correctly returning
		assertEquals(test.GetshippingTemp(),shippingTemp,0.001);
	}
}
