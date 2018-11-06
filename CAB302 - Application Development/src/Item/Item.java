package Item;

/**
 * A single Item
 * 
 * This class represents a single item 
 * eg. Chips or Beef or Cheese...
 * 
 * An item has a Name, manufacturing cost, sale price, reorderpoint, reorder amount and shipping temp
 * 
 * 
 * @author Gordon
 * @version 1.0
 * @since 2018-05-27
 */


public class Item {

	
	private String name;
	private double mancost;
	private double sellprice;
	private int reorderpoint;
	private int reorderamount;
	private double shippingTemp;
	
	
	/**
	 * Constructor of an item
	 * 
	 * @param name items name
	 * @param mancost items manufacturing cost
	 * @param sellprice items sale price
	 * @param reorderpoint items reorder point (min qunatity before reorder)
	 * @param reorderamount items reorder amount
	 * @param shippingTemp items shipping temp
	 */
	public Item(String name, double mancost, double sellprice, int reorderpoint, int reorderamount, double shippingTemp ) {
		
		this.name = name;
		this.mancost = mancost;
		this.sellprice = sellprice;
		this.reorderpoint = reorderpoint;
		this.reorderamount = reorderamount;
		this.shippingTemp = shippingTemp;
		
	}
	
	/**
	 * Retunrs item name
	 * @return item name
	 */
	public String Getname(){
		return name;
	}
	
	/**
	 * returns item manufacturing cost
	 * @return item manufacturing cost
	 */
	public double Getmancost() {
		return mancost;
	}
	
	/**
	 * returns item sale price
	 * @return item sale price
	 */
	public double Getsellprice() {
		return sellprice;
	}
	
	/**
	 * returns item reorder point
	 * @return item reorder point
	 */
	public int Getreorderpoint() {
		return reorderpoint;
	}
	
	/**
	 * returns item reorder amount
	 * @return item reorder amount
	 */
	public int Getreorderamount() {
		return reorderamount;
	}
	
	/**
	 * returns item shipping temperature
	 * @return item shipping temperature
	 */
	public double GetshippingTemp() {
		return shippingTemp;
	}
	

	
	
	

}
