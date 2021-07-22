package ao.rms.food;

public class Beverage extends Food{
	
	public Beverage(String name, double price) {
		super(name, price);
		super.setFoodType("Beverage");
	}
	
}
