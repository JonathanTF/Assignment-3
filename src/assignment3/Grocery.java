package assignment3;

public class Grocery extends Item {

	boolean perishable;
	double shipping = 0;
	double tax = 0;
	public Grocery(String new_name, float new_price, int new_quantity, int new_weight, String op1){
		super(new_name, new_price, new_quantity, new_weight);
		op1  = op1.toUpperCase();
		if (op1.equals("P")){
			perishable = true;
		}
		else{
			perishable = false;
		}
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping = ((20*weight)*quantity);// standard shipping cost
		if(perishable == true){
			shipping = shipping + ((shipping*20)/100);// if it is perishable there is a 20% additional charge for premium shipping
		}
		final_price = Math.round(((price*quantity) +  shipping)*100);
		final_price = final_price / 100;
		return  final_price;
	}
}
