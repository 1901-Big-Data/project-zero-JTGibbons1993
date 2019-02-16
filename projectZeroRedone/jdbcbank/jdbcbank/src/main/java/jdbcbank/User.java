package jdbcbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	public static boolean isLogin = false;
	public static String password = "";
	public static String name = "";
	public static Account bankAccount = null;
	private static Scanner scin =  null;
	
	
	public static void logout() {
		if(isLogin) {
			isLogin = false;
			password = "";
			name = "";
			bankAccount = null;
			System.out.println("User logged out successfully.");
		}
		else {
			System.out.println("Please login before using this feature.");
		}
	}
	
	public static void login() {
		if(scin == null) {
			scin = new Scanner(System.in);
		}
		if(isLogin) {
			System.out.println("Another user is already logged in.");
			return;
		}
		System.out.print("Enter Account number : ");
		String accountNum = scin.nextLine();
		System.out.print("Enter password : ");
		String pass = scin.nextLine();
		ResultSet res = Database.getUser(accountNum, pass);
		if(res != null) {
			try {
				name = res.getString("NAME");
				password = res.getString("PASSWORD");
				bankAccount = new Account(""+res.getString("ACCOUNTNUMBER"),res.getFloat("BALANCE"));
				System.out.println("Welcome : "+name+"\n"+bankAccount);
				isLogin = true;	
			} catch (SQLException e) {
				System.out.println("ERROR: "+e.getMessage());
				isLogin = true;	
				logout();
			}
		}
		else {
			System.out.println("Invalid accountNum/password.");
		}
	}
	
	public static void createAccount() {
		if(scin == null) {
			scin = new Scanner(System.in);
		}
		if(isLogin) {
			System.out.println("Another user is already logged in.");
			return;
		}
		bankAccount = new Account();
		System.out.print("Enter your name    : ");
		name = scin.nextLine();
		while(true) {
			System.out.print("Enter new password : ");
			password = scin.nextLine();
			System.out.print("Confirm password   : ");
			String cp = scin.nextLine();
			if(!password.equals(cp)) {
				System.out.println("Password mismatch......Try Again.");
			}
			else {
				break;
			}
		}
		if(Database.createUser(name,password)) {
			System.out.println("Account created successfully.");
		}
		else {
			logout();
		}
	}

	public static void deposit() {
		if(scin == null) {
			scin = new Scanner(System.in);
		}
		if(!isLogin) {
			System.out.println("Please login before using this feature.");
			return;
		}
		double amount = 0;
		while(true) {
			System.out.print("Enter amount : ");
			try {
				amount = Double.parseDouble(scin.nextLine().trim());
				if(amount <= 0) {
					throw new Exception();
				}
				break;
			}
			catch (Exception e) {
				System.out.println("Invalid amount entered.....Try Again.");
			}
		}
		
		if(bankAccount.deposit(amount)) {
			System.out.println("Amount "+amount+" $ deposited successfully.");
			System.out.println(bankAccount);
		}
	}

	public static void withdraw() {
		if(scin == null) {
			scin = new Scanner(System.in);
		}
		if(!isLogin) {
			System.out.println("Please login before using this feature.");
			return;
		}
		double amount = 0;
		while(true) {
			System.out.print("Enter amount : ");
			try {
				amount = Double.parseDouble(scin.nextLine().trim());
				if(amount <= 0) {
					throw new Exception();
				}
				break;
			}
			catch (Exception e) {
				System.out.println("Invalid amount entered.....Try Again.");
			}
		}
		try {
			bankAccount.withdraw(amount);

			System.out.println("Amount "+amount+" $ withdrawn successfully.");
			System.out.println(bankAccount);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(bankAccount);
		}
		
	}
	
}
