package ao.rms.restaurant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import ao.rms.employee.Cook;
import ao.rms.employee.Employee;
import ao.rms.employee.Manager;
import ao.rms.employee.Server;
import ao.rms.food.Beverage;
import ao.rms.food.Food;
import ao.rms.food.Hamburger;
import ao.rms.food.Menu;

public class test {

	public static void main(String[] args) {
		
		initRestaurant();
		
	}

	
	
	public static Restaurant initRestaurant() {
		
		ArrayList<Hamburger> burgers = new ArrayList<Hamburger>();
		

		burgers.add(new Hamburger("The Original Double Cheese", 3.99, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("American Cheese")),
							  new ArrayList<String>(Arrays.asList("Vine-ripened Tomato", "Lettuce","Pickles", "Onion")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Ketchup"))))));
	
		burgers.add(new Hamburger("The Organic Signature 6oz. Steakburger", 6.99, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("American Cheese")),
							  new ArrayList<String>(Arrays.asList("Vine-ripened Tomato", "Pickles", "Red onion")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Ketchup"))))));
		
		burgers.add(new Hamburger("Western BBQ 'n Bacon", 4.99, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("American Cheese")),
							  new ArrayList<String>(Arrays.asList("Diced Onion", "Crispy Fried Onion")),
							  new ArrayList<String>(Arrays.asList("Applewood Smoked Bacon")),
							  new ArrayList<String>(Arrays.asList("Sweet 'n Smoky BBQ Sauce"))))));
		
		burgers.add(new Hamburger("Grilled Portobello 'n Swiss", 5.49, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("Swiss Cheese")),
							  new ArrayList<String>(Arrays.asList("Caramelized Onions","Portobello Mushrooms")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Garlic Mayo"))))));
		
		burgers.add(new Hamburger("Fresh Guacamole", 5.49, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("Pepperjack Cheese")),
							  new ArrayList<String>(Arrays.asList("Tomato", "Lettuce", "Red Onion", "Fresh Guacamole")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Chipotlo Mayo"))))));
		
		burgers.add(new Hamburger("Frisco Melt", 5.49, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("American Cheese", "Swiss Cheese")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Sweet 'n Tangy Frisco Sauce"))))));
		
		burgers.add(new Hamburger("Jalapeno Crunch", 4.99, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("Pepperjack Cheese")),
							  new ArrayList<String>(Arrays.asList("Crispy Fried Onions",  "Jalapenos", "Fresh Salsa")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Chipotle Mayo"))))));
		
		burgers.add(new Hamburger("Rayole", 4.99, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("American Cheese")),
							  new ArrayList<String>(Arrays.asList("Tomato", "Lettuce")),
							  new ArrayList<String>(Arrays.asList("Fried Egg", "Applewood Smoked Bacon")),
							  new ArrayList<String>(Arrays.asList("Mayo"))))));
		
		burgers.add(new Hamburger("Vegan Master", 4.50, new ArrayList<ArrayList<String>>(
				Arrays.asList(new ArrayList<String>(Arrays.asList("Vegan 'Cheese'")),
							  new ArrayList<String>(Arrays.asList("Tomato","Lettuce", "Pickles", "Caramelized Onions", "Fresh Guacamole")),
							  new ArrayList<String>(Arrays.asList()),
							  new ArrayList<String>(Arrays.asList("Ketchup", "Vegan Mayo"))))));
		
	
		ArrayList<Beverage> beverages = new ArrayList<Beverage>();
		beverages.add(new Beverage("Chocolate Milkshake", 2.99));
		beverages.add(new Beverage("Vanilla Milkshake", 2.99));
		beverages.add(new Beverage("Strawberry Milkshake", 2.99));
		beverages.add(new Beverage("Banana Milkshake", 2.99));
		beverages.add(new Beverage("Butter Finger Milkshake", 3.49));
		beverages.add(new Beverage("Chocolate Banana Milkshake", 3.49));
		beverages.add(new Beverage("M&M Milkshake", 3.49));
		beverages.add(new Beverage("Cookie Dough Milkshake", 3.49));
		beverages.add(new Beverage("Cookies 'n Cream Milkshake", 3.49));
		beverages.add(new Beverage("Mint Cookies 'n Cream Milkshake", 3.49));
		beverages.add(new Beverage("Peanut Butter Milkshake", 3.49));
		beverages.add(new Beverage("Ultimate Banana Milkshake", 3.49));
		
		ArrayList<Food> food = new ArrayList<Food>();
		food.addAll(burgers);
		food.addAll(beverages);
		
		Menu menu = new Menu(food);
		
		Restaurant sns = new Restaurant("Steak 'n Shake", "Myrtle Beach");
		sns.setMenu(menu);
		Manager mngr = new Manager("Alper", "Ozkan", 6000, sns);
		sns.getEmployees().add(mngr);
		
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int tableNo = 0; tableNo < 8; tableNo++) {
			if(tableNo%2 == 0)
				tables.add(new Table(2));
			else
				tables.add(new Table(4));
		}
		
//		System.out.println("Restaurant has successfully created");
//		System.out.println("Restaurant name: " + sns.getName() + "\nLocation: " + sns.getLocation());
		
		return sns;
	}
	
	public static void validatePassword(Restaurant sns) {
		System.out.print("Please enter your password: ");
		Scanner sc = new Scanner(System.in);
		String passwordEntered = sc.nextLine();
		System.out.println();
		for(Employee emp: sns.getEmployees()) {
			if(emp.getPassword().equals(passwordEntered)) {
				if(emp instanceof Manager) {
					((Manager)emp).displayMenu();
				}
				else if(emp instanceof Server) {
					((Server)emp).displayMenu();
				}
				else if(emp instanceof Cook) {
					((Cook)emp).displayMenu();
				}
			}
		}

	}

}
