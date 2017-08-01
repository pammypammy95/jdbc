package login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import connection.DBConnection;
import login.pojo.Employee;

public class LoginDAO {
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Scanner sc = null;
	Employee e = null;
	
	void choosenChoice(int choice) throws SQLException{
		
		if(choice == 1){
			
			insertData();
			
		}else if(choice == 2){
			
			editData();
			
		}else if(choice == 3){
			
			deleteData();
			
		}else{
			
			calculateAge();
			
		}
		
	}
	
	public boolean validate(Employee e) throws SQLException{
		
		boolean flag = false;
		
		con = DBConnection.myConnection();
		st = con.createStatement();
		rs = st.executeQuery("select * from employee");
		
		while(rs.next()){
			if(rs.getInt(1) == e.getUsername() && rs.getString(6).equals(e.getPassword())){
				flag = true;
				break;
			}else{
				System.out.println("Wrong input. Try again. ");
				break;
			}
		}
		
		con.close();
		return flag;
	}

	void insertData() throws SQLException{
		
		con = DBConnection.myConnection();
		ps = con.prepareStatement("insert into employee(employeeName,dateOfBirth,mobileNumber,password) values(?,?,?,aes_encrypt(?,unhex(sha2('assignment06',512))))");
																					
		sc = new Scanner(System.in);
		
		System.out.print("Enter new employee's name: ");
		String name = sc.nextLine();
		System.out.print("Enter date of birth(yyyy-mm-dd): ");
		String dateString = sc.next();
		System.out.print("Enter mobile number: ");
		int mobileNumber = sc.nextInt();
		System.out.print("Enter password: ");
		String password = sc.next();
		
		e = new Employee();
		e.setName(name);
		e.setDateOfBirth(dateString);
		e.setMobileNumber(mobileNumber);
		e.setPassword(password);
		
		ps.setString(1, e.getName());
		ps.setString(2, e.getDateOfBirth());
		ps.setInt(3, e.getMobileNumber());
		ps.setString(4, e.getPassword());
		
		int i = ps.executeUpdate();
		System.out.println("---------------------");
		System.out.println(i + " records instead.");
		System.out.println("---------------------");
	}
	
	void editData() throws SQLException{
		con = DBConnection.myConnection();
		ps = con.prepareStatement("update employee set mobileNumber = ? where employeeID = ? ");
		
		sc = new Scanner(System.in);
		
		System.out.print("Enter employeeID: ");
		int employeeID = sc.nextInt();
		System.out.print("Enter new mobile number: ");
		int mobileNumber = sc.nextInt();
		
		e = new Employee();
		e.setMobileNumber(mobileNumber);
		e.setEmployeeID(employeeID);
		
		ps.setInt(1, e.getMobileNumber());
		ps.setInt(2, e.getEmployeeID());
		
		int i = ps.executeUpdate();
		System.out.println("--------------------");
		System.out.println(i + " records edited.");
		System.out.println("--------------------");
	};
	
	void deleteData() throws SQLException{
		
		con = DBConnection.myConnection();
		ps = con.prepareStatement("delete from employee where employeeID = ? ");
		
		sc = new Scanner(System.in);
		
		System.out.print("Enter employeeID: ");
		int employeeID = sc.nextInt();
		
		e = new Employee();
		e.setEmployeeID(employeeID);
		
		ps.setInt(1, e.getEmployeeID());
		
		int i = ps.executeUpdate();
		System.out.println("---------------------");
		System.out.println(i + " records deleted.");
		System.out.println("---------------------");
		
	};
	
	void calculateAge() throws SQLException{
		
		con = DBConnection.myConnection();
		ps = con.prepareStatement("select dateOfBirth from employee where employeeID = ?");
		
		sc = new Scanner(System.in);
		
		System.out.print("Enter employeeID: ");
		int employeeID = sc.nextInt();
		
		e = new Employee();
		e.setEmployeeID(employeeID);
		
		ps.setInt(1, e.getEmployeeID());
		rs = ps.executeQuery();
		
		while(rs.next()){
			
			String dateOfBirthYear = rs.getString(1).substring(0,4);
			String dateOfBirthMonth = rs.getString(1).substring(5,7);
			String dateOfBirthDate = rs.getString(1).substring(8);
			
			// convert from string to integer
			int year = Integer.parseInt(dateOfBirthYear);
			int month = Integer.parseInt(dateOfBirthMonth);
			int date = Integer.parseInt(dateOfBirthDate);
			
			LocalDate dob = LocalDate.of(year, month, date);
			LocalDate current = LocalDate.now();
			
			Period p = Period.between(dob, current);
		
			System.out.println("The age of employee " + employeeID + " is " + p.getYears() + ". ");
		}
		
		con.close();
	}
	
	
	
}
