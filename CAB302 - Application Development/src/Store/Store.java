
package Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Collectors;


import Item.Item;
import Manifest.Manifest;
import OrdinaryTruck.OrdinaryTruck;
import RefrigeratedTruck.RefrigeratedTruck;
import Stock.Stock;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * The 'Store'
 * 
 * The singleton store is accessed from here
 * 
 * as well as several utility functions
 * 
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */


public class Store {

	private double Capital;
	private String name;
	private Stock inventory;
	
	private static Store Store_instance = null;
	
	/**
	 *  Store constructor, Only inisialises a new Stock
	 */
	private Store() {
		
		inventory = new Stock();
		
	}
	
	/**
	 * Store Singleton makes sure the Store is singleton
	 * @return
	 */
	public static Store Singleton() {
		
		if (Store_instance == null) {
			Store_instance = new Store();
		}
	    return Store_instance;
	}
	
	/**
	 * Inisialises the stores name and capital in a singleton way
	 * @param name The name of the store
	 * @param Capital Hard-Sets the capital of the store (N.B. will overwrite capital if already exists!)
	 */
	public void init(String name, double Capital) {
		this.name = name;
		this.Capital = Capital;
	}
	
	/**
	 * Utility function for checking if a value can be cast to int with no error 
	 * @param str the input string to be checked
	 * @return 	Boolean
	 */
	public static boolean isInteger(String str) {
    if (str == null) {
        return false;
    }
    int length = str.length();
    if (length == 0) {
        return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
        if (length == 1) {
            return false;
        }
        i = 1;
    }
    for (; i < length; i++) {
        char c = str.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
    }
    return true;
}
	
	/**
	 * returns store inventory
	 * @return inventory
	 */
	public Stock Inventory() {
		return inventory;
	}
	
	/**
	 * Sets Item properties and adds items to the store initalises items with no shipping temp to 999
	 * @param scan A scanner of a CSV
	 * @see CSVreader
	 */
	public void InitialiseItemProperties(Scanner scan) {
		String name;
		double mancost;
		double sellprice;
		int reorderpoint;
		int reorderamount;
		double shippingTemp; 
		
		Item item;
		scan.useDelimiter(",");
		String scans = scan.next();
		
		while(scan.hasNext()) {
			
			if(!isInteger(scans)) {
				name = scans;
			} else {
				name = scan.next();
			}
			mancost = Integer.parseInt(scan.next());
			sellprice = Integer.parseInt(scan.next());
			reorderpoint = Integer.parseInt(scan.next());
			reorderamount = Integer.parseInt(scan.next());
			
			scans = scan.next();
			if(isInteger(scans)) {
				shippingTemp = Integer.parseInt(scans);
			} else {
				shippingTemp = 999;
			}
			//System.out.println("shippingtemp " + shippingTemp);
				
			
			item = new Item(name, mancost, sellprice, reorderpoint, reorderamount, shippingTemp);
					
			inventory.UpdateStock( item , 0);
			
			
		}
		
	}
	
	/**
	 * returns the stores name
	 * @return  returns store name
	 */
	public String Getname() {
		return name;
	}
	
	/**
	 *  returns capital
	 * @return store capital
	 */
	public double Getcapital() {

		return Math.round(Capital*100)/100;
	}
	

	/**
	 * Changes the stores capital by input amount
	 * @param d amount to change capital by
	 */
	public void changecapital(double d) {
		Capital += d;
	}
	
	/**
	 * Adds an item to the stores Stock/Inventory
	 * @param item
	 * @param quantity
	 */
	public void additem(Item item, int quantity) {
		inventory.UpdateStock(item, quantity);
	}
	
	/**
	 * Generates a Stock order by checking whats low and adding it to a list
	 * @return an ArrayList of Items  
	 */
	public ArrayList<Item> GenerateStockOrder() {
		
		ArrayList<Item> order = new ArrayList<Item>();
		
		for( Item x: inventory.Getkeys() ) {
			
			if (x.Getreorderpoint() >= inventory.GetStock().get(x)) {
				
				order.add(x);
			}
			
		}
		return order;
	}
	
