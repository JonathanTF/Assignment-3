package assignment3;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	public Clothing(String new_name, float new_price, int new_quantity, int new_weight){
		name = new_name;
		price = new_price;
		quantity = new_quantity;
		weight = new_weight;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = price + ((20*weight)*quantity);// standard shipping cost
		int temp_x = (int)(final_price*100);// ensure that it is only two decimal places
		final_price = (float) (temp_x / 100);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		float total_price = calculatePrice();
		System.out.println("Clothing Item: "+ name + " ("+quantity+" ct.) "+weight+"lbs; $"+total_price);
		//Print all applicable attributes of this sub-class
	}
	

}
