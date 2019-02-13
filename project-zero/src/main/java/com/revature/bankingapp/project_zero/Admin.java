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

public class Admin extends Customer implements Serializable{
	
	private static final long serialVersionUID = 4178314932166633275L;
	private Account account; //customer can have an account
	private String name;
	private String password= "password"; 
	private int ssn;
	private String status= "Admin";
	transient Scanner scanner = new Scanner(System.in);
	
	public Admin(){
		super();
		
	}

	public Admin(String name, String password, int ssn, String status) {
		this.account= new Account();
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.status = "admin";
		LoggingUtil.logInfo(" set admin ");
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
		LoggingUtil.logInfo(" set account ");
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		LoggingUtil.logInfo("name set ");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		LoggingUtil.logInfo(" name set ");
	}

	public int getSsn() {
		return ssn;
		
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
		LoggingUtil.logInfo(" ssn set ");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		LoggingUtil.logInfo(" status active ");
	}
	protected boolean approvedAccount(Account account ) throws ClassNotFoundException{
		System.out.println("Please confirm the account number for approval");
		int decision = scanner.nextInt();
		boolean check = false;
		for(int i = 0; i < Bank.getLu().size(); i++) {
			if(Bank.getLu().get(i).getAccount().getAccountNumber() == decision) {
				Customer c = new Customer(Bank.getLu().get(i).getName(), Bank.getLu().get(i).getPassword(),Bank.getLu().get(i).getSsn());
				Bank.getCustomers1().add(c);
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("notLockedUsers.dat"))){
					
					oos.writeObject(c);
					LoggingUtil.logInfo(" Serialized ");
					System.out.println("Done");
					adminMenu();
					LoggingUtil.logInfo(" Reset Menu ");
					System.out.println(c);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lockedUsers.dat"))){
					
					Customer notLocked = (Customer) ois.readObject();
					
					System.out.println("Found: " + notLocked );
					LoggingUtil.logInfo(" Locked ");
					
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
		
		for(int i = 0; i < Bank.getCustomers1().size(); i++) {
			if(Bank.getCustomers1().get(i).getAccount().getAccountNumber() == accountNumber) {
				LockedUser lu = new LockedUser(Bank.getCustomers1().get(i).getName(), Bank.getCustomers1().get(i).getPassword(),Bank.getCustomers1().get(i).getSsn());
				Bank.getLu().add(lu);
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lockedUsers.dat"))){
					
					oos.writeObject(lu);
					System.out.println("Done");
					adminMenu();
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
	
	public void adminMenu() throws ClassNotFoundException{
		
		for(LockedUser lu: Bank.getLu()) {
			if(lu != null) {
			System.out.println(lu);
			System.out.println(lu.getName()+"\n"+lu.getPassword()+"\n"+lu.getSsn()+"\n"+lu.getAccount().getAccountNumber()+"\n"+lu.getAccount().getBalance());
			//gets locked user
			LoggingUtil.logInfo(" Got locked user ");
			}
		}
		
		for(Customer c: Bank.getCustomers1()) {
			if(c != null) {
			System.out.println(c);
			System.out.println(c.getName()+"\n"+c.getPassword()+"\n"+c.getSsn()+"\n"+c.getAccount().getAccountNumber()+"\n"+c.getAccount().getBalance());
			//gets access to everything in customers
			LoggingUtil.logInfo(" Access granted ");
			}
			
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
		LoggingUtil.logInfo(" approved ");
		break;
		
		case 2: 
			for(Customer c: Bank.getCustomers1()) {
				if(c.getAccount().getAccountNumber()== aN) {
				System.out.print(c.getAccount().getAccountNumber()+"\n"+c.getAccount().getBalance());
				}
				
			}
			adminMenu();
			break;
			
		case 3: 
			lockingAccount( Bank.getCuz(aN).getAccount().getAccountNumber());
			break;
		case 4:
			RunBank rb = new RunBank();
			rb.theMenu();
			break;
			
		}
			
	}
		
}