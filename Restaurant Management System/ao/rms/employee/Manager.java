package ao.rms.employee;
import java.util.ArrayList;
import java.util.Scanner;

import ao.rms.restaurant.Restaurant;

public class Manager extends Employee {
	
	private ArrayList<Employee> supervisedEmployees;
	
	public Manager(String name, String surname, double salary, Restaurant workplace) {
		super(name, surname, salary, workplace, null);
		supervisedEmployees = null;
		setID("mngr" + surname.toLowerCase());
		setPassword("admin");
	}
	
	public String getTitle() {
		return "Manager";
	}
	
	public ArrayList<Employee> getSupervisedEmployees() {
		return supervisedEmployees;
	}
	
}
