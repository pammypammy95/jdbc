package login.application;

import java.sql.SQLException;
import java.text.ParseException;

import login.controller.LoginController;

public class EmployeeLoginApplication {

	public static void main(String[] args) throws SQLException, ParseException {
		
		LoginController loginCon = new LoginController();
		loginCon.logic();
		
	} // end of main()

} // end of EmployeeLoginApplication
