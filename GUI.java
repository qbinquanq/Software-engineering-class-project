import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * 
 * @author Binquan Wang
 *  class extends JFrame
 */
public class GUI extends JFrame implements ActionListener{  // Class GUI extends JFrame and implements ActionListener
   private static final ActionEvent GUI = null;  // set ActionEvent GUI
   protected static TextField TxtWebsite = new TextField("0",20); // declare TextField and initialize it
 
   protected static Button btnSearch; // button btnSearch
   public static JComboBox CatBox;    // create a JComboBox called CatBox to display all categories
   public static String searchCat;   // String searchCat
   public static String Idnumber;  // String Idnumber
   public static int searchCount; //count for how many times to click the search button
   public static Button btngetCategories; // click this button will get all categories
  // public static String[] StoreCategories; // array used to store filtered categories
  // public static int categoriesNumber = 0; // used to count assign the position to array when store categories
 //  public static int StoreIDCount = 0; // used to count assign the position to array when store ID number
 //  public static String[] StoreID; // array used to store ID number
   
   public static Button btngetSeller; // click this button to get seller
  // public static String[] StoreSeller;  // array used to store filtered seller
  // public static int sellerNumber = 0; // used to assign position to store the seller
   
   public static JComboBox SellerBox; // create a JComboBox called SellerBox to display all sellers
   JFrame jframe;  //declare type JFrame jframe
    /**
     * create a JFrame that have GridLayout and FlowLayout
     */
    public GUI(String title, int height, int width) throws Exception{
    //   StoreCategories = new String[384]; 
    //   StoreSeller = new String[10000];
    //   StoreID = new String[384];
    	setTitle(title); // set the title
       setSize(height, width); // set the height and width
       setLocation(400, 200); // set the location
       
       setDefaultCloseOperation(EXIT_ON_CLOSE);  // set its close operation
    //   FlowLayout newFlow = new FlowLayout();
       this.setLayout(new FlowLayout());  //arranges the GUI components in left-to-right 
       									//and flows into next row in a top-to-bottom manner.
	   Container myContentPane = this.getContentPane();  // Declare Container myContentPane and initialize it 
	   
	   Label lblInput; //Declare an lable instance called lblInput
	   lblInput = new Label("Website"); //Construct by invoking a constructor
	   myContentPane.add(lblInput, BorderLayout.NORTH);  // Frame adds lable
	   TxtWebsite = new TextField("www.shopgoodwill");  // Construct by invoking a constructor
	   myContentPane.add(TxtWebsite,BorderLayout.NORTH );  // add txtWebsite to ContentPane
	   TxtWebsite.setEditable(false);  // set to read-only

	   btngetCategories = new Button("Get all Categories");  // initialize the button to be called "Get all Categories"
	   myContentPane.add(btngetCategories);    // add this button btngetCategories to myContentPane
	   btngetCategories.addActionListener(new ActionListener(){  // call ActionListener
		   public void actionPerformed(ActionEvent e){  // actionPerformed
			   JOptionPane.showMessageDialog(null,"Got Categories"); // open a message dialog
		
			
		   }
	   });	  
	   btngetSeller = new Button("get Seller");  //  initialize the button to be called ""get Seller""
	   myContentPane.add(btngetSeller);// add this button btngetSeller to myContentPane
	   btngetSeller.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   JOptionPane.showMessageDialog(null,"Got Sellers"); // open a message dialog
			   }
		   });	  
	  
	   CatBox= new JComboBox(Phase3.StoreCategories); // initialize JComboBox CatBox
	   myContentPane.add(CatBox); // add CatBox into myContentPane
	   CatBox.setSize(20, 20);  // set the size
	   CatBox.setEditable(true); // makes it editable
	   myContentPane.add(CatBox); // add CatBox into myContentPane
	   CatBox.addActionListener(new ActionListener(){ // call ActionListener
		   public void actionPerformed(ActionEvent e){  // call actionPerformed
			   JComboBox temp = (JComboBox)e.getSource(); // set temp is e.getSource
			   searchCat = (String)temp.getSelectedItem(); // searchCat is the category which the user selected
			   System.out.println(searchCat); // print out to the console
			   
		   }
	   });
	   
	   SellerBox = new JComboBox(Phase3.StoreSeller);  // initialize JComboBox  SellerBox
	   SellerBox.setSize(20,20);// set the size
	   SellerBox.setEditable(true);// makes it editable
	   myContentPane.add(SellerBox);// add CatBox into myContentPane
	   
	   btnSearch = new Button("Search Product"); // Declare and allocate a Button
	   myContentPane.add(btnSearch);  // Frame adds btnSearch
	   // Construct an anonymous instance of an anonymous inner class.
	   // The source Button adds the anonymous instance as ActionEvent listener
	   btnSearch.addActionListener(new ActionListener(){ // call ActionListener
		   public void actionPerformed(ActionEvent e){// call actionPerformed
			 for(String key : Phase3.hashTable.keySet())  // used to check every element in the hashtable, if it found it, will make the complete address to open the website
			   if (Phase3.hashTable.get(key)== searchCat){ // if it find the category in hashtable equals to which the user selected from GUI
				   searchCount++;  // count how many time user click the button "Search"
				   Idnumber = key;  // initialize int IDnumber equals to key
				   try{// try
				   Phase3.url = Phase3.website+Idnumber;  // makes the complete address
				   java.awt.Desktop.getDesktop().browse(java.net.URI.create(Phase3.url));  // open the connection of the website
				   }
				   catch (java.io.IOException t){
					   System.out.println(t.getMessage());  // if it catches, print out message
				   }
				   System.out.println(key); // print out the key in hashtable to the console
				   try {
					Phase3.allURLExtract(Phase3.url);  //call method allURLExtract in Phase3 to extract all url
					try {
						Phase3.ExecuteHTML(); // call method ExecuteHTML in Phase3 to extract HTML 
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}// call method allURLExtract from class Phase3
					Phase3.ExecuteImage();
				   } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }
				   
				   break; // out of for loop
			   }
		   }
	   });
	   setTitle("Search Engine"); // Frame sets title
	   myContentPane.setLayout(new GridLayout(4,1));    // Frame shows
       this.pack();
       setVisible(true); // set myContentPane is visible
   }// method GUI
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
  }
    
   
