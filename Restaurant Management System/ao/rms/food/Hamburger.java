package ao.rms.food;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;

public class Hamburger extends Food {
	
	private ArrayList<ArrayList<String>> ingredients;
	private String ingredientList = "";
	private static ArrayList<String> cheeseTypes = new ArrayList<String>(Arrays.asList("American Cheese","Swiss Cheese", "Pepperjack Cheese", "Vegan 'Cheese'"));
	private static ArrayList<String> veggieTypes = new ArrayList<String>(Arrays.asList("Vine-ripened Tomato", "Tomato", "Lettuce", "Pickles", "Onion", "Red Onion",
			"Diced Onion", "Crispy Fried Onion","Caramelized Onions", "Fresh Guacamole", "Portobello Mushrooms", "Jalapenos", "Fresh Salsa"));
	private static ArrayList<String> eggAndBaconTypes = new ArrayList<String>(Arrays.asList("Fried Egg", "Runny Egg", "Applewood Smoked Bacon"));
	private static ArrayList<String> sauceTypes = new ArrayList<String>(Arrays.asList("Ketchup", "Mayo", "Vegan Mayo", "Sweet 'n Smoky BBQ Sauce", "Garlic Mayo", "Chipotlo Mayo", "Sweet 'n Tangy Frisco Sauce"));
	private static ArrayList<ArrayList<String>> allIngredients = new ArrayList<ArrayList<String>>(Arrays.asList(cheeseTypes, veggieTypes, eggAndBaconTypes, sauceTypes));
	
	public static String[] ingredientHeaders = {"Choose Cheese", "Choose Veggies", "Choose Egg/Bacon", "Choose Sauces"};

	
	public Hamburger(String name, double price, ArrayList<ArrayList<String>> ingredients) {
		super(name, price);
		super.setFoodType("Hamburger");
		this.ingredients = ingredients;
		updateIngredientList();
	}

	public static ArrayList<ArrayList<String>> getAllIngredients(){
		return allIngredients;
	}
	
	public ArrayList<ArrayList<String>> getIngredients() {
		return this.ingredients;
	}
	
	public void setIngredients(ArrayList<ArrayList<String>> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void updateIngredientList() {
		ingredientList = "";
		for(int i = 0; i < ingredients.size(); i++) {
			for(int j = 0; j < ingredients.get(i).size(); j++) {
				if(i == ingredients.size() - 1 && j == ingredients.get(i).size() - 1)
					ingredientList += ingredients.get(i).get(j) + "";
				else
					ingredientList += ingredients.get(i).get(j)+ ", ";
			}
		}
	}
	
	public String getIngredientList() {
		return ingredientList;
	}
	
}
