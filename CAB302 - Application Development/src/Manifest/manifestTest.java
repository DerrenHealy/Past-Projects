package Manifest;
/**
 * <h1>Unit Tests For The Manifest Class</h1>
 * <p>The itemTest JUnitTests Test the
 *  expected inputs and outputs of the Manifest class</p>
 *  
 * @author Derren Healy
 * @Version 1.0
 * @since 2018-05-27
 */
import static org.junit.Assert.*;

import org.junit.Test;

import OrdinaryTruck.OrdinaryTruck;
import RefrigeratedTruck.RefrigeratedTruck;

public class manifestTest {
	
	Manifest test =new Manifest();
	RefrigeratedTruck van = new RefrigeratedTruck();
	RefrigeratedTruck van3 = new RefrigeratedTruck();
	OrdinaryTruck van2 = new OrdinaryTruck();
	
	@Test
	public void addRefTruckTest(){
		test.AddRefrigeratedTruck(van);
		//assertEquals(test.GetRefrigeratedTrucks(),Van in the array of vans);
	}
	
	@Test
	public void addOrdTruckTest(){
		test.AddOrdinaryTruck(van2);
		//assertEquals(test.GetOrdinairyTrucks(),Van2 in the array of vans);
	}
	
	@Test
	public void getOrdinaryTruck(){
		test.GetOrdinaryTrucks();
		//assertEquals(test.GetOrdinaryTrucks(),test.OrdinaryTrucks[van2]);
	}
	
	@Test
	public void getRefrigeratedTruck() {
		test.GetRefrigeratedTrucks();
	}
	
	@Test
	public void refTruckAmount() {
		test.AddRefrigeratedTruck(van);
		test.AddRefrigeratedTruck(van3);
		assertEquals(test.Rsize(),2);
	}
	
	
	
	
	

}