	/**
	 *  Generates a manifest based on Items received in a stock order
	 * @param order a stockorder
	 */
	public void GenerateAManifest(ArrayList<Item> order) {
		
		//System.out.println("Generating Manifest");
		
		HashMap<Item, Integer> cold = new HashMap<Item, Integer>();
		ArrayList<Item> warm = new ArrayList<Item>();
		
		//sort into cold and warm
		for(Item x: order) {
			if(x.GetshippingTemp() != 999) {
				cold.put(x, (int) x.GetshippingTemp());
			} else {
				warm.add(x);
			}
		}
		
		//order cold items by ascending temp
		HashMap<Item, Integer> OrderedCold = cold.entrySet().stream().sorted(HashMap.Entry.comparingByValue()).collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		

		//new manifest
		Manifest Man = new Manifest();
		//System.out.println("Created New Manifest");
		// items iterated through
		ArrayList<Item> AddedToManifest = new ArrayList<Item>();
				
		
		//for initial num of cold trucks
		double min = 0.0;
		for(Item x :OrderedCold.keySet()) {
			min += x.Getreorderamount();
		}
		min = Math.ceil(min/800.0);
		
		//this loop checks if items can go in trucks and checks over the min amount of trucks regardless of need
		//for min num of trucks 
		for(int TruckNum = 0; TruckNum <= min+1; TruckNum++) {
			
			//make a ref truck
			RefrigeratedTruck CurrentColdTruck = new RefrigeratedTruck();
			//System.out.println("Created Cold Truck Number "+TruckNum);
			// for every item in the cold list
			for(Item x : OrderedCold.keySet()) {

				// check if a previous truck has space for it
				for(RefrigeratedTruck ExistingCold : Man.GetRefrigeratedTrucks() ) {
					// make sure we dont add to some random empty truck
					if(ExistingCold.Getremainingcapacity() >= x.Getreorderamount() && !AddedToManifest.contains(x)) {
						ExistingCold.AddItem(x, x.Getreorderamount());
						AddedToManifest.add(x);
						//System.out.println("Added cold item: "+x.Getname()+", to previous Cold Truck");
					}
				}
				
				// check if the current truck has space for it   
				if(CurrentColdTruck.Getremainingcapacity() >= x.Getreorderamount() && !AddedToManifest.contains(x)) {
					CurrentColdTruck.AddItem(x, x.Getreorderamount());
					AddedToManifest.add(x);
					//System.out.println("Added cold item: "+x.Getname()+", to Current Cold Truck");
				}
				
			}
			
			//System.out.println("Added CurrentColdTruck to truck Manifest");
			// add current truck to manifest
			Man.AddRefrigeratedTruck(CurrentColdTruck);
		}
		
		//clear added items from list
		AddedToManifest.clear();
		AddedToManifest = new ArrayList<Item>();
		
		// initail number of warm trucks
		min = 0.0;
		for(Item x : warm) {
			min += x.Getreorderamount();
		}
		min = Math.ceil(min/800.0);

		//this loop checks if items can go in trucks and checks over the min amount of trucks regardless of need
		//for min num of trucks 
		for(int TruckNum = 0; TruckNum <= min+1; TruckNum++) {
			
			//make an ordinary truck
			OrdinaryTruck CurrentWarmTruck = new OrdinaryTruck();
			//System.out.println("Created Warm Truck Number "+TruckNum);
			// for every warm item
			for(Item x: warm) {
			
				//check if a cold truck has space for it
				for(RefrigeratedTruck ExistingCold : Man.GetRefrigeratedTrucks() ) {
					// make sure truck has space, not some random empty truck and item needs placement
					if(ExistingCold.Getremainingcapacity() >= x.Getreorderamount() && ExistingCold.HasItems() && !AddedToManifest.contains(x)) {
						ExistingCold.AddItem(x, x.Getreorderamount());
						AddedToManifest.add(x);
						//System.out.println("Added warm item: "+x.Getname()+", to previous Cold Truck");
					}
				}
				
				//else check if a previous warm truck has space
				for(OrdinaryTruck ExistingWarm : Man.GetOrdinaryTrucks()) {
					// make sure truck has space, not some random empty truck and item needs placement
					if(ExistingWarm.Getremainingcapacity() >= x.Getreorderamount() && !AddedToManifest.contains(x)) {
						ExistingWarm.AddItem(x, x.Getreorderamount());
						AddedToManifest.add(x);
						//System.out.println("Added warm item: "+x.Getname()+", to previous Warm Truck");
					}
				}
				// else add it to the current working tuck
				if(CurrentWarmTruck.Getremainingcapacity() >= x.Getreorderamount() && !AddedToManifest.contains(x)) {
					CurrentWarmTruck.AddItem(x, x.Getreorderamount());
					AddedToManifest.add(x);	
					//System.out.println("Added warm item: "+x.Getname()+", to current Warm Truck");
				}	
				
				
			}
			
			//System.out.println("Added CurrentWarmTruck "+TruckNum+" to truck Manifest");
			//add current truck to manifest
			Man.AddOrdinaryTruck(CurrentWarmTruck);
			
	
			
		}
		

		// prints every trucks cargo
		/*
		int full = 0;
		for(RefrigeratedTruck reftruck: Man.GetRefrigeratedTrucks()) {

			System.out.print("Cold Truck Cargo: ");
			for(Item i: reftruck.Getinv().Getkeys()) {
				System.out.print( i.Getname()+",");
				full+= i.Getreorderamount();
			}
			System.out.println(full);
			full = 0;

		}

		full = 0;
		for(OrdinaryTruck OrdTruck: Man.GetOrdinaryTrucks()) {

			System.out.print("Warm Truck Cargo: ");
			for(Item i: OrdTruck.Getinv().Getkeys()) {
				System.out.print( i.Getname()+",");
				full+= i.Getreorderamount();
			}
			System.out.println(full);
			full = 0;

		}*/
		
		// start manifest writing
				PrintWriter writer = null;
				try {
					writer = new PrintWriter("manifest.csv", "UTF-8");
				} catch (FileNotFoundException e) {} catch (UnsupportedEncodingException e) {}
				
				//add cold
				for(RefrigeratedTruck x : Man.GetRefrigeratedTrucks()){
					
					// makes sure its not an empty truck ie buffer truck
					if(x.Getinv().GetQuantityCargo()>0) {
						writer.println(">Refrigerated");
						for(Item y : x.Getinv().Getkeys()) {
							writer.println(y.Getname()+","+y.Getreorderamount()  );
						}	
					}
				}
				
				// add warm
				for(OrdinaryTruck x : Man.GetOrdinaryTrucks()){
					
					// makes sure its not empty
					if(x.Getinv().GetQuantityCargo()>0) {
						writer.println(">Ordinary");
						for(Item y : x.Getinv().Getkeys()) {
							writer.println(y.Getname()+","+y.Getreorderamount()  );
							
						}
					}
				}
				writer.close();
	}
	
