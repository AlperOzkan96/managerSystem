package BackEnd;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
public abstract class Employee {
	
	private String name;
	private String surname;
	private String ID;
	private String password;
	private double salary;
	private Manager supervisor;
	private String supervisorName;
	private Restaurant workplace;
	
	public Employee(String name, String surname, double salary, Restaurant workplace, Manager supervisor) {
		this.name = name;
		this.surname = surname;
		this.salary =  salary;
		this.workplace = workplace;
		this.supervisor = supervisor;
		if(supervisor != null)
			this.supervisorName = supervisor.getName() + " " + supervisor.getSurname();
		}
	
	public Employee(String name, String surname, double salary) {
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Restaurant getRestaurant() {
		return workplace;
	}
	
	public void setRestaurant(Restaurant workplace) {
		this.workplace = workplace;
	}
	
	public abstract void displayMenu();
	
	public abstract String getTitle();

	public Manager getSupervisor() {
		return supervisor;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

}
