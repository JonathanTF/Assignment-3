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
		return final_price;
	}
	

	float printItemAttributes () //Print all applicable attributes of this sub-class
	{
		String item_type = this.getClass().getSimpleName();//Grocery, Electronics, Clothing
		float total_price = calculatePrice();// each subclass has it's own unique calculatePrice();
		System.out.println(item_type + " Item: "+ name + " Quantity: " + quantity + " ct." + 
		" Final price (after tax and shipping): $" + total_price);
		return total_price;
		
	}
	
	@Override
	public int compareTo(Item temp){
		return name.compareTo(temp.name);
	}

}
