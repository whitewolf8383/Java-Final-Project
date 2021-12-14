package com.cognixia.jump.javafinal;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EMScrud {
	
	// Attributes
	static List<Employee> employeeRoster = new ArrayList<Employee>();
	
	// Get All Employee records
	public static void getAllEmployees() {
		if(employeeRoster.isEmpty()) {
			System.out.println("\n-----------------------------");
			System.out.println("No employees have been added.");
			System.out.println("-----------------------------\n");
		} else {
			System.out.println("\n----------------------------------------------------");
			for(Employee person : employeeRoster) {
				System.out.println(person.toString());
				System.out.println("----------------------------------------------------\n");
			}
		}
	}
	
	
	// Add New Employee
	public static void addNewEmployee() throws ParseException, EMSExceptions {
		@SuppressWarnings("resource")
		Scanner crudInput = new Scanner(System.in);
		Pattern stringCheck = Pattern.compile("[^a-zA-Z' ']");
		Matcher match;
		// Get first name
		System.out.println("Please enter the employee's first name.");
		String firstName = crudInput.nextLine();
		match = stringCheck.matcher(firstName);
		if(match.find()) {
			throw new EMSExceptions("First Name can not contain special characters or numbers.");
		}
		
		// Get Last Name
		System.out.println("Please enter the employee's last name.");
		String lastName = crudInput.nextLine();
		match = stringCheck.matcher(lastName);
		if(match.find()) {
			throw new EMSExceptions("Last Name can not contain special characters or numbers.");
		}
		
		// Get Employment Date
		System.out.println("Please enter the employee's start date (MM/DD/YYYY).");
		System.out.println("Example: 05/24/2019");

		String date = crudInput.nextLine();
		DateTimeFormatter dateParse = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate dateOfEmployment = LocalDate.parse(date, dateParse);
		
		// Get Employees Department
		System.out.println("Please enter the employee's department.");
		String department = crudInput.nextLine();
				
		// Get Employee Salary
		System.out.println("Please enter the employee's salary.");
		double salary = Double.parseDouble(crudInput.nextLine());
		if(salary < 0.00) {
			throw new EMSExceptions("Employees' salary can Not be less than $0.00.");
		}
		
		// Create new Employee
		Employee employee = new Employee(firstName, lastName, dateOfEmployment, salary, department);
		
		try {
			employeeRoster.add(employee);
			System.out.println("\n----------------");
			System.out.println("New employee added");
			System.out.println("----------------\n");
		} catch (Exception e) {
			System.out.println("\n--------------------------");
			System.out.println("Employee not added to roster");
			System.out.println("--------------------------\n");
			throw new EMSExceptions("Entered data for new employee was invalid. Please try again.");
		}
	}
	
	// Update Employee
	public static void updateEmployee() throws EMSExceptions {
		@SuppressWarnings("resource")
		Scanner updateInput = new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner stringUpdate = new Scanner(System.in);
		System.out.println("Enter the employee's ID");
		UUID id = UUID.fromString(updateInput.nextLine());
		Pattern stringCheck = Pattern.compile("[^a-zA-Z' ']");
		Matcher match;
		
		System.out.println("Which value would you like to update?");
		System.out.println("0 - Update Employees' Last Name");
		System.out.println("1 - Update Employees' Salary");
		System.out.println("2 - Update Employees' Department");
		int userChoice = updateInput.nextInt();
		if(userChoice > 2) {
			throw new EMSExceptions("Entered option is invalid.");
		}
		Boolean flag = true;
		String updateInformation = "";
		double updateSalary = 0.00;
		
		for(Employee person : employeeRoster) {
			if(person.getEmployeeID().equals(id)) {
				flag = false;
				 if(userChoice == 0) {
					System.out.println("You have chosen to update Employees' Last Name.");
					System.out.println("Please enter Employees' New Last Name...");
					updateInformation = stringUpdate.nextLine();
					match = stringCheck.matcher(updateInformation);
					if(match.find()) {
						throw new EMSExceptions("First Name can not contain special characters or numbers.");
					}
					person.setLastName(updateInformation);
					System.out.println("\n--------------------------------");
					System.out.println("Employees' last name has been updated");
					System.out.println("--------------------------------\n");
				 } else if (userChoice == 1) {
					System.out.println("You have chosen to update Employees' Salary.");
					System.out.println("Please enter Employees' New Salary...");
					updateSalary = updateInput.nextDouble();
					if(updateSalary < 0) {
						throw new EMSExceptions("Salary can not be less than $0.00.");
					}
					person.setSalary(updateSalary);
					System.out.println("\n--------------------------------");
					System.out.println("Employees' salary has been updated");
					System.out.println("--------------------------------\n");
				 } else {
					System.out.println("You have chosen to update Employees' Department.");
					System.out.println("Please enter Employees' New Department...");
					updateInformation = stringUpdate.nextLine();
					match = stringCheck.matcher(updateInformation);
					if(match.find()) {
						throw new EMSExceptions("First Name can not contain special characters or numbers.");
					}
					person.setDepartment(updateInformation);
					System.out.println("\n--------------------------------");
					System.out.println("Employees' department has been updated");
					System.out.println("--------------------------------\n");
				 }
			}
		}
		
		if(flag) {
			System.out.println("EMSException: The Employee ID entered does not match any employee.");
		}
		
	}

	
	// Get Employee's Information
	public static void fullEmployeeInformation() {
		@SuppressWarnings("resource")
		Scanner fullInput = new Scanner(System.in);
		System.out.println("Enter the employee's ID");
		UUID id = UUID.fromString(fullInput.nextLine());
		Boolean flag = true;
		for(Employee person : employeeRoster) {
			if(person.getEmployeeID().equals(id)) {
				System.out.println("\n----------------------------------------------------");
				System.out.println(person.toString());
				System.out.println("----------------------------------------------------\n");
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("\n----------------------------------------");
			System.out.println("No employee with that EmployeeID exists.");
			System.out.println("----------------------------------------\n");
		}
	}
	
	// Get Employee Names in Order with Stream
	public static void employeeNames() {
		System.out.println("\n----------------------------------------------------");
		if(employeeRoster.isEmpty()) {
			System.out.println("No employees have been added.");
		} else {
			List<Employee> sortedEmployees = employeeRoster.stream()
					.sorted((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()))
					.collect(Collectors.toList());
			for(Employee person : sortedEmployees) {
				System.out.println("Name: " + person.getLastName() + ", " + person.getFirstName());
			}
		}
		System.out.println("----------------------------------------------------\n");
	}
	
	// Get Number of Employees
	public static void numberOfEmployees() {
		System.out.println("\n----------------------------------------");
		System.out.println(Employee.getNumberOfEmployees());
		System.out.println("----------------------------------------\n");
	}
	
	// Delete an Employee
	public static void deleteEmployee() throws EMSExceptions {
		@SuppressWarnings("resource")
		Scanner deleteInput = new Scanner(System.in);
		UUID id;
		Boolean flag = true;
		System.out.println("Enter the employee's ID");
		try {
			id = UUID.fromString(deleteInput.nextLine());
			int counter = 0;
			for(Employee person : employeeRoster) {
				if(person.getEmployeeID().equals(id)) {
					employeeRoster.remove(counter);
					System.out.println("\n-----------------------------------");
					System.out.println("Employee " + id + " has been removed.");
					System.out.println("-----------------------------------\n");
					flag = false;
					break;
				}
				counter++;
			}
		} catch (Exception e) {
			System.out.println("EMSException: Incorrect UUID format.\n UUID Example: dae7a347-023b-4ab5-b46e-6de7aa2166a6");
		}
		
		if(flag) {
			System.out.println("\n----------------------------------------");
			System.out.println("No employee with that EmployeeID exists.");
			System.out.println("----------------------------------------\n");
		}
	}
	
	// Delete All Employees
	public static void deleteAllEmployees() {
		@SuppressWarnings("resource")
		Scanner deleteAllInput = new Scanner(System.in);
		System.out.println("This will DELETE ALL employees...");
		System.out.println("Are you sure you want to do this? Y / N");
		
		String userChoice = deleteAllInput.next().toUpperCase();
		
		if (userChoice.equals("Y")) {
			employeeRoster.clear();
			System.out.println("\n------------------------------");
			System.out.println("All Employees Have Been Deleted.");
			System.out.println("-------------------------------n");
		} else if (userChoice.equals("N")) {
			System.out.println("\n----------------------------");
			System.out.println("Delete All Employees Canceled.");
			System.out.println("-----------------------------n");
		} else {
			System.out.println("\n------------------------------------------");
			System.out.println("User Input Invalid. Only 'Y' or 'N' is valid");
			System.out.println("-------------------------------------------n");
		}
	}

}
