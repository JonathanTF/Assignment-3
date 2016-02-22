package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class A3Driver
	{

	
	  public static void main(String[] args) 
	  {
		/*Open file; file name specified in args (command line)*/
		String file_name = args[0];
		int index = 1; //keep track of what transaction we are on
		ArrayList<String> input_transactions = getFromFile(file_name);// returns an array of the transactions in an array of Strings
		String delims = "[ ]+";	
		String str = input_transactions.get(index-1);
		String[] tokens = str.split(delims);
		  
		//Parse input, take appropriate actions.
		  
		//Stub for arraylist.
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}	
	

	  
	}
	  
	  
	  
	/******************************************************************************
	* Method Name: getFromFile                                                    *
	* Purpose: Reads through the input file and converts each line into an array  *							  
	*          of Strings. The output of the method as an ArrayList 'input list'  *
	*          in which each element is a String. For assignment 3 each element   *
	*          is a transaction.                                                  *
	******************************************************************************/
	 public static ArrayList<String> getFromFile (String file){
	 ArrayList<String> input_array = new ArrayList<String>();
	 FileReader freader;
	 	try {
	 		freader = new FileReader(file);
	 		BufferedReader reader = new BufferedReader(freader);
	 		String s = reader.readLine();
	 		while(s != null){
	 			input_array.add(s);
				s = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				System.out.println("Cannot find file: " + file);
				System.exit(-1);
			} catch (IOException e) {
				System.out.println("I/O Excepetion error");
				System.exit(-1);
			} finally{
				
			}
		return input_array;
	 }   
	 
}