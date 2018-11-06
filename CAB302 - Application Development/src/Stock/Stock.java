/**
 * An Inventory or Stock
 * 
 * This class is an 'inventory', 'cargo' or 'stock of items'
 * 
 * This class is a collection of Items
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */


package Stock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import Item.Item;

public class Stock {

	
	private HashMap<Item, Integer> Inventory = new HashMap<Item, Integer>();
	
	/**
	 * Stock constructor
	 * Inisialises a stock with an empty inventory
	 */
	public Stock() {
		
		Inventory = new HashMap<Item, Integer>();
		
	}
	
	/**
	 * updates the quantity of an item in the inventory 
	 * @param item the item to update
	 * @param quantity quantity of the item to add
	 */
	public void UpdateStock(Item item, int quantity) {
		
		if(!Inventory.containsKey(item)) {
			Inventory.put(item, quantity);
		} else {
			int x = Inventory.get(item) + quantity;
			Inventory.replace(item, x);
			
		}
	
	}
	
	/**
	 * returns the quantity of an item in the stock
	 * @param item item to fetch
	 * @return quantity of an item
	 */
	public int GetQuantityItem(Item item) {
		return Inventory.get(item);
		
	}
	
	/**
	 * returns the total quantity of all items in the stock
	 * @return int of number of all combined items
	 */
	public int GetQuantityCargo() {
		int total = 0;
		for(int x : Inventory.values()) {
			total += x;
		}
		return total;
	}
	
	/**
	 * returns inventory
	 * @return inventory
	 */
	public HashMap<Item, Integer> GetStock() {
		return Inventory;
	}
	
	/**
	 * returns a set of all items in the list
	 * @return Inventory keyset
	 */
	public Set<Item> Getkeys() {
		return Inventory.keySet();
	}
	
	/**
	 * returns a collection of only the quantitys of items
	 * @return collection of ints
	 */
	public Collection<Integer> Getvalues() {
		return Inventory.values();
	}
	
	
	
	
	

}
