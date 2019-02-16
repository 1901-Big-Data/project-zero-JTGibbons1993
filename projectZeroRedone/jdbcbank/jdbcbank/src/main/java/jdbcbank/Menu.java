package jdbcbank;

import java.util.Scanner;

public class Menu {
	private static Scanner scin;
	public static void showMenu() {
		System.out.println("\r\n");
		System.out.print("Press 1 to Login\r\n" + 
				"Press 2 to deposit\r\n" + 
				"Press 3 to withdraw\r\n" + 
				"Press 4 to create account\r\n"+
				"Press 5 to logout\r\n"+
				"Press 0 to Exit\r\n");
	}
	private static int getUserChoice() {
		scin = new Scanner(System.in);
		while(true) {
			System.out.print("Enter choice: ");					
			try {	
				int item = Integer.parseInt(scin.nextLine().trim());
				if(item < 0 || item > 5) {
					throw new Exception();
				}
				return item;
			}
			catch(Exception e) {
				System.out.println("Invalid choice... Try Again.");
			}
		}
	}
	public static void doTask() {
		int item = getUserChoice();
		switch(item) {
		case 0:
			System.out.println("Thank you for using our service.");
			System.exit(0);
		case 1:
			User.login();
			break;
		case 2:
			User.deposit();
			break;
		case 3:
			User.withdraw();
			break;
		case 4:
			User.createAccount();
			break;
		case 5:
			User.logout();
			break;
		}
	}
}
