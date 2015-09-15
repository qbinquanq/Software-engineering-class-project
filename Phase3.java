
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/*
 * Arthor Binquan Wang
 * Project Phase3
 * In Computer Science 370
 * This project is used to get all source from the web and process its information and store it in the local computer
 */
	public class Phase3 {
	  public static Hashtable<String, String> hashTable; // used to store category and its ID
	  public static Hashtable<Integer, String> hashTableLocation; // used to store the location
	  public static int integer;  
	  public static  String [] allCategory; // stored all categories
	  public static String website; // will be initilized to "http://www.shopgoodwill.com/listings/listbycat.asp?catid="
	  public static String url =null; // used to store the address of variabe website and the id which is gotten from hashtable to make complete address.
	  public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36";
	  public static PrintStream outputFile; // used to store all hyperlinks extracted from a URL
	  //	  File outputImageFile = null;
	  public static PrintStream htmlFile;  // used to store all filtered html urls
	  public static PrintStream imageFile; // used to store all filtered image urls
	  public static PrintStream categoriesAll; // used to store all not filtered categories
	  public static PrintStream categoriesID; // used to store all not filtered categories ID
	  public static Hashtable <String, String> hashDataLocation; // used to store the location where searched category is
	  
	  public static String[] StoreCategories; // array used to store filtered categories
	  public static int categoriesNumber = 0; // used to count assign the position to array when store categories
	  public static String[] StoreID; // array used to store ID number
	  public static int StoreIDCount = 0; // used to count assign the position to array when store ID number
	  public static String[] StoreSeller;  // array used to store filtered seller
	   public static int sellerNumber = 0; // used to assign position to store the seller
	   
	  
	  
	// This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
	// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

	  public static BufferedReader read(String url) throws Exception {
			return new BufferedReader(
				new InputStreamReader(
					new URL(url).openStream()));
		} // read
	// This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
	// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

	  public static BufferedImage image= null;
	
	  // This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
	// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

	  public static void fetchImageFromURL (URL url){
	    	try{
	    		image = ImageIO.read(url);
	    		
	    	}catch(IOException e){
	    		e.getMessage();
	    	}
	    }
	// This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
	// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

	  public static InputStream getURLInputStream(String sURL) throws Exception {
		    URLConnection oConnection = (new URL(sURL)).openConnection();  // create connection
		    oConnection.setRequestProperty("User-Agent", USER_AGENT); // set connection's property
		    return oConnection.getInputStream();
		    }

		    public static BufferedReader read2(String url) throws Exception {
		        //InputStream content = (InputStream)uc.getInputStream();
//		    BufferedReader in = new BufferedReader (new InputStreamReader
		//(content));
		        InputStream content = (InputStream)getURLInputStream(url);
		        return new BufferedReader (new InputStreamReader(content));
		    } // read
		 // This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
		 // Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

		    public static BufferedReader read3(String url) throws Exception {
		            return new BufferedReader(
		                    new InputStreamReader(
		                            new URL(url).openStream()));
		    } // read
		// read file and process the image url and store the image information in the new created file    
	  public static void ExecuteImage() throws Exception, MalformedURLException, IOException{
		  int ImageFolderNumber = 0;    // set ImageFolderNumber = 0, this will be the name of folder
		  String getURL;     // declare a string getURL
		  File inputImageFile = new File("image.txt");   // declare and initialize the image file
 
		  Pattern pattern = Pattern.compile(".jpg");   // make the pattern
		  
		  String path = "C:\\newPhase3Folder\\";   // this is initial path of the folder
  		  File newFileFolder = new File(path);   //used to check the file is existed or not
  		  if(!newFileFolder.exists())   // if the file is not existed yet, create it
  			newFileFolder.mkdirs();
  	      try{ // try to get the URL from the input file
			Scanner scanInput = new Scanner(new FileReader(inputImageFile));   // scan file "image.txt"
			while(scanInput.hasNextLine()){   // keep reading the input file until it is empty
	           
				getURL = scanInput.nextLine();   // string getURL initialized to the line which is read from file
				Matcher matcher = pattern.matcher(getURL);  // make the Matcher to the URL read from file
				if(matcher.find()){  // if it finds
					URL url = new URL(getURL);   // set string getURL to be URL url
					
					String searchcount = Integer.toString(GUI.searchCount);   // string searchcount always equals to how many times the user click the button
					String newSearchPath = path+"\\"+"Search"+searchcount;   // this will set a new path
					newFileFolder = new File(newSearchPath);   // check the file is existed or not
		    		if(!newFileFolder.exists())         // if not existed yet, just create this file
		    			newFileFolder.mkdirs();
		    		
		    		ImageFolderNumber++;  // this integer will be the name of the folder
					String FolderNum = Integer.toString(ImageFolderNumber);    // convert the integer into String
					String newPath = newSearchPath+"\\"+FolderNum;    // set the new path
					newFileFolder = new File(newPath);    // check the file is existed or not
		    		if(!newFileFolder.exists())          // if not existed yet, just create this file
		    			newFileFolder.mkdirs();
		    		
		    		File htmFile = new File(newPath+"\\"+FolderNum+".jpg");   // set the file's path
	    					
	    			fetchImageFromURL(url);
	    			ImageIO.write(image, "jpg", htmFile);
	    			//	write information to the file
				}
				
			}
			scanInput.close();  // close scanner
			}catch(FileNotFoundException ioe){
				ioe.printStackTrace();
			} // catch
	  }
	  
	  // read the file and process html URL. it will read the information and store its information in its own file
      public static void ExecuteHTML()throws Exception, MalformedURLException, IOException{
    	//    int jpgnumber = 0;
    		int HTMLFolderNumber = 0;   // this will be the name for the html folder
    		String getURL; // declare a variable to catch the URL from filtered html.txt and image.txt
    	
    		File inputHTMLFile = new File("html.txt");     // get the file which is the first parameter in the command line
    		PrintStream createdNewFile;       // this is used to print into a file
    		Pattern pattern=Pattern.compile(".html");    //get the pattern which is used to correct the website's name
    	
    		String path = "C:\\newPhase3Folder\\";   // set the path for html file
    		File newFileFolder = new File(path);  // check the file is existed or not
    		if(!newFileFolder.exists()) 		// if it is not existed yet, create it
    			newFileFolder.mkdirs();
    		try{ // try to get the URL from the input file
    			Scanner scanInput = new Scanner(new FileReader(inputHTMLFile));     // set scanner to scan the file 
    			while(scanInput.hasNextLine()){   // keep reading the input file until it is empty
    	           
    				getURL = scanInput.nextLine();  // String getURL will be the line which is read from html file
    				Matcher matcher = pattern.matcher(getURL);      // set matcher
    		//		Matcher matcher1= pattern1.matcher(getURL);
    			//	Matcher matcher2= pattern2.matcher(webpage);
    		//		Matcher matcher3= pattern3.matcher(webpage);
    				if(matcher.find()){    // if the URL's ending name is .html or .htm do the if statement
    			
    					BufferedReader reader = read2(getURL);  // put information into buffer
    					String line = reader.readLine();  // set string line is the line which is read from buffer
    					
    					String searchcount = Integer.toString(GUI.searchCount);   // set string searchcount is equals to how many times the user click the button to search
    					String newSearchPath = path+"\\"+"Search"+searchcount;   // set the path
    					newFileFolder = new File(newSearchPath);    // check the file is existed or not
    		    		if(!newFileFolder.exists())		// if it is not existed yet, create it
    		    			newFileFolder.mkdirs();
    					
    					HTMLFolderNumber++;   // it is the folder's name
    					String FolderNum = Integer.toString(HTMLFolderNumber);    // convert the integer into String
    					String newPath = newSearchPath+"\\"+FolderNum;   // set the path
    					newFileFolder = new File(newPath);// check the file is existed or not
    		    		if(!newFileFolder.exists())// if it is not existed yet, create it
    		    			newFileFolder.mkdirs();
    		    		
    				    File htmFile = new File(newPath+"\\"+FolderNum+".txt");  // set the path of the file
    				    
    				    createdNewFile = new PrintStream(new FileOutputStream(htmFile));   // output to the file
    					createdNewFile.println("Contents of the following URL: "+getURL+"\n");    // print info
    					int totalLine =0;  // set total line is 0, used to track total lines in the file
    					while (line != null) {   // if the line is not enpty
    						totalLine++;  // every time line is not 0, totalline increment by 1
    						
    						createdNewFile.println(line);  // output the line to the file
    						
    						line = reader.readLine();  // set line is next new line
    					} // end while
    				//	outputFile.println("The URL is: "+ webpage+ ", " + "stores in "+htmFile+ ", and The total lines are "+ totalLine);
    					createdNewFile.close();  // close the output file
    				}// end if.
    			}
    				scanInput.close();      // close the output file
    		}// end try
    		
    		catch(FileNotFoundException ioe){
    				ioe.printStackTrace();
    			} // catch
    } // Execute
      //this is hashtable used to store the id and categories
      public static void hash(){
    	  for(int count = 0; count<StoreCategories.length && count<StoreID.length; count++) // define the length of array StoreCategories and array Store.ID
    		  																				
	        	Phase3.hashTable.put(StoreID[count], StoreCategories[count]); // put storeID and storecategories into hashtable
    	  for (String key : Phase3.hashTable.keySet()) {     // this is used to print out all data in the hashtable
    		    System.out.println(key + ":" + Phase3.hashTable.get(key)+ ":");
    		}
      }
      // return categories
      public static PrintStream getcategoriesAll(){
    	  return categoriesAll;
      }
	// the main initializes objects and call GUI class.
	  public static void main (String args[]) throws Exception {
	    // Start with ten, expand by ten when limit reached
	    hashTable = new Hashtable<String, String>();  // initializes hashtable
	    website = "http://www.shopgoodwill.com/listings/listbycat.asp?catid=";  // set string website
	    
	    categoriesAll = new PrintStream(new FileOutputStream("AllCategories.txt"));  // initialized output variable categoriesAll
	    categoriesID = new PrintStream(new FileOutputStream("AllCategoryID.txt"));  // initialized output variable categoriesID
	    
	    StoreCategories = new String[384]; //initialize string StoreCategories
	    StoreSeller = new String[10000];   //initialize string StoreSeller
	    StoreID = new String[384];  // initialize String StoreID
	    
	    
	    String searchWebPage = "http://www.shopgoodwill.com/search/";  // this string is used to find all categories and ID
	    URL searchPage = new URL(searchWebPage);  // Initialize URL searchPage
    	Reader reader = new InputStreamReader((InputStream)searchPage.getContent());  // call method Reader
    	
        
        new ParserDelegator().parse(reader, new LinkPage2(), false);  // call class LinkPage2
        try { // try
			Scanner scanCategoriesFile = new Scanner(new FileReader("AllCategories.txt")); // scan file "AllCategories.txt"
			String line = scanCategoriesFile.nextLine(); // string line is the line read from file "AllCategories.txt"
			while(line != null){  // while loop, if line is not empty
				//System.out.println("sdsd");
				if(line.equals("All Categories")){  // if the line is "All Categories"
					while(!line.equals("Seller")) // if the line is not "Seller"
					{
						System.out.println("sdsdttt ->>>" + line);  // used to check the each line
						System.out.println(categoriesNumber);  // print out the categories number
						StoreCategories[categoriesNumber]= line;  //the array[categoriesNumber] is equals to line read from the line
						categoriesNumber++;  // the index of the array increment by 1
						line = scanCategoriesFile.nextLine();  // set the line is next line
					}
					break;  // out of while loop
				}
				
				line = scanCategoriesFile.nextLine();  // set the line is next line
			}
			scanCategoriesFile.close();  // close scanner
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		try{
			Scanner scanCategoriesFile = new Scanner(new FileReader("AllCategoryID.txt"));  // scan the file "AllCategoryID.txt"
			String line = scanCategoriesFile.nextLine(); // set the line is next line
				while(!line.equals("all")){ // while loop, if the line is not equals to "all"
					StoreID[StoreIDCount] = line;  // store the line to the array StoreID
					StoreIDCount++;  // this is index of array StoreID
					System.out.println(line);  // print out the line
					line = scanCategoriesFile.nextLine(); // set the line is next line
				}
		}
		catch(FileNotFoundException e2){
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Scanner scanCategoriesFile = new Scanner(new FileReader("AllCategories.txt")); // scan the file "AllCategories.txt"
			String line = scanCategoriesFile.nextLine();// set the line is next line
			while(!line.equals("All Sellers")) // while loop, if the line is not equals to "All Sellers"
			{
				line = scanCategoriesFile.nextLine(); // go to next line
			}
			while(!line.equals("Status")){  // if the line is not equals to "Status"
				StoreSeller[sellerNumber]= line;  // put string into array StoreSeller
				sellerNumber++;      // used to increment the index of array StoreSeller
				line = scanCategoriesFile.nextLine();    // go to next line
				
			}
			scanCategoriesFile.close();   // close scanner
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		
		hash(); // call hash method
        GUI GUIGraph = new GUI ("Search", 200, 100);  // call GUI class and the variable is GUIGraph
	   
	    
	  }
	// this method is used to extract all URL from a URL 
	public static void allURLExtract(String url) throws Exception{
	    if(url != null){  // if url is not equals to empty
	    	outputFile= new PrintStream(new FileOutputStream("File.txt"));  // initialize the output file, the variable is outputFile
	    	htmlFile= new PrintStream(new FileOutputStream("html.txt"));// initialize the output file, the variable is htmlFile
	    	imageFile =  new PrintStream(new FileOutputStream("image.txt"));// initialize the output file, the variable is imageFile
	    	URL htmlURL = new URL(url);  // URL htmlURL equals to url
	    	Reader reader = new InputStreamReader((InputStream)htmlURL.getContent());  // call Reader method
	    	System.out.println("<HTML><HEAD><TITLE>Links for " + url + "</TITLE>");  // print out 
	        System.out.println("<BASE HREF=\"" + url + "\"></HEAD>");// print out
	        System.out.println("<BODY>");// print out
	        
	        new ParserDelegator().parse(reader, new LinkPage(), false); // call class LinkPage
	        
	        System.out.println("</BODY></HTML>"); // print out
	        System.out.println("</BODY></HTML>");  // print out
	        File inputFile = new File("File.txt");  // initializes File inputFile
	        Pattern pattern = Pattern.compile(".html");  // set pattern
	        Pattern pattern1 = Pattern.compile("http://www.shopgoodwill.com.*"); // sometimes html urls doesn't start with 'http://www.shopgoodwill.com', needs to find those urls
	        Pattern pattern2 = Pattern.compile(".jpg"); // set pattern
	        Pattern pattern3 = Pattern.compile("//www\\.shopgoodwill\\.com/images/goodwillbooksfooter\\.jpg"); // set pattern
	        Pattern pattern4 = Pattern.compile("http://images.shopgoodwill.com.*"); // set pattern
	        try{
	        Scanner scanInput = new Scanner(new FileReader(inputFile)); // scan file
	        while(scanInput.hasNextLine()){  // if the file has next line
	        	String webpage = scanInput.nextLine();  // read the next line of the file
	        	Matcher matcher = pattern.matcher(webpage); // set matcher
	        	Matcher matcher2 = pattern2.matcher(webpage);// set matcher2
	        	Matcher matcher3 = pattern3.matcher(webpage);// set matcher3
	        	Matcher matcher4 = pattern4.matcher(webpage);// set matcher4
	        	if(matcher.find()){  // if matcher matches the pattern
	        		Matcher matcher1 = pattern1.matcher(webpage);  // set matcher1
	        		
	        		if(matcher1.find()){ // if matcher1 matches the pattern
	        		System.out.println(webpage); // print out
	        		htmlFile.println(webpage);  // print out to the htmlFile
	        		}
	        		else  {
	        			System.out.println("http://www.shopgoodwill.com"+webpage);  // print out
	        			htmlFile.println("http://www.shopgoodwill.com"+webpage);    // print out to the file htmlFile
	        		}
	        	}
	        	if(matcher2.find()){  // if matcher2 matches
	        		if(matcher3.find()){  // if matcher3 matches
	        			break;  // break
	        			
	        		}
	        		else if(matcher4.find()){  // matcher4 matches
	        			System.out.println(webpage);  //print out 
	        			imageFile.println(webpage);   // print out to the file imageFile
	        		}
	        	}
	        }
	        outputFile.close();     // close the input file
	    	scanInput.close();      // close the output file
	        }catch(FileNotFoundException ioe){
	    		ioe.printStackTrace();
	    	}//catch
	        }
	      }
	}  
	
	// class LinkPage2 used to get out all ID and text
	class LinkPage2 extends HTMLEditorKit.ParserCallback{	
		// this is used to get all ID
  	  public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
  	//if tag is option
					if (t == HTML.Tag.OPTION) {
			    	  // store the ID into the array categoriesID
						Phase3.categoriesID.println(a.getAttribute(HTML.Attribute.VALUE));
			    	    	//print out to the console
			    	    	System.out.println(a.toString());
			    	    	}
			
			} // ends of handleStartTag
  	// used to print out all text in the URL
  	  public void handleText(char[] data, int pos) 
  	  {
  		  // print out the text to the console
				System.out.println(data);
				// print out the text to the array
				Phase3.categoriesAll.println(data);
  	    }
    }
	// class LinkPage is used to get all html URL and Image URL
	class LinkPage extends HTMLEditorKit.ParserCallback {
		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		//if the tag is A
			if (t == HTML.Tag.A) {
		    //	print out to outputFile
		    	Phase3.outputFile.println(a.getAttribute(HTML.Attribute.HREF));
		    }
		}
		public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
			//if the tag is IMG 
			if (t == HTML.Tag.IMG){
				// print out the console
				  System.out.println(a.getAttribute(HTML.Attribute.SRC));
				  // print out to the file
			 		Phase3.outputFile.println(a.getAttribute(HTML.Attribute.SRC));
			 		}
			  
		  }
	}
	
