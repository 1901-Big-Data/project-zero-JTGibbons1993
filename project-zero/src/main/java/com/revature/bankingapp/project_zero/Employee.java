package com.revature.bankingapp.project_zero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import LoggingUtil.LoggingUtil;

public class Employee extends Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4395077132295823391L;
	private Account account; //customer can have an account
	private String name;
	private String password; // last name CHANGE IT
	private int ssn;   //fields
	private String type;
	transient Scanner scanner = new Scanner(System.in);
	
	
	
	public Employee() {
		super();
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
		LoggingUtil.logInfo(" Account Stored ");
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
		LoggingUtil.logInfo(" Name Set ");
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSsn() {
		return ssn;
	}


	public void setSsn(int ssn) {
		this.ssn = ssn;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	


	public Employee(String name, String password, int ssn) {
		super();
		this.account= new Account();
		this.name = name;
		this.password = password;
		this.ssn = ssn;
	}
	
	
	
	
	protected boolean approvedAccount(Account account ){
		System.out.println("Enter the account number of the user you want to approve");
		int decision = scanner.nextInt();
		boolean check = false;
		for(int i = 0; i < Bank.getLu().size(); i++) {
			if(Bank.getLu().get(i).getAccount().getAccountNumber() == decision) {
				Customer c = new Customer(Bank.getLu().get(i).getName(), Bank.getLu().get(i).getPassword(),Bank.getLu().get(i).getSsn());
				Bank.getCustomers1().add(c);
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("notLockedUsers.dat"))){
					LoggingUtil.logInfo(" Serialized/Approved ");
					oos.writeObject(c);
					System.out.println("Done");
					System.out.println(c);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("notLockedUser.dat"))){
					
					Customer notLocked = (Customer) ois.readObject();
					
					System.out.println("Found: " + notLocked );
					LoggingUtil.logInfo(" User Found ");
					
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
				check = true;
				return check;
		}
			
			
		}
		return check;
	}
	protected boolean lockingAccount(int accountNumber) throws ClassNotFoundException{
		System.out.println("Enter the account number of the user you want to lock-in");
		String decision = scanner.nextLine();
		boolean check = false;
		RunBank rb = new RunBank();
		for(int i = 0; i < Bank.getCustomers1().size(); i++) {
			if(Bank.getCustomers1().get(i).getAccount().getAccountNumber() == accountNumber) {
				LockedUser lu = new LockedUser(Bank.getCustomers1().get(i).getName(), Bank.getCustomers1().get(i).getPassword(),Bank.getCustomers1().get(i).getSsn());
				LoggingUtil.logInfo(" Locked User Got Account ");
				Bank.getLu().add(lu);
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("notLockedUsers.dat"))){
					LoggingUtil.logInfo(" Serialized Locked User ");	
					oos.writeObject(lu);
					System.out.println("Done");
					employeeMenu();
					System.out.println(lu);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("notLockedUser.dat"))){
					
					Customer notLocked = (Customer) ois.readObject();
					
					System.out.println("Found: " + notLocked );
					LoggingUtil.logInfo(" Found Locked");
					
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
				return check;
		}
			
			
		}
		return check;
	}
	
	public void employeeMenu() throws ClassNotFoundException{
		
		for(Customer c: Bank.getCustomers1()) {
			System.out.println(c.getName()+"\n"+c.getPassword()+"\n"+c.getSsn());
			LoggingUtil.logInfo(" Got Customers Stored ");
			
		}
		System.out.println("1. Approve: ");
		System.out.println("2. Check Customer Accounts");
		System.out.println("3. Remove Account");
		System.out.println("4. Exit");
		int choice=scanner.nextInt();
		
		System.out.println("Enter the account number of choice");
		
		int aN = scanner.nextInt();
		
		switch(choice) {
		
		case 1 : approvedAccount(Bank.getUnapproved(aN).getAccount());
		break;
		
		case 2: 
			for(Customer c: Bank.getCustomers1()) {
				System.out.print(c.getAccount().getAccountNumber()+"\n"+c.getAccount().getBalance());
				LoggingUtil.logInfo(" Checked Account ");
			}
			break;
			
		case 3: 
			lockingAccount( Bank.getCuz(aN).getAccount().getAccountNumber());
			break;
		case 4:
			RunBank.main(null);
			LoggingUtil.logInfo(" Reset Menu ");
			
		}
		
		//disply admin stuff
		
	}
	
	
	
	
}

	
	
	
	


