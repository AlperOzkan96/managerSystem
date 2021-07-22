package BackEnd;

public class Server extends Employee {
	
	private Manager supervisor;

	public Server(String name, String surname, double salary, Restaurant workplace, Manager supervisor) {
		super(name, surname, salary, workplace, supervisor);
		setID(surname.toLowerCase());
		setPassword(name.toLowerCase());
	}
	
	@Override
	public void displayMenu() {
		System.out.println("asdf");
	}

	@Override
	public String getTitle() {
		return "Server";
	}

}
