package ao.rms.employee;

import ao.rms.restaurant.Restaurant;

public class Server extends Employee {
	
	private Manager supervisor;

	public Server(String name, String surname, double salary, Restaurant workplace, Manager supervisor) {
		super(name, surname, salary, workplace, supervisor);
		setID(surname.toLowerCase());
		setPassword("server" + name.toLowerCase());
	}

	@Override
	public String getTitle() {
		return "Server";
	}

}
