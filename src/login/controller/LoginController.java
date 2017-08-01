package login.controller;

import java.sql.SQLException;
import java.text.ParseException;

import login.model.LoginDAO;
import login.model.UserInput;
import login.pojo.Employee;

public class LoginController {

	public void logic() throws SQLException{
		
		UserInput userIn = new UserInput();			
		LoginDAO logDAO = new LoginDAO();
		
		boolean flag = false;
		
		do{
			
			flag = logDAO.validate(userIn.login());
			
		}while(!flag);
		
		userIn.choice();
		
		
		
	} // end of logic()
	
} // end of LoginController
