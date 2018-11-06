/**
 * An ordinary truck without a temperature
 * 
 * an extension of the abstract class truck
 * this class represents 1 ordinary unrefirgerated truck for generating manifests
 * the Manifest class is a collection of these trucks
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */


package OrdinaryTruck;

import Item.Item;
import Stock.Stock;
import Truck.Truck;

public class OrdinaryTruck extends Truck {
	
	private Stock stock;
	private int capacity;

	/**
	 * Ordinary Truck constructor
	 * Initialises an empty Stock and max capacity
	 */
	public OrdinaryTruck() {
		
		stock = new Stock();
		//this.stock = stock;
		capacity = 1000;
		
	}
	
	/**
	 * Returns the cost of a refrigerated truck based on a how full it is
	 * @return a a double of cost 
	 */
	public double Getcost() {

		return 750 + 0.25 * stock.GetQuantityCargo();
	}

	/**
	 * returns truck max capacity
	 * @return max capacity
	 */
	public int Getcapacity() {
		return capacity;
	}
	
	/**
	 * returns a trucks remaining capacity
	 * @return remaining capacity
	 */
	public int Getremainingcapacity() {

		return capacity - stock.GetQuantityCargo();

	}


	/**
	 * checks to see if a truck is empty
	 * @return boolean false if empty
	 */
	public boolean HasItems() {
		if(Getremainingcapacity()<capacity) {
			return true;
		} else {
			return false;
		}
	}

	
	
	/**
	 * returns trucks inventory
	 * @return inventory
	 */
	@Override
	public Stock Getinv() {
		return stock;
	}


	/**
	 * adds an item to the truck
	 * @param item the Item to add
	 * @param the quantity of the item to add
	 * @see Item
	 */
	@Override
	public void AddItem(Item item, int quant) {
		stock.UpdateStock(item, quant);

	}


}
