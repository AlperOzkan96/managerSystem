package BackEnd;
import java.util.ArrayList;

public class Restaurant {

	private String name;
	private String location;
	private ArrayList<Employee> employees;
	private Menu menu;
	private ArrayList<Table> tables;
	
	public Restaurant(String name, String location) {
		this.name = name;
		this.location = location;
		this.employees = new ArrayList<Employee>();
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public ArrayList<Table> getTables() {
		return tables;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
