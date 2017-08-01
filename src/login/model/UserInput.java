package login.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import login.controller.LoginController;
import login.pojo.Employee;

public class UserInput {
	
	Employee e = new Employee();
	Scanner sc = new Scanner(System.in);
	LoginDAO logDAO = new LoginDAO();
	
	public Employee login(){
		
		System.out.print("Enter username: ");
		
		// To check for InputMismatch
		while(!sc.hasNextInt()){
			System.out.print("Wrong input format. Enter username again: ");
			sc.next();
		}
		
		int username = sc.nextInt();
		System.out.print("Enter password: ");
		String password = sc.next();
		
		e.setUsername(username);
		e.setPassword(password);
		
		return e;
	}
	
	public void choice() throws SQLException{
		
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("1. Insert new employee record. ");
		System.out.println("2. Edit existng employee record. ");
		System.out.println("3. Delete existing employee record. ");
		System.out.println("4. Calculate the age of existing employee.");
		System.out.println("------------------------------------------");
		System.out.println();
		
		// To check for InputMismatch
		while(!sc.hasNextInt()){ 
			System.out.print("Wrong input format. Enter again: ");
			sc.next();
		}
		
		int choice = sc.nextInt();
		
		// To check the input is 1/2/3
		while(choice > 4 || choice < 1){
			
			System.out.print("Wrong input choice. Enter again: ");
			sc.nextLine();
			
			while(!sc.hasNextInt()){
				System.out.print("Wrong input format. Enter again: ");
				sc.next();
			}
			
			choice = sc.nextInt();
		}
		
		System.out.println();
		logDAO.choosenChoice(choice);
		
	}
}
