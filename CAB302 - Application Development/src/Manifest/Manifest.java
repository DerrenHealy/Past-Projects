package Manifest;


import java.util.ArrayList;

import OrdinaryTruck.OrdinaryTruck;
import RefrigeratedTruck.RefrigeratedTruck;


/**
 * A manifest of trucks
 *  This class is just a collection of trucks used for manifest generation
 * 
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */


public class Manifest {
	
	private ArrayList<RefrigeratedTruck> RefrigeratedTrucks;
	private ArrayList<OrdinaryTruck> OrdinaryTrucks;
	
	/**
	 * Constructor inisialises an array of Refrigerated and ordinary Trucks
	 */
	public Manifest() {
		
		RefrigeratedTrucks = new ArrayList<RefrigeratedTruck>();
		OrdinaryTrucks = new ArrayList<OrdinaryTruck>();
	}
	
	/**
	 * Adds a refrigerated truck to the manifest
	 * @param truck A refrigerated truck
	 * @see RefrigeratedTruck
	 */
	public void AddRefrigeratedTruck(RefrigeratedTruck truck) {
		RefrigeratedTrucks.add(truck);
	
	}
	
	/**
	 * Adds an ordinary truck to the manifest
	 * @param truck an ordinary truck
	 * @see OrdinaryTruck
	 */
	public void AddOrdinaryTruck(OrdinaryTruck truck) {
		OrdinaryTrucks.add(truck);
	
	}
	
	/**
	 * returns the list of ordinary trucks
	 * @return list of ordinary trucks
	 */
	public ArrayList<OrdinaryTruck> GetOrdinaryTrucks() {
		return OrdinaryTrucks;
	}
	
	/**
	 * returns the list of RefrigeratedTrucks
	 * @return list of RefrigeratedTrucks
	 */
	public ArrayList<RefrigeratedTruck> GetRefrigeratedTrucks() {
		return RefrigeratedTrucks;
	}
	
	/**
	 * Utility method to check the size of the Refirgerated trucks array (ie how many trucks in the list)
	 * @return Size of Refirgerated trucks array
	 */
	public int Rsize() {
		return RefrigeratedTrucks.size();
	}
	
	
	


}
