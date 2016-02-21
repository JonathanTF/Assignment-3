package assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	String name;
	float price;
	int quantity;
	int weight;
// You will need a constructor (Why?). Create it here.
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		System.out.println(name + quantity + price + weight);
		//Print all applicable attributes of this class
	}

}
