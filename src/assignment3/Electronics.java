package assignment3;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	boolean fragile; 
	String state; 
	public Electronics (String new_name, float new_price, int new_quantity, int new_weight, 
			String op1, String new_state){
		op1 = op1.toUpperCase();
		state = state.toUpperCase();
		name = new_name;
		price = new_price;
		quantity = new_quantity;
		weight = new_weight;
		if (op1 == "F"){
			fragile = true;
		}
		else{
			fragile = false;
		}
		state = new_state;
	}
	//Implement calculate price/print methods as necessary
	
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

}
