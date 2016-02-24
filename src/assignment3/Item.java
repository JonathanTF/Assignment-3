package assignment3;

public class Item implements Comparable<Item>
{
//Declare variables for this class. Think about its type: public, protected or private?
	String name;
	float price;
	protected int quantity;
	protected int weight;

	public Item(String new_name, float new_price, int new_quantity, int new_weight){
		name = new_name;
		price = new_price;
		quantity = new_quantity;
		weight = new_weight;
	}
	
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
	
	@Override
	public int compareTo(Item temp){
		return name.compareTo(temp.name);
	}

}
