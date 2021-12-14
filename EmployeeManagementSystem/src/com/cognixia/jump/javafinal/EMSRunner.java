package com.cognixia.jump.javafinal;

import java.text.ParseException;
import java.util.Scanner;

public class EMSRunner {

	public static void main(String[] args) throws ParseException, EMSExceptions {
		intro();
		userSelection();
		exit();
	}
	
	// Console program introduction
	public static void intro() {
		System.out.println("--------------------------------------------");
		System.out.println(" Welcome to the Employee Management System ");
		System.out.println("--------------------------------------------\n");
	}
	
	// Console program options
	public static void choicePrint() {
		System.out.println("Please select your command by number...");
		System.out.println("0 - View all current employees");
		System.out.println("1 - Add New Employment");
		System.out.println("2 - Update Employee");
		System.out.println("3 - View Full Employee Information");
		System.out.println("4 - Get Employee Names");
		System.out.println("5 - Get Number Of Employees");
		System.out.println("6 - Delete Employee");
		System.out.println("7 - Delete All Employees");
		System.out.println("8 - Exit Employee Management System\n");
	}
	
	// Get option from user
	public static void userSelection() throws ParseException, EMSExceptions {
		Scanner input = new Scanner(System.in);
		int userChoice;
		
		while(true) {
			choicePrint();
			userChoice = input.nextInt();
			
			if(userChoice == 0) {
				EMScrud.getAllEmployees();
			} else if(userChoice == 1) {
				EMScrud.addNewEmployee();
			} else if (userChoice == 2) {
				EMScrud.updateEmployee();
			} else if (userChoice == 3) {
				EMScrud.fullEmployeeInformation();
			} else if (userChoice == 4) {
				EMScrud.employeeNames();
			} else if (userChoice == 5) {
				EMScrud.numberOfEmployees();
			} else if (userChoice == 6) {
				EMScrud.deleteEmployee();
			} else if (userChoice == 7) {
				EMScrud.deleteAllEmployees();
			} else {
				input.close();
				break;
			}
		}
	}
	
	// Console program exit
	public static void exit() {
		System.out.println("\nThank you for using the Employment Management System.");
		System.out.println("--------------------------------------------");
		System.out.println(" Now Exiting the Employee Management System ");
		System.out.println("--------------------------------------------");
	}
}
