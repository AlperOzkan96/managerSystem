package BackEnd;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Employee {
	
	private ArrayList<Employee> supervisedEmployees;
	
	public Manager(String name, String surname, double salary, Restaurant workplace) {
		super(name, surname, salary, workplace, null);
		supervisedEmployees = null;
		setID(surname.toLowerCase());
		setPassword(name.toLowerCase());
	}
	
	public String getTitle() {
		return "Manager";
	}

	@Override
	public void displayMenu() {
		System.out.println("ASDfas");
		
	}
	
	public ArrayList<Employee> getSupervisedEmployees() {
		return supervisedEmployees;
	}
	
	
}
