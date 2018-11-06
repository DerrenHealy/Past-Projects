package Stock;
/**
 * <h1>Unit Tests For The Stock Class</h1>
 * <p>The itemTest JUnitTests Test the
 *  expected inputs and outputs of the Stock class</p>
 *  
 * @author Derren Healy
 * @Version 1.0
 * @since 2018-05-27
 */
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import Item.Item;

public class stockTest {

	String name = "name";
	double mancost = 100;
	double sellprice = 100;
	int reorderpoint = 100;
	int reorderamount = 100;
	double shippingTemp = 100;
	
	Item test = new Item(name, mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
	HashMap<Item, Integer> Inventory = new HashMap<Item, Integer>();
	
	HashMap<Item, Integer> Inventory2 = new HashMap<Item, Integer>();
	Item test2 = new Item("test_item_2", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
	Item test3 = new Item("test_item_3", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
	Item test4 = new Item("test_item_4", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
	
	
	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void testUpdateStockWithKey() {
	//Update stock with key and see if is updated
		HashMap<Item, Integer> Inventory = new HashMap<Item, Integer>();
		//Inventory.UpdateStock(test, 10);
		//assertEquals(Inventory.get(test),true);
	}
	
	@Test
	public void testUpdateStockWithoutKey() {
		//Test To see if stock is changed correctly based on no key
		HashMap<Item, Integer> Inventory = new HashMap<Item, Integer>();
		//Inventory.UpdateStock(test, 10);
		//assertEquals(Inventory.get(test).get(10),true);
	}
	
	@Test
	public void testStockLevel() {
		//Inventory.UpdateStock(test,100);
		//assertEquals(Inventory.GetQuantityItem(test),100);
	}
	
	@Test
	public void testCargoLevel() {
		Item test = new Item("test_item_2", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
		Item test2 = new Item("test_item_3", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
		Item test4 = new Item("test_item_4", mancost, sellprice, reorderpoint, reorderamount, shippingTemp );
		//Inventory.UpdateStock(test,100);
		//Inventory.UpdateStock(test2,20);
		//Inventory.UpdateStock(test3,35);
		//Inventory.UpdateStock(test4,94);
		//assertEquals(Inventory.GetQuantityCargo(),4);
	}
	
	@Test
	public void testGetStock() {
		//Inventory.UpdateStock(test,100);
		//Inventory.UpdateStock(test2,20);
		//Inventory.UpdateStock(test3,35);
		//Inventory.UpdateStock(test4,94);
		//assertEquals(Inventory2.GetStock(),Inventory2);
	}
	
	@Test
	public void testGetkeys() {
		//assertEquals(Inventory.GetKeys(),Inventory.keySet());
	}
	
	@Test
	public void testGetvalues() {
		//assertEquals(Inventory.Getvalues(),Inventory.values());
	}

}
