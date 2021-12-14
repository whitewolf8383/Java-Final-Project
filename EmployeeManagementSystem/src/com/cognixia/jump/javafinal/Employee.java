package com.cognixia.jump.javafinal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributes
	private static int numberOfEmployees = 0;
	
	private UUID employeeID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfEmployment;
	private double salary;
	private String department;
	
	public Employee(String firstName, String lastName, LocalDate dateOfEmployment, double salary, String department) {
		super();
		this.employeeID = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfEmployment = dateOfEmployment;
		this.salary = salary;
		this.department = department;
		numberOfEmployees++;
	}

	public UUID getEmployeeID() {
		return employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public static int getNumberOfEmployees() {
		return numberOfEmployees;
	}
	
	@Override
	public String toString() {
		return "EmployeeID: " + this.employeeID + "\nFirst Name: " + this.firstName + "\nLast Name: " + this.lastName 
				+ "\nDate of Employment: " + this.dateOfEmployment + "\nSalary: " + this.salary + "\nDepartment: " + this.department;
	}

}
