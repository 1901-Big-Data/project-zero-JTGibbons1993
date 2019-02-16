package jdbcbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
	private static Connection connection = null;
	public static void connect() {
		try{    
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			connection = DriverManager.getConnection("jdbc:oracle:thin:@my-database.cqg5xvdl5hrj.us-east-2.rds.amazonaws.com:1521:ORCL", "root","Flag$hip2111");
			
		}
		catch(Exception e){
			System.out.println(e);
		}  
	}
	public static ResultSet getUser(String accountNum, String pass) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM USERS WHERE ACCOUNTNUMBER = "+accountNum+" AND PASSWORD = '"+pass+"'");
			if(res.next()) {
				return res;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	public static boolean createUser(String name,String password) {
		try {
			Statement stmt = connection.createStatement();
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyymmddhhmmss");
			stmt.execute("INSERT INTO USERS (ACCOUNTNUMBER, NAME,PASSWORD) VALUES ("+ft.format(dNow)+",'"+name+"','"+password+"')");
			System.out.println("Account created successfully.\nYour account # : "+ft.format(dNow));
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public static boolean setBalance(String accountNumber, double b) {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("UPDATE USERS SET BALANCE = "+b+" WHERE ACCOUNTNUMBER = "+accountNumber);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
