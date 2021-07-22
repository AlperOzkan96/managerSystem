package ao.rms.employee;

import ao.rms.restaurant.Restaurant;

public class Cook extends Employee {

	private Manager supervisor;
	
	public Cook(String name, String surname, double salary, Restaurant workplace, Manager supervisor) {
		super(name, surname, salary, workplace, supervisor);
		setPassword(name.toLowerCase() + "cook");
	}

	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		return "Cook";
	}

}
