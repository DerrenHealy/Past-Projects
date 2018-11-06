package Truck;

import Item.Item;
import Stock.Stock;

/**
 * Truck abstract class
 * 
 * the abstract class RefrigeratedTruck and OrdinaryTruck use
 * @see RefrigeratedTruck
 * @see OrdinaryTruck
 * 
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */
public abstract class Truck {

	public Truck() {
	
	}
	
	public abstract int Getcapacity();
	public abstract int Getremainingcapacity();
	public abstract Stock Getinv();
	public abstract void AddItem(Item item, int quant);
		
}
