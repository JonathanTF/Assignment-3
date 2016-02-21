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
		float extra = 0;
		if (checkTax(state)){
			extra = (float) (price * 0.1); 
		}
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	
	// checks whether the state has sales tax
	boolean checkTax (String state){
		int count = 0;
		String[] no_tax = {"AK", "AZ", "NM", "TX", "VA"};
		while (count <= 4){
			if (state == no_tax[count]){
				return false;
			}
			else{
				count ++;
			}
		}
		//if you get to this point, the state has sales tax
		return true;
	}
	

}
