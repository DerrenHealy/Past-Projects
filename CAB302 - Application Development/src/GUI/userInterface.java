package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import CSV.CSVFormatException;
import CSV.CSVreader;
import Item.Item;
import Store.Store;

@SuppressWarnings("serial")
public class userInterface extends JFrame{
	Store my_store = Store.Singleton();

	public void createAndShowGUI() {
		
		//Create A new JFrame And Define its on Close Function
		JFrame frame =new JFrame ("User Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Change The Frame's Icon
		//frame.setIconImage(new ImageIcon(imgURL).getImage());
		
		//Discover the User's Screen Size In order To Center the Frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenHeight = screenSize.getHeight();
		double screenWidth = screenSize.getWidth();
		
		//Panel To Store Components
		JPanel tablePanel=new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel,BoxLayout.Y_AXIS));
		Border padding = BorderFactory.createEmptyBorder(50,50,50,50);
		tablePanel.setBorder(padding);
		
		//Button Panel
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		//Example Button
		JButton load_properties  = new JButton();
		JButton export_manifest  = new JButton();
		JButton load_manifest = new JButton();
		JButton sales_logs  = new JButton();
		
		String headers[] = { "Name", "Quantity", "Manufacturing Cost ($)", "Sell Price ($)", "Reorder Point", "Reorder amount", "Temperature (C)"};
		JTable itemTable =new JTable(new DefaultTableModel(headers,0));
		itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		
		//Example Label
		JLabel capital=new JLabel(my_store.Getname()+" Capital: $"+my_store.Getcapital());
		capital.setFont(new Font("Serif", Font.BOLD,30));
		
		load_properties.setSize(100,100);
		load_properties.setText("Load Properties");
		load_properties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String currentDirectory = loadfile(evt);
				try {
					my_store.InitialiseItemProperties(CSVreader.readFile(currentDirectory));
					for(Item item : my_store.Inventory().Getkeys()) {
			            Object[] Values = {item.Getname(),
			                    my_store.Inventory().GetQuantityItem(item),
			                    item.Getmancost(), 
			                    item.Getsellprice(), 
			                    item.Getreorderpoint(),
			                    item.Getreorderamount(),
			                    item.GetshippingTemp()};
			            model.addRow(Values);  
			        }
					load_properties.setEnabled(false);
					load_manifest.setEnabled(true);
					sales_logs.setEnabled(true);
					export_manifest.setEnabled(true);
				} catch (CSVFormatException e) {
					e.printStackTrace();
				}
			}
		});
		
		export_manifest.setSize(100,100);
		export_manifest.setText("Export Manifest");
		export_manifest.setEnabled(false);
		export_manifest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				my_store.GenerateAManifest( my_store.GenerateStockOrder());
				export_manifest.setText("Successfully Exported");
				
			}
			
		});
		
		load_manifest.setSize(100,100);
		load_manifest.setText("Load Manifest");
		load_manifest.setEnabled(false);
		load_manifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String currentDirectory = loadfile(evt);
				try {
					my_store.UpdateFromManifest(CSVreader.readFile(currentDirectory));
					System.out.println(my_store.Getcapital());
				}catch (CSVFormatException e) {
					e.printStackTrace();
				}
				capital.setText(my_store.Getname()+" Capital: $"+my_store.Getcapital());
				itemTable.removeAll();
				for(Item item : my_store.Inventory().Getkeys()) {
		            Object[] Values = {item.Getname(),
		                    my_store.Inventory().GetQuantityItem(item),
		                    item.Getmancost(), 
		                    item.Getsellprice(), 
		                    item.Getreorderpoint(),
		                    item.Getreorderamount(),
		                    item.GetshippingTemp()};
		            model.addRow(Values);  
		        }
			}
		});
		
		
		sales_logs.setSize(100,100);
		sales_logs.setText("Sales Logs");
		sales_logs.setEnabled(false);
		sales_logs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String currentDirectory=loadfile(evt);
				try {
					my_store.UpdateSalesFromLog( CSVreader.readFile(currentDirectory)  );
				} catch (CSVFormatException e) {
					e.printStackTrace();
				}
				itemTable.removeAll();
				for(Item item : my_store.Inventory().Getkeys()) {
		            Object[] Values = {item.Getname(),
		                    my_store.Inventory().GetQuantityItem(item),
		                    item.Getmancost(), 
		                    item.Getsellprice(), 
		                    item.Getreorderpoint(),
		                    item.Getreorderamount(),
		                    item.GetshippingTemp()};
		            model.addRow(Values);  
		        }
				
			}
			
		});
				
		//Add Panel Components
		tablePanel.add(new JScrollPane(itemTable));
		buttonPanel.add(load_manifest);
		buttonPanel.add(export_manifest);
		buttonPanel.add(load_properties);
		buttonPanel.add(sales_logs);
		
		
		//Add Panel To Frame
		frame.add(capital,BorderLayout.NORTH);
		frame.add(tablePanel,BorderLayout.CENTER);
		frame.add(buttonPanel,BorderLayout.SOUTH);
		
		//Pack the Frame and Make it Visible
		frame.setSize(850,400);
		//frame.pack();
		
		int frameWidth=frame.getWidth();
		int frameHeight=frame.getHeight();
		
		//frame.setSize(800, 600);
		frame.setLocation((int) ((screenWidth/2)-frameWidth/2),(int) ((screenHeight/2)-frameHeight/2));
		frame.setVisible(true);
		

	}
	
	private String loadfile (java.awt.event.ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Csv Files(*.csv)","csv");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.showOpenDialog(userInterface.this);
		String currentDirectory=fileChooser.getSelectedFile().toString();
		return 	currentDirectory;
	  }
		
	public static void main(String[] args) {
		userInterface GUI = new userInterface();
		GUI.my_store.init("SuperMart", 100000);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI.createAndShowGUI();
			}
		});
	}

}
