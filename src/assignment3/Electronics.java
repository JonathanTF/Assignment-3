package assignment3;

public class Electronics extends Item 
{

	boolean fragile; 
	String state;
	double shipping = 0;
	double tax = 0;
	public Electronics (String new_name, float new_price, int new_quantity, int new_weight, 
			String op1, String new_state){
		super(new_name, new_price, new_quantity, new_weight);
		op1 = op1.toUpperCase();
		state = new_state;
		state = state.toUpperCase();
		if (op1.equals("F") == true){
			fragile = true;
		}
		else{
			fragile = false;
		}
	}

	
	float calculatePrice () 
	{	
		float extra = 0;
		if (checkTax(state)){// must check to see if it is in a tax - free state
			tax = 0.1*(price*quantity);
		}
		if (fragile == true){
			shipping = (1.2*(20*(weight)*quantity));// fragile electronics have premium shipping
		}
		else{
			shipping = (20*(weight)*quantity);
		}
		extra = (float) (shipping+tax);
		float final_price = extra + (price*quantity);
		final_price = Math.round(((price*quantity) +  extra)*100);
		final_price = final_price / 100;
		return final_price;
	}
	
	// the following method checks whether the state has sales tax
	boolean checkTax (String state){
		int count = 0;
		String[] no_tax = {"AK", "AZ", "NM", "TX", "VA"};
		while (count <= 4){
			if (state.equals(no_tax[count]) == true){
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
