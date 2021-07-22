package ao.rms.food;

public abstract class Food {

	private String name;
	private double price;
	private String foodType;
	
	public Food(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getFoodType() {
		return this.foodType;
	}
}
