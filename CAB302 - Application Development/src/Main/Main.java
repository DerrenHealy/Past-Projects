package Main;

import Store.Store;
import CSV.CSVFormatException;
import CSV.CSVreader;
import Item.Item;


public class Main {

	
	public static void main(String[] args) throws CSVFormatException {
		
		Store my_store = Store.Singleton();
		my_store.init("SuperMart", 100000);
		
		System.out.println(my_store.Getname());
		
		my_store.InitialiseItemProperties( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/item_properties.csv") );
		System.out.println("Initial Capital " + my_store.Getcapital());
		////////
		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Store Opening credit " + my_store.Getcapital());
//		System.out.println("Difference: " + (-42717.88+my_store.Getcapital()) );
//		
//		////////
//		my_store.UpdateSalesFromLog( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/sales_log_0.csv")  );
//		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Log 0 Store credit " + my_store.Getcapital());
//		System.out.println("Difference: " + (-27569.79+my_store.Getcapital()) );
//		////////
//		my_store.UpdateSalesFromLog( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/sales_log_1.csv")  );
//		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Log 1 Store credit " + my_store.Getcapital());
//		System.out.println("Difference: " + (-42069.94+my_store.Getcapital()) );
//		////////
//		my_store.UpdateSalesFromLog( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/sales_log_2.csv")  );
//		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Log 2 Store credit " +my_store.Getcapital());
//		System.out.println("Difference: " + (-47549.04+my_store.Getcapital()) );
//		////////
//		my_store.UpdateSalesFromLog( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/sales_log_3.csv")  );
//		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Log 3 Store credit " +my_store.Getcapital());
//		System.out.println("Difference: " + (-51838.22+my_store.Getcapital()) );
//		////////
//		my_store.UpdateSalesFromLog( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/CSV/sales_log_4.csv")  );
//		my_store.GenerateAManifest( my_store.GenerateStockOrder() );
//		my_store.UpdateFromManifest( CSVreader.readFile("C:/Users/Gordon/eclipse-workspace/Assignment2/manifest.csv") );
//		System.out.println("Log 4 Store credit " +my_store.Getcapital());
//		System.out.println("Difference: " + (-56140.25+my_store.Getcapital()) );
//		my_store.PrintInventoryToConsole();
//		////////
		
		/*
		for(Item item : my_store.Inventory().Getkeys()) {
			Object[] Values = {item.Getname(),
					my_store.Inventory().GetQuantityItem(item),
					item.Getmancost(), 
					item.Getsellprice(), 
					item.Getreorderpoint(),
					item.Getreorderamount(),
					item.GetshippingTemp()};
			
			
		}
		*/

	}

}
