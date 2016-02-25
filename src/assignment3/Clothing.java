package assignment3;

public class Clothing extends Item 
{
	double shipping = (20*weight)*quantity;//standard shipping and tax on all clothes
	double tax = 0.1*price;
	public Clothing(String new_name, float new_price, int new_quantity, int new_weight){
		super(new_name, new_price, new_quantity, new_weight);
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		float extra = (float) ((0.1*price*quantity) + ((20*weight)*quantity));
		final_price = Math.round(((price*quantity) +  extra)*100);
		final_price = final_price / 100;
		return final_price;
	}

	

}
