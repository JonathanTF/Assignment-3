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
		ArrayList<String> input_transactions = getFromFile(file_name);// returns an array of the transactions in an array of Strings
		Iterator<String> list = input_transactions.iterator();// use an iterator to run through the list of transactions
		ArrayList<Item> shoppingCart = new ArrayList<Item>();// the shopping cart
		String delims = "[ ]+";	
		while (list.hasNext()){// while there is a next element
			/* A tester block that just prints out the shopping cart each iteration **doesn't show that last transaction
			Iterator<Item> testing = shoppingCart.iterator();
			while(testing.hasNext()){
				Item tester = testing.next();
				tester.printItemAttributes();
			}
			*/
			String str = list.next();// this will bring up the next transaction
			String[] tokens = str.split(delims);// each transaction is split into 'tokens' array
			boolean check = errorCheck(tokens);
			if(check){
				switch (tokens[0]) {// case argument for the 5 types of transactions
					case "insert": insert(tokens, shoppingCart);
								   break;
					case "search": search(tokens, shoppingCart);
								   break;
					case "delete": delete(tokens, shoppingCart);
								   break;
					case "update": update(tokens, shoppingCart);
								   break;
					case "print": print(tokens, shoppingCart);
								  break;
				}
			}
		}
		
		  
		/*//Stub for arraylist.
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
		*/

	  
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
	 
	 /******************************************************************************
		* Method Name: isInteger                                                   *
		* Purpose: checks if a string is an integer
		******************************************************************************/
	 
	 public static boolean isInteger(String str) {
		    if (str == null) {
		        return false;
		    }
		    int length = str.length();
		    if (length == 0) {
		        return false;
		    }
		    for (int i = 0; i < length; i++) {
		        char c = str.charAt(i);
		        if (c < '0' || c > '9') {
		            return false;
		        }
		    }
		    return true;
		}
	 
	 /******************************************************************************
		* Method Name: errorCheck                                                    *
		* Purpose: checks a transaction to make sure it is valid
		******************************************************************************/
	 
	 public static boolean errorCheck(String[] tokens){
		 tokens[0] = tokens[0].toLowerCase();
		 boolean check = false;
		 switch (tokens[0]) {// case argument for the 5 types of transactions
			case "insert": 
				if((tokens[1] == "clothing") && (tokens.length == 6) && (tokens[3].matches("[-+]?\\d*\\.?\\d+")) && 
					 (isInteger(tokens[4])) && (isInteger(tokens[5]))){	
				 check = true; 
				}
				else if((tokens[1] == "groceries") && (tokens.length == 7)&& (tokens[3].matches("[-+]?\\d*\\.?\\d+")) && 
					 (isInteger(tokens[4])) && (isInteger(tokens[5]))){
					tokens[6] = tokens[6].toUpperCase();
					if (tokens[6] == "P" || tokens[6] == "NP"){
						check = true;
					}
				}
				else if((tokens[1] == "electronics") && (tokens.length == 8) && (tokens[3].matches("[-+]?\\d*\\.?\\d+")) && 
					 (isInteger(tokens[4])) && (isInteger(tokens[5]))){
					tokens[6] = tokens[6].toUpperCase();
					if (tokens[6] == "F" || tokens[6] == "NF"){
						tokens[7] = tokens[7].toUpperCase();
						switch(tokens[7]){
							
						}
					}
				}
			case "search": 
				if (tokens.length == 2){
					 check = true;
				}
				break;
			case "delete": 
				if (tokens.length == 2){
					 check = true;
				}
				break;
			case "update": 
				if((tokens.length == 3) && (isInteger(tokens[2]))){
					 check = true;	
				 }
				break;
			case "print": 
				 if(tokens.length == 1){
					 check = true;
				 }
				 break;
		 }
		 return check; 
	 }
	 
	 /******************************************************************************
	 * 	Method Name: insert														   *
	 *	 Purpose: parses through tokens array for the insert transaction           *
	 * @param input_transactions                                                   *
	 ******************************************************************************/
	 
	 public static void insert(String[] tokens, ArrayList<Item> shopping_cart){
		 Float priceFloat = Float.parseFloat(tokens[3]);
		 int quantityInt = Integer.parseInt(tokens[4]);
		 int weightInt = Integer.parseInt(tokens[5]);
		 if(tokens[1].equals("groceries") == true){
			Item temp = new Grocery(tokens[2], priceFloat, quantityInt, weightInt, tokens[6]);
			shopping_cart.add(temp);
			return;
		 }else if(tokens[1].equals("electronics") == true){
			Item temp = new Electronics(tokens[2], priceFloat, quantityInt, weightInt, tokens[6], tokens[7]);
			shopping_cart.add(temp);
			return;
		 }else if(tokens[1].equals("clothing") == true){
			Item temp = new Clothing(tokens[2], priceFloat, quantityInt, weightInt);
			shopping_cart.add(temp);
			return;
		 } 
	 }
	 
	 
	 /******************************************************************************
	 * 	Method Name: print														   *
	 *	 Purpose: print the contents of the shopping cart by name		           *
	 * @param shopping_cart                                                        *
	 ******************************************************************************/
	private static void print(String[] tokens, ArrayList<Item> shopping_cart) {
		return;
	}
	
	
	 /******************************************************************************
	 * 	Method Name: update														   *
	 *	 Purpose: updates the quantity field for the first occurrence              *
	 *   of a matching name                                                        *
	 * @param shopping_cart                                                        *
	 ******************************************************************************/
	private static void update(String[] tokens, ArrayList<Item> shopping_cart) {
		int index = 0;
		Iterator<Item> p = shopping_cart.iterator();
		while (p.hasNext()){
			Item temp = p.next();
			if(temp.name.equals(tokens[1])==true){
				temp.quantity = Integer.parseInt(tokens[2]);
				shopping_cart.set(index, temp);
				System.out.println("Update successful. There are now " + tokens[2]+" "+tokens[1]+"(s) in the Shopping Cart" );	
				return;
			}
			index+=1;
		}
		System.out.println("Update unsuccessful. Item " + tokens[1] +" not found Shopping Cart" );	
	}
	
	
	 /******************************************************************************
	 * 	Method Name: delete                                                        *
	 *	 Purpose: would delete all entries the same input name, and output to the  *
	 *   screen how many were deleted                                              *
	 ******************************************************************************/
	private static void delete(String[] tokens, ArrayList<Item> shopping_cart) {
		int item_tracker = 0;
		Iterator<Item> p = shopping_cart.iterator();
		while (p.hasNext()){
			Item temp = p.next();
			if(temp.name.equals(tokens[1])==true){
				item_tracker = (item_tracker + temp.quantity);
				p.remove();
			}
		}
		System.out.println(item_tracker + " "+ tokens[1]+"(s) successfully removed from Shopping Cart");
	}
	
	
	 /******************************************************************************
	 * 	Method Name: search														   *
	 *	 Purpose: searches for all objects with name field as <name> and then      *
     *   outputs the number of objects found to the screen                         *
	 * @param shopping_cart                                                        *
	 ******************************************************************************/
	private static void search(String[] tokens, ArrayList<Item> shopping_cart) {
		return;
	}
 
}