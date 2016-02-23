package assignment3;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	boolean fragile; 
	String state; 
	public Electronics (String new_name, float new_price, int new_quantity, int new_weight, 
			String op1, String new_state){
		super(new_name, new_price, new_quantity, new_weight);
		op1 = op1.toUpperCase();
		state = new_state;
		state = state.toUpperCase();
		if (op1 == "F"){
			fragile = true;
		}
		else{
			fragile = false;
		}
	}
	//Implement calculate price/print methods as necessary
	
	float calculatePrice () 
	{	
		float extra = 0;
		if (checkTax(state)){
			extra = (float) (price * 0.1); 
		}
		if (fragile == true){
			extra = (float) (extra + (1.2*(20*(weight)*quantity)));
		}
		else{
			extra = extra + (20*(weight)*quantity);
		}
		float final_price = extra + (price*quantity);
		int temp_x = (int)(final_price*100);// ensure that it is only two decimal places
		final_price = (float) (temp_x / 100);
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
	
	// prints attributes of this class
	void printItemAttributes () 
	{
		float total_price = calculatePrice();
		if(fragile == true){
			System.out.println("(Fragile) Electronics Item: "+ name + " ("+quantity+" ct.) "+weight+"lbs; $"+total_price);
		}else{
			System.out.println("(Non-Fragile) Electronics Item: "+ name + " ("+quantity+" ct.) "+weight+"lbs; $"+total_price);
		}
		//Print all applicable attributes of this class
	}
	
	

}
