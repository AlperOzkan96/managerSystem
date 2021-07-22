package ao.rms.food;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<Food> food;
	
	public Menu(ArrayList<Food> food) {
		this.food = food;
	}
	
	public ArrayList<Food> getFood() {
		return this.food;
	}
	
	public void setFood(ArrayList<Food> food) {
		this.food = food;
	}
}
