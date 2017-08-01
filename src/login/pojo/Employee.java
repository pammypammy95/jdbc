package login.pojo;

import java.util.Date;

public class Employee {
	
	private int username;
	private String password;
	private String name;
	private String dateOfBirth;
	private int mobileNumber;
	private int employeeID;
	
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String date) {
		this.dateOfBirth = date;
	}

	public int getUsername() {
		return username;
	}
	
	public void setUsername(int username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