	/**
	 * utility function for testing purposes that prints the stores invnetory to console
	 */
	public void PrintInventoryToConsole() {
		for(Item x: inventory.Getkeys()) {
			System.out.println(x.Getname()+" = " + inventory.GetQuantityItem(x) );
		}
	}
	
	/**
	 * Updates store Inventory from a manifest and lowers Capital
	 * @param scan A Scanner generated from a CSV
	 * @see CSVreader
	 */
	
	//TODO COST OF ORDINARY TRUCK
	public void UpdateFromManifest(Scanner scan) {
		int quant;
		String current;
		
		boolean LastTruckOrdinary = false;
		int OrdinaryTruckCargoQuantity = 0;
		
		scan.useDelimiter(",");
		//while there are items to iterate through
		while(scan.hasNext()) {
			current = scan.next();
			
			if(current.contains("Refrigerated")) {
				
				LastTruckOrdinary = false;
				current = scan.next();
				quant = scan.nextInt();
				
				for(Item it: inventory.Getkeys()) {
					if(it.Getname().contains(current)&& current.contains(it.Getname())) {
						
						additem(it, quant);
						changecapital(-1 * it.Getmancost()*quant);
						
						changecapital(-1 * (900 + 200 * Math.pow(0.7, it.GetshippingTemp()/5))  );
					}
						
				}

					
			} else if(current.contains("Ordinary")) {
				
				current = scan.next();
				quant = scan.nextInt();
				
				
				
				for(Item it: inventory.Getkeys()) {
					if(it.Getname().contains(current)&& current.contains(it.Getname())) {
						additem(it, quant);
						OrdinaryTruckCargoQuantity += quant;
						changecapital(-1 * it.Getmancost()*quant);
						
					}
				}
				
				if(LastTruckOrdinary) {
					
					changecapital(-1 * (750 + 0.25 * OrdinaryTruckCargoQuantity));
					
					OrdinaryTruckCargoQuantity = 0;
				}
				
				LastTruckOrdinary = true;

			} else {
				
				quant = scan.nextInt();
				for(Item it: inventory.Getkeys()) {
					if(it.Getname().contains(current) && current.contains(it.Getname())) {
						additem(it, quant);
						if(LastTruckOrdinary) {
							OrdinaryTruckCargoQuantity += quant;
						}
						changecapital( -1 * it.Getmancost()*quant);
						
					}
				}
				
				
			}
			
			
			
			

		}

	}
	
	/**
	 * Updates Inventory and raises capital from a Sales Log
	 * @param scan A scanner generated from a sales log
	 * @see CSVreader
	 */
	public void UpdateSalesFromLog(Scanner scan) {

		scan.useDelimiter(",");
		//while there are items to iterate through
		while(scan.hasNext()) {
			//get item
			String next = scan.next();
			int quant;
			// find matching item in database
			for(Item x : inventory.Getkeys()) {
				if(x.Getname().contains(next) && next.contains(x.Getname())) {
					//update theat items stock
					quant = scan.nextInt();
					inventory.UpdateStock(x, -quant );
					changecapital(x.Getsellprice()*quant);

				}

			}

		}
		
	}

}

