package RefrigeratedTruck;

import Item.Item;
import Stock.Stock;
import Truck.Truck;

/**
 * A refigerated truck with a temperature
 * 
 * an extension of the abstract class truck
 * this class represents 1 refirgerated truck for generating manifests
 * the Manifest class is a collection of these trucks
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */
public class RefrigeratedTruck extends Truck {

	private int capacity;
	private Stock inventory;
	
	/**
	 * Refrigerated Truck constructor
	 * Initialises an empty Stock and max capacity
	 */
	public RefrigeratedTruck() {
		
		inventory = new Stock();
		//this.stock = stock;
		capacity = 800;
		
	}
	
	/**
	 * Returns the cost of a refrigerated truck based on a temperature
	 * @param d truck temperature
	 * @return a a double of cost 
	 */
	public double Getcost(double d) {
		
		return 900 + 200 * Math.pow(0.7, d/5);
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
		
		return capacity - inventory.GetQuantityCargo();
		
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
	 * returns the Temperature of the coldest item in the truck or 10 (max temp)
	 * @return Truck temperature
	 */
	public int TruckTemp() {
		int coldest = 10;
		
		for(int x: inventory.GetStock().values()) {
			if(x < coldest) {
				coldest = x;
			}
		}
		return coldest;
		
	}
	
	/**
	 * returns trucks inventory
	 * @return inventory
	 */
	@Override
	public Stock Getinv() {
		return inventory;
	}

	
	/**
	 * adds an item to the truck
	 * @param item the Item to add
	 * @param the quantity of the item to add
	 * @see Item
	 */
	@Override
	public void AddItem(Item item, int quant) {
		inventory.UpdateStock(item, quant);
		
	}


}
