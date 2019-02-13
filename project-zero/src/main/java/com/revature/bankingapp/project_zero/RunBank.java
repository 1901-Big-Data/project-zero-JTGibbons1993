package com.revature.bankingapp.project_zero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Properties;

import LoggingUtil.LoggingUtil;

public class RunBank {
	/**
	 * Things to do Scanner sc = new Scanner(); Sysout please enter your name String
	 * s = scan.next();
	 * 
	 * @param args
	 */
	static Scanner scanner = new Scanner(System.in);
	static AccountDao n= new AccountDaoImpl();
	public static void main(String[] args) throws ClassNotFoundException {
		
		displaySql();
		Bank.startBank();
		System.out.println("1. Open an Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Check Balance");
		System.out.println("5. Admin Login");
		System.out.println("6. Employee Login");
		System.out.println("7. exit");
		LoggingUtil.logInfo(" Choice Selected ");
		//rb.
		theMenu();
		LoggingUtil.logInfo(" Menu Reset ");
	}

	public static void displaySql() throws ClassNotFoundException {

		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			System.out.println("Connected");
			String sql = "SELECT * FROM accounta";
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getRow());
				System.out.println(rs.getString(2));

			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public static void theMenu() throws ClassNotFoundException {
		System.out.println("Enter Option : ");
		int option = scanner.nextInt();

		switch (option) {

		case 1:
			createAccount();
			LoggingUtil.logInfo(" Account Created ");
			break;
		case 2:
			System.out.println("Enter the account number than amount to deposit in next line:");
			int accountNum1 = scanner.nextInt();
			double amount = scanner.nextDouble();
			for (int o = 0; o < Bank.getCustomers1().size(); o++) {
				if (Bank.getCustomers1().get(o).getAccount().getAccountNumber() == accountNum1) {
					boolean depositStatus = Customer.deposit(accountNum1, amount); // make this access a specific user
																					// using scanner
					if (depositStatus == true) {
						System.out.println("Deposited");
						//RunBank rb = new RunBank();
						//rb.
						theMenu();
						LoggingUtil.logInfo(" Reset Menu ");
					} else {
						System.out.println("Fails to deposit");
						//RunBank rb = new RunBank();
						//rb.
						theMenu();
						LoggingUtil.logInfo(" Reset Menu ");
					}

				}
			}
			break;
		case 3:
			System.out.println("Enter account number than amount to withdraw in next line:");
			int accountNum2 = scanner.nextInt();
			double amount2 = scanner.nextDouble();
			for (int o = 0; o < Bank.getCustomers1().size(); o++) {
				if (Bank.getCustomers1().get(o).getAccount().getAccountNumber() == accountNum2) {
					Bank.getCustomers1().get(o);
					boolean withdrawStatus = Customer.withdraw(accountNum2, amount2); // make this access a specific
																						// user using scanner
					if (withdrawStatus == true) { // you will need getters and other methods from the bank to make this
													// access a specific account
						System.out.println("Withdrawn");
						theMenu();
						LoggingUtil.logInfo(" Reset Menu ");
					} else {
						System.out.println("Fails to withdraw");
						theMenu();
						LoggingUtil.logInfo(" Reset Menu ");
					}

				}
			}
			break;
		case 4:
			System.out.println("Enter account number:");
			int accountNum = scanner.nextInt();
			double balance = Bank.getCuz(accountNum).getAccount().getBalance(); // JAVA NULL POINTER HERE
			if (balance <= 0)
				System.out.println("Account Number Not Found");
			else
				System.out.println("Balance : " + balance);

			break;
		case 5:
			System.out.println("Admin login");
			Admin a = new Admin();
			LoggingUtil.logInfo(" Admin Accessed ");
			a.adminMenu(); // added type cast to int/switched accounts to accountNumbers??
			break;
		case 6:
			System.exit(0);
			LoggingUtil.logInfo(" System Reset ");

		default:
			System.out.println("Invalid Option !!");
		}

	}

	// TODO Auto-generated method stub
	public static void createAccount() throws ClassNotFoundException {
		System.out.println("Make a select account type please \n");
		String type = scanner.next();
		System.out.println("Please type in your Username");
		String name = scanner.next();
		scanner.nextLine();
		System.out.println("Type in social security number");
		int ssn = scanner.nextInt();// add a regex
		scanner.nextLine();
		System.out.println("Please type in password");
		String password = scanner.next();
		Account ac = new Account();

		if (ssnCheck(ssn)) {
			LockedUser lu = new LockedUser(name, password, ssn);
			Bank.addLu(lu);
			n.createAccountLocked(lu);
			System.out.println("User added");
			//createAccountDB(lu);
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lockedUsers.dat"))) {

				oos.writeObject(lu);
				System.out.println("Done");
				System.out.println(lu);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lockedUsers.dat"))) {

				LockedUser locker = (LockedUser) ois.readObject();

				System.out.println("Found: " + locker);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Bank.getBank().getspecificCustomer().getAccount().setBalance(); for deposit
			// and wthdraw
			// Bank.getBank().addLu(); going to add to the array of locked users,
			// serilialized.

			theMenu(); // resets menu
		} else
			System.out.println("User not added");

	}

	public static boolean ssnCheck(int ssn) {

		for (Customer c : Bank.getCustomers1()) {
			System.out.println(c.getSsn() + " " + ssn);
			if (c.getSsn() == ssn) {

				System.out.println("Give me unique social security number");
				return false;
			}
		}
		return true;

	}

}