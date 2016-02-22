package assignment3;

public class Grocery extends Item {
	//variables, constructor here
	boolean perishable;
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	public Grocery(String new_name, float new_price, int new_quantity, int new_weight, String op1){
		super(new_name, new_price, new_quantity, new_weight);
		op1  = op1.toUpperCase();
		if (op1 == "P"){
			perishable = true;
		}
		else{
			perishable = false;
		}
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_cost = ((20*weight)*quantity);// standard shipping cost
		if(perishable == true){
			shipping_cost = shipping_cost + ((shipping_cost*20)/100);// if it is perishable there is a 20% additional charge for premium shipping
		}
		final_price = (price*quantity) + shipping_cost;
		int temp_x = (int)(final_price*100);// ensure that it is only two decimal places
		final_price = (float) (temp_x / 100);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		float total_price = calculatePrice();
		if(perishable == true){
			System.out.println("(Perishable) Grocery Item: "+ name + " ("+quantity+" ct.) "+weight+"lbs; $"+total_price);
		}else{
			System.out.println("(Non-Perishable) Grocery Item: "+ name + " ("+quantity+" ct.) "+weight+"lbs; $"+total_price);
		}
		//Print all applicable attributes of this class
	}
	
}
