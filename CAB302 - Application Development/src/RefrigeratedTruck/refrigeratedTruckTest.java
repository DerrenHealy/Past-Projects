package RefrigeratedTruck;
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

public class refrigeratedTruckTest {
	
	RefrigeratedTruck RefVan = new RefrigeratedTruck();
	Stock stock = new Stock();
	Item apple =new Item("apple" ,0, 0, 0, 0, 0);
	Item bananna =new Item("bananna" ,0, 0, 0, 0, 0);
	
	@Test
	public void getCostTest() {
		RefVan.AddItem(bananna, 100);
		RefVan.AddItem(apple, 100);
		stock=RefVan.Getinv();
		double expectedResult = 750 + 0.25 *stock.GetQuantityCargo();
		
		assertEquals(expectedResult,RefVan.Getcost(expectedResult),0.001);
	}
	
	@Test 
	public void GetRefcapacityTest() {
		System.out.print(RefVan.Getcapacity());
		assertEquals(RefVan.Getcapacity(),800);
	}
	
	@Test
	public void GetRefremainingcapacityTest() {
		
		RefVan.AddItem(bananna, 100);
		RefVan.AddItem(apple, 100);
		stock=RefVan.Getinv();
		
		assertEquals(RefVan.Getremainingcapacity(),600);
		
	}
	
	@Test
	public void Refhasnoitems() {
		assertEquals(RefVan.HasItems(),false);
	}
	
	@Test
	public void Refhasitems() {
		RefVan.AddItem(apple, 100);
		stock=RefVan.Getinv();
		assertEquals(RefVan.HasItems(),true);
	}

}
