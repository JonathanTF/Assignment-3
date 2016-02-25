/* EE422C assignment3: Shopping Cart
 * by Jonathan Friesen and Royce Li
 */
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
		List<Item> shoppingCart = new ArrayList<Item>();// the shopping cart
		String delims = "[ ]+";	
		while (list.hasNext()){// while there is a next element
			String str = list.next();// this will bring up the next transaction
			if(str.equals("") == true){
				continue;
			}
			String[] tokens = str.split(delims);// each transaction is split into 'tokens' array
			boolean check = errorCheck(tokens);// main fail-safe
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
			else{
				System.out.println("Invalid transaction");
			}
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
	 
	 /*****************************************************************************
	*  Method Name: isInteger                                                     *
	*  Purpose: checks if a string is an integer                                  *
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
		* Method Name: errorCheck                                                  *
		* Purpose: checks a transaction to make sure it is valid                   *
		******************************************************************************/
	 
	 public static boolean errorCheck(String[] tokens){
		 tokens[0] = tokens[0].toLowerCase();
		 boolean check = false;
		 switch (tokens[0]) {// case argument for the 5 types of transactions
			case "insert": 
				if((tokens[1].equals("clothing")) && (tokens.length == 6) && (tokens[3].matches("[-+]?\\d*\\.?\\d+"))){	
				 check = true; 
				}
				else if((tokens[1].equals("groceries")) && (tokens.length == 7)&& (tokens[3].matches("[-+]?\\d*\\.?\\d+"))){//check if it is perishable
					tokens[6] = tokens[6].toUpperCase();
					if (tokens[6].equals("P") || tokens[6].equals("NP")){
						check = true;
					}
				}
				else if((tokens[1].equals("electronics")) && (tokens.length == 8) && (tokens[3].matches("[-+]?\\d*\\.?\\d+"))){// check if it is fragile and if it is a state
					tokens[6] = tokens[6].toUpperCase();
					if (tokens[6].equals("F") || tokens[6].equals("NF")){
						tokens[7] = tokens[7].toUpperCase();
						String states[] = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", 
								"HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", 
								"MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", 
								"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", 
								"SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
						for (int count = 0; count < states.length; count++){
							if (states[count].equals(tokens[7])){
								check = true; 
							}
						}
					}
				}
				// the following block deals with the fact that "6.00" is valid integer but "6." is not
				try{ 
					if(Float.parseFloat(tokens[3])<0 || Integer.parseInt(tokens[4])<0 || Integer.parseInt(tokens[5])<0){
						check = false;
						break;
					}
					if(tokens[4].endsWith(".") || tokens[5].endsWith(".")){
						check = false;
						break;
					}
					if (tokens[4].contains("e") || tokens[5].contains("e")){
						check = false;
						break;
					}
					double num_p =Double.parseDouble(tokens[3]);
					num_p = num_p*100;
					long left_part_p = (long) (num_p);
					double right_part_p = num_p - left_part_p;
					if(right_part_p != 0){
						check = false;
						break;
					}
					for(int k = 4;k<6;k++){
						double num = Double.parseDouble(tokens[k]);
						long left_part = (long) num;
						double right_part = num - left_part;
						if(right_part == 0){
							tokens[k] = String.valueOf(left_part);
						}else if(right_part != 0){
							check = false;
						}
					}
				} catch(java.lang.NumberFormatException e){
					check = false;
				} catch(java.lang.NullPointerException f){
					check = false;
				} catch(java.lang.ArrayIndexOutOfBoundsException g){
					check = false;
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
	 
	 public static void insert(String[] tokens, List<Item> shopping_cart){
		 Float priceFloat = Float.parseFloat(tokens[3]);
		 int quantityInt = Integer.parseInt(tokens[4]);
		 int weightInt = Integer.parseInt(tokens[5]);
		 if(tokens[1].equals("groceries") == true){
			Item temp = new Grocery(tokens[2], priceFloat, quantityInt, weightInt, tokens[6]);
			shopping_cart.add(temp);
		 }else if(tokens[1].equals("electronics") == true){
			Item temp = new Electronics(tokens[2], priceFloat, quantityInt, weightInt, tokens[6], tokens[7]);
			shopping_cart.add(temp);
		 }else if(tokens[1].equals("clothing") == true){
			Item temp = new Clothing(tokens[2], priceFloat, quantityInt, weightInt);
			shopping_cart.add(temp);
		 } 
		 Collections.sort(shopping_cart);
		 return;
	 }
	 
	 /******************************************************************************
	 * 	Method Name: print														   *
	 *	 Purpose: print the contents of the shopping cart by name		           *
	 * @param shopping_cart                                                        *
	 ******************************************************************************/
	private static void print(String[] tokens, List<Item> shopping_cart) {
		float total_charge = 0;
		System.out.println("Your Shopping Cart");
		Iterator<Item> list = shopping_cart.iterator();
		while(list.hasNext()){
			Item temp = list.next();
			total_charge = (total_charge + temp.printItemAttributes());
		}
		System.out.println("Shopping Cart Total Charge: $" + total_charge);
	}
	
	
	 /******************************************************************************
	 * 	Method Name: update														   *
	 *	 Purpose: updates the quantity field for the first occurrence              *
	 *   of a matching name                                                        *
	 * @param shopping_cart                                                        *
	 ******************************************************************************/
	private static void update(String[] tokens, List<Item> shopping_cart) {
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
	private static void delete(String[] tokens, List<Item> shopping_cart) {
		int item_tracker = 0;
		Iterator<Item> list = shopping_cart.iterator();
		while (list.hasNext()){
			Item temp = list.next();
			if(temp.name.equals(tokens[1])==true){
				item_tracker = (item_tracker + temp.quantity);
				list.remove();
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
	private static void search(String[] tokens, List<Item> shopping_cart) {
		int tracker = 0;
		Iterator<Item> list = shopping_cart.iterator();
		while(list.hasNext()){
			Item temp = list.next();
			if(temp.name.equals(tokens[1]) == true){
				tracker = tracker+temp.quantity;
			}
		}
		System.out.println("There are " + tracker +" "+tokens[1] + " object(s)");
		return;
	}
 
}