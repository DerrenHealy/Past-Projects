package OrdinaryTruck;
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

import Item.Item;
import Stock.Stock;

public class ordinaryTruckTest {
	OrdinaryTruck Van = new OrdinaryTruck();
	Stock stock = new Stock();
	Item apple =new Item("apple" ,0, 0, 0, 0, 0);
	Item bananna =new Item("bananna" ,0, 0, 0, 0, 0);
	
	@Test
	public void getCostTest() {
		Van.AddItem(bananna, 100);
		Van.AddItem(apple, 100);
		stock=Van.Getinv();
		double expectedResult = 750 + 0.25 *stock.GetQuantityCargo();
		
		assertEquals(expectedResult,Van.Getcost(),0.001);
	}
	
	@Test 
	public void GetcapacityTest() {
		assertEquals(Van.Getcapacity(),1000);
	}
	
	@Test
	public void GetremainingcapacityTest() {
		
		Van.AddItem(bananna, 100);
		Van.AddItem(apple, 100);
		stock=Van.Getinv();
		
		assertEquals(Van.Getremainingcapacity(),800);
		
	}
	
	@Test
	public void hasnoitems() {
		assertEquals(Van.HasItems(),false);
	}
	
	@Test
	public void hasitems() {
		Van.AddItem(apple, 100);
		stock=Van.Getinv();
		assertEquals(Van.HasItems(),true);
	}
	

}
